package com.techm.po.service.impl;

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
import org.springframework.util.StringUtils;

import com.techm.po.dao.ResourceRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.DefaultEnums;
import com.techm.po.model.bo.ResourceBO;
import com.techm.po.model.dto.ResourceDTO;
import com.techm.po.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	@Transactional
	public Map<String, Object> showAllResource(String associateId, String associateName, String band) {
		List<ResourceDTO> resourceList;
		resourceList = new ArrayList<ResourceDTO>();
		Map<String, Object> response;
		response = new HashMap<>();

		if (StringUtils.isEmpty(associateName) && StringUtils.isEmpty(associateId) && StringUtils.isEmpty(band)) {

			try {
				resourceList = resourceRepository.findAll();
				if (resourceList.size() != 0 && !resourceList.isEmpty()) {
					response.put("message", "Resource Details fetched Successfully");
					response.put("status", HttpStatus.OK.value());
					response.put("resourcedetails", resourceList);
				} else {
					response.put("message", "No data found.");
					response.put("status", HttpStatus.NO_CONTENT.value());
					response.put("resourcedetails", resourceList);
				}
			} catch (Exception e) {
				throw new InvalidServiceException("Exception occured while fetching Resource Details.");
			}
		} else {
			
			
			associateName = associateName.isEmpty() ? "": ("%"+associateName+"%").toLowerCase();
			associateId = associateId.isEmpty() ? "": ("%"+associateId+"%").toLowerCase();
			band = band.isEmpty() ? "": ("%"+band+"%").toLowerCase();
//			if (associateName == "") {
//
//				associateName = "%null%";
//			} else {
//				associateName = "%" + associateName + "%";
//			}
//			if (associateId == "") {
//
//				associateId = "%null%";
//			} else {
//				associateId = "%" + associateId + "%";
//			}
//			if (band == "") {
//
//				band = "%null%";
//			} else {
//				band = "%" + band + "%";
//			}
			try {
				resourceList = resourceRepository.fetchResourceDetails(associateId, associateName, band);
				if (resourceList.size() != 0 && !resourceList.isEmpty()) {
					response.put("message", "Resource Details fetched Successfully");
					response.put("status", HttpStatus.OK.value());
					response.put("resourcedetails", resourceList);
				} else {
					response.put("message", "No data found.");
					response.put("status", HttpStatus.NO_CONTENT.value());
					response.put("resourcedetails", resourceList);
				}
			} catch (Exception e) {
				throw new InvalidServiceException("Exception occured while fetching Access Details.");
			}
		}
		return response;
// return resourceRepository.findAll();

	}

	@Override
	public Optional<ResourceDTO> showOneResource(String ac_cat) {
		return resourceRepository.findById(ac_cat);
	}

	@Override
	public void deleteResource(String ac_cat) {
		resourceRepository.deleteById(ac_cat);
	}

	@Transactional
	@Override
	public Map<String, Object> addResource(ResourceBO resourcebo){
	Map<String, Object> response;
	response = new HashMap<>();
	Optional<ResourceDTO> resource1 = resourceRepository.findById(resourcebo.getAssociateId());
	if (resource1.isPresent()) {
	response.put("message", "Associate ID "+resourcebo.getAssociateId()+" already exists !");
	response.put("status", HttpStatus.CONFLICT.value()); 
	} else {

	try {
	ResourceDTO resource11 = new ResourceDTO();
	resource11.setAssociateId(resourcebo.getAssociateId());
	resource11.setAssociateName(resourcebo.getAssociateName());
	resource11.setContactNumber(resourcebo.getContactNumber());
	resource11.setBand(resourcebo.getBand());
	resource11.setEmailId(resourcebo.getEmailId());
	resource11.setpId(resourcebo.getpId());
	resource11.setCreatedBy(resourcebo.getCreatedBy());
	resource11.setCreatedDate(LocalDateTime.now());
	resource11.setStatus(DefaultEnums.ACTIVE.name());
	resourceRepository.save(resource11);
	response.put("message", "Resouce details created successfully");
	response.put("status", HttpStatus.CREATED.value());

	} catch (Exception e) {
	throw new InvalidServiceException("Exception occured while adding Resouce details.");
	}
	}
	return response;
	}

	@Transactional
	@Override
	public Map<String, Object> modifyResourceDetails(ResourceBO resBo) {
		Optional<ResourceDTO> resData;
		Map<String, Object> response;
		ResourceDTO resDetailsObj = null;
		response = new HashMap<String, Object>();
		resData = resourceRepository.findById(resBo.getAssociateId());
		if (resData.isPresent()) {
			try {
				resDetailsObj = resData.get();
				resDetailsObj.setAssociateName(resBo.getAssociateName());
				resDetailsObj.setBand(resBo.getBand());
				resDetailsObj.setContactNumber(resBo.getContactNumber());
				resDetailsObj.setEmailId(resBo.getEmailId());
				resDetailsObj.setpId(resBo.getpId());
				
				resDetailsObj.setModifiedBy(resBo.getModifiedBy());
				resDetailsObj.setModifiedDate(LocalDateTime.now());
				resourceRepository.save(resDetailsObj);
				response.put("message", "Resource details updated successfully");
				response.put("status", HttpStatus.OK.value());
			} catch (Exception e) {
				throw new InvalidServiceException("Error while updating Access Details");
			}
		} else {
			response.put("message", "Resource details are not found");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}
}