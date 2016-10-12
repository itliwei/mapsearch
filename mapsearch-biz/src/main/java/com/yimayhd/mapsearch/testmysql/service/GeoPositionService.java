package com.yimayhd.mapsearch.testmysql.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yimayhd.mapsearch.testmysql.dao.GeoPositionDAO;
import com.yimayhd.mapsearch.testmysql.model.GeoPosition;
import com.yimayhd.mapsearch.testmysql.utils.GpsUtil;

/**
 * mysql版本的地理位置检索
 * 
 * @author gaotingping
 *
 * 2016年10月10日 下午3:19:28
 */
public class GeoPositionService implements GeoApi{

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
	 * 附近检索:算距离，并且按照由近到远排序
	 * 
	 * 查询出所有满足条件的点，然后计算距离,再去tomN最近的点
	 * 这有个问题:就是当满足条件的点特别多的时候，这个方便就特
	 * 别不现实了，所以实际应用中，还是需要mysql来算这个距离的
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
	
	//附近搜索:距离的计算，给mysql完成
	public List<GeoPosition> nearSearchTopN(double lat, double lng, double radius){
		return geoPositionDAO.nearSearch(lat,lng,radius);
	}

	/**
	 * 区域检索:只查询，不计算距离
	 * 这适用拿符合条件的数据点去展示
	 * 如将附近的车都标注到地图上
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
