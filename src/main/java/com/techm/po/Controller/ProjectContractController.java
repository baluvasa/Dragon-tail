package com.techm.po.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techm.po.service.ProjectContractService;

@RestController
@RequestMapping("/contract")
@CrossOrigin
public class ProjectContractController {
	@Autowired
	private ProjectContractService projectContractService;

	@GetMapping("/fetch/contracts")
	public Map<String, Object> fetchContracts(@RequestParam(name = "pid", required = true) String pid) {
		Map<String, Object> response;
		response = projectContractService.fetchContracts(pid);
		return response;
	}
}
