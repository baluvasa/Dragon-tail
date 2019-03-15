package com.techm.po.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.techm.po.dao.ProjectContractRepositiory;
import com.techm.po.service.ProjectContractService;

@Service
public class ProjectContractServiceImpl implements ProjectContractService {

	@Autowired
	private ProjectContractRepositiory projectContractRepositiory;
	
	@Override
	public Map<String, Object> fetchContracts(String pid) {
		return null;
	}
}
