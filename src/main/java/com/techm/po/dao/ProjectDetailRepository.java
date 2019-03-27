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

	@Transactional
	@Modifying
	@Query(value="UPDATE ProjectDTO SET customerSpoc=:customerSpoc, approvalMethod=:approvalMethod, "
			+ " submissionMode=:submissionMode, projectType=:projectType, "
			+ "billingCurrency=:billingCurrency, poAmount=:poAmount, projectStartDate=:projectStartDate,"
			+ " projectEndDate=:projectEndDate, status=:status, "
			+ "deliverySpoc=:deliverySpoc, effortSpoc=:effortSpoc, modifiedDate=:modifiedDate, modifiedBy=:modifiedBy "
			+ "WHERE id=:id")
	void modifyProjectDetail(@Param("customerSpoc") String customerSpoc,@Param("approvalMethod") String approvalMethod,
			@Param("submissionMode") String submissionMode,@Param("projectType") String projectType,
			@Param("billingCurrency") String billingCurrency,@Param("poAmount") String poAmount, 
			@Param("projectStartDate") LocalDate projectStartDate,@Param("projectEndDate") LocalDate projectEndDate,
			@Param("status") String status,@Param("deliverySpoc") String deliverySpoc, 
			@Param("effortSpoc") String effortSpoc,
			@Param("modifiedDate") LocalDateTime now, @Param("modifiedBy") String modifiedBy,@Param("id") int id);
}

