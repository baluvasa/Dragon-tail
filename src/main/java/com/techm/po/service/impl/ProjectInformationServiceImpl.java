package com.techm.po.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techm.po.dao.ProjectInformationRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.ProjectBO;
import com.techm.po.model.dto.ProjectDTO;
import com.techm.po.service.ProjectInformationService;

@Service
public class ProjectInformationServiceImpl implements ProjectInformationService{
	
	@Autowired
	private ProjectInformationRepository projectInformationRepository;
	
	@Autowired
	private ProjectDetailServiceImpl projectDetailServiceImpl;
	

	@Override
	public Map<String, Object> fetchAccountCategory() {
		Map<String, Object> response;
		List<String> accountCategoriesList;
		response = new HashMap<String, Object>();
		try {
			accountCategoriesList = projectInformationRepository.fetchAccountCategories();
			if(accountCategoriesList.size() > 0) {
				response.put("accountCategoryList", accountCategoriesList);
				response.put("status", HttpStatus.OK.value());
			}
			
			else {
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
			}			
		}
		catch (Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Account Category details.");
		}
		return response;
	}



	@Override
	public Map<String, Object> fetchprojectInfo(String accountCategory) {
		Map<String, Object> response;
		List<ProjectDTO> projectInfoList;
		List<ProjectBO> projectBOList;
		response = new HashMap<String, Object>();
		try {
			projectInfoList = projectInformationRepository.fetchprojectInfo(accountCategory);
			if(projectInfoList.size() > 0) {
				projectDetailServiceImpl = new ProjectDetailServiceImpl();
				projectBOList = projectDetailServiceImpl.mapDtoToBo(projectInfoList);
				response.put("projectInfoList", projectBOList);
				response.put("status", HttpStatus.OK.value());
			}
			
			else {
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
			}			
		}
		catch (Exception e) {
			throw new InvalidServiceException("Exception occured while fetching Project Infornation details.");
		}
		return response;
	}

	
	
//	@Override
//	public Map<String, Object> addPoApprovalDetails(PoApprovalsDTO approvalsDto) {
//		Optional<PoApprovalsDTO> approvalData;
//		Map<String, Object> response;
//		response = new HashMap<>();
//		
//		approvalData = poApprovalRepository.fetchPoApprovalsByMonthYear(approvalsDto.getAssociateId(), approvalsDto.getMonthYear());
//		if (approvalData.isPresent()) {
//			throw new InvalidServiceException("PO is already generated for the given month and year.");
//		} else {
//				try {
//					approvalsDto.setStatus(DefaultEnums.ACTIVE.name());
//					approvalsDto.setCreatedDate(LocalDateTime.now());
//					poApprovalRepository.save(approvalsDto);
//					response.put("message", "PO Approval details added successfully");
//					response.put("status", HttpStatus.CREATED.value());
//				} catch (Exception e) {
//					
//					throw new InvalidServiceException("Exception occured while adding PO Approval details.");
//				}
//			} 
//		return response;
//	}
//
//
//	@Override
//	public Map<String, Object> fetchProjectByCategory(String accountCategory, String accountName) {
//		Map<String, Object> response;
//		Optional<ProjectDTO> projectData;
//		ProjectBO projectBo;
//		List<ResourceDTO> resourcesDtoList;
//		
//		response = new HashMap<String, Object>();
//		try {
//			projectData = projectRepository.fetchProjectDetails(accountCategory, accountName);
//			resourcesDtoList = resourcesRepository.fetchResourceDetailsbyID(projectData.get().getPid());
//			if(projectData.isPresent()) {
//				projectBo = mapDtoToBo(projectData.get());
//				response.put("projectDetails", projectBo);
//			}
//			if(resourcesDtoList.size() != 0 && !resourcesDtoList.isEmpty()) {
//				response.put("resourcesDetails", resourcesDtoList);
//			}
//			else {
//				response.put("message", "No data found.");
//				response.put("status", HttpStatus.NO_CONTENT.value());
//			}			
//		}
//		catch (Exception e) {
//			throw new InvalidServiceException("Exception occured while fetching project and associate details.");
//		}
//		return response;
//	}
//
//	
//	private ProjectBO mapDtoToBo(ProjectDTO dto) throws ParseException {
//		ProjectBO bo;
//		bo = new ProjectBO();
//		bo.setAccountCategory(dto.getAccountCategory());
//		bo.setAccountName(dto.getAccountName());
//		bo.setProjectName(dto.getProjectName());
//		String startDate = DateUtils.reverseDateParsing(dto.getProjectStartDate().toString());
//		String endDate = DateUtils.reverseDateParsing(dto.getProjectEndDate().toString());
//		bo.setProjectStartDate(startDate);
//		bo.setProjectEndDate(endDate);
//		bo.setCustomerName(dto.getCustomerName());
//		bo.setProjectBillingCurrency(dto.getBillingCurrency());
//		return bo;	
//	}
}
