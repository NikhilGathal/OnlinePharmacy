package com.jsp.onlinepharmacy.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Booking;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class CustomerDto {

	
	private int customerid;
	private String customerName;
	
	
	@OneToMany
	private List<AddressDto> addressdto;
	
	@OneToMany
	private List<BookingDto> bookingDtos;
	
	
}
