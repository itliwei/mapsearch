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
     * 查询多条记录
     * @param count int
     * @return List<CarPoint>
     */
    List<CarPoint> queryTopN(int count);

    /**
     * 插入单个carPoint
     * @param carPoint CarPoint
     * @return int
     */
    int saveCarPoint(CarPoint carPoint);

    /**
     * 插入单个carRoad信息
     * @param carRoad CarRoad
     * @return
     */
    int saveCarRoad(CarRoad carRoad);

    /**
     * 附近的点
     * @param carPointNearQuery CarPointNearQuery
     * @return List<CarPoint>
     */
    CarPointNearResult geoNearCarPoint(CarPointNearQuery carPointNearQuery);



    /**
     * 随机修改
     * @return CarPoint
     */
    CarPoint updateCarPoint();

    /**
     * 创建地理位置索引
     */
    void createGeoIndex();

}
