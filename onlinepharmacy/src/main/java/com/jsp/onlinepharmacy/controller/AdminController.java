package com.jsp.onlinepharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacy.dao.AdminDao;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.service.AdminService;
import com.jsp.onlinepharmacy.util.ResponseStructure;
import com.jsp.onlinepharmacy.entity.Admin;
import java.util.*;

@RestController
public class AdminController {
	
	// meant for creating rest api and control other classes
	
	@Autowired 
	private AdminDao dao;
	
	
	@Autowired
	private AdminService service; 
	
//	@PostMapping("/save") 
//	public Admin signupAdmin(@RequestBody Admin admin)
//	{
//		 return  dao.saveAdmin(admin);
//	 
//	}
	
	// using response structure
	
	
//	@PostMapping("/save")
//	public ResponseStructure<Admin>signupAdmin(@RequestBody Admin admin)
//	{
//		 return  service.saveAdmin(admin);
//	}
	
	// using response entity of responsestructure
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<AdminDto>>signupAdmin(@RequestBody Admin admin)
	{
		 return  service.saveAdmin(admin);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<AdminDto>>fetchAdminById(@RequestParam int id)
	{
		 return  service.fetchAdminById(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<AdminDto>>updateAdmin(@RequestParam int id,@RequestBody Admin admin)
	{
		return service.updateAdmin(id,admin);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<AdminDto>>deleteAdmin(@RequestParam int id)
	{
		return service.deleteAdminById(id);
	}
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<AdminDto>>>fetchAllAdmins()
	{
		 return  service.fetchAllAdminBy();
	}
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AdminDto>>loginAdmin(@RequestParam String email,@RequestParam String password)
	{
		return service.loginAdmin(email,password);
	}
	
	
	
	
}
