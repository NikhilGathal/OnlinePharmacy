package com.jsp.onlinepharmacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinepharmacy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
