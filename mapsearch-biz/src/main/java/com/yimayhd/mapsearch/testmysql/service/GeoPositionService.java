package com.yimayhd.mapsearch.testmysql.service;

import java.util.List;

import com.yimayhd.mapsearch.testmysql.model.GeoPosition;

/**
 * mysql版本的地理位置检索
 * 
 * @author gaotingping
 *
 *         2016年10月10日 下午3:19:28
 */
public interface GeoPositionService {

	/**
	 * 更新一个点
	 *
	 * @param lat
	 * @param lng
	 * @param id
	 */
	public void updatePoint(double lat, double lng, long id);
	
	/**
	 * 查询附近
	 *
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return
	 */
	public List<GeoPosition> nearSearch(double lat, double lng, double radius);

	//下面是自己测试方法
	public List<GeoPosition> nearSearchTopN(double lat, double lng, double radius);

	public List<GeoPosition> areaSearch(double lat, double lng, double radius);

	public void add(GeoPosition geoPosition);
}
