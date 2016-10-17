package com.yimayhd.mapsearch.dao;

import java.util.List;

import com.yimayhd.mapsearch.client.domain.CarLocDO;
import com.yimayhd.mapsearch.client.query.CarLocNearQuery;

//车位置
public interface CarLocDOMapper {
	
    int insert(CarLocDO carLocDO);

    int updateByCarId(CarLocDO carLocDO);
    
    List<CarLocDO> nearSearch(CarLocNearQuery nearQuery);
}