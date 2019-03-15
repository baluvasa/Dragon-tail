package com.techm.po.service;

import java.util.Map;

public interface ProjectInformationService {

	/*
	 * Map<String, Object> addPoApprovalDetails(ProjectInformationDTO approvalsDto);
	 * 
	 * Map<String, Object> fetchProjectByCategory(String accountCategory, String
	 * accountName);
	 */

	Map<String, Object> fetchAccountCategory();

	Map<String, Object> fetchprojectInfo(String accountCategory);
	
	Map<String, Object> getPOSummaryDetails(String customerName, String projectStartDate, 
			String projectEndDate, String quote, String pId, String contract);

	Map<String, Object> fetchProjectDetails(String accountCategory, String accountName, String projectName,
			String projectType, String yyyyMM);	
}
