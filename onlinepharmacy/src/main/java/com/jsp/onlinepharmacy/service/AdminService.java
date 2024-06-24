package com.jsp.onlinepharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AdminDao;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.AdminNotFoundWithThisEmail;
import com.jsp.onlinepharmacy.exception.AdminPasswordNotValidException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class AdminService {

	// it will take data from controller and pass it to dao layer
	// it will receive data returned by dao
	// it will add some extra details to it
	// it will return responsestructure of admin

	@Autowired
	private AdminDao dao;

	// Using response structure

//	public ResponseStructure<Admin> saveAdmin(Admin admin) {
//
//		Admin dbadmin = dao.saveAdmin(admin);
//		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
//		structure.setMessage("User data saved successfully");
//		structure.setHttpstatus(HttpStatus.CREATED.value());
//		structure.setData(dbadmin);
//
//		return structure;
//	}

	// Using response entity

	@Autowired
	private AdminDto admindto;

	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin) {

		Admin dbadmin = dao.saveAdmin(admin);
		admindto.setAdminid(dbadmin.getAdminid());
		admindto.setAdminName(dbadmin.getAdminName());
		admindto.setAdminAddress(dbadmin.getAdminAddress());

		// for which layer we want to return the structure create the structure for that
		// layer only
		// like here we want to return admindto then create respstruct for admindto only

		ResponseStructure<AdminDto> structure = new ResponseStructure<>();
		structure.setMessage("Admin SignedUp successfully");
		structure.setHttpstatus(HttpStatus.CREATED.value());
		structure.setData(admindto);

		return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> fetchAdminById(int id) {

		Admin dbadmin = dao.findAdminById(id);
		if (dbadmin != null) {
			// id is present
			admindto.setAdminid(dbadmin.getAdminid());
			admindto.setAdminName(dbadmin.getAdminName());
			admindto.setAdminAddress(dbadmin.getAdminAddress());

			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setMessage("User data Fetched successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(admindto);

			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.FOUND);

		}

		else {
			// id not present

			throw new AdminIdNotFoundException("sorry failed to fetch data");
		}

	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int id, Admin admin) {

		Admin dbadmin = dao.updateAdmin(id, admin);
		if (dbadmin != null) {
			admindto.setAdminid(dbadmin.getAdminid());
			admindto.setAdminName(dbadmin.getAdminName());
			admindto.setAdminAddress(dbadmin.getAdminAddress());
			// id is present data updated success

			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setMessage("User data Updated successfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(admindto);

			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
		} else {
			// id not present
			throw new AdminIdNotFoundException("sorry failed to update data");
		}

	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminById(int id) {

		Admin dbadmin = dao.deleteAdminByid(id);
		if (dbadmin != null) {
			// id is present

			admindto.setAdminid(dbadmin.getAdminid());
			admindto.setAdminName(dbadmin.getAdminName());
			admindto.setAdminAddress(dbadmin.getAdminAddress());

			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setMessage("User data deleted successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(admindto);

			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.FORBIDDEN);

		} else {
			throw new AdminIdNotFoundException("sorry failed to delete data");
		}

	}

	public ResponseEntity<ResponseStructure<List<AdminDto>>> fetchAllAdminBy() {

		List<Admin> list = dao.getAllAdmins();

		List<AdminDto> admindtos = new ArrayList<>();

		for (Admin admin : list) {

			AdminDto admindto1 = new AdminDto();

			// every time admindto1 value is null for each loop / Iteration

			admindto1.setAdminid(admin.getAdminid());
			admindto1.setAdminName(admin.getAdminName());
			admindto1.setAdminAddress(admin.getAdminAddress());
			admindtos.add(admindto1);

			// code will not work if we not create object using new
			// bcz only reference variable we are using then
			// it will reassign values to same ref variables
			// and ref variable will point to last list object
			// that why we get same output on postman for findalls

		}

		ResponseStructure<List<AdminDto>> structure = new ResponseStructure<>();
		structure.setMessage("User data Fetched successfully");
		structure.setHttpstatus(HttpStatus.FOUND.value());
		structure.setData(admindtos);

		return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure, HttpStatus.FOUND);

	}

	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(String email, String password) {

		Admin dbadmin = dao.findAdminByEmail(email);

		if (dbadmin != null) {
			// if admin present means can check with password

			if(password.equals(dbadmin.getAdminPassword()))
			{
				// login success if pass match means dsipaly all details
				
				admindto.setAdminid(dbadmin.getAdminid());
				admindto.setAdminName(dbadmin.getAdminName());
				admindto.setAdminAddress(dbadmin.getAdminAddress());

				// for which layer we want to return the structure create the structure for that
				// layer only
				// like here we want to return admindto then create respstruct for admindto only

				ResponseStructure<AdminDto> structure = new ResponseStructure<>();
				structure.setMessage("Admin Login successfully");
				structure.setHttpstatus(HttpStatus.FOUND.value());
				structure.setData(admindto);

				return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.FOUND);
				
				
			}
			else
			{
				// invalid password
				throw new AdminPasswordNotValidException("Sorry failed to login password is incorrect");
			}
			
			
		} else {
			
			
			// admin not present with this email means invalid email

			throw new AdminNotFoundWithThisEmail("Sorry failed to login ");

		}

		
	}

}
