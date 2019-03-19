package com.techm.po.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.techm.po.dao.ProjectContractRepositiory;
import com.techm.po.model.bo.DefaultEnums;
import com.techm.po.model.dto.ProjectContractDTO;
import com.techm.po.service.ProjectContractService;
import com.techm.po.utils.DateUtils;

@Service
public class ProjectContractServiceImpl implements ProjectContractService {

	@Autowired
	private ProjectContractRepositiory projectContractRepositiory;
	
	@Override
	public Map<String, Object> fetchContracts(String cid) {
		Optional<ProjectContractDTO> contractData;
		Map<String, Object> response;
		response = new HashMap<>();
		contractData=projectContractRepositiory.fetchcontractinfo(cid);
		if(contractData.isPresent()) {
			response.put("message", "Contract Number is already Exisits for Project");
			response.put("status", HttpStatus.CONFLICT.value());
		}
		else
		{
			response.put("message", "Contract Number is Not Exisits in any Project");
			response.put("status", HttpStatus.OK.value());
		}
		return response;
	}

	@Transactional
	@Override
	public Map<String, Object> adContractDetails(ProjectContractDTO projectContractDto) {

		Optional<ProjectContractDTO> contractData;
		Optional<ProjectContractDTO> qouteData;
		Optional<ProjectContractDTO> poData;
		Map<String, Object> response;
		response = new HashMap<>();
		contractData=projectContractRepositiory.fetchcontractinfo(projectContractDto.getContractNumber().toLowerCase());
		qouteData=projectContractRepositiory.fetchquoteinfo(projectContractDto.getQuote().toLowerCase());
		poData=projectContractRepositiory.fetchpoinfo(projectContractDto.getPo().toLowerCase());
		
		if(contractData.isPresent() || qouteData.isPresent() || poData.isPresent()) {
			String m="";
			if(contractData.isPresent() ) {
				m +="Contract Id "+ projectContractDto.getContractNumber() +",";
			}
			if(qouteData.isPresent() ) {
				m +="Quote Id "+  projectContractDto.getQuote() +",";
			}

			if(poData.isPresent() ) {
				m += "PO Id "+ projectContractDto.getPo() +",";
			}
			
			response.put("message", m.substring(0, m.length() - 1)+ " already Exisits for Project");
			response.put("status", HttpStatus.FOUND.value());
		}
		else
		{
			projectContractDto.setContractNumber(projectContractDto.getContractNumber().toUpperCase());
			projectContractDto.setContractEndDate(projectContractDto.getContractEndDate());
			projectContractDto.setContractStartDate(projectContractDto.getContractStartDate());
			projectContractDto.setPid(projectContractDto.getPid());
			projectContractDto.setQuote(projectContractDto.getQuote());
			projectContractDto.setPo(projectContractDto.getPo());
			projectContractDto.setUom(projectContractDto.getUom());
			projectContractDto.setCreatedBy(projectContractDto.getCreatedBy());
			projectContractDto.setStatus(DefaultEnums.ACTIVE.name());
			projectContractDto.setCreatedDate(LocalDateTime.now());
			projectContractRepositiory.save(projectContractDto);
			response.put("message", "Contract details Added successfully");
			response.put("status", HttpStatus.CREATED.value());			
		}
		
		return response;
	}

	@Override
	public Map<String, Object> fetchContractsInfo(String pid) {
		List<ProjectContractDTO> contractData;
		Map<String, Object> response;
		response = new HashMap<>();
		contractData=projectContractRepositiory.fetchcontractinfobypid(pid);
		if(contractData.size()>0) {
			response.put("message", "Contract Details Fetched Successfully.");
			response.put("status", HttpStatus.OK.value());
			response.put("contractdetails", contractData);
		}
		else
		{
			response.put("message", "Contract Details is Not Exisits in any Project");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}
	
	
}
