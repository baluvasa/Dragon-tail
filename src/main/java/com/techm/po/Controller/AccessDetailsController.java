package com.techm.po.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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

import com.techm.po.model.dto.AccessDetailsDTO;
import com.techm.po.service.AccessDetailsService;

/**
 * 
 * @author Anji
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/access")
public class AccessDetailsController {

	@Autowired
	private AccessDetailsService accessDetailsService;
	
	/**
	 * This API is for Fetching the user Access Details 
	 * @param id of type {@link String}, ID of the associate
	 * @param name of type {@link String}, name of the associate
	 * @param type of type {@link String}, type of the associate
	 * @return
	 */
	@GetMapping("/fetch")
	public Map<String, Object> fetchAccessDetails(@RequestParam(name="gid", required=false) String id,
													@RequestParam(name="name", required=false) String name,
													@RequestParam(name="type", required=false) String type,
													@RequestParam(name="status", required=false) String status){
		Map<String, Object> response;
		response = new HashMap<>();		
		response = accessDetailsService.fetchAccessDetails(id, name, type, status);		
		return response;
	}
	
	
	/**
	 * This API is for Adding Access Details
	 * @param accessDto of type {@link AccessDetailsDTO}
	 * @return
	 */
	@PostMapping("/add")
	public Map<String, Object> addAccessDetails(@RequestBody AccessDetailsDTO accessDto){
		
		Map<String, Object> response;
		response = new HashMap<>();
		response=accessDetailsService.addAccessDetails(accessDto);
		
		return response;
	}	
	
	
	@PutMapping("/update")
	public Map<String, Object> modifyAccessDetails(@RequestBody AccessDetailsDTO accessDto){
		
		Map<String, Object> response;
		response = new HashMap<>();
		response=accessDetailsService.modifyAccessDetails(accessDto);
		return response;
	}
	
	
	@DeleteMapping("/delete")
	public Map<String, Object> deleteAccessDetails(@RequestParam(name="gid", required=true) String id){
		
		Map<String,Object> response;
		response = new HashMap<>();		
		response=accessDetailsService.deleteAccessDetails(id);
		return response;
	}
	
}
