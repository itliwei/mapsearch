package com.yimayhd.mapsearch.testmysql.service;

import java.util.List;

import com.yimayhd.mapsearch.testmysql.model.GeoPosition;


public interface GeoApi {

	//添加一个点
	public void add(GeoPosition geoPosition);

	//附近搜索，默认就去top100吧
	public List<GeoPosition> nearSearch(double lat, double lng, double radius);
}
