package com.onda2me.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("DateUtil")
public class DateUtil {

	public static Date getDate() {		
		return Calendar.getInstance().getTime();	
	}
	
	public static String getDate(String format) {		
		Calendar currCal = Calendar.getInstance();
		return getDateFormat(currCal.getTime(), format);		
	}
	
	public static String getDateAddDay(String format, int add) {		
		Calendar currCal = Calendar.getInstance();
		currCal.add(Calendar.DATE, add);

		return getDateFormat(currCal.getTime(), format);		
	}
	
	public static String getDateAddMonth(String format, int add) {	
		
		Calendar currCal = Calendar.getInstance();
		currCal.add(Calendar.MONTH, add);
		return getDateFormat(currCal.getTime(), format);		
	}
	
	public static String getDateAddHour(String format, int add) {	
		
		Calendar currCal = Calendar.getInstance();
		currCal.add(Calendar.HOUR, add);
		return getDateFormat(currCal.getTime(), format);		
	}
	
	public static String getDateAddMinute(String format, int add) {	
		
		Calendar currCal = Calendar.getInstance();
		currCal.add(Calendar.MINUTE, add);
		return getDateFormat(currCal.getTime(), format);		
	}
	
	public static String getDateAddSecond(String format, int add) {	
		
		Calendar currCal = Calendar.getInstance();
		currCal.add(Calendar.SECOND, add);
		return getDateFormat(currCal.getTime(), format);		
	}

	public static String getDateFormat(java.util.Date time, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(time);
	}
	public static String getDateFormat(java.util.Date time) {
		return getDateFormat(time, "yyyy-MM-dd");
	}
	public static String getTimeFormat(java.util.Date time) {
		return getDateFormat(time, "HH:mm:ss");
	}	
	public static String getDateTimeFormat(java.util.Date time) {
		return getDateFormat(time, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getDateFormat(String date, String bf, String af) throws ParseException  {

        if (date == null || bf == null || af == null) {
            return null;
        }        
        
        SimpleDateFormat formatter1 = new SimpleDateFormat(bf);
        Date tempDate = formatter1.parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat(af);
		
		return formatter.format(tempDate);
	}
}
