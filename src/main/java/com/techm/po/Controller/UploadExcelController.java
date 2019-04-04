package com.techm.po.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techm.po.service.UploadExcelService;

@RestController
@CrossOrigin
@RequestMapping("/excel")
public class UploadExcelController {
	
	@Autowired
	private UploadExcelService uploadExcelService;
	
	@PostMapping("/export")
	public Map<String, Object> uploadExcelData(@RequestParam("file") MultipartFile reapExcelDataFile){
		
		Map<String, Object> response;
		response = new HashMap<>();		
		response=uploadExcelService.uploadExcelData(reapExcelDataFile);
		return response;
		
	}
}
