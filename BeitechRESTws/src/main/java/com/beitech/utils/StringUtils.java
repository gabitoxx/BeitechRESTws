package com.beitech.utils;

import java.util.Calendar;

import static com.beitech.ws.Queries.FROM_DATE_FORMAT;
import static com.beitech.ws.Queries.TO_DATE_FORMAT;

public class StringUtils {
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getCalendarFromString(String date) {
		
		if ( null == date || date.isEmpty() || date.length() != 8) {
			return null;
		}
		
		Calendar result = Calendar.getInstance();
		
		int year = Integer.parseInt( date.substring(0, 4) );
		int month= Integer.parseInt( date.substring(4, 6) );
		int day  = Integer.parseInt( date.substring(6, 8) );
		
		result.set(year, month, day);
		
		return result;
	}
	
	
	public static String replaceDatesInQuery(String query, Calendar fromDate, Calendar toDate) {
		
		if ( null == query ) {
			return "";
		}
		
		String result = query;
		
		if ( null != fromDate ) {
			int year = fromDate.get(Calendar.YEAR);
			int month= fromDate.get(Calendar.MONTH);
			int day  = fromDate.get(Calendar.DATE);
			
			result = result.replace(FROM_DATE_FORMAT, year+"-"+month+"-"+day);
		}
		
		if ( null != toDate ) {
			int year = toDate.get(Calendar.YEAR);
			int month= toDate.get(Calendar.MONTH);
			int day  = toDate.get(Calendar.DATE);
			
			result = result.replace(TO_DATE_FORMAT, year+"-"+month+"-"+day);
		}
		
		return result;
	}
}