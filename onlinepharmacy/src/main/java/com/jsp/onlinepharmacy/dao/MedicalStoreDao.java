package com.jsp.onlinepharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacy.entity.MedicalStore;
import com.jsp.onlinepharmacy.repo.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {
	
	@Autowired 
	private MedicalStoreRepo repo;

	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
		// TODO Auto-generated method stub
		return repo.save(medicalStore);
	}

	public MedicalStore updateMedicalStore(int storeId, MedicalStore medicalStore) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore dbMedicalStore=optional.get();
			medicalStore.setStoreid(storeId);
//			id storename managername phone
//			address admin=null 
			// need to set relationship entity also
			medicalStore.setAddress(dbMedicalStore.getAddress());
			medicalStore.setAdmin(dbMedicalStore.getAdmin());
			
//			medicalSTore=id storename managername phone address admin
			return repo.save(medicalStore);
		}
		return null;
	}
	
	public MedicalStore findMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public MedicalStore deleteMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore medicalStore =  optional.get();
			medicalStore.setAdmin(null);
			// 
			repo.deleteById(storeId);
			return optional.get();
		}
		return null;
	}
	
}
