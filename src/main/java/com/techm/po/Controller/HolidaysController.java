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

import com.techm.po.model.dto.HolidaysDTO;
import com.techm.po.service.HolidayService;

@RestController
@CrossOrigin
@RequestMapping("/holidays")
public class HolidaysController {
	
	@Autowired
	private HolidayService holidayService;
	
	@GetMapping("/fetch")
	public Map<String, Object> fetchHolidaysDetails(@RequestParam(name="accountCategory", required=false) String accountCategory,
													@RequestParam(name="accountName", required=false) String accountName,
													@RequestParam(name="projectName", required=false) String projectName,
	                                                @RequestParam(name="year",required=false) String year){
		Map<String, Object> response;
		response = holidayService.fetchHolidaysDetails(accountCategory,accountName,projectName,year);
		return response;
	}
	

	@PostMapping("/add")
	public Map<String, Object> addHolidayDetails(@RequestBody HolidaysDTO holidaysDto) {
		Map<String, Object> response;
		response = holidayService.addHolidayDetails(holidaysDto);
		return response;
		
	}

	
	@DeleteMapping("/delete")
	public Map<String, Object> deleteHoliday(@RequestParam(name="holidayId", required=false) Integer holidayId) {
		Map<String, Object> response;
		response = holidayService.deleteHoliday(holidayId);
		return response;	
	}

	
	@PutMapping("/modify")
	public Map<String, Object> modifyHolidays(@RequestBody HolidaysDTO holidaysDto) {
		Map<String, Object> response = new HashMap<String, Object>();	
		response = holidayService.modifyHolidays(holidaysDto);
		return response;
	}

}


