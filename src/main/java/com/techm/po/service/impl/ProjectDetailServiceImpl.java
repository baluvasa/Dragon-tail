package com.techm.po.service.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techm.po.dao.ProjectDetailRepository;
import com.techm.po.dao.ResourceMapRepository;
import com.techm.po.dao.ResourceRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.ProjectBO;
import com.techm.po.model.bo.ResourceMap;
import com.techm.po.model.dto.ProjectDTO;
import com.techm.po.model.dto.ResourceDTO;
import com.techm.po.model.dto.ResourceMapDTO;
import com.techm.po.service.ProjectDetailService;
import com.techm.po.utils.DateUtils;

@Service
public class ProjectDetailServiceImpl implements ProjectDetailService {

	@Autowired
	private ProjectDetailRepository projectDetailRepository;

	@Autowired
	private ResourceRepository resourcesRepository;
	@Autowired
	private ResourceMapRepository resourceMapRepository;
//	private ResourceDTO resourcesDto;

	@Transactional
	@Override
	public Map<String, Object> addProject(ProjectBO projectBo) {
		Map<String, Object> response = new HashMap<>();
		Optional<ProjectDTO> projectDetailList;
		ProjectDTO dto = new ProjectDTO();
		try {
			dto = mapBoToDto(projectBo);
			projectDetailList = projectDetailRepository.fetchProjectDetail(projectBo.getPid());
			if (projectDetailList.isPresent()) {
				response.put("message", "Project Details already exists");
				response.put("status", HttpStatus.CONFLICT.value());
			} else {
				dto.setProjectStartDate(DateUtils.parseDate(projectBo.getProjectStartDate()));
				dto.setProjectEndDate(DateUtils.parseDate(projectBo.getProjectEndDate()));
				dto.setCreatedDate(LocalDateTime.now());

				if (projectBo.getResources().size() > 0) {
					for (ResourceMap r : projectBo.getResources()) {
						ResourceMapDTO rdto;
						rdto = rmapBoToDto(r);
						resourceMapRepository.save(rdto);
					}
				}
				if(projectBo.getContractToPid().size()>0) {
					for (ResourceMap r : projectBo.getContractToPid()) {
						ResourceMapDTO rdto;
						rdto = rmapBoToDto(r);
						resourcesRepository.updatecontractpid(rdto.getAssociateId(),rdto.getpId());
					}
				}

				projectDetailRepository.save(dto);
				response.put("message", "Project Details added successfully");
				response.put("status", HttpStatus.CREATED.value());
			}
		} catch (Exception e) {
			throw new InvalidServiceException("error occured");
		}
		return response;
	}

	private ResourceMapDTO rmapBoToDto(ResourceMap r) {
		ResourceMapDTO rDto = new ResourceMapDTO();
		rDto.setAssociateId(r.getAssociateId());
		rDto.setLocation(r.getLocation());
		rDto.setAssociateStartDate(DateUtils.parseDate(r.getAssociateStartDate()));
		rDto.setAssociateEndDate(DateUtils.parseDate(r.getAssociateEndDate()));
		rDto.setRatePerHour(r.getRatePerHour());
		rDto.setLinked(r.getLinked());
		rDto.setpId(r.getpId());		
		return rDto;
	}

	private ProjectDTO mapBoToDto(ProjectBO projectBo) {

		ProjectDTO pDto = new ProjectDTO();
		pDto.setAccountCategory(projectBo.getAccountCategory());
		pDto.setAccountName(projectBo.getAccountName());
		pDto.setProjectName(projectBo.getProjectName());
		pDto.setCustomerName(projectBo.getCustomerName());
		pDto.setCustomerSpoc(projectBo.getCustomerSpoc());
		pDto.setApprovalMethod(projectBo.getApprovalMethod());
		pDto.setSubmissionMode(projectBo.getSubmissionMode());
		pDto.setProjectType(projectBo.getProjectType());
		pDto.setBillingCurrency(projectBo.getBillingCurrency());
		pDto.setPoAmount(projectBo.getPoAmount());
		pDto.setResourceCount(projectBo.getResourceCount());		
		pDto.setProjectStartDate(DateUtils.parseDate(projectBo.getProjectStartDate()));
		pDto.setProjectEndDate(DateUtils.parseDate(projectBo.getProjectEndDate()));
		pDto.setStatus(projectBo.getStatus());
		pDto.setDeliverySpoc(projectBo.getDeliverySpoc());
		pDto.setEffortSpoc(projectBo.getEffortSpoc());
		pDto.setUnitOfMeasurement(projectBo.getUnitOfMeasurement());
		pDto.setPid(projectBo.getPid());
		pDto.setQuote(projectBo.getQuote());
		pDto.setContract(projectBo.getContract());
		pDto.setPo(projectBo.getPo());
		pDto.setCreatedBy(projectBo.getCreatedBy());
		pDto.setModifiedBy(projectBo.getModifiedBy());
		pDto.setCreatedDate(LocalDateTime.now());
		return pDto;
	}

	public List<ProjectBO> mapDtoToBo(List<ProjectDTO> projectDtoList) throws ParseException {

		List<ProjectBO> projectBoList = new ArrayList<ProjectBO>();
		for (ProjectDTO dto : projectDtoList) {
			ProjectBO projectBo = new ProjectBO();
			projectBo.setId(dto.getId());
			projectBo.setAccountCategory(dto.getAccountCategory());
			projectBo.setAccountName(dto.getAccountName());
			projectBo.setProjectName(dto.getProjectName());
			projectBo.setCustomerName(dto.getCustomerName());
			projectBo.setCustomerSpoc(dto.getCustomerSpoc());
			projectBo.setApprovalMethod(dto.getApprovalMethod());
			projectBo.setSubmissionMode(dto.getSubmissionMode());
			projectBo.setProjectType(dto.getProjectType());
			projectBo.setBillingCurrency(dto.getBillingCurrency());
			projectBo.setPoAmount(dto.getPoAmount());
			projectBo.setResourceCount(dto.getResourceCount());
			projectBo.setProjectStartDate(DateUtils.reverseDateParsing(dto.getProjectStartDate().toString()));
			projectBo.setProjectEndDate(DateUtils.reverseDateParsing(dto.getProjectEndDate().toString()));
			projectBo.setUnitOfMeasurement(dto.getUnitOfMeasurement());
			projectBo.setDeliverySpoc(dto.getDeliverySpoc());
			projectBo.setEffortSpoc(dto.getEffortSpoc());
			projectBo.setPid(dto.getPid());
			projectBo.setQuote(dto.getQuote());
			projectBo.setContract(dto.getContract());
			projectBo.setPo(dto.getPo());
			projectBo.setStatus(dto.getStatus());
			projectBoList.add(projectBo);
		}
		return projectBoList;
	}

	@Override
	public Map<String, Object> fetchProjectDetail(String accountCategory, String projectName, String projectType,
			String status) {

		List<ProjectDTO> projectDtoList = new ArrayList<ProjectDTO>();
		List<ProjectBO> projectBoList = new ArrayList<ProjectBO>();
		Map<String, Object> response;
		response = new HashMap<>();

		if (accountCategory.isEmpty() && projectName.isEmpty() && projectType.isEmpty() && status.isEmpty()) {
//			accountCategory = "% %";
//			projectName = "% %";
//			projectType = "% %";
//			status = "% %";
			projectDtoList = projectDetailRepository.findAll();
			if (projectDtoList.size() != 0 && !projectDtoList.isEmpty()) {
				try {
//					projectBoList = mapDtoToBo(projectDtoList);
					if (projectDtoList.size() != 0 && !projectDtoList.isEmpty()) {
						projectBoList = mapDtoToBo(projectDtoList);
						response.put("message", "Project Details fetched Successfully");
						response.put("status", HttpStatus.OK.value());
						response.put("projectDetailsList", projectBoList);
					} else {
						response.put("message", "No data found.");
						response.put("status", HttpStatus.NO_CONTENT.value());
						response.put("projectDetailsList", projectBoList);
					}
				} catch (ParseException e) {
					throw new InvalidServiceException("Exception occured while fetching Project Details.");
				}
			}
			
		} else {
			accountCategory = accountCategory.isEmpty() ? "" : ("%" + accountCategory + "%").toLowerCase();
			projectName = projectName.isEmpty() ? "" : ("%" + projectName + "%").toLowerCase();
			projectType = projectType.isEmpty() ? "" : (projectType).toLowerCase();
			status = status.isEmpty() ? "" : (status).toLowerCase();
		

		try {
			projectDtoList = projectDetailRepository.fetchProjectDetails(accountCategory, projectName, projectType,
					status);
			if (projectDtoList.size() != 0 && !projectDtoList.isEmpty()) {
				projectBoList = mapDtoToBo(projectDtoList);
				response.put("message", "Project Details fetched Successfully");
				response.put("status", HttpStatus.OK.value());
				response.put("projectDetailsList", projectBoList);
			} else {
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
				response.put("projectDetailsList", projectBoList);
			}
		} catch (Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Project Details.");
		}
		}
		return response;
	}

	@Override
	public Map<String, Object> deleteProjectDetail(String id) {
		Map<String, Object> response;
		response = new HashMap<>();

		Integer projectDetailcount =projectDetailRepository.fetchProjectDetailid(Integer.valueOf(id));
		if (projectDetailcount > 0) {
			try {
				projectDetailRepository.deleteProjectDetail(Integer.valueOf(id));
				response.put("message", "Project Details deleted successfully");
				response.put("status", HttpStatus.OK.value());
			} catch (Exception e) {
				throw new InvalidServiceException("Exception occured while deleting Project details.");
			}
		} else {
			response.put("message", "No data found");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}

	@Override
	public Map<String, Object> modifyprojectDetails(ProjectBO projectBo) {
		Optional<ProjectDTO> projectDetailList;
		Map<String, Object> response;
		response = new HashMap<>();

		ProjectDTO pDto = mapBoToDto(projectBo);
		projectDetailList = projectDetailRepository.fetchProjectDetail(pDto.getPid());
		if (projectDetailList.isPresent()) {
			try {
				projectDetailRepository.modifyProjectDetail(pDto.getCustomerSpoc(), pDto.getApprovalMethod(),
						pDto.getSubmissionMode(), pDto.getProjectType(), pDto.getBillingCurrency(), pDto.getPoAmount(),
						pDto.getProjectStartDate(), pDto.getProjectEndDate(), pDto.getStatus(), pDto.getDeliverySpoc(),
						pDto.getEffortSpoc(), pDto.getQuote(), pDto.getContract(), pDto.getPo(), LocalDateTime.now(),
						pDto.getPid(), pDto.getModifiedBy(),pDto.getUnitOfMeasurement(),pDto.getResourceCount());
				if(projectBo.getResources().size() > 0) {
					for (ResourceMap r : projectBo.getResources()) {
						Optional<ResourceMapDTO> rmapList;
						if(r.getResourceMapId().equals(null)) {
							rmapList=resourceMapRepository.findById(r.getResourceMapId());
							if(rmapList.isPresent()) {							
								resourceMapRepository.updateProjectinfo(r.getResourceMapId(),r.getLocation(),DateUtils.parseDate(r.getAssociateStartDate()),DateUtils.parseDate(r.getAssociateEndDate()),r.getLinked(),r.getpId(),r.getRatePerHour());	
							}							
						}
						else
						{
							ResourceMapDTO rdto;
							rdto = rmapBoToDto(r);
							resourceMapRepository.save(rdto);
						}
						
					}
				}
				
				

				response.put("message", "Project Details updated successfully");
				response.put("status", HttpStatus.OK.value());
			} catch (Exception e) {
				throw new InvalidServiceException("Exception occured while updating Project details.");
			}
		} else {
			response.put("message", "No data found");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}

	@Override
	public Map<String, Object> getResourceByPID(String pId) {
		List<ResourceDTO> resourcesList;
		Integer pidcount;
		Map<String, Object> response;
		response = new HashMap<>();

		try {
			pidcount=projectDetailRepository.checkpidscount(pId);
			if(pidcount==0) {
				resourcesList = resourcesRepository.fetchResourcesDetail(pId);
				if (resourcesList.size() > 0) {
					response.put("message", "Resources details fetched successfully.");
					response.put("status", HttpStatus.OK.value());
					response.put("resourceDetails", resourcesList);
				} else {
					response.put("message", "No data found");
					response.put("status", HttpStatus.NO_CONTENT.value());
				}	
			}
			else
			{
				response.put("message", "Already Exists for this PID");
				response.put("status", HttpStatus.CONFLICT.value());
			}
		
		} catch (Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Resources details.");
		}
		return response;
	}

	@Override
	public Map<String, Object> getcResource() {
		List<ResourceDTO> resourcesList;
		Map<String, Object> response;
		response = new HashMap<>();

		try {
			resourcesList = resourcesRepository.fetchcResourcesDetail();
			if (resourcesList.size() > 0) {
				response.put("message", "Contract Resources details fetched successfully.");
				response.put("status", HttpStatus.OK.value());
				response.put("cresourceDetails", resourcesList);
			} else {
				response.put("message", "No data found");
				response.put("status", HttpStatus.NO_CONTENT.value());
			}
		} catch (Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Resources details.");
		}
		return response;
	}

	@Override
	public Map<String, Object> getResourceByPIDonupdate(String pId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
