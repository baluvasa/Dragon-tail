package com.techm.po.Controller;

import java.util.HashMap;
import java.util.Map;
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
import com.techm.po.model.bo.LeavesBO;
import com.techm.po.model.dto.LeavesDTO;
import com.techm.po.service.LeavesDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/leaves")
public class LeavesDetailsController {
	
	@Autowired
	private LeavesDetailsService leavesDetailsService;
	
	@PostMapping("/create")
	public Map<String, Object> addLeavesDetails(@RequestBody LeavesBO LeavesBo){
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		response=leavesDetailsService.addLeavesDetails(LeavesBo);		
		return response;
	}
	
	@GetMapping("/fetch")
	public Map<String, Object> fetchLeavesDetails(@RequestParam(name="id", required=false) String id,
												  @RequestParam(name="name", required=false) String name,
												  @RequestParam(name="mmyy", required=false) String monthYear){


		Map<String, Object> response;
		response=new HashMap<String, Object>();
						
		response=leavesDetailsService.fetchLeavesDetails(id,name,monthYear);
		
		return response;
	}
	
	@PutMapping("/update")
	public Map<String, Object> updateLeavesDetails(@RequestBody LeavesBO LeavesBo){
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		
		response=leavesDetailsService.updateLeavesDetails(LeavesBo);
		
		return response;
	}		

	@DeleteMapping("/delete")
	public Map<String, Object> deleteLeavesDetails(@RequestParam(name="id") Integer id){
		
		Map<String, Object> response;
		response=new HashMap<String, Object>();
		response=leavesDetailsService.deleteLeavesDetails(id);
		
		return response;
	}
	
}
