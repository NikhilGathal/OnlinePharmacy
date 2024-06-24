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

import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.service.AddressService;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address) {
		return service.saveAddress(address);

	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestParam int id,@RequestBody Address address) {
		return service.updateAddress(id,address);

	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Address>> findAddress(@RequestParam int id) {
		return service.findAddress(id);

	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Address>> deleteAddress(@RequestParam int id) {
		return service.deleteAddress(id);

	}
	
	
	
	
}
