package com.yimayhd.mapsearch.testmysql.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yimayhd.mapsearch.testmysql.dao.GeoPositionDAO;
import com.yimayhd.mapsearch.testmysql.model.GeoPosition;
import com.yimayhd.mapsearch.testmysql.utils.GpsUtil;

public class SpatialRetrievalServiceImpl implements SpatialRetrievalService{
	
	@Autowired
	private GeoPositionDAO geoPositionDAO;
	
	private final static double lat=40;
	private final static double lng=116;
	
	@Override
	public void updatePoint() {
		
		Random r = new Random();
		double t = (double) r.nextInt(100000) / 100000;
		long id=r.nextInt(100000);
		
		GeoPosition geo=new GeoPosition();
		geo.setLat(lat+t);
		geo.setLng(lng+t);
		geo.setId(id);
		geoPositionDAO.updatePoint(geo);
	}

	@Override
	public List<GeoPosition> nearSearch() {
		
		Random r=new Random();
		double radius = (double)r.nextInt(1000);
		
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

}
