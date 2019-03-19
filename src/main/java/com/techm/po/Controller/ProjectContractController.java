package com.techm.po.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techm.po.model.dto.ProjectContractDTO;
import com.techm.po.service.ProjectContractService;

@RestController
@RequestMapping("/contract")
@CrossOrigin
public class ProjectContractController {
	@Autowired
	private ProjectContractService projectContractService;

	@GetMapping("/fetch/contractslist")
	public Map<String, Object> fetchContractslist(@RequestParam(name = "pid", required = true) String pid) {
		Map<String, Object> response;
		response = projectContractService.fetchContractsList(pid);
		return response;
	}
	
	@GetMapping("/fetch/contracts")
	public Map<String, Object> fetchContracts(@RequestParam(name = "cid", required = true) String cid) {
		Map<String, Object> response;
		response = projectContractService.fetchContracts(cid);
		return response;
	}
	
	@GetMapping("/fetch/contractsinfo")
	public Map<String, Object> fetchContractsInfo(@RequestParam(name = "pid", required = true) String pid) {
		Map<String, Object> response;
		response = projectContractService.fetchContractsInfo(pid);
		return response;
	}
	
	@PostMapping("/add")
	public Map<String, Object> addAccessDetails(@RequestBody ProjectContractDTO projectContractDto){
		
		Map<String, Object> response;
		response = new HashMap<>();
		response=projectContractService.adContractDetails(projectContractDto);
		
		return response;
	}
}
