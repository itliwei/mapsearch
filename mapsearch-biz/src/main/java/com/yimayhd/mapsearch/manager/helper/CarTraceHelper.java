package com.yimayhd.mapsearch.manager.helper;

import java.util.Date;

import com.yimayhd.mapsearch.client.domain.CarTraceDO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceDTO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceQueryDTO;

public class CarTraceHelper {

	public static boolean checkCarTrace(CarTraceDTO carTraceDTO) {
		if(carTraceDTO==null || carTraceDTO.getCarId()<1 || carTraceDTO.getOrderId()<1){
			return false;
		}
		return true;
	}

	public static CarTraceDO fullCarTrace(CarTraceDTO carTraceDTO) {
		
		CarTraceDO carTraceDO=new CarTraceDO();
		
		carTraceDO.setOrderId(carTraceDTO.getOrderId());
		carTraceDO.setCarId(carTraceDTO.getCarId());
		
		carTraceDO.setLat(carTraceDTO.getLat());
		carTraceDO.setLng(carTraceDTO.getLng());
		carTraceDO.setGpsTime(carTraceDTO.getGpsTime());
		carTraceDO.setDirection(carTraceDTO.getDirection());
		carTraceDO.setSpeed(carTraceDTO.getSpeed());
		
		carTraceDO.setGmtCreated(new Date());
		
		return carTraceDO;
	}

	public static boolean checkQueryCarTrace(CarTraceQueryDTO carTraceQueryDTO) {
		if(carTraceQueryDTO==null || carTraceQueryDTO.getCarId()<1 || carTraceQueryDTO.getOrderId()<1){
			return false;
		}
		return true;
	}
}
