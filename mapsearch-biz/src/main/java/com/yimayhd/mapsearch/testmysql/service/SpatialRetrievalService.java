package com.yimayhd.mapsearch.testmysql.service;

import java.util.List;

import com.yimayhd.mapsearch.testmysql.model.GeoPosition;

public interface SpatialRetrievalService {

	/**
	 * 随机更新一条数据
	 */
	public void updatePoint();
	
	/**
	 * 附近检索计算0-1000m内的点，并且计算距离和排序
	 *
	 * @return
	 */
	public List<GeoPosition> nearSearch();
}
