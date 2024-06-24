package com.jsp.onlinepharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.dao.CustomerDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.BookingDto;
import com.jsp.onlinepharmacy.dto.CustomerDto;
import com.jsp.onlinepharmacy.dto.MedicalStoreDto;
import com.jsp.onlinepharmacy.dto.MedicineDto;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Booking;
import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class CustomerService {

	// code in class not for forgot password

	@Autowired
	private CustomerDao dao;

	@Autowired
	private CustomerDto dto;

	@Autowired
	private AddressDao addressdao;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(Customer customer, int addressId) {

		Address dbaddress = addressdao.findAddress(addressId);

		if (dbaddress != null) {

			List<Address> addresses = new ArrayList<Address>();
			addresses.add(dbaddress);

			customer.setAddress(addresses);
			Customer dbcustomer = dao.saveCustomer(customer);

			// update address also

			dbaddress.setCustomer(customer);
			addressdao.updateAddress(addressId, dbaddress);

			ResponseStructure<CustomerDto> structure = new ResponseStructure<>();
			structure.setMessage("customer data saved successfully");
			structure.setHttpstatus(HttpStatus.CREATED.value());

			CustomerDto dto = this.mapper.map(dbcustomer, CustomerDto.class);

			List<BookingDto> bookingDtos = new ArrayList<BookingDto>();

////			for (Booking bookings : dbcustomer.getBooking()) {
////
////				BookingDto bookingDto = this.mapper.map(bookings, BookingDto.class);
////				bookingDtos.add(bookingDto);
////			}

			dto.setBookingDtos(bookingDtos);

			List<AddressDto> addressdtos = new ArrayList<AddressDto>();

			for (Address address : dbcustomer.getAddress()) {
				AddressDto addressdto = this.mapper.map(address, AddressDto.class);
				addressdtos.add(addressdto);
			}

			dto.setAddressdto(addressdtos);
			structure.setData(dto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.CREATED);

		} else {
			throw new AddressIdNotFoundException("sorry failed to save customer");
		}

	}

	public ResponseEntity<ResponseStructure<CustomerDto>> fetchCustomerById(int customerid) {

		Customer dbcustomer = dao.findCustomerById(customerid);

		if (dbcustomer != null) {
			CustomerDto customerDto = this.mapper.map(dbcustomer, CustomerDto.class);

			List<AddressDto> addressDtos = new ArrayList<AddressDto>();
			for (Address address : dbcustomer.getAddress()) {
				AddressDto addressDto = this.mapper.map(address, AddressDto.class);
				addressDtos.add(addressDto);

			}
			customerDto.setAddressdto(addressDtos);

			List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
			List<Medicine> medicines = new ArrayList<Medicine>();
			for (Booking booking : dbcustomer.getBooking()) {
				BookingDto bookingDto = this.mapper.map(booking, BookingDto.class);
				
				Medicine medicine = this.mapper.map(booking.getMedicine(),Medicine.class);
				medicines.add(medicine);
				bookingDto.setMedicines(medicines);
				
				
				bookingDtos.add(bookingDto);
				
				
			}
			
		
		
			
			
			
			customerDto.setBookingDtos(bookingDtos);
			
			
			
			
			
//			AddressDto addressDto = this.mapper.map(dbcustomer.getAddress(), AddressDto.class);
//			BookingDto bookingDto = this.mapper.map(dbcustomer.getBooking(), BookingDto.class);

			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer data fetched successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(customerDto);

			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);

		} else {
			throw new CustomerIdNotFoundException("sorry failed to find customer");
		}

	}

//	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(String email, String password) {
//		Customer dbCustomer=custmerDao.findByEmail(email);
//		if(dbCustomer!=null) {
//			if(dbCustomer.getPassword().equals(password)) {
////				login success
//				CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
//				List<AddressDto> addressDtos;
//			for(Address address:dbCustomer.getAddresses()) {
//				AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
//				addressDtos=new ArrayList<AddressDto>();
//				addressDtos.add(addressDto);
//				customerDto.setAddresses(addressDtos);
//			} 
//			List<BookingDto> bookingDtos;
//			for(Booking booking:dbCustomer.getBookings()) {
//				BookingDto bookingDto=this.modelMapper.map(booking, BookingDto.class);
//				bookingDtos=new ArrayList<BookingDto>();
//				bookingDtos.add(bookingDto);
//				customerDto.setBookingDtos(bookingDtos);
//			}
//				
//				ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
//				structure.setMessage("Customer Login Success WELCOME TO ONLINEPHARMACY");
//				structure.setStatus(HttpStatus.OK.value());
//				structure.setData(customerDto);
//				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
//				
//			}else {
////				invalid password
//				throw new InvalidPasswordException("Sorry failed to Login");
//			}
//			
//			
//		}else {
//			throw new EmailNotFoundException("Sorry Failed to Login");
//		}
//	}
//
//	public ResponseEntity<ResponseStructure<CustomerDto>> forgotPassword(String email, long phone,String password) {
//	   Customer customer=custmerDao.findByEmail(email);
//		if(customer!=null) {
////			customer is present
//			if(customer.getPhoneNumber()==phone) {
////				phone is registered
//				customer.setPassword(password);
//				Customer dbCustomer=custmerDao.updateCustomer(customer.getCustomerId(), customer);
//				
//				
//				CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
//				List<AddressDto> addressDtos;
//			for(Address address:dbCustomer.getAddresses()) {
//				AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
//				addressDtos=new ArrayList<AddressDto>();
//				addressDtos.add(addressDto);
//				customerDto.setAddresses(addressDtos);
//			} 
//			List<BookingDto> bookingDtos;
//			for(Booking booking:dbCustomer.getBookings()) {
//				BookingDto bookingDto=this.modelMapper.map(booking, BookingDto.class);
//				bookingDtos=new ArrayList<BookingDto>();
//				bookingDtos.add(bookingDto);
//				customerDto.setBookingDtos(bookingDtos);
//			}
//				
//				ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
//				structure.setMessage("Customer Pssword reset successfully");
//				structure.setStatus(HttpStatus.OK.value());
//				structure.setData(customerDto);
//				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
//				
//				
//			}else {
//				throw new PhoneNumberNotValidException("Sorry failed to reset password Please enter Registered PhoneNumber");
//			}
//		}else {
//			throw new EmailNotFoundException("Sorry failed to reset password Please enter valid Email");
//		}
//	}

	// code for forgor password

}
