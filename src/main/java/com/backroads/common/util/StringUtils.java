package com.backroads.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtils {
	
	public static Date toStringMMddyyyWithSlash(String slashedMMddyyyy) {

	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return formatter.parse(slashedMMddyyyy);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getTodaysDateMMddyyyyWithSlash() {
	    Date date = Calendar.getInstance().getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    return formatter.format(date);
	}
	
	public static String getAddedDatesMMddyyyyWithSlash(int plusDays) {
	    Date date = new Date();
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(Calendar.DATE, plusDays);
	    date = c.getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    return formatter.format(date);
	}
	
	//will return dates in String for today from numDays ago
	public static String [] getDates(int numDays){
		String [] date = new String [numDays];
		for (int i = 0; i < numDays; i++){
			date[i] = getAddedDatesMMddyyyyWithSlash(i*-1);
		}
		return date;
	}
	
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	

	public static String [] increasedNumValues(double increaseBy, String [] values) {
		String [] changedValues = new String[values.length];
		try {
			for (int i = 0; i < values.length; i++) {
				if (!values[i].equals("0")) {
					changedValues[i] = String.valueOf(Double.parseDouble(values[i]) + increaseBy);
				} else {
					changedValues[i] = values[i];
				}
			}
		} catch (Exception e) {
		    System.out.println("Non numeric value may be included in the array.");
			return null;
		}
		return changedValues;
	}
	
	
}
