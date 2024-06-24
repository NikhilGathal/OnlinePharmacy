package com.jsp.onlinepharmacy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.BookingDao;
import com.jsp.onlinepharmacy.dao.CustomerDao;
import com.jsp.onlinepharmacy.dao.MedicineDao;
import com.jsp.onlinepharmacy.dto.BookingDto;
import com.jsp.onlinepharmacy.dto.CustomerDto;
import com.jsp.onlinepharmacy.entity.Booking;
import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.enums.BookingStatus;
import com.jsp.onlinepharmacy.exception.BookingAlreadyCancelledException;
import com.jsp.onlinepharmacy.exception.BookingAlreadyDeliveredException;
import com.jsp.onlinepharmacy.exception.BookingCantCancel;
import com.jsp.onlinepharmacy.exception.BookingIdNotFoundException;
import com.jsp.onlinepharmacy.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacy.repo.BookingRepo;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class BookingService {

	@Autowired
	private BookingDao dao;

	@Autowired
	private MedicineDao medicineDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<BookingDto>> addbooking(int customerId, int medicineId, Booking booking) {
		Customer dbcustomer = customerDao.findCustomerById(customerId);
		LocalDate expectedDate = LocalDate.now().plusDays(7);

		if (dbcustomer != null) {
			// bcz booking status we are deciding not taking from frontend

			booking.setExpectedDate(expectedDate);

			booking.setBookingstatus(BookingStatus.ACTIVE);
			booking.setCustomer(dbcustomer);
			Medicine dbmedicine = medicineDao.findMedicine(medicineId);

			List<Medicine> medicines = new ArrayList<>();
			if (dbmedicine != null) {
				medicines.add(dbmedicine);
				booking.setMedicine(medicines);
				Booking dbbooking = dao.savebookings(booking);

				// update customer
				List<Booking> bookings = dbcustomer.getBooking();
				// here taking old bookings into list

				// i am taking customer old booking along with
				// i am adding new booking also along with old one
				bookings.add(dbbooking);
				// here added new booking

				// now inside this we are having new and old booking
				// again going to reset booking

				dbcustomer.setBooking(bookings);
				// here setting old+new booking to customer
				customerDao.updatecustomer(customerId, dbcustomer);

				ResponseStructure<BookingDto> structure = new ResponseStructure<BookingDto>();
				structure.setMessage("Booking successfully");
				structure.setHttpstatus(HttpStatus.CREATED.value());

				BookingDto bookingDto = this.mapper.map(dbbooking, BookingDto.class);
				bookingDto.setMedicines(medicines);
				bookingDto.setCustomerDto(this.mapper.map(dbbooking.getCustomer(), CustomerDto.class));

				structure.setData(bookingDto);

				return new ResponseEntity<ResponseStructure<BookingDto>>(structure, HttpStatus.CREATED);

			}

			else {
				throw new CustomerIdNotFoundException("sorry failed to add booking");
			}

			// customer having booking
			// booking is also having customer
			// update customer detail

		} else {
			throw new CustomerIdNotFoundException("sorry failed to add booking");
		}

	}

	public ResponseEntity<ResponseStructure<BookingDto>> cancelBooking(int bookingid) {

		Booking dbbooking = dao.findBooking(bookingid);

		if (dbbooking != null) {
			// booking id is present (booking is present)

			// cant cancel date

			LocalDate cantCancelledDate = dbbooking.getExpectedDate().minusDays(2);

			if (dbbooking.getBookingstatus().equals(BookingStatus.CANCELLED)) {
				throw new BookingAlreadyCancelledException("Sorry failed to cancel the booking");
			} else if (dbbooking.getBookingstatus().equals(BookingStatus.DELIVERED)) {
				throw new BookingAlreadyDeliveredException("Sorry fail to cancel booking");
			} else if (LocalDate.now().equals(cantCancelledDate) || LocalDate.now().isAfter(cantCancelledDate)) {
				throw new BookingCantCancel("Sorry fail to cancel booking");
			} else {
				Booking deletedbooking = dao.deleteBooking(bookingid);

				ResponseStructure<BookingDto> structure = new ResponseStructure<BookingDto>();
				structure.setMessage("Booking deleted successfully");
				structure.setHttpstatus(HttpStatus.FORBIDDEN.value());

				BookingDto bookingDto = this.mapper.map(dbbooking, BookingDto.class);

				// booking is having list of medicine

				bookingDto.setMedicines(dbbooking.getMedicine());

				bookingDto.setCustomerDto(this.mapper.map(dbbooking.getCustomer(), CustomerDto.class));

				structure.setData(bookingDto);

				return new ResponseEntity<ResponseStructure<BookingDto>>(structure, HttpStatus.CREATED);

			}

		} else {

			// booking id not present

			throw new BookingIdNotFoundException("sorry failed to cancel booking");
		}

	}

}
