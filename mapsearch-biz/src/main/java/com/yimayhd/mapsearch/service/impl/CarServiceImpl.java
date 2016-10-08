package com.yimayhd.mapsearch.service.impl;

import com.yimayhd.mapsearch.client.domain.es.CarVo;
import com.yimayhd.mapsearch.client.query.PageQuery;
import com.yimayhd.mapsearch.client.service.CarService;
import com.yimayhd.mapsearch.es.car.CarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CarServiceImpl
 *
 * @author lilin
 * @date 16/9/29
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarClient carClient;

    public CarVo searchById(long id) {
        return null;
    }

    public List<CarVo> searchByGeoDistance(double lat, double lon, double distance, PageQuery pageVo) {
        return carClient.geoSearch(lat,lon,distance,pageVo);
    }

    @Override
    public boolean bulkIndex(List<CarVo> carVos) {
        return carClient.bulkInsertCar(carVos);
    }


}
