package com.yimayhd.mapsearch.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yimayhd.mapsearch.client.domain.mongo.*;
import com.yimayhd.mapsearch.client.enums.ErrorCode;
import com.yimayhd.mapsearch.client.errorcode.ReturnCode;
import com.yimayhd.mapsearch.client.service.MongoLbsService;
import com.yimayhd.mapsearch.mongo.dao.CarPointDao;
import com.yimayhd.mapsearch.mongo.dao.CarRoadDao;
import net.pocrd.dubboext.DubboExtProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.IndexOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;


/**
 * 实现类
 */
@Service
public class MongoLbsServiceImpl implements MongoLbsService {
    private static final Logger logger = LoggerFactory.getLogger(MongoLbsServiceImpl.class);
    private final double maxLat = 40.017472;
    private final double minLat = 39.828952;

    private final double maxLon = 116.542984;
    private final double minLon = 116.216141;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CarPointDao carPointDao;

    @Autowired
    CarRoadDao carRoadDao;


    @Override
    public int batchInsertCarPoint(List<CarPoint> carPointList) {
        if (carPointList == null || carPointList.size() == 0 ){
            logger.error("saveCarRoad 参数异常");
            DubboExtProperty.setErrorCode(ReturnCode.PARAMETER_ERROR);
        }
        return carPointDao.batchInsert(carPointList,"carPoint");
    }

    @Override
    public int saveCarPoint(CarPoint carPoint) {
        if (carPoint == null ){
            logger.error("saveCarRoad 参数异常");
            DubboExtProperty.setErrorCode(ReturnCode.PARAMETER_ERROR);
        }
        return carPointDao.save(carPoint,"carPoint");
    }

    @Override
    public int saveCarRoad(CarRoad carRoad) {
        if (carRoad == null ){
            logger.error("saveCarRoad 参数异常");
            DubboExtProperty.setErrorCode(ReturnCode.PARAMETER_ERROR);
        }
        carRoadDao.save(carRoad,"carRoad");
        return 0;
    }

    @Override
    public CarPointNearResult geoNear(CarPointNearQuery carPointNearQuery) {
        CarPointNearResult carPointNearResult = new CarPointNearResult();
        //判断是否有索引
        if (carPointNearQuery == null || carPointNearQuery.getLongitude()==0.0 || carPointNearQuery.getLatitude()==0){
            logger.error("geoNear 参数异常");
            carPointNearResult.setErrorCode(ErrorCode.PARAM_ERROR);
            return null;
        }
        createIndex();
        Point point =new Point(carPointNearQuery.getLongitude(), carPointNearQuery.getLatitude());

        NearQuery near = NearQuery.near(point, Metrics.KILOMETERS);
        Query query =new Query();
        //	       query.addCriteria(Criteria.where("name").is("liwei"));
        query.limit(carPointNearQuery.getCount()==0?100:carPointNearQuery.getCount());
        near.query(query);
        near.maxDistance(new Distance(carPointNearQuery.getDistance()==0?1:carPointNearQuery.getDistance(), Metrics.KILOMETERS));
        near.spherical(true);

        long t1 = System.currentTimeMillis();
        GeoResults<CarPoint> geoResults = carPointDao.geoNear(near, CarPoint.class, "carPoint");
        long t2 = System.currentTimeMillis();
        logger.info("耗时："+(t2-t1)+" 记录数"+geoResults.getContent().size());

        carPointNearQuery.setCount(geoResults.getContent().size());
        carPointNearQuery.setDistance(near.getMaxDistance().getValue());
        carPointNearResult.setCarPointNearQuery(carPointNearQuery);

        List<GeoResult<CarPoint>> geoResultsContent = geoResults.getContent();
        List<CarPointResult> resultsList = new ArrayList<CarPointResult>();
        for (GeoResult<CarPoint> geoResult : geoResultsContent){
            CarPointResult carPointResult = new CarPointResult();
            carPointResult.setDistance(geoResult.getDistance().getValue());
            carPointResult.setCarPoint(geoResult.getContent());
            resultsList.add(carPointResult);
        }
        carPointNearResult.setCarPointList(resultsList);
        return carPointNearResult;
    }

    private void createIndex() {
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
