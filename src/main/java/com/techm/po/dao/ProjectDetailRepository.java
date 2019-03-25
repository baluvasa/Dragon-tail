package com.techm.po.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techm.po.model.dto.ProjectDTO;

public interface ProjectDetailRepository extends JpaRepository<ProjectDTO, String>{

@Query("SELECT pDtl FROM ProjectDTO pDtl WHERE LOWER(pDtl.pid)=:id")
	Optional<ProjectDTO> fetchProjectDetail(@Param("id") String id);

	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_project SET status='INACTIVE' WHERE id=?1", nativeQuery=true)
	void deleteProjectDetail(Integer id);

	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_project SET customer_spoc=?1, approval_method=?2, submission_mode=?3, project_type=?4, "
			+ "billing_currency=?5, po_amount=?6, project_start_date=?7, project_end_date=?8, status=?9, "
			+ "delivery_spoc=?10, effort_spoc=?11, modified_date=?12, modified_by=?14,WHERE pid=?13", nativeQuery=true)
	void modifyProjectDetail(String customerSpoc, String approvalMethod, String submissionMode, String projectType,
			String billingCurrency, String poAmount, LocalDate projectStartDate, LocalDate projectEndDate, String status,
			String deliverySpoc, String effortSpoc, LocalDateTime modifiedDate, String pid, String modifiedBy);

	@Query("select pDtl from ProjectDTO pDtl where lower(pDtl.accountCategory) like :accountCategory or "
            + "lower(pDtl.projectName) like :projectName or lower(pDtl.projectType) like :projectType or lower(pDtl.status) like :status")
	List<ProjectDTO> fetchProjectDetails(@Param("accountCategory") String accountCategory,@Param("projectName") String projectName,@Param("projectType") String projectType,
			@Param("status") String status);
	
	@Query("select count(*) from ProjectDTO pDtl where pDtl.pid=:id")		    
	Integer checkpidscount(@Param("id") String pId);

	@Query("select count(*) from ProjectDTO pl where pl.id=:id")
	Integer fetchProjectDetailid(@Param("id") Integer id);

	@Query(value="select distinct pid from tbl_project", nativeQuery=true)
	List<String> fetchpidList();
	
	@Query("select pl from ProjectDTO pl where lower(pl.accountCategory)=:category and lower(pl.accountName)=:name")
	List<ProjectDTO> fetchProjectDetailinfo(@Param("category") String accountcategory,@Param("name") String accountname);
	}
