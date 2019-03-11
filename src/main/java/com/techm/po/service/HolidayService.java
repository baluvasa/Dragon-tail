package com.techm.po.service;

import java.util.Map;
import com.techm.po.model.dto.HolidaysDTO;

public interface HolidayService {

	Map<String, Object> addHolidayDetails(HolidaysDTO holidaysDto);

	Map<String, Object> fetchHolidaysDetails(String accountCategory, String accountName, String projectName,
			String year);
	
	Map<String, Object> deleteHoliday(Integer holidayId);

	Map<String, Object> modifyHolidays(HolidaysDTO holidaysDto);

}
