package com.techm.po.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techm.po.dao.AccessDetailsRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.exception.ResourceNotFoundException;
import com.techm.po.model.bo.AccessDetailsBO;
import com.techm.po.model.bo.DefaultEnums;
import com.techm.po.model.dto.AccessDetailsDTO;
import com.techm.po.service.AccessDetailsService;

@Service
public class AccessDetailsServiceImpl implements AccessDetailsService {

	//private final String password = "PO@123456";
	
	@Value("${po.default.password}")
	private String password;
	
	@Autowired
	private AccessDetailsRepository accessDetailsRepository;
	
	@Transactional
	@Override
	public Map<String, Object> addAccessDetails(AccessDetailsDTO accessDetailsDTO) {
		
		Optional<AccessDetailsDTO> accessData;
		Map<String, Object> response;
		response = new HashMap<>();
		accessData=accessDetailsRepository.fetchAssociate(accessDetailsDTO.getGid());
		
		if(accessData.isPresent()) {
			response.put("message", "Access Details already exist for the given associate id.");
			response.put("status", HttpStatus.CONFLICT.value());
			return response;
		}
		else {
			try {
				accessDetailsDTO.setGid(accessDetailsDTO.getGid().toUpperCase());
				accessDetailsDTO.setAssociateName(accessDetailsDTO.getAssociateName().toUpperCase());
				accessDetailsDTO.setStatus(DefaultEnums.ACTIVE.name());
				accessDetailsDTO.setRole(accessDetailsDTO.getRole());
				
				accessDetailsDTO.setPassword(password);
				accessDetailsDTO.setCreatedBy(accessDetailsDTO.getCreatedBy());
				accessDetailsDTO.setCreatedDate(LocalDateTime.now());
				accessDetailsRepository.save(accessDetailsDTO);
				response.put("message", "Access details created successfully");
				response.put("status", HttpStatus.CREATED.value());
				return response;
			}
			catch (Exception e) {
				throw new InvalidServiceException("Exception occured while adding resource.");
			}
		}
		
	}
	
	@Transactional
	@Override
	public Map<String, Object> fetchAccessDetails(String id, String name, String type, String status) {
		
		List<AccessDetailsDTO> accessDetailsList;
		List<AccessDetailsBO> accessBoList;
		accessDetailsList=new ArrayList<AccessDetailsDTO>();
		accessBoList = new ArrayList<AccessDetailsBO>();
		Map<String, Object> response;
		response = new HashMap<>();	
	
		if(id.isEmpty() && name.isEmpty() &&type.isEmpty()&&status.isEmpty())
		{

			accessDetailsList=accessDetailsRepository.findAll();	
			if(accessDetailsList.size()!=0 && !accessDetailsList.isEmpty()) {
				accessBoList = mapDtoToBo(accessDetailsList);
				response.put("message","Access Details fetched Successfully");
				response.put("status", HttpStatus.OK.value());
				response.put("accessdetails", accessDetailsList);
			}else {
				response.put("message", "No data found.");
				response.put("status", HttpStatus.NO_CONTENT.value());
				response.put("accessdetails", accessDetailsList);
			}
		}
		else
		{	
			id = id.isEmpty() ? "": ("%"+id+"%").toLowerCase();
			name = name.isEmpty() ? "": ("%"+name+"%").toLowerCase();
			type = type.isEmpty() ? "": ("%"+type+"%").toLowerCase();
			status = status.isEmpty() ? "": ("%"+status+"%").toLowerCase();
			
			try {				
				accessDetailsList=accessDetailsRepository.fetchAccessDetails(id,name,type,status);				
				//accessDetailsObj=accessDetailsRepository.getOne(id);
				if(accessDetailsList.size()!=0 && !accessDetailsList.isEmpty()) {
					accessBoList = mapDtoToBo(accessDetailsList);
					response.put("message","Access Details fetched Successfully");
					response.put("status", HttpStatus.OK.value());
					response.put("accessdetails", accessDetailsList);
				}else {
					response.put("message", "No data found.");
					response.put("status", HttpStatus.NO_CONTENT.value());
					response.put("accessdetails", accessDetailsList);
				}
			}
			catch(Exception e) {
				throw new InvalidServiceException("Exception occured while fetching Access Details.");
			}
		}	
			return response;
		}		
	/*return response;
	}*/
	
	
	@Transactional
	@Override
	public Map<String, Object> modifyAccessDetails(AccessDetailsDTO accessDto){
	
		Optional<AccessDetailsDTO> accessData;
		Map<String, Object> response;
		AccessDetailsDTO accessDetailsObj=null;
		response=new HashMap<String, Object>();
		accessData=accessDetailsRepository.findById(accessDto.getGid());		
		if(accessData.isPresent()) {
			try {
				
				accessDetailsObj=accessData.get();
								
				accessDetailsObj.setAssociateName(accessDto.getAssociateName().toUpperCase());
				accessDetailsObj.setAccessType(accessDto.getAccessType());
				accessDetailsObj.setStatus(accessDto.getStatus());
				accessDetailsObj.setModifiedDate(LocalDateTime.now());
				accessDetailsObj.setModifiedBy(accessDto.getModifiedBy());
				
				accessDetailsRepository.save(accessDetailsObj);
				response.put("message", "Access details updated successfully");
				response.put("status", HttpStatus.OK.value());
				//response.put("accessDetails", accessDetailsObj);
				
			}catch(Exception e) {
				throw new InvalidServiceException("Error while updating Access Details");
			}
		}else {
			response.put("message", "Access details are not found");
			response.put("status", HttpStatus.NO_CONTENT.value());
			//response.put("accessDetails", accessDetailsObj);
		}
		
		return response;
	}
	
	
	@Transactional
	@Override
	public Map<String, Object> deleteAccessDetails(String id){
		
		Optional<AccessDetailsDTO> accessData;
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		
		accessData=accessDetailsRepository.findById(id);
		if(accessData.isPresent()) {
			try {
				accessDetailsRepository.deleteById(id);
				response.put("message","Access Details Deleted Successfully");
				response.put("Status",HttpStatus.OK.value());
			}
			catch(Exception e) {
				
			}
		}else {
			response.put("message","No details found for entered Associate Id");
			response.put("Status",HttpStatus.NO_CONTENT.value());
		}
		return response;
	}
	
	private List<AccessDetailsBO> mapDtoToBo(List<AccessDetailsDTO> dtoList){
		List<AccessDetailsBO> boList;
		AccessDetailsBO bo;
		boList = new ArrayList<AccessDetailsBO>();
		for(AccessDetailsDTO dto : dtoList) {
			bo = new AccessDetailsBO();
			bo.setGid(dto.getGid());
			bo.setAssociateName(dto.getAssociateName());
			bo.setAccessType(dto.getAccessType());
			bo.setStatus(dto.getStatus());
			boList.add(bo);
		}
		return boList;
	}
	
}
