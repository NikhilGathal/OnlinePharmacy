package com.jsp.onlinepharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.Medicine;
import com.jsp.onlinepharmacy.repo.MedicineRepo;

@Repository
public class MedicineDao {
	
	@Autowired
	private MedicineRepo repo;

	public Medicine saveMedicines(Medicine medicine) {
		return repo.save(medicine);
	}
	
	public Medicine updateMedicine(int medicineId, Medicine medicine) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional.isPresent()) {
//			id is present so i can update the data
			medicine.setMedicineId(medicineId);
			medicine.setMedicalStore(optional.get().getMedicalStore());
			return repo.save(medicine);
			
		}
		return null;
	}

	public Medicine findMedicine(int medicineId) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional.isPresent()) {
//			id is present 
			return optional.get();
			
		}
		return null;
	}

	public Medicine deleteMedicine(int medicineId) {
		Optional<Medicine> optional=repo.findById(medicineId);
		if(optional.isPresent()) {
//			id is present 
			repo.deleteById(medicineId);
			return optional.get();
			
		}
		return null;
	}
	
	public Medicine findMedicineByName(String medicineName) {
		Optional<Medicine> optional=repo.findByName(medicineName);
		if(optional.isPresent()) {
//			medicine is present 
			return optional.get();
		}
		return null;
	}

}
