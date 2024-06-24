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
public class Staff {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int staffid;
	private String staffName;
	private String staffEmail;
	private String staffPassword;
	private long phoneNumber;
	
	@ManyToOne
	private Admin admin;
	
	@OneToOne
	private MedicalStore medicalStore;
	
}
