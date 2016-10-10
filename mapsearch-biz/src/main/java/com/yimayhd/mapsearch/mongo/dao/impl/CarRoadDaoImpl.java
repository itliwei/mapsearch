package com.yimayhd.mapsearch.mongo.dao.impl;


import com.yimayhd.mapsearch.client.domain.mongo.CarPoint;
import com.yimayhd.mapsearch.client.domain.mongo.CarRoad;
import com.yimayhd.mapsearch.mongo.base.impl.BaseDaoImpl;
import com.yimayhd.mapsearch.mongo.dao.CarPointDao;
import com.yimayhd.mapsearch.mongo.dao.CarRoadDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


public class CarRoadDaoImpl extends BaseDaoImpl<CarRoad> implements CarRoadDao{
	private static final Logger logger = Logger.getLogger(CarRoadDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;


}
