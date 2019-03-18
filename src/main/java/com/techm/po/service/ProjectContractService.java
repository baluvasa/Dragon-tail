package com.techm.po.service;

import java.util.Map;

import com.techm.po.model.dto.ProjectContractDTO;

public interface ProjectContractService {

	 Map<String, Object> fetchContracts(String pid);

	Map<String, Object> adContractDetails(ProjectContractDTO projectContractDto);

	Map<String, Object> fetchContractsInfo(String pid);
}
