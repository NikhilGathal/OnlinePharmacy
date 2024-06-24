package com.jsp.onlinepharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AddressDao;

import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao dao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {

		Address dbaddress = dao.saveAddress(address);
		ResponseStructure<Address> structure = new ResponseStructure<Address>();
		structure.setMessage("Address saved successfully");
		structure.setHttpstatus(HttpStatus.CREATED.value());
		structure.setData(dbaddress);

		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(int id, Address address) {

		Address dbaddress = dao.updateAddress(id, address);

		if (dbaddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address updated successfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(dbaddress);

			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		} else {
			// id not present
			throw new AddressIdNotFoundException("Sorry Fail to update address");
		}

	}

	public ResponseEntity<ResponseStructure<Address>> findAddress(int id) {

		Address dbaddress = dao.findAddress(id);

		if (dbaddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address Fetched successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbaddress);

			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		} else {
			// id not present
			throw new AddressIdNotFoundException("Sorry Fail to Fetch address");
		}

	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddress(int id) {

		Address dbaddress = dao.deleteaddress(id);

		if (dbaddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address Deleted successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dbaddress);

			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FORBIDDEN);
		} else {
			// id not present
			throw new AddressIdNotFoundException("Sorry Fail to Delete address");
		}

		
	}
}
