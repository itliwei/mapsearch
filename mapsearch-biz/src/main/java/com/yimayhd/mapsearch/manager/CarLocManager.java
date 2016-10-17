package com.yimayhd.mapsearch.manager;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yimayhd.mapsearch.client.domain.CarLocDO;
import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;
import com.yimayhd.mapsearch.client.domain.param.CarLocQueryDTO;
import com.yimayhd.mapsearch.client.query.CarLocNearQuery;
import com.yimayhd.mapsearch.dao.CarLocDOMapper;
import com.yimayhd.mapsearch.util.GpsUtil;

public class CarLocManager {
	
	@Autowired
	private CarLocDOMapper carLocDOMapper;
	
	public int saveCarLoc(CarLocDTO carLocDTO) {
		
		if(carLocDTO!=null){
			CarLocDO carLocDO=new CarLocDO();
			carLocDO.setLat(carLocDTO.getLat());
			carLocDO.setLng(carLocDTO.getLng());
			carLocDO.setGmtCreated(new Date());
			carLocDO.setGmtModified(new Date());
			return carLocDOMapper.insert(carLocDO);
		}
		return -1;
	}

	public int updateCarLoc(CarLocDTO carLocDTO) {
		
		if(carLocDTO!=null){
			CarLocDO carLocDO=new CarLocDO();
			carLocDO.setLat(carLocDTO.getLat());
			carLocDO.setLng(carLocDTO.getLng());
			return carLocDOMapper.updateByCarId(carLocDO);
		}
		
		return -1;
	}

	public  List<CarLocDO> nearSearch(CarLocQueryDTO carLocSearchDTO) {
		
		double[] boxArea = GpsUtil.getBoxArea(carLocSearchDTO.getLat(), carLocSearchDTO.getLng(), carLocSearchDTO.getRadius());
		
		CarLocNearQuery  query=new CarLocNearQuery();
		query.setBoxArea(boxArea);
		query.setPageSize(carLocSearchDTO.getTopN());
		
		List<CarLocDO> list = carLocDOMapper.nearSearch(query);
		
		if(CollectionUtils.isNotEmpty(list)){
			for(CarLocDO loc:list){
				loc.setDistance(GpsUtil.getGPSDistance(carLocSearchDTO.getLat(), carLocSearchDTO.getLng(), loc.getLat(), loc.getLng()));
			}
			Collections.sort(list, new Comparator<CarLocDO>(){
				@Override
				public int compare(CarLocDO o1, CarLocDO o2) {
					return (int)(o1.getDistance()-o2.getDistance());
				}
			});
			return list;
		}
		return null;
	}

	public  List<CarLocDO> areaSearch(CarLocQueryDTO carLocSearchDTO) {
		double[] boxArea = GpsUtil.getBoxArea(carLocSearchDTO.getLat(), carLocSearchDTO.getLng(), carLocSearchDTO.getRadius());
		
		CarLocNearQuery  query=new CarLocNearQuery();
		query.setBoxArea(boxArea);
		query.setPageSize(carLocSearchDTO.getTopN());
		
		return carLocDOMapper.nearSearch(query);
	}
}
