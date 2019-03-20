package com.techm.po.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techm.po.service.ProjectInformationService;

@RestController
@CrossOrigin
@RequestMapping("/po_approval")
public class ProjectInformationController {

	@Autowired
	private ProjectInformationService projectInformationService;

	/**
	 * This API is for adding PO Approval Details
	 * 
	 * @param approvalsDto of type {@link PoApprovalsDTO}
	 * @return success message as response, along with the status code
	 *//*
		 * @PostMapping("/add") public Map<String, Object>
		 * addPoApprovalDetails(@RequestBody ProjectInformationDTO approvalsDto){
		 * Map<String, Object> response; response =
		 * poApprovalService.addPoApprovalDetails(approvalsDto); return response; }
		 * 
		 * 
		 * @PutMapping("/update") public Map<String, Object>
		 * updatePoApprovalDetails(@RequestBody ProjectInformationDTO approvalsDto){
		 * Map<String, Object> response; response =
		 * poApprovalService.addPoApprovalDetails(approvalsDto); return response; }
		 */

	@GetMapping("/fetch/accountCategory")
	public Map<String, Object> fetchAccountCategory() {
		Map<String, Object> response;
		response = projectInformationService.fetchAccountCategory();
		return response;
	}
	
	@GetMapping("/fetch/projectInfo")
	public Map<String, Object> fetchprojectInfo(@RequestParam(name = "accountCategory", required = true) String accountCategory) {
		Map<String, Object> response;
		response = projectInformationService.fetchprojectInfo(accountCategory);
		return response;
	}
	
	@GetMapping("/fetch/projectDetails")
	public Map<String, Object> fetchProjectDetails(@RequestParam(name="accountCategory", required=true) String accountCategory,
			@RequestParam(name="accountName", required=true) String accountName,
			@RequestParam(name="projectName", required=true) String projectName,
			@RequestParam(name="projectType", required=true) String projectType,
			@RequestParam(name="yyyyMM", required=true) String yyyyMM){
		
		Map<String, Object> response;
		response=projectInformationService.fetchProjectDetails(accountCategory, accountName, projectName, projectType, yyyyMM);
		return response;
	}
	
	@GetMapping("/fetch/projectInfoList")
	public Map<String, Object> fetchprojectInfoList(@RequestParam(name = "customerName", required = true) String customerName,
			@RequestParam(name = "quote", required = true) String quote,
			@RequestParam(name = "pId", required = true) String pId,
			@RequestParam(name = "contract", required = true) String contract,
			@RequestParam(name = "yyyyMM", required = true) String yyyyMM) {
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		
		response = projectInformationService.getPOSummaryDetails(customerName, quote, pId, contract, yyyyMM);
		return response;
	}
	
}
