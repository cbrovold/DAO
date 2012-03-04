package com.brov0010.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String DATE_FORMAT = "MM/dd/yyyy";
	
	/**
	 * Takes a string and parses it as a date and uses the date non-zero "REAL"
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final Date parseDate(String date, String pattern) {
		return getRealDateBecauseJavasDateImplIsFuckingTerrible(date, pattern);
	}
	
	public static final Date parseDate(String date) {
		return getRealDateBecauseJavasDateImplIsFuckingTerrible(date);
	}
	
	public static final String format(Date date) {
		return format(date, DATE_FORMAT);
	}
	
	public static final String format(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		
		return df.format(date);
	}
	
	private static Date getRealDateBecauseJavasDateImplIsFuckingTerrible(String date, String pattern) {
		
		DateFormat df = new SimpleDateFormat(pattern);
		Date retval = null;
		try {
			Date d = df.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
//			int year = cal.get(Calendar.YEAR);
//			cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.YEAR, year);
			retval = cal.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retval;
	}
	
	private static Date getRealDateBecauseJavasDateImplIsFuckingTerrible(String date) {
		return getRealDateBecauseJavasDateImplIsFuckingTerrible(date, DATE_FORMAT);
	}
}
