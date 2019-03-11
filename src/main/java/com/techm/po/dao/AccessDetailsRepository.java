package com.techm.po.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.techm.po.model.dto.AccessDetailsDTO;

@Repository
public interface AccessDetailsRepository extends JpaRepository<AccessDetailsDTO,String>{

	  @Query("select ad from AccessDetailsDTO ad where lower(ad.gid) like :id or "
		  		+ "lower(ad.associateName) like :name or lower(ad.accessType) like :type or lower(ad.status) like :status") 
		  List<AccessDetailsDTO> fetchAccessDetails(@Param("id") String id,@Param("name") String name,@Param("type") String type,@Param("status") String status);

//	  @Query("select ad from AccessDetailsDTO ad where ad.gid like :id or "
//		  		+ "ad.associateName like :name or ad.accessType like :type or ad.status like :status") 
//		  List<AccessDetailsDTO> fetchAccessDetails(@Param("id") String id,@Param("name") String name,@Param("type") String type,@Param("status") String status);

	  @Query(value="SELECT * FROM tbl_access_details ad where Lower(ad.gid)= Lower(:gid);", nativeQuery=true)
	  Optional<AccessDetailsDTO> fetchAssociate(String gid);
}
