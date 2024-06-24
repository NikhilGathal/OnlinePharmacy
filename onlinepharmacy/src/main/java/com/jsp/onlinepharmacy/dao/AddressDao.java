package com.jsp.onlinepharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.repo.Addressrepo;

@Repository
public class AddressDao {

	@Autowired
	private Addressrepo repo;

	public Address saveAddress(Address address) {

		return repo.save(address);
	}

	public Address updateAddress(int id, Address address) {

		Optional<Address> optional = repo.findById(id);
		if (optional.isPresent()) {
			address.setAddressid(id);
			return repo.save(address);
		}

		return null;
	}

	public Address findAddress(int id) {
		
		Optional<Address> optional = repo.findById(id);
		if (optional.isPresent()) {
			
			return optional.get();
		}

		return null;
		
	}

	public Address deleteaddress(int id) {
		
		Optional<Address> optional = repo.findById(id);
		if (optional.isPresent()) {
			repo.deleteById(id);
			return optional.get();
		}

		return null;
		
	}

}
