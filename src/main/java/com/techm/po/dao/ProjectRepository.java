package com.techm.po.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techm.po.model.dto.CreateProjectDTO;
import com.techm.po.model.dto.ProjectDTO;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectDTO, Integer> {
	
	@Query(value="Select * from tbl_project pro where pro.account_category=?1 and pro.account_name=?2", nativeQuery=true)
	Optional<ProjectDTO> fetchProjectDetails(String accountCategory, String accountName);

}
