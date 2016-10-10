package com.yimayhd.mapsearch.testmysql.utils;

/**
 * GPS 工具 经度或者纬度0.00001度，约等于1米
 * 
 * @author gaotingping
 *
 *         2016年10月9日 下午5:54:16
 */
public class GpsUtil {

	public static final double CONVERSION_UNIT_LAT = 110.94;// 维度

	public static final double CONVERSION_UNIT_LNG = 85.276;// 经度

	/**
	 * 获得正方形区域点
	 * 
	 * @param lat
	 * @param lng
	 * @param radius
	 */
	public static double[] getBoxArea(double lat, double lng, double radius) {

		double[] tmp = new double[4];

		double t1 = radius / CONVERSION_UNIT_LAT;

		tmp[0] = lat - t1;
		tmp[1] = lat + t1;

		tmp[2] = lng - t1;
		tmp[3] = lng + t1;

		return tmp;
	}

	/**
	 * GPS坐标的平面距离:单位为米
	 *
	 * @param latA
	 * @param lonA
	 * @param latB
	 * @param lonB
	 * @return
	 */
	public static long getDistance(double latA, double lonA, double latB, double lonB) {
		double lonDistance = Math.abs((lonA - lonB) * Math.cos(Math.toRadians(latA)) * CONVERSION_UNIT_LAT);
		double latDistance = Math.abs((latA - latB) * CONVERSION_UNIT_LAT);
		double distance = Math.sqrt(lonDistance * lonDistance + latDistance * latDistance);
		return Math.round(distance);
	}
}
