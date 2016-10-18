package com.yimayhd.mapsearch.manager.helper;

import java.util.Date;

import com.yimayhd.mapsearch.client.domain.CarLocDO;
import com.yimayhd.mapsearch.client.domain.CarTraceDO;
import com.yimayhd.mapsearch.client.domain.param.CarLocTO;

public class CarLocToHelper {

	public static boolean checkParam(CarLocTO carLocTO) {
		if (carLocTO == null || carLocTO.getCarId() < 1) {
			return false;
		}
		return true;
	}

	public static CarLocDO fullCarLoc(CarLocTO carLocTO) {

		CarLocDO carLocDO = new CarLocDO();

		carLocDO.setCarId(carLocTO.getCarId());

		carLocDO.setLat(carLocTO.getLat());
		carLocDO.setLng(carLocTO.getLng());
		carLocDO.setGpsTime(carLocTO.getGpsTime());
		carLocDO.setDirection(carLocTO.getDirection());
		carLocDO.setSpeed(carLocTO.getSpeed());
		
		carLocDO.setGmtModified(new Date());

		return carLocDO;
	}

	public static CarTraceDO fullCarTrace(CarLocTO carLocTO) {
		
		CarTraceDO carTraceDO = new CarTraceDO();

		carTraceDO.setOrderId(carLocTO.getOrderId());
		carTraceDO.setCarId(carLocTO.getCarId());

		carTraceDO.setLat(carLocTO.getLat());
		carTraceDO.setLng(carLocTO.getLng());
		carTraceDO.setGpsTime(carLocTO.getGpsTime());
		carTraceDO.setDirection(carLocTO.getDirection());
		carTraceDO.setSpeed(carLocTO.getSpeed());

		carTraceDO.setGmtCreated(new Date());

		return carTraceDO;
	}

}
