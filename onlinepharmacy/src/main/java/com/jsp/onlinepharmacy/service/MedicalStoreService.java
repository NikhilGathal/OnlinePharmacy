package com.jsp.onlinepharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.AddressDao;
import com.jsp.onlinepharmacy.dao.AdminDao;
import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dto.AddressDto;
import com.jsp.onlinepharmacy.dto.AdminDto;
import com.jsp.onlinepharmacy.dto.MedicalStoreDto;
import com.jsp.onlinepharmacy.entity.Address;
import com.jsp.onlinepharmacy.entity.Admin;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;

@Service
public class MedicalStoreService {

	@Autowired
	private MedicalStoreDao dao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> establishmedicalStore(int adminId, int addressId,
			MedicalStore medicalStore) {
		Admin dbAdmin = adminDao.findAdminById(adminId);

		if (dbAdmin != null) {
//			admin is a valid admin
			medicalStore.setAdmin(dbAdmin);
//			i have set the admin to the medicalstore
//			i want to check whether that address is present or not
			Address dbAddress = addressDao.findAddress(addressId);
			if (dbAddress != null) {
//				that address is present then i can establish the medicalstore
				medicalStore.setAddress(dbAddress);

				MedicalStore dbMedicalStore = dao.saveMedicalStore(medicalStore);
//				extra code added on 24/05/2024 next 2 lines 

				// this code is to update address table when we add new medicalstore
				// then that medicalstore id should update in address table

				dbAddress.setMedicalStore(medicalStore);

				addressDao.updateAddress(addressId, dbAddress);

				// to return medicalstore

				MedicalStoreDto dto = new MedicalStoreDto();
				dto.setStoreId(dbMedicalStore.getStoreid());
				dto.setStoreName(dbMedicalStore.getStoreName());
				dto.setManagerName(dbMedicalStore.getManagerName());
				dto.setPhone(dbMedicalStore.getPhone());

				Address address = dbMedicalStore.getAddress();

				// to return addressdto

				AddressDto addressDto = new AddressDto();
				addressDto.setAddressId(address.getAddressid());
				addressDto.setStreetName(address.getStreetname());
				addressDto.setState(address.getState());
				addressDto.setPincode(address.getPincode());
				addressDto.setCity(address.getCity());

				dto.setAddressDto(addressDto);

				Admin admin = dbMedicalStore.getAdmin();

				// to return admindto

				AdminDto adminDto = new AdminDto();
				adminDto.setAdminAddress(admin.getAdminAddress());
				adminDto.setAdminid(admin.getAdminid());
				adminDto.setAdminName(admin.getAdminName());

				dto.setAdminDto(adminDto);

				ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
				structure.setMessage("MedicalStore established successfully");
				structure.setHttpstatus(HttpStatus.CREATED.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.CREATED);
			} else {
//				address id is not present
				throw new AddressIdNotFoundException("Sorry failed to establish the medicalStore add not found");
			}
		} else {
//			id is not present and he is not a valid admin 
			throw new AdminIdNotFoundException("Sorry failed to establish the medicalstore not a valid admin ");
		}

	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,
			MedicalStore medicalStore) {
		MedicalStore dbMedicalStore = dao.updateMedicalStore(storeId, medicalStore);
		if (dbMedicalStore != null) {
//			id is present and data updated successfully

			MedicalStoreDto medicalStoreDto = this.mapper.map(dbMedicalStore, MedicalStoreDto.class);

			AddressDto addressDto = this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto = this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);

			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);

			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("Medical data updated succees");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(medicalStoreDto);

			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.OK);
		} else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to update the MedicalStore");

		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(int storeId) {
		MedicalStore dbMedicalStore = dao.findMedicalStore(storeId);
		if (dbMedicalStore != null) {
//			id is present and data updated successfully

			MedicalStoreDto medicalStoreDto = this.mapper.map(dbMedicalStore, MedicalStoreDto.class);

			AddressDto addressDto = this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto = this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);

			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);

			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore data fetched successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(medicalStoreDto);

			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to FETCH the MedicalStore");

		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(int storeId) {
		MedicalStore dbMedicalStore = dao.deleteMedicalStore(storeId);
		if (dbMedicalStore != null) {
//			id is present and data updated successfully

			MedicalStoreDto medicalStoreDto = this.mapper.map(dbMedicalStore, MedicalStoreDto.class);

			AddressDto addressDto = this.mapper.map(dbMedicalStore.getAddress(), AddressDto.class);
			AdminDto adminDto = this.mapper.map(dbMedicalStore.getAdmin(), AdminDto.class);

			medicalStoreDto.setAddressDto(addressDto);
			medicalStoreDto.setAdminDto(adminDto);

			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore data deleted successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(medicalStoreDto);

			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.FORBIDDEN);
		} else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to delete the MedicalStore");

		}
	}

}
