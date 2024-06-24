package com.jsp.onlinepharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AdminDao;
import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dao.StaffDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.MedicalStoreDto;
import com.jsp.onlinepharmacy.dto.StaffDto;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.entity.Staff;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.exception.StaffIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class StaffService {
	@Autowired
	private StaffDao dao;
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private MedicalStoreDao storeDao;
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<StaffDto>> signupStaff(int adminId, int storeId, Staff staff) {
		Admin dbAdmin = adminDao.findAdminById(adminId);
		if (dbAdmin != null) {
//			admin is present then you can procceed the next steps
			MedicalStore dbMedicalStore = storeDao.findMedicalStore(storeId);
			if (dbMedicalStore != null) {
//				medicalStore is present 
//				i just want to add staff
				staff.setAdmin(dbAdmin);
				;
				staff.setMedicalStore(dbMedicalStore);
				Staff dbStaff = dao.saveStaff(staff);

				StaffDto staffDto = this.mapper.map(dbStaff, StaffDto.class);

				AdminDto adminDto = this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
				MedicalStoreDto medicalStoreDto = this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);

				staffDto.setAdminDto(adminDto);
				staffDto.setMedicalStoreDto(medicalStoreDto);

				ResponseStructure<StaffDto> structure = new ResponseStructure<>();
				structure.setMessage("Staff data saved successfully");
				structure.setHttpstatus(HttpStatus.CREATED.value());
				structure.setData(staffDto);
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.CREATED);
			} else {
				throw new MedicalStoreIdNotFoundException("Sorry failed to add staff");
			}
		} else {
			throw new AdminIdNotFoundException("Sorry failed to add Staff");
		}

	}

	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
		Staff dbStaff = dao.updateStaff(staffId, staff);

		if (dbStaff != null) {
//			id is present then data updated successfully
			StaffDto staffDto = this.mapper.map(dbStaff, StaffDto.class);

			AdminDto adminDto = this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			MedicalStoreDto medicalStoreDto = this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);

			staffDto.setAdminDto(adminDto);
			staffDto.setMedicalStoreDto(medicalStoreDto);

			ResponseStructure<StaffDto> structure = new ResponseStructure<>();
			structure.setMessage("Staff data updated successfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.OK);
		} else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to update the staff");
		}
	}

	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(int staffId) {
		Staff dbStaff = dao.findStaff(staffId);
		if (dbStaff != null) {
//			id is present then data updated successfully
			StaffDto staffDto = this.mapper.map(dbStaff, StaffDto.class);

			AdminDto adminDto = this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			MedicalStoreDto medicalStoreDto = this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			
			
			AdminDto admindto = this.mapper.map(dbStaff.getMedicalStore().getAdmin(), AdminDto.class);
			medicalStoreDto.setAdminDto(admindto);
			AddressDto addressDto = this.mapper.map(dbStaff.getMedicalStore().getAddress(), AddressDto.class);
			medicalStoreDto.setAddressDto(addressDto);
			
			
			staffDto.setAdminDto(adminDto);
			staffDto.setMedicalStoreDto(medicalStoreDto);

			ResponseStructure<StaffDto> structure = new ResponseStructure<>();
			structure.setMessage("Staff data fetched successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.FOUND);
		} else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to fetch the staff");
		}
	}

	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbStaff = dao.deleteStaff(staffId);
		if (dbStaff != null) {
//			id is present then data updated successfully
			StaffDto staffDto = this.mapper.map(dbStaff, StaffDto.class);

			AdminDto adminDto = this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			MedicalStoreDto medicalStoreDto = this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);

			staffDto.setAdminDto(adminDto);
			staffDto.setMedicalStoreDto(medicalStoreDto);

			ResponseStructure<StaffDto> structure = new ResponseStructure<>();
			structure.setMessage("Staff data deleted successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure, HttpStatus.FORBIDDEN);
		} else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to delete the staff");
		}
	}

}
