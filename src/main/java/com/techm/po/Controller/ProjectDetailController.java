package com.techm.po.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techm.po.model.bo.ProjectBO;
import com.techm.po.service.ProjectDetailService;

@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectDetailController {
	
	@Autowired
	private ProjectDetailService projectDetailService;
	
	/**
	 * This API is for creating Project Details
	 * @param projectDTO
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> createProject(@RequestBody ProjectBO projectBo) {
		Map<String, Object> response;
		response = projectDetailService.addProject(projectBo);
		return response;
	}

	/**
	 * This API is for fetching Project Details
	 * @param accountCategory
	 * @param accountName
	 * @param projectName
	 * @param projectType
	 * @param status
	 * @return
	 */
	@GetMapping("/fetch")
	public Map<String, Object> getProjectDetail(@RequestParam(name = "accountCategory", required = false) String accountCategory,
								@RequestParam(name = "projectName", required = false) String projectName,
								@RequestParam(name = "projectType", required = false) String projectType,
								@RequestParam(name = "status", required = false) String status) {
		Map<String, Object> response;
		response = projectDetailService.fetchProjectDetail(accountCategory, projectName, projectType, status);
		return response;
	}
	
	/**
	 * This API is for deleting Project Details
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> deleteProjectDetail(@RequestParam(name = "pId", required = true) String pId) {
		Map<String, Object> response;
		response = projectDetailService.deleteProjectDetail(pId);
		return response;
	}
	
	/**
	 * This API is for updating Project Details
	 * @param projectDTO
	 * @return
	 */
	@PutMapping("/update")
	public Map<String, Object> modifyProjectDetail(@RequestBody ProjectBO projectBo) {
		Map<String, Object> response;
		response = projectDetailService.modifyprojectDetails(projectBo);
		return response;
	}
	
	/**
	 * This API is for fetching Resources Details
	 * @param pId
	 * @return
	 */
	@GetMapping("/fetch/resources")
	public Map<String, Object> getResourceByPID(@RequestParam(name="pId", required = true) String pId) {
		Map<String, Object> response;
		response = projectDetailService.getResourceByPID(pId);
		return response;
	}
	
	@GetMapping("/fetch/cresources")
	public Map<String, Object> getcResource() {
		Map<String, Object> response;
		response = projectDetailService.getcResource();
		return response;
	}
	
	@GetMapping("/fetch/resourcesonupdate")
	public Map<String, Object> getResourceByPIDonupdate(@RequestParam(name="pId", required = true) String pId) {
		Map<String, Object> response;
		response = projectDetailService.getResourceByPIDonupdate(pId);
		return response;
	}
	
	@GetMapping("/fetch/checkpid")
	public Map<String, Object> getPIDs(@RequestParam(name="pid", required = true) String pid) {
		Map<String, Object> response;
		response = projectDetailService.getPIDs(pid);
		return response;
	}
}
