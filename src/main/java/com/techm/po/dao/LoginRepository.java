package com.techm.po.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.techm.po.model.dto.AccessDetailsDTO;

@Repository
public interface LoginRepository extends JpaRepository<AccessDetailsDTO, String>{

	@Query(value="select * from tbl_access_details ac where ac.gid=?1 ", nativeQuery=true)
	Optional<AccessDetailsDTO> fetchAccessDetails(String associateId);
}
