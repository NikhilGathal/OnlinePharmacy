package com.jsp.onlinepharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Booking;
import com.jsp.onlinepharmacy.repo.BookingRepo;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepo repo;

	public Booking savebookings(Booking booking) {

		return repo.save(booking);
	}

	public Booking findBooking(int bookingid) {

		Optional<Booking> optional = repo.findById(bookingid);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	public Booking deleteBooking(int bookingid) {
		Optional<Booking> optional = repo.findById(bookingid);

		if (optional.isPresent()) {
			repo.delete(optional.get());
			return optional.get();
		}

		return null;
	}
}
