package com.jsp.onlinepharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Customer;
import com.jsp.onlinepharmacy.repo.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;

	public Customer findCustomerById(int customerId) {

		Optional<Customer> optional = repo.findById(customerId);
		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	public Customer updatecustomer(int customerId, Customer dbcustomer) {

		Optional<Customer> optional = repo.findById(customerId);
		if (optional.isPresent()) {
			dbcustomer.setCustomerid(customerId);
			dbcustomer.setAddress(optional.get().getAddress());
			dbcustomer.setBooking(optional.get().getBooking());
			return repo.save(dbcustomer);
		}

		return null;

	}

	public Customer saveCustomer(Customer customer) {

		return repo.save(customer);
	}
	
	
	// code for forgot password
	
//	@Autowired
//	private CustomerRepo repo;
//
//	public Customer saveCustomer(Customer customer) {
//		return repo.save(customer);
//	}
//
//	public Customer updateCustomer(int customerId, Customer customer) {
//		Optional<Customer> optional=repo.findById(customerId);
//		if(optional.isPresent()) {
//			customer.setCustomerId(customerId);
//			customer.setAddresses(optional.get().getAddresses());
//			customer.setBookings(optional.get().getBookings());
//			return repo.save(customer);
//		}
//		return null;
//	}
//
//	public Customer getCustomerById(int customerId) {
//		Optional<Customer> optional=repo.findById(customerId);
//		if(optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
//	}
//
//	public Customer deleteCustomerById(int customerId) {
//		Optional<Customer> optional=repo.findById(customerId);
//		if(optional.isPresent()) {
//			Customer customer= optional.get();
//			
//			repo.delete(customer);
//			return customer;
//		}
//		
//		return null;
//	}
//
//	public  Customer findByEmail(String email) {
//		Optional<Customer> optional=repo.findByEmail(email);
//		if(optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
//	}

}
