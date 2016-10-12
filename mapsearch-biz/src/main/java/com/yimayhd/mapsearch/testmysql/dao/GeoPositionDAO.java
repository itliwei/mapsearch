package com.yimayhd.mapsearch.testmysql.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.mapsearch.testmysql.model.GeoPosition;

public interface GeoPositionDAO {
	
	//添加数据:
	public void add(GeoPosition geoPosition);
	
	//区域检索
    public List<GeoPosition> areaSearch(@Param("minLat") double minLat,
    									@Param("maxLat") double maxLat,
    									@Param("minLng") double minLng,
    									@Param("maxLng") double maxLng);

    //附近检索
	public List<GeoPosition> nearSearch(double lat, double lng,double radius);
	
	//update数据
	public Long updatePoint(GeoPosition geoPosition);
}
