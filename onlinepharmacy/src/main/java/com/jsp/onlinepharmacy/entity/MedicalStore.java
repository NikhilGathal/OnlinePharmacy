package com.jsp.onlinepharmacy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MedicalStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeid;
	private String storeName ;
	private String managerName ;
	private long phone ;
	
	@OneToOne(mappedBy = "medicalStore")
	private Address address;
	
	@ManyToOne
	private Admin admin;
	
}
