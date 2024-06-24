package com.jsp.onlinepharmacy.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AddressDto {
	
	private int addressId;
	private String streetName;
	private String city;
	private String state;
	private long pincode;
}
