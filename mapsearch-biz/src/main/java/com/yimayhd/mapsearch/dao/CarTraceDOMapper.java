package com.yimayhd.mapsearch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.mapsearch.client.domain.CarTraceDO;

//车轨迹
public interface CarTraceDOMapper {
	
    int insert(CarTraceDO carTraceDO);
    
    List<CarTraceDO> getCarTraceByOrderId(@Param("orderId")long orderId);
}