package com.techm.po.service;

import java.util.Map;

import com.techm.po.model.bo.ProjectBO;

public interface ProjectDetailService {

	Map<String, Object> addProject(ProjectBO projectBo);
	
	Map<String, Object> fetchProjectDetail(String accountCategory, String projectName, String projectType, String status);

	Map<String, Object> deleteProjectDetail(String id);

	Map<String, Object> modifyprojectDetails(ProjectBO projectBo);

	Map<String, Object> getResourceByPID(String pId);

	Map<String, Object> getcResource();

	Map<String, Object> getResourceByPIDonupdate(String pId);

	Map<String, Object> getPIDs(String pId);
}
