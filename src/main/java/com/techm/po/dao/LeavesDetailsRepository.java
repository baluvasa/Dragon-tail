package com.techm.po.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techm.po.model.dto.AccessDetailsDTO;
import com.techm.po.model.dto.LeavesDTO;

@Repository
public interface LeavesDetailsRepository extends JpaRepository<LeavesDTO, String>{
	
//	@Query(value="select ad from tbl_leaves ad where ad.associate_id like ?1 or ad.associate_name like ?2 or ad.leave_date BETWEEN ?3 AND ?4 and status='ACTIVE'",nativeQuery=true)	
//	List<LeavesDTO> fetchLeavesDetails(String id, String name, LocalDate startdate,LocalDate enddate);	

	
	 @Query("select ad from LeavesDTO ad where lower(ad.associateId) like :id or "
		  		+ "lower(ad.associateName) like :name and ad.status='ACTIVE'") 
		  List<LeavesDTO> fetchLeavesDetailsdateempty(@Param("id") String id,@Param("name") String name);

	
	
	 @Query("select ad from LeavesDTO ad where lower(ad.associateId) like :id or "
		  		+ "lower(ad.associateName) like :name or (ad.leaveDate BETWEEN :startdate and :enddate) and ad.status='ACTIVE'") 
		  List<LeavesDTO> fetchLeavesDetails(@Param("id") String id,@Param("name") String name,@Param("startdate") LocalDate startdate,@Param("enddate") LocalDate enddate);

	
//	@Query("select ld from LeavesDTO ld where ld.status='ACTIVE'")
//	List<LeavesDTO> fetchLeavesDetailsbymonths(String id, String name, String startdate,String enddate);	
//	
//	@Query("select ld from LeavesDTO ld where ld.status='ACTIVE'")
//	List<LeavesDTO> fetchAllLeavesDetails(String id, String name, String startdate,String enddate);	
	
	@Query("select ld from LeavesDTO ld where ld.associateId= ?1")
	Optional<LeavesDTO> findById(String id);
	
	
	@Query("select ld from LeavesDTO ld where ld.leaveId= :id")
	Optional<LeavesDTO> findByleaveId(Integer id);
	
	@Query("select ld from LeavesDTO ld where ld.associateId= :id and ld.leaveDate= :date")
	Optional<LeavesDTO> findByIdDate(String id,LocalDate date);

	@Query("select ld from LeavesDTO ld where ld.leaveId= :id")
    Optional<LeavesDTO> findByIdLeaveDate(Integer id);
	
	@Query(value="select * from tbl_leaves l where l.leave_date between :startdate1 and :enddate1", nativeQuery=true)
	List<LeavesDTO> getAssociateLeaveDetails(LocalDate startdate1, LocalDate enddate1);

}
