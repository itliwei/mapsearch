package com.yimayhd.mapsearch.client.service;


import com.yimayhd.mapsearch.client.domain.mongo.*;

import java.util.List;

/**
 *
 */
public interface MongoLbsService {
    /**
     * 批量导入carPoint
     * @param carPointList
     * @return int
     */
    int batchInsertCarPoint(List<CarPoint> carPointList);

    /**
     * 插入单个carPoint
     * @param carPoint CarPoint
     * @return int
     */
    int saveCarPoint(CarPoint carPoint);

    /**
     * 插入单个carRoad信息
     * @param carRoad
     * @return
     */
    int saveCarRoad(CarRoad carRoad);

    /**
     * 附近的点
     * @param carPointNearQuery CarPointNearQuery
     * @return List<CarPoint>
     */
    CarPointNearResult geoNear(CarPointNearQuery carPointNearQuery);

}
