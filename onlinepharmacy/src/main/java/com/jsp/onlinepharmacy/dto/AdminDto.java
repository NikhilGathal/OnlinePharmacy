package com.jsp.onlinepharmacy.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AdminDto {

	
	private int adminid;
	private String adminName;
	private String adminAddress;
}
