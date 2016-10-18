package com.yimayhd.mapsearch.service.impl;

import com.yimayhd.mapsearch.client.domain.es.CarVo;
import com.yimayhd.mapsearch.client.domain.es.LocationPoint;
import com.yimayhd.mapsearch.client.query.PageQuery;
import com.yimayhd.mapsearch.client.service.CarService;
import com.yimayhd.mapsearch.es.car.CarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CarServiceImpl
 *
 * @author lilin
 * @date 16/9/29
 */
@Service
public class CarServiceImpl implements CarService {

    private final double maxLat = 40.017472;
    private final double minLat = 39.828952;

    private final double maxLon = 116.542984;
    private final double minLon = 116.216141;

    private final int CAR_COUNT = 100000;

    public static double[] randomLonLat(double minLon, double maxLon, double minLat, double maxLat) {
        BigDecimal db = new BigDecimal(Math.random() * (maxLon - minLon) + minLon);
        double lon = Double.parseDouble(db.setScale(6, BigDecimal.ROUND_HALF_UP).toString());//小数后6位
        db = new BigDecimal(Math.random() * (maxLat - minLat) + minLat);
        double lat = Double.parseDouble(db.setScale(6, BigDecimal.ROUND_HALF_UP).toString());
        return new double[]{lat, lon};
    }

    @Autowired
    private CarClient carClient;


    public CarVo searchById(long id) {
        return carClient.searchCarById(id);
    }

    public List<CarVo> searchByGeoDistance(double lat, double lon, double distance, PageQuery pageVo) {
        return carClient.geoSearch2(lat, lon, distance, pageVo);
    }

    @Override
    public boolean bulkIndex(List<CarVo> carVos) {
        return carClient.bulkInsertCar(carVos);
    }

    @Override
    public boolean updateIndex(CarVo carVo) {
        return carClient.updateCar(carVo);
    }

    @Override
    public boolean testUpdateIndex() {
        int carId = (int) (Math.random() * CAR_COUNT);

        double[] bigDecimals = randomLonLat(minLon, maxLon, minLat, maxLat);
        LocationPoint locationPoint = new LocationPoint(bigDecimals[0], bigDecimals[1]);
        CarVo carVo = new CarVo();
        carVo.setId(carId);
        carVo.setTitle("测试测量" + carId);
        carVo.setLocationPoint(locationPoint);

        boolean suc = updateIndex(carVo);
        System.out.println("result is "+suc);
        return suc;
    }

    @Override
    public List<CarVo> testSearch(double distance) {
        double[] bigDecimals = randomLonLat(minLon, maxLon, minLat, maxLat);
        double lat = bigDecimals[0];
        double lon = bigDecimals[1];
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNo(1);
        pageQuery.setPageSize(20);
        List<CarVo> carVos = searchByGeoDistance(lat, lon, distance, pageQuery);
        return carVos;
    }

    @Override
    public boolean testInitIndex() {
        List<CarVo> carList = new ArrayList<CarVo>(CAR_COUNT);
        for (int i = 1; i <= CAR_COUNT; i++) {
            CarVo carVo = new CarVo();
            carVo.setId(i);
            carVo.setTitle("测试测量" + i);
            carVo.setRegisterDate(new Date());
            carVo.setOnline(1);
            double[] bigDecimals = randomLonLat(minLon, maxLon, minLat, maxLat);
            LocationPoint locationPoint = new LocationPoint(bigDecimals[0], bigDecimals[1]);
            carVo.setLocationPoint(locationPoint);
            carList.add(carVo);
        }
        return bulkIndex(carList);
    }
}
