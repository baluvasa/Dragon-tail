package com.techm.po.service;

import java.util.Map;

import com.techm.po.model.dto.AccessDetailsDTO;

public interface AccessDetailsService {

	Map<String, Object> addAccessDetails(AccessDetailsDTO accessDto);
	
	Map<String, Object> fetchAccessDetails(String id, String name, String type, String active);
	
	//Map<String, Object> modifyAccessDetails(AccessDetailsDTO accessDto);
	Map<String, Object> modifyAccessDetails(AccessDetailsDTO accessDto);
	
	Map<String, Object> deleteAccessDetails(String id);	
}
