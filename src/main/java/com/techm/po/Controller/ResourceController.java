package com.techm.po.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techm.po.dao.ResourceRepository;
import com.techm.po.exception.ResourceNotFoundException;
import com.techm.po.model.bo.ResourceBO;
import com.techm.po.model.dto.ResourceDTO;
import com.techm.po.service.ResourceService;



@RestController
@RequestMapping("/resource")
@CrossOrigin
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ResourceRepository resourceRepository;

	@GetMapping("/fetch")	
	public Map<String, Object> showAllResource(
			@RequestParam(name="associateId" , required=false) String associateId,
			@RequestParam(name="associateName", required=false) String associateName,
			@RequestParam(name="band", required=false) String band
			) {
		Map<String, Object> response;
		response = resourceService.showAllResource(associateId,associateName,band);
		return response;
	}

	@GetMapping("/show-one-resource")
	public Map<String, Object>  showOneAccountCategory(@RequestParam("associateId") String resource){
		
		Map<String, Object> response;
		response = resourceService.showOneResource(resource);
		return response;
	}

	@DeleteMapping("/delete")
	public Map<String, Object> deleteResource(@RequestParam("associateId") String resource) {
			Map<String, Object> response;
		response = resourceService.deleteOneResource(resource);
		return response;

	}

	@PostMapping("/create")
	public Map<String, Object> addResource(@RequestBody ResourceBO resourcebo) {

		Map<String, Object> response;
		response = resourceService.addResource(resourcebo);
		return response;

	}

	
	@PutMapping("/update")
	public Map<String, Object> updateResource(@RequestBody ResourceBO resource) {		
		Map<String, Object> response;
		response = new HashMap<>();
		response=resourceService.modifyResourceDetails(resource);
		return response;
	}
	
//	@GetMapping("/getpid")
//	public ResponseEntity<Object> showPidResources(@RequestParam("pId") String resource){
//		
//		Optional<ResourceDTO> resource1 = resourceRepository.findById(resource);
//		if (!resource1.isPresent()) {
//			throw new ResourceNotFoundException("id-" + resource);
//		} else {
//			return new ResponseEntity<Object>(resource1, HttpStatus.OK);
//		}
//	}

}



