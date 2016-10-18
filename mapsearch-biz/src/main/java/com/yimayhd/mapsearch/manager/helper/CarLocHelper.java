package com.yimayhd.mapsearch.manager.helper;

import java.util.Date;

import com.yimayhd.mapsearch.client.domain.CarLocDO;
import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;
import com.yimayhd.mapsearch.client.domain.param.CarLocQueryDTO;
import com.yimayhd.mapsearch.client.domain.param.CarStatusDTO;

public class CarLocHelper {

	public static boolean checkCarLoc(CarLocDTO carLocDTO) {
		
		if(carLocDTO==null){
			return false;
		}
		if(carLocDTO.getCarId()<1){
			return false;
		}
		
		return true;
	}

	public static CarLocDO fullCarLoc(CarLocDTO carLocDTO) {
		
		CarLocDO carLocDO=new CarLocDO();
		
		carLocDO.setCarId(carLocDTO.getCarId());
		
		carLocDO.setLat(carLocDTO.getLat());
		carLocDO.setLng(carLocDTO.getLng());
		carLocDO.setGpsTime(carLocDTO.getGpsTime());
		carLocDO.setDirection(carLocDTO.getDirection());
		carLocDO.setSpeed(carLocDTO.getSpeed());
		
		carLocDO.setGmtCreated(new Date());
		carLocDO.setGmtModified(new Date());
		
		return carLocDO;
	}

	public static CarLocDO fullCarInfo(CarLocDTO carLocDTO) {
		
		CarLocDO carLocDO=new CarLocDO();
		
		carLocDO.setCarId(carLocDTO.getCarId());
		
		carLocDO.setLat(carLocDTO.getLat());
		carLocDO.setLng(carLocDTO.getLng());
		carLocDO.setGpsTime(carLocDTO.getGpsTime());
		carLocDO.setDirection(carLocDTO.getDirection());
		carLocDO.setSpeed(carLocDTO.getSpeed());
		
		carLocDO.setGmtModified(new Date());
		
		return carLocDO;
	}

	
	public static boolean checkLocSearchDTO(CarLocQueryDTO carLocSearchDTO) {
		if(carLocSearchDTO==null){
			return false;
		}
		if(carLocSearchDTO.getRadius()<0){
			return false;
		}
		return true;
	}

	public static boolean checkStatusDTO(CarStatusDTO carStatusDTO) {
		if(carStatusDTO == null || carStatusDTO.getCarId()<1 || carStatusDTO.getStatus()<1){
	       return false;
		}
		return true;
	}
}
