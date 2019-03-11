package com.techm.po.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.techm.po.dao.FxRatesRepository;
import com.techm.po.exception.InvalidServiceException;
import com.techm.po.model.bo.FxRatesBO;
import com.techm.po.model.dto.FxRatesDTO;
import com.techm.po.service.FxRatesService;

@Service
public class FxRatesServiceImpl implements FxRatesService{
	
	@Autowired
	private FxRatesRepository fxRatesRepository;
	
	@Transactional
	@Override
	public Map<String, Object> addFxRateDetails(FxRatesBO fxRatesBO) {
	
		FxRatesDTO fxRates;
		Map<String, Object> response;
		String fxRateId;
		
		response = new HashMap<>();
		fxRates = mapDtoToBo(fxRatesBO);
		try {
			fxRateId = fxRatesRepository.fetchFxRateId(fxRatesBO.getCurrencyCode(), parseDate(fxRatesBO.getFxDate()));
			if(null == fxRateId) {
				
				fxRatesRepository.save(fxRates);
				response.put("message", "FxRates added successfully");
				response.put("status", HttpStatus.CREATED.value());
			}
			else {
				fxRatesRepository.changeStatusFxRate(fxRatesBO.getCurrencyCode(), parseDate(fxRatesBO.getFxDate()));
				fxRatesRepository.save(fxRates);
				response.put("message", "FxRates added successfully");
				response.put("status", HttpStatus.CREATED.value());
			}
		}
		catch (Exception e) {
			throw new InvalidServiceException("error occured");
		}
		return response;
	}
	
	
	private FxRatesDTO mapDtoToBo(FxRatesBO fxRatesBO) {
		FxRatesDTO dto = new FxRatesDTO();
		String id;
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
		
		if(null != fxRatesBO.getFxDate()) {
			LocalDate localDate = LocalDate.parse(fxRatesBO.getFxDate(), formatter);
			//System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+localDate);
            dto.setFxDate(localDate);
		}
		id = UUID.randomUUID().toString();
		dto.setCurrencyCode(fxRatesBO.getCurrencyCode());
		dto.setFxId(id.substring(0,7));
		dto.setFxRate(fxRatesBO.getFxRate());
		dto.setCreatedBy(fxRatesBO.getCreatedBy());
		dto.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
		dto.setStatus("ACTIVE");
		return dto;
	}
	
	private LocalDate parseDate(String date) {
		LocalDate parsedDate = null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		
		if(null != date) {
			LocalDate localDate = LocalDate.parse(date, formatter);
			parsedDate = localDate;
		}
		return parsedDate;
	}
	
	@Override
	public Map<String, Object> fetchFxRatesDetails(String countryCode, String date) {
		List<FxRatesDTO> fxRatesDtoList;
		List<FxRatesBO> fxRatesBoList;
		Map<String, Object> response;
		response = new HashMap<>();
		fxRatesBoList = new ArrayList<FxRatesBO>();
		System.out.println("countryCode: "+countryCode);
		System.out.println("date: "+date);
//		if(countryCode == null) {
//			countryCode = "";
//		}
//		if(date == null) {
//			date = "";
//		}
		System.out.println("after countryCode: "+countryCode);
		System.out.println("after date: "+date);
		if (StringUtils.isEmpty(countryCode) && StringUtils.isEmpty(date)) {
			try {
				fxRatesDtoList = fxRatesRepository.fetchFxRates();
				if (fxRatesDtoList.size() != 0 && !fxRatesDtoList.isEmpty()) {
					fxRatesBoList = mapBoToDto(fxRatesDtoList);
					response.put("message", "FxRates details fetched successfully.");
					response.put("status", HttpStatus.OK.value());
					response.put("fxRates", fxRatesBoList);
				} else {
					response.put("message", "No data found.");
					response.put("status", HttpStatus.NO_CONTENT.value());
					response.put("fxRates", fxRatesBoList);
				}
			}
			catch (Exception e) {
				throw new InvalidServiceException("Exception occured while fetching Account Category.");
			}
		}
		else {
			try {
				
				if(countryCode != null && countryCode !="" && date != null && date != "") {
					LocalDate localDate=null;
					localDate = parseDate(date.toString());
					fxRatesDtoList = fxRatesRepository.fetchFxRatesByCountryCodeAndDate(countryCode, localDate);
					
					if (fxRatesDtoList.size() != 0 && !fxRatesDtoList.isEmpty()) {
						fxRatesBoList = mapBoToDto(fxRatesDtoList);
						response.put("message", "FxRates details fetched successfully.");
						response.put("status", HttpStatus.OK.value());
						response.put("fxRates", fxRatesBoList);
					} else {
						response.put("message", "No data found.");
						response.put("status", HttpStatus.NO_CONTENT.value());
						response.put("fxRates", fxRatesBoList);
					}
				} else if(countryCode != null && (date == null || date == "")) {
					fxRatesDtoList = fxRatesRepository.fetchFxRatesByCountryCode(countryCode);
					
					if (fxRatesDtoList.size() != 0 && !fxRatesDtoList.isEmpty()) {
						fxRatesBoList = mapBoToDto(fxRatesDtoList);
						response.put("message", "FxRates details fetched successfully.");
						response.put("status", HttpStatus.OK.value());
						response.put("fxRates", fxRatesBoList);
					} else {
						response.put("message", "No data found.");
						response.put("status", HttpStatus.NO_CONTENT.value());
						response.put("fxRates", fxRatesBoList);
					}
				} else if(date != null && (countryCode == null || countryCode == "")) {
					LocalDate localDate=null;
					localDate = parseDate(date.toString());
					fxRatesDtoList = fxRatesRepository.fetchFxRatesByDate(localDate);
					
					if (fxRatesDtoList.size() != 0 && !fxRatesDtoList.isEmpty()) {
						fxRatesBoList = mapBoToDto(fxRatesDtoList);
						response.put("message", "FxRates details fetched successfully.");
						response.put("status", HttpStatus.OK.value());
						response.put("fxRates", fxRatesBoList);
					} else {
						response.put("message", "No data found.");
						response.put("status", HttpStatus.NO_CONTENT.value());
						response.put("fxRates", fxRatesBoList);
					}
				}
			}
			catch (Exception e) {
				throw new InvalidServiceException("Exception occured while fetching FxRates details.");
			}
		}
		return response;
	}

	
	@Override
	public Map<String, Object> modifyFxRates(FxRatesBO fxRatesBO) {
		List<FxRatesDTO> fxRatesData;
		Map<String, Object> response;
		response = new HashMap<String, Object>();
		
		fxRatesData = fxRatesRepository.fetchFxRates(fxRatesBO.getFxId());
		if (fxRatesData.size() > 0) {
			try {
				fxRatesRepository.updateFxRates(fxRatesBO.getFxRate(), fxRatesBO.getCurrencyCode(), fxRatesBO.getFxId(), parseDate(fxRatesBO.getFxDate().toString()));
				response.put("message", "FxRates details updated successfully.");
				response.put("status", HttpStatus.OK.value());
			}
			catch (Exception e) {
				throw new InvalidServiceException("Exception occured while updating FxRates details.");
			}
		}
		else {
			response.put("message", "No Data found for the given request.");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}

	
	@Override
	public Map<String, Object> deleteFxRate(String fxId) {
		List<FxRatesDTO> fxRateData;
		Map<String, Object> response;
		response = new HashMap<>();
		
		fxRateData = fxRatesRepository.fetchFxRates(fxId);
		if (fxRateData.size() > 0) {
			try {
				fxRatesRepository.deleteFxRate(fxId);
				response.put("message", "FxRate deleted successfully");
				response.put("status", HttpStatus.OK.value());
			}
			catch (Exception e) {
				throw new InvalidServiceException("Exception occured while deleting FxRate details.");
			}
		}
		else {
			response.put("message", "No data found");
			response.put("status", HttpStatus.NO_CONTENT.value());
		}
		return response;
	}
	
	private List<FxRatesBO> mapBoToDto(List<FxRatesDTO> fxRateDtoList) throws ParseException{
		List<FxRatesBO> fxBoList;
		fxBoList = new ArrayList<FxRatesBO>();
		for(FxRatesDTO dto :  fxRateDtoList) {
			FxRatesBO bo = new FxRatesBO();
			bo.setFxId(dto.getFxId());
			bo.setCurrencyCode(dto.getCurrencyCode());
			bo.setFxRate(dto.getFxRate());
			bo.setFxDate(reverseDateParsing(dto.getFxDate().toString()));
			fxBoList.add(bo);
		}
		return fxBoList;
	}

	private String reverseDateParsing(String date) throws ParseException {
		String parsedDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		
		if(null != date) {
			Date dateObj = formatter.parse(date);
			parsedDate = format.format(dateObj);
		}
		return parsedDate;
	}
}
