package com.yimayhd.mapsearch.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.mapsearch.client.domain.CarTraceDO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceDTO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceQueryDTO;
import com.yimayhd.mapsearch.dao.CarTraceDOMapper;

public class CarTraceManager {
	
	@Autowired
	private CarTraceDOMapper carTraceDOMapper;

	public int saveCarTrace(CarTraceDTO carTraceDTO) {
		
		CarTraceDO carTraceDO=new CarTraceDO();
		carTraceDO.setLat(carTraceDTO.getLat());
		carTraceDO.setLng(carTraceDTO.getLng());
		carTraceDO.setOrderId(carTraceDTO.getOrderId());
		
		return carTraceDOMapper.insert(carTraceDO);
	}

	public List<CarTraceDO> queryCarTrace(CarTraceQueryDTO carTraceQueryDTO) {
		return carTraceDOMapper.getCarTraceByOrderId(carTraceQueryDTO);
	}
}
