package com.techm.po.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techm.po.model.dto.ProjectContractDTO;

@Repository
public interface ProjectContractRepositiory extends JpaRepository<ProjectContractDTO ,String> {

}
