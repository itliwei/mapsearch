package com.yimayhd.mapsearch.util;

/**
 * GPS操作工具
 * 
 * @author gaotingping
 *
 * 2016年10月17日 下午1:42:39
 */
public class GpsUtil {

	private static final double EARTH_RADIUS = 6378137;
	
	public static final double CONVERSION_UNIT_LAT = (EARTH_RADIUS/180)*Math.PI;
	
	/**
	 * 获得正方形区域点
	 * 
	 * @param lat
	 * @param lng
	 * @param radius 半径
	 * 
	 * @return [minLat,maxLat,minLng,maxLng]
	 */
	public static double[] getBoxArea(double lat, double lng, double radius) {

		double[] tmp = new double[4];
		
		double t1 = radius / CONVERSION_UNIT_LAT;
		double t2 = radius / (Math.cos(Math.toRadians(lat)) * CONVERSION_UNIT_LAT);
		
		tmp[0] = lat-t1;
		tmp[1] = lat+t1;
		tmp[2] = lng-t2;
		tmp[3] = lng+t2;

		return tmp;
	}
	
	/**
	 * GPS坐标的曲面距离:单位为米(建议这种方式)
	 *
	 * @param lat_a
	 * @param lng_a
	 * @param lat_b
	 * @param lng_b
	 * @return
	 */
	public static double getGPSDistance(double lat_a, double lng_a, double lat_b, double lng_b) {
		double radLat1 = Math.toRadians(lat_a);
		double radLat2 = Math.toRadians(lat_b);

		double a = radLat1 - radLat2;

		double b = Math.toRadians(lng_a - lng_b);

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
}
