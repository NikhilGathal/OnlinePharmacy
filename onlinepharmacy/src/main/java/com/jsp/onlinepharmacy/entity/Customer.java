package com.jsp.onlinepharmacy.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerid;
	private String customerName;
	private String customerEmail;
	private String password;
	private long phoneNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Address> address;
	
	@OneToMany(mappedBy = "customer")
	private List<Booking> booking;
	
}
