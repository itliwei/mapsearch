package com.yimayhd.mapsearch.testmysql.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yimayhd.mapsearch.testmysql.dao.GeoPositionDAO;
import com.yimayhd.mapsearch.testmysql.model.GeoPosition;
import com.yimayhd.mapsearch.testmysql.utils.GpsUtil;

public class GeoPositionService {

	@Autowired
	private GeoPositionDAO geoPositionDAO;

	/**
	 * 添加数据
	 * 
	 * @author gaotingping
	 * 2016年10月10日
	 *
	 * @param geoPosition
	 */
	public void add(GeoPosition geoPosition){
		geoPositionDAO.add(geoPosition);
	}

	/**
	 * 附近检索:算距离，并且按照由远到近排序
	 * 
	 * @author gaotingping
	 * 2016年10月10日
	 *
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return
	 */
	public List<GeoPosition> nearSearch(double lat, double lng, double radius){
		double[] box = GpsUtil.getBoxArea(lat, lng, radius);
		List<GeoPosition> list = geoPositionDAO.areaSearch(box[0], box[1], box[2], box[3]);
		if(CollectionUtils.isNotEmpty(list)){
			for(GeoPosition loc:list){
				loc.setDistance(GpsUtil.getDistance(lat, lng, loc.getLat(), loc.getLng()));
			}
			Collections.sort(list, new Comparator<GeoPosition>(){
				@Override
				public int compare(GeoPosition o1, GeoPosition o2) {
					return (int)(o1.getDistance()-o2.getDistance());
				}
			});
			return list;
		}
		return null;
	}

	/**
	 * 区域检索:只查询，不计算距离
	 * 
	 * @author gaotingping
	 * 2016年10月10日
	 *
	 * @param lat
	 * @param lng
	 * @param radius
	 * @return
	 */
	public List<GeoPosition> areaSearch(double lat, double lng, double radius){
		double[] box = GpsUtil.getBoxArea(lat, lng, radius);
		return geoPositionDAO.areaSearch(box[0], box[1], box[2], box[3]);
	}
}
