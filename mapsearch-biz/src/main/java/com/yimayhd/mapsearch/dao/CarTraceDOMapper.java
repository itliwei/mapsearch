package com.yimayhd.mapsearch.dao;

import java.util.List;

import com.yimayhd.mapsearch.client.domain.CarTraceDO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceQueryDTO;

//车轨迹
public interface CarTraceDOMapper {
	
    int insert(CarTraceDO carTraceDO);
    
    List<CarTraceDO> getCarTraceByOrderId(CarTraceQueryDTO carTraceQueryDTO);
}