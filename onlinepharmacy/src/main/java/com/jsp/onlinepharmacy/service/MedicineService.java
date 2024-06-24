package com.jsp.onlinepharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacy.dao.MedicalStoreDao;
import com.jsp.onlinepharmacy.dao.MedicineDao;
import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinepharmacy.exception.MedicineIdNotFoundException;
import com.jsp.onlinepharmacy.util.ResponseStructure;
import com.jsp.onlinepharmacy.exception.MedicineNameNotFoundException;

@Service
public class MedicineService {

	@Autowired
	private MedicineDao dao;

	@Autowired
	private MedicalStoreDao storeDao;

	public ResponseEntity<ResponseStructure<Medicine>> addMedicines(int storeId, Medicine medicine) {
		MedicalStore dbMedicalStore = storeDao.findMedicalStore(storeId);
		if (dbMedicalStore != null) {
//			id is present means store is present then i can add the medicines
			medicine.setMedicalStore(dbMedicalStore);
			Medicine dbMedicine = dao.saveMedicines(medicine);
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine saved successfully");
			
			structure.setHttpstatus(HttpStatus.CREATED.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.CREATED);
		} else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to add medicines to the MedicalStore");
		}
	}

	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(int medicineId, Medicine medicine) {
		Medicine dbMedicine = dao.updateMedicine(medicineId, medicine);
		if (dbMedicine != null) {
//			id is present and data updated successfully
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine updated successfully");
			structure.setHttpstatus(HttpStatus.OK.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.OK);
		} else {
//			id is not present and failed to update the data
			throw new MedicineIdNotFoundException("Sorry failed to update the medicine");
		}
	}

	public ResponseEntity<ResponseStructure<Medicine>> findMedicine(int medicineId) {
		Medicine dbMedicine = dao.findMedicine(medicineId);
		if (dbMedicine != null) {
//			id is present and data updated successfully
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine fetched successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.FOUND);
		} else {
//			id is present and failed to update the data
			throw new MedicineIdNotFoundException("Sorry failed to fetch the medicine");
		}
	}

	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(int medicineId) {
		Medicine dbMedicine=dao.deleteMedicine(medicineId);
		if (dbMedicine != null) {
//			id is present and data updated successfully
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine deleted successfully");
			structure.setHttpstatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.FORBIDDEN);
		} else {
//			id is present and failed to update the data
			throw new MedicineIdNotFoundException("Sorry failed to delete the medicine");
		}
	}
	
	public ResponseEntity<ResponseStructure<Medicine>> findMedicine(String medicineName) {
		Medicine dbMedicine=dao.findMedicineByName(medicineName);
		if (dbMedicine != null) {
//			id is present and data updated successfully
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine fetched successfully");
			structure.setHttpstatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure, HttpStatus.FOUND);
		} else {
//			id is present and failed to update the data
			throw new MedicineNameNotFoundException("Sorry failed to fetch the medicine");
		}

	}
}