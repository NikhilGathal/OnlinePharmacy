package com.jsp.onlinepharmacy.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.repo.AdminRepo;

@Repository
public class AdminDao {

	// responsible for database operation (Logic)
	// it will perform crud operation and return data

	@Autowired
	private AdminRepo repo;

	public Admin saveAdmin(Admin admin) {
		return repo.save(admin);
	}

	public Admin findAdminById(int id) {

		Optional<Admin> optional = repo.findById(id);
		if (optional.isPresent()) {
			Admin admin = optional.get();
			return admin;
		}

//		else 
//		{
//			return null;
//		}

		// OR

		return null;

	}

	public Admin updateAdmin(int id, Admin admin) {

		// if id present then update data not present then return null

		Optional<Admin> optional = repo.findById(id);
		if (optional.isPresent()) {
			admin.setAdminid(id);
			return repo.save(admin);
		}

		return null;

	}

	public Admin deleteAdminByid(int id) {

		Optional<Admin> optional = repo.findById(id);
		if (optional.isPresent()) {
			repo.deleteById(id);
			// to display deleted admin data
			return optional.get();
		}

		return null;
	}

	public List<Admin> getAllAdmins() {
		return repo.findAll();

	}

	public Admin findAdminByEmail(String email) {
		
		Optional<Admin> optional = repo.findByEmail(email);
		if(optional.isPresent())
		{
			return optional.get();
		}
		
		repo.findByEmail(email);
		
		return null;
	}

}
