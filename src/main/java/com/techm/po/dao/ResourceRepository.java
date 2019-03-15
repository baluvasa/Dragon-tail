package com.techm.po.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techm.po.model.bo.ResourceMap;
import com.techm.po.model.dto.ResourceDTO;


@Repository
public interface ResourceRepository extends JpaRepository<ResourceDTO, String>{

//   @Query("select rd from ResourceDTO rd where rd.associateId like :searchTerm or "
//   + "rd.associateName like :searchTerm1 or rd.band like :searchTerm2") 
//   List<ResourceDTO> fetchResourceDetails(@Param("searchTerm")  String searchTerm,@Param("searchTerm1") String associateName,@Param("searchTerm2") String band);
  
  @Query("select rd from ResourceDTO rd where lower(rd.associateId) like lower(concat('%',:associateId,'%')) or "
  + "lower(rd.associateName) like lower(concat('%',:associateName,'%')) or lower(rd.band) like lower(concat('%',:band,'%'))") 
  List<ResourceDTO> fetchResourceDetails(String associateId,String associateName,String band);
  
  @Query(value="select res.*, poa.accruals_amount as accrualsAmount, poa.accruals_hours as accrualsHours, poa.build_amount as buildAmount, "
			+ "poa.build_hours as buildHours from tbl_resources res inner join tbl_po_approvals poa on res.associate_id=poa.associate_id " + 
			"where res.pid=?1 and res.status='ACTIVE'", nativeQuery=true)
	List<ResourceDTO> fetchResourceDetailsbyID(String pid); 
  
  

	@Query(value="UPDATE tbl_resources SET associate_name=:associateName, band=:band,contact_number=:contactNumber,email=:email WHERE associate_id=:associateId", nativeQuery=true)
	void modifyResourceDetails(String associateId, String associateName, String band, String contactNumber, String email);
	
	@Query(value="SELECT * FROM tbl_resources pDtl WHERE pDtl.p_id=?1 and status='ACTIVE'", nativeQuery=true)
	List<ResourceDTO> fetchResourcesDetail(String pId);

	@Query(value="SELECT * FROM tbl_resources pDtl WHERE pDtl.p_id='00000' and status='ACTIVE'", nativeQuery=true)
	List<ResourceDTO> fetchcResourcesDetail();

	@Transactional
	@Modifying
	@Query(value="UPDATE tbl_resources SET p_id=?2 where associate_id=?1",nativeQuery=true)
	void updatecontractpid(String associateId,String getpId);

//	void updatePidFlag(ResourceMap r);

//	@Transactional
//	@Modifying
//	@Query(value="UPDATE tbl_resources SET linked = 'Y' WHERE associate_id=:associateIdsWithPid",nativeQuery=true)
//	void updatePidFlag(String associateIdsWithPid);
//	
//	@Transactional
//	@Modifying
//	@Query(value="UPDATE tbl_resources SET linked = 'Y',p_id=:pid WHERE associate_id=:associateIdsWithBuffPid",nativeQuery=true)
//	void updateBuffPidFlag(String associateIdsWithBuffPid,String pid);
//
//	@Transactional
//	@Modifying
//	@Query(value="UPDATE tbl_resources SET linked = 'N',p_id='00000' WHERE associate_id=:associateIdsWithPidToBuff",nativeQuery=true)
//	void updatePidtoBuffFlag(String associateIdsWithPidToBuff);
  
}



