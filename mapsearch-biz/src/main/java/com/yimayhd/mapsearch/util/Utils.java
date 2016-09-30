package com.yimayhd.mapsearch.util;

import java.util.List;

/**
 * 
 * @author wuzhengfei358
 *
 */
public class Utils {
	public static final String SPERATOR = ",";
	
	
    public static long[] longList2Array(List<Long> list){
    	if( list == null || list.isEmpty() ){
    		return null;
    	}
    	long[] arrays = new long[list.size()] ;
    	for( int i=0 ; i<list.size() ; i++ ){
    		Long item = list.get(i);
    		if( item != null ){
    			arrays[i] = item;
    		}
    	}
    	return arrays ;
    }
}
