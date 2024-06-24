package com.jsp.onlinepharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.CustomerDto;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.service.CustomerService;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> signupCustomer(@RequestBody Customer customer,
			@RequestParam int addressId) {
		return service.saveCustomer(customer, addressId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> fetchCustomerById(@RequestParam int customerid) {
		return service.fetchCustomerById(customerid);
	}

//	@PutMapping("/forgotpassword")
//	public ResponseEntity<ResponseStructure<CustomerDto>> forgotPassword(@RequestParam String email,
//			@RequestParam long phone, @RequestParam String password) {
//		return service.forgotPassword(email, phone, password);
//	}

//	@PutMapping
//	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestParam int customerId,
//			@RequestBody Customer customer) {
//		return service.updateCustomer(customerId, customer);
//	}
//
//	@DeleteMapping
//	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(@RequestParam int customerId) {
//		return service.deleteCustomerById(customerId);
//	}

//	@GetMapping("/login")
//	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(@RequestParam String email,
//			@RequestParam String password) {
//		return service.loginCustomer(email, password);
//	}

}
