package com.techm.po.service;

import java.util.Map;
import java.util.Optional;

import com.techm.po.model.bo.ResourceBO;
import com.techm.po.model.dto.ResourceDTO;




public interface ResourceService {

//List<ResourceDTO> showAllResource();

Map<String, Object>  showOneResource(String ac_cat);

//Map<String, Object> deleteResource(String ac_cat);

Map<String, Object> addResource(ResourceBO resourcebo);

Map<String, Object> showAllResource(String associateId, String associateName, String band);

Map<String, Object> modifyResourceDetails(ResourceBO resource);

Map<String, Object> deleteOneResource(String resource);

}



