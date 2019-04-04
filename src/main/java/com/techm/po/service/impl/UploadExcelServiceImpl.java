package com.techm.po.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techm.po.dao.UploadExcelRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.dto.ResourceDTO;
import com.techm.po.service.UploadExcelService;

@Service
public class UploadExcelServiceImpl implements UploadExcelService {
	
	@Autowired
	private ResourceServiceImpl resourceServiceImpl;
	
	@Autowired
	private UploadExcelRepository uploadExcelRepository;
	
	@Override
	public Map<String, Object> uploadExcelData(MultipartFile reapExcelDataFile) {
		Map<String, Object> response;
		response = new HashMap<>();
		
		try {			
			
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			
			for(int i=0;i<worksheet.getPhysicalNumberOfRows();i++) {
				ResourceDTO resourceDTOObj=new ResourceDTO();
				XSSFRow row=worksheet.getRow(i);
				resourceDTOObj.setAssociateId(row.getCell(0).toString());
				resourceDTOObj.setAssociateName(row.getCell(1).toString());
				resourceDTOObj.setBand(row.getCell(2).toString());
				//resourceDTOObj.setContactNumber(Long.parseLong(row.getCell(3)));
				resourceDTOObj.setCreatedBy(row.getCell(4).toString());
				//resourceDTOObj.setCreatedDate(row.getCell(5));
				resourceDTOObj.setEmailId(row.getCell(6).toString());
				resourceDTOObj.setModifiedBy(row.getCell(7).toString());
				//resourceDTOObj.setModifiedDate(row.getCell(8));
				resourceDTOObj.setpId(row.getCell(9).toString());
				resourceDTOObj.setStatus(row.getCell(10).toString());
				try {
					uploadExcelRepository.save(resourceDTOObj);
					response.put("message", "Resouce details created successfully");
					response.put("status", HttpStatus.CREATED.value());
				}catch(Exception e) {
					throw new InvalidServiceException("Exception occured while adding Resouce details.");
				}
				
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return response;
	}

}
