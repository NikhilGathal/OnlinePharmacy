package com.jsp.onlinepharmacy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminid;
	private String adminName;
	private String adminAddress;
	private String adminEmail;
	private String adminPassword;
	private long adminPhoneNumber;
	
	

}
