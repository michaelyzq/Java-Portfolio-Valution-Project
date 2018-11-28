package com.ms.portvaluation.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;



/**
 * @author michael yin
 *
 */
public final class TimeUtils {
	public static BigDecimal getRandomTimeInterval(){
		try{
			return BigDecimal.valueOf(Math.random()*2);
		}catch (Exception e){
			return BigDecimal.valueOf(2);
		}
		
	}
	
	public static String dateToString(Date date) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(ServiceConstant.ISO_8601_TIMESTAMP_FORMAT);
		return date != null ? df.format(date):null;
	}

	public static Date stringToDate(String dateString) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(ServiceConstant.ISO_8601_TIMESTAMP_FORMAT);
		return dateString != null ? df.parse(dateString):null;
	}

	public static Date dateToSimpleDate(Date timeStamp) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(ServiceConstant.SIMPLE_DATE_FORMAT);
		String s = df.format(timeStamp);
		return df.parse(s);
	}

	public static Date simpleStringToSimpleDate(String dateString) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(ServiceConstant.SIMPLE_DATE_FORMAT);
		Date s = df.parse(dateString);
		return s;
	}

	public static String dateToSimpleDateString(Date timeStamp) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(ServiceConstant.SIMPLE_DATE_FORMAT);
		return df.format(timeStamp);
	}

	public static Date stringToSimpleDate(String dateString) throws ParseException {
		Date _d = stringToDate(dateString);
		return dateToSimpleDate(_d);
	}

	public static boolean compareSimpleDate(Date date1, Date date2) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(ServiceConstant.SIMPLE_DATE_FORMAT);
		return (df.parse(df.format(date1)).compareTo(df.parse(df.format(date2))) == 0);
	}



	public static LocalDate createLocalDateNow() {
		return LocalDate.now();
	}

	public static String dateToString(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ServiceConstant.SIMPLE_DATE_FORMAT);
		return localDate.format(formatter);
	}

	public static String dateTimeToString(LocalDateTime localDateTime) {
		ZonedDateTime zone = localDateTime.atZone(ZoneId.systemDefault());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ServiceConstant.ISO_8601_TIMESTAMP_FORMAT);
		return zone.format(formatter);
	}

	public static LocalDate stringToLocalDate(String dateString) {	// Assumes ISO_LOCAL_DATE e.g. yyyy-mm-dd
		return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(ServiceConstant.SIMPLE_DATE_FORMAT));
	}

	public static String stringDateToStringSimpleDate(String dateString) {	// Assumes ISO_LOCAL_DATE e.g. yyyy-mm-dd
		return dateString.substring(0, 10);
	}



}
