package com.yimayhd.mapsearch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yimayhd.mapsearch.client.domain.CarLocDO;
import com.yimayhd.mapsearch.client.query.CarLocNearQuery;

//车位置
public interface CarLocDOMapper {
	
	//保存
    int saveCar(CarLocDO carLocDO);
    
    //查询
    CarLocDO getCarByCarId(@Param("carId") long carId);
    
    //附近搜索
    List<CarLocDO> nearSearch(CarLocNearQuery nearQuery);
   
    //修改车位置,车速,速度等信息
    int updateCarInfo(CarLocDO carLocDO);
	
    //修改车状态
    int updateCarStatus(@Param("carId") long carId, @Param("status") int status);
}