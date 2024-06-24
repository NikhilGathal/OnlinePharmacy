package com.jsp.onlinepharmacy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.onlinepharmacy.entity.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine, Integer>{
    @Query("Select m from Medicine m where m.medicineName=?1")
	public Optional<Medicine> findByName(String medicineName);

}