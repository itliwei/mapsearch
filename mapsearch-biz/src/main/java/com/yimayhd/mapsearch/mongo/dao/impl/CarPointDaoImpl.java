package com.yimayhd.mapsearch.mongo.dao.impl;


import com.yimayhd.mapsearch.client.domain.mongo.CarPoint;
import com.yimayhd.mapsearch.mongo.base.impl.BaseDaoImpl;
import com.yimayhd.mapsearch.mongo.dao.CarPointDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.IndexOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.IndexInfo;

import java.util.List;


public class CarPointDaoImpl extends BaseDaoImpl<CarPoint> implements CarPointDao{
	private static final Logger logger = Logger.getLogger(CarPointDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void createGeoIndex() {
        IndexOperations io=mongoTemplate.indexOps("carPoint");
//	     io.dropIndex("location_2dsphere");
        List<IndexInfo> indexInfoList = io.getIndexInfo();
        boolean status = false;
        for (IndexInfo info:indexInfoList){
            if("location_2dsphere".equals(info.getName())){
                status = true;
                break;
            }
        }
        if (!status){
            GeospatialIndex index =new GeospatialIndex("location");
            index.typed(GeoSpatialIndexType.GEO_2DSPHERE);
            index.named("location_2dsphere");
            index.withBits(26);
            index.withMax(900);
            index.withMin(0);
            io.ensureIndex(index);
        }
    }


}
