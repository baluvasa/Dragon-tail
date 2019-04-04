package com.techm.po.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techm.po.model.dto.ResourceDTO;

@Repository
public interface UploadExcelRepository extends JpaRepository<ResourceDTO, String>{
	
	
	
}
