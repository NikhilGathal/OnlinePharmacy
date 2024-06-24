package com.jsp.onlinepharmacy.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class MedicalStoreDto {

	private int storeId;
	private String storeName;
	private String managerName;
	private long phone;
	
	@OneToOne
	private AddressDto addressDto;
	@ManyToOne
	private AdminDto adminDto;
}
