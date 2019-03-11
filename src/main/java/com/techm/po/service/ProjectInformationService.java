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
}
