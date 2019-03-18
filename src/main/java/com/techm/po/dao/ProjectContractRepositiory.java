package com.techm.po.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techm.po.model.dto.ProjectContractDTO;

@Repository
public interface ProjectContractRepositiory extends JpaRepository<ProjectContractDTO ,String> {

	 @Query("SELECT pc FROM ProjectContractDTO pc where Lower(pc.contractNumber)= Lower(:cid)")
	 Optional<ProjectContractDTO> fetchcontractinfo(String cid);
	 
	 @Query("SELECT pc FROM ProjectContractDTO pc where Lower(pc.quote)= Lower(:qid)")
	 Optional<ProjectContractDTO> fetchquoteinfo(String qid);

	 @Query("SELECT pc FROM ProjectContractDTO pc where Lower(pc.po)= Lower(:poid)")
	 Optional<ProjectContractDTO> fetchpoinfo(String poid);


}
