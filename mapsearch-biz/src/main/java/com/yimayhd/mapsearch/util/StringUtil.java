package com.yimayhd.mapsearch.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @autor sunji180 on 2015/9/28.
 */
public class StringUtil {
    public static final String SEPARATOR = ",";
    public static final String SEPSPLIT = "\\|";
    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    public static String[] string2Array(String str, String separator){
        if(StringUtils.isBlank(str)){
            return null;
        }
        String[] result = str.split(separator);
        if (result.length == 0) {
            return null;
        }
        return result;
    }

    public static String[] string2Array(String str){
        return string2Array(str, SEPARATOR);
    }

    public static long[] string2LongArray(String str){
        String[] result = string2Array(str);
        long[] longResult = new long[result.length];
        for (int i = 0; i < result.length; i++) {
            try {
                longResult[i] = Long.valueOf(result[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                logger.error("long NumberFormatException, String=" + result[i]);
            }
        }
        return longResult;
    }
    
    public static String String2String(String str){
    	String[] strings = string2Array(str,SEPSPLIT);
    	return strings[1];
    }
    
	public static void main(String args[]) { 
		String string = String2String("|L1SyhTB7bT1RXrhCrK.jpg|L13ahTBKJT1RXrhCrK.jpg|");
         System.out.println(string); 
    } 
}
