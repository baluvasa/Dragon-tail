package com.techm.po.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateUtils {

	public static LocalDate parseDate(String date) {
		LocalDate parsedDate = null;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		
		if(null != date) {
			LocalDate localDate = LocalDate.parse(date, formatter);
			parsedDate = localDate;
		}
		return parsedDate;
	}

	public static String reverseDateParsing(String date) throws ParseException {
		String parsedDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		
		if(null != date) {
			Date dateObj = formatter.parse(date);
			parsedDate = format.format(dateObj);
		}
		return parsedDate;
	}
	
    public static LocalDate stringDateParsing(String date) {
        LocalDate parsedDate = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        if(null != date) {
               LocalDate localDate = LocalDate.parse(date, formatter);
               parsedDate = localDate;
        }
        return parsedDate;
 }

}
