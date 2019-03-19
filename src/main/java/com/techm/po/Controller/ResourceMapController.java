package com.techm.po.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techm.po.model.bo.ResourceMapBO;
import com.techm.po.service.ResourceMapService;

@RestController
@CrossOrigin
@RequestMapping("/resourcemap")
public class ResourceMapController {
	
	@Autowired
	private ResourceMapService resourceMapService;
	
	@PostMapping("/add")
	public Map<String, Object> addAccessDetails(@RequestBody ResourceMapBO resourceMapBo){
		
		Map<String, Object> response;
		response = new HashMap<>();
		response=resourceMapService.mapResources(resourceMapBo);		
		return response;
	}	
	
}
