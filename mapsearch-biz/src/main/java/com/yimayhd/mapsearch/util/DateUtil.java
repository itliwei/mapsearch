package com.yimayhd.mapsearch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author wuzhengfei358
 *
 */
public class DateUtil {
    public static final String DATE_FORAMT      = "yyyy-MM-dd";
    public static final String TIME_HOUR_FORAMT = "HH:mm";
    public static final String TIME_FORAMT      = "HH:mm:ss";
    public static final String TIME_MS_FORAMT = "HH:mm:ss:SSS";
    /**
     * 日期+时间格式，不包括毫秒
     */
    public static final String DATE_TIME_FORMAT = DATE_FORAMT + " " + TIME_FORAMT;
    /**
     * 日期+时间格式，包括毫秒
     */
    public static final String DATE_TIME_MS_FORMAT = DATE_FORAMT + " " + TIME_MS_FORAMT;

    public static boolean isAfterNow(Date date){
    	if( date == null ){
    		return false ;
    	}
    	long target = date.getTime() ;
    	long current = System.currentTimeMillis() ;
    	return current < target ;
    }

    public static boolean isBeforeNow(Date date){
    	if( date == null ){
    		return false ;
    	}
    	long target = date.getTime() ;
    	long current = System.currentTimeMillis() ;
    	return target < current;
    }
    
    public static long date2Long(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

    public static Date long2Date(long time) {
        if (time <= 0) {
            return null;
        }
        return new Date(time);
    }

    public static String format(Date date, String format) {
        if (date == null || format == null || "".equals(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String format(long time, String format) {
        if (time <= 0 || format == null || "".equals(format)) {
            return null;
        }
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Date parse(String time, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse2(String time, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(time);
    }

    public static int getYear(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        int y1 = Integer.parseInt(format(date1, "yyyy"));
        int y2 = Integer.parseInt(format(date2, "yyyy"));
        return Math.abs(y1 - y2);
    }

    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        return parse(format(date, DATE_FORAMT), DATE_FORAMT);
    }
    /**
     * 计算2个时间相差多少小时
     *
     * @param nowDate
     * @param oldDate
     *
     * @return
     */
    public static long getDatePoor(Date nowDate, Date oldDate) {
        long nh = 1000 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = nowDate.getTime() - oldDate.getTime();
        // 计算差多少小时
        long hour = diff/nh;
        return hour;
    }

    public static Date get72HourBeforeDate() {
        long date = (System.currentTimeMillis()/1000 - 72L * 3600L) * 1000L;
        return new Date(date);
    }
    
    public static Date get24HourBeforeDate() {
    	long date = System.currentTimeMillis() - 24l * 3600l *1000l;
    	return new Date(date);
    }
    
    public static Date get48HourBeforeDate() {
    	long date = System.currentTimeMillis() - 48l * 3600l *1000l;
    	return new Date(date);
    }
    
    /**
     * 获取指定日期零点的时间
     */
    public static Date getStartTimeOfDate(Date date) {
    	Calendar cal = Calendar.getInstance();
    	if (date != null) {
    		cal.setTime(date);
    	}
    	
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	
    	return cal.getTime();
    }    
    
    public static void main(String args[]) {
    	long currentTime = System.currentTimeMillis();
    	long startTime = getStartTimeOfDate(new Date()).getTime();
    	
    	System.out.println((currentTime-startTime)/1000);
    }
}
