package com.techm.po.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.techm.po.dao.ResourceMapRepository;
import com.techm.po.dao.ResourceRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.ResourceMap;
import com.techm.po.model.bo.ResourceMapBO;
import com.techm.po.model.dto.ProjectDTO;
import com.techm.po.model.dto.ResourceMapDTO;
import com.techm.po.service.ResourceMapService;
import com.techm.po.utils.DateUtils;

@Service
public class ResourceMapServiceImpl implements ResourceMapService{
	@Autowired
	private ResourceRepository resourcesRepository;
	@Autowired
	private ResourceMapRepository resourceMapRepository;
	
		private ResourceMapDTO rmapBoToDto(ResourceMap r) {
		ResourceMapDTO rDto = new ResourceMapDTO();
		rDto.setAssociateId(r.getAssociateId());
		rDto.setLocation(r.getLocation());
		rDto.setAssociateStartDate(DateUtils.parseDate(r.getAssociateStartDate()));
		rDto.setAssociateEndDate(DateUtils.parseDate(r.getAssociateEndDate()));
		rDto.setRatePerHour(r.getRatePerHour());
		rDto.setLinked(r.getLinked());
		rDto.setpId(r.getpId());		
		rDto.setContractId(r.getContractId());
		return rDto;
	}
	@Override
	public Map<String, Object> mapResources(ResourceMapBO resourceMapBo) {
		Map<String, Object> response = new HashMap<>();
		try {
			
			if (resourceMapBo.getResources().size() > 0) {
				for (ResourceMap r : resourceMapBo.getResources()) {
					ResourceMapDTO rdto_single;
					rdto_single = rmapBoToDto(r);
					Optional<ResourceMapDTO> resourceDetailList;
					
					resourceDetailList=resourceMapRepository.fetchResourceInfo(rdto_single.getAssociateId().toLowerCase(),rdto_single.getContractId().toLowerCase());
					if(!resourceDetailList.isPresent()) {
						resourceMapRepository.save(rdto_single);						
					}
				}
			}
			if(resourceMapBo.getContractToPid().size()>0) {
				for (ResourceMap r : resourceMapBo.getContractToPid()) {
					ResourceMapDTO rdto_single_c;
					rdto_single_c = rmapBoToDto(r);
					resourcesRepository.updatecontractpid(rdto_single_c.getAssociateId(),rdto_single_c.getpId());
				}
			}
			response.put("message", "Resource Details added successfully");
			response.put("status", HttpStatus.CREATED.value());
			
		} catch (Exception e) {
			throw new InvalidServiceException("error occured");
		}
		return response;
	}

	
}
