package com.techm.po.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techm.po.model.dto.ProjectDTO;
import com.techm.po.model.dto.ProjectInformationDTO;

@Repository
public interface ProjectInformationRepository extends JpaRepository<ProjectDTO, String>{

	@Query(value="select * from tbl_po_approvals po where po.associate_id=?1 and po.month_year=?2", nativeQuery=true)
	Optional<ProjectInformationDTO> fetchPoApprovalsByMonthYear(String associateId, String monthYear);
	
	@Query(value="select distinct account_category from tbl_project", nativeQuery=true)
	List<String> fetchAccountCategories();

	@Query(value="select * from tbl_project po where po.account_category=?1", nativeQuery=true)
	List<ProjectDTO> fetchprojectInfo(String accountCategory);
}
