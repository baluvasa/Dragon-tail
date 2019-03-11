package com.techm.po.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.techm.po.dao.HolidayRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.AccessDetailsBO;
import com.techm.po.model.bo.DefaultEnums;
import com.techm.po.model.dto.AccessDetailsDTO;
import com.techm.po.model.dto.HolidaysDTO;
import com.techm.po.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {

	@Autowired
	private HolidayRepository holidaysRepository;

	@Override
	public Map<String, Object> fetchHolidaysDetails(String accountCategory, String accountName, String projectName, String year) {
	
		Map<String, Object> response;
		response = new HashMap<>();
		List<HolidaysDTO> holidaysDetailsList;
		holidaysDetailsList=new ArrayList<HolidaysDTO>();
		holidaysDetailsList = new ArrayList<HolidaysDTO>();
		
//		if(accountCategory.isEmpty() && accountName.isEmpty() &&projectName.isEmpty()&&year.isEmpty())
//		{
//			accountCategory="% %";
//			accountName="% %";
//			projectName="% %";
//			year="% %";
//		}
//		else
//		{
//			
//		}
		
		
		
		
		
	
		if (StringUtils.isEmpty(accountCategory) && StringUtils.isEmpty(accountName) && StringUtils.isEmpty(projectName)
							&& StringUtils.isEmpty(year)) {
			try {
				holidaysDetailsList = holidaysRepository.findAll();
				if (holidaysDetailsList.size() != 0 && !holidaysDetailsList.isEmpty()) {
					response.put("message", "holidays details fetched successfully");
					response.put("status", HttpStatus.OK.value());
					response.put("holidaysDetails", holidaysDetailsList);
				} else {
					response.put("message", "No data found");
					response.put("status", HttpStatus.NO_CONTENT.value());
					response.put("accessDetails", holidaysDetailsList);
				}
			} catch (Exception e) {
				throw new InvalidServiceException("Error occured while fetching Holidays");
			}
		} else {
			
			accountCategory = accountCategory.isEmpty() ? "": ("%"+accountCategory+"%").toLowerCase();
			accountName = accountName.isEmpty() ? "": ("%"+accountName+"%").toLowerCase();
			projectName = projectName.isEmpty() ? "": ("%"+projectName+"%").toLowerCase();
			year = year.isEmpty() ? "": ("%"+year+"%").toLowerCase();
			
			try {
				holidaysDetailsList = holidaysRepository.fetchHolidaysDetails(accountCategory, accountName, projectName, year);
				if (holidaysDetailsList.size() != 0 && !holidaysDetailsList.isEmpty()) {
					response.put("message", "holidays details fetched successfully");
					response.put("status", HttpStatus.OK.value());
					response.put("holidaysDetails", holidaysDetailsList);
				} else {
					response.put("message", "No data found");
					response.put("status", HttpStatus.NO_CONTENT.value());
					response.put("accessDetails", holidaysDetailsList);
				}
			} 
			catch (Exception e) {
				throw new InvalidServiceException("Error occured while fetching Holidays");
			}
		}
		return response;
	}


	@Override
	public Map<String, Object> addHolidayDetails(HolidaysDTO holidaysDto) {
		Map<String, Object> response;
		response = new HashMap<>();
		Optional<HolidaysDTO> holidayData;
		holidayData=holidaysRepository.fetchHolidaysDetailsExists(holidaysDto.getAccountCategory(),holidaysDto.getAccountName(),holidaysDto.getProjectName(),holidaysDto.getYear());
		if(holidayData.isPresent()) {
			response.put("message", "Details already exist for the given Project for given year.");
			response.put("status", HttpStatus.CONFLICT.value());
		}
		else
		{
			try {
				holidaysDto.setStatus(DefaultEnums.ACTIVE.name());
				holidaysDto.setCreatedDate(LocalDateTime.now());
				holidaysRepository.save(holidaysDto);
				response.put("message", "Holidays details created successfully");
				response.put("status", HttpStatus.CREATED.value());
			} catch (Exception e) {
				throw new InvalidServiceException("Exception occured while adding Holidays details.");
			}	
		}
		

		return response;
	}

	
	@Override
	public Map<String, Object> deleteHoliday(Integer holidayId) {
		Optional<HolidaysDTO> holidayData = null;
		Map<String, Object> response;
		response = new HashMap<>();

		HolidaysDTO holidaysDetailsObj=null;
		
		holidayData = holidaysRepository.findHolidaysDetails(holidayId);
		
		if (holidayData.isPresent()) {
			try {
				//holidaysRepository.deleteById(id);
				holidaysDetailsObj=holidayData.get();
				holidaysDetailsObj.setModifiedDate(LocalDateTime.now());
				holidaysDetailsObj.setStatus(DefaultEnums.INACTIVE.name());
				holidaysRepository.save(holidaysDetailsObj);
				response.put("message", "holidays deleted successfully");
				response.put("status", HttpStatus.OK.value());
			} catch (Exception e) {
				throw new InvalidServiceException("Exception occured while deleting Holidays details.");
			}
		} else {
			response.put("message", "No data found");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}

	
	@Override
	public Map<String, Object> modifyHolidays(HolidaysDTO holidaysDto){

		Optional<HolidaysDTO> holidaysData;
		Map<String, Object> response;
		HolidaysDTO holidaysDetailsObj=null;
		response=new HashMap<String, Object>();
		holidaysData = holidaysRepository.findHolidaysDetails(holidaysDto.getHolidayId());
		if(holidaysData.isPresent()) {
			try {
				
				holidaysDetailsObj=holidaysData.get();
				holidaysDetailsObj.setModifiedDate(LocalDateTime.now());
				holidaysDetailsObj.setModifiedBy(holidaysDto.getModifiedBy());
				holidaysDetailsObj.setYear(holidaysDto.getYear());
				holidaysDetailsObj.setJanuary(holidaysDto.getJanuary());
				holidaysDetailsObj.setFebruary(holidaysDto.getFebruary());
				holidaysDetailsObj.setMarch(holidaysDto.getMarch());
				holidaysDetailsObj.setApril(holidaysDto.getApril());
				holidaysDetailsObj.setMay(holidaysDto.getMay());
				holidaysDetailsObj.setJune(holidaysDto.getJune());
				holidaysDetailsObj.setJuly(holidaysDto.getJuly());
				holidaysDetailsObj.setAugust(holidaysDto.getAugust());
				holidaysDetailsObj.setSeptember(holidaysDto.getSeptember());
				holidaysDetailsObj.setOctober(holidaysDto.getOctober());
				holidaysDetailsObj.setNovember(holidaysDto.getNovember());
				holidaysDetailsObj.setDecember(holidaysDto.getDecember());
				System.out.println("holidaysDetailsObj"+holidaysDetailsObj);
				holidaysRepository.save(holidaysDetailsObj);
				response.put("message", "Access details updated successfully");
				response.put("status", HttpStatus.OK.value());
				//response.put("accessDetails", accessDetailsObj);
				
			}catch(Exception e) {
				throw new InvalidServiceException("Error while updating Access Details");
			}
		}else {
			response.put("message", "Access details are not found");
			response.put("status", HttpStatus.NO_CONTENT.value());
			//response.put("accessDetails", accessDetailsObj);
		}
		
		return response;
		
	}
}

