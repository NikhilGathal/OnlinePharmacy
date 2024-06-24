package com.jsp.onlinepharmacy.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class StaffDto {
	private int staffId;
	private String staffName;
	@ManyToOne
	private AdminDto adminDto;
	@OneToOne
	private MedicalStoreDto medicalStoreDto;
}