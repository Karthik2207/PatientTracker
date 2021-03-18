package com.tracker.patienttracker.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

public class DateUtil {

	public static Date convertToDate(String date) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date convertedDate=null;
		try {
			convertedDate = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertedDate;
	}
	public static Date convertToDate1(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate=null;
		try {
			convertedDate = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}
	
	public static Date convertIsoStringToDate(String isoDate) {
		return DatatypeConverter.parseDateTime(isoDate).getTime();
	}
}
