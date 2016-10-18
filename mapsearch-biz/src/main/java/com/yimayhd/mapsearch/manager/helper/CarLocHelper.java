package com.yimayhd.mapsearch.manager.helper;

import java.util.Date;

import com.yimayhd.mapsearch.client.domain.CarLocDO;
import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;

public class CarLocHelper {

	public static boolean checkCarLoc(CarLocDTO carLocDTO) {
		
		if(carLocDTO==null){
			return false;
		}
		return true;
	}

	public static CarLocDO fullCarLoc(CarLocDTO carLocDTO) {
		
		CarLocDO carLocDO=new CarLocDO();
		
		carLocDO.setLat(carLocDTO.getLat());
		carLocDO.setLng(carLocDTO.getLng());
		carLocDO.setGmtCreated(new Date());
		carLocDO.setGmtModified(new Date());
		
		return carLocDO;
	}

}
