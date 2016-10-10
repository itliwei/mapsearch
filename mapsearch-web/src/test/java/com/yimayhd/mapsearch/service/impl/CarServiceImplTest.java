package com.yimayhd.mapsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yimayhd.mapsearch.client.domain.es.CarVo;
import com.yimayhd.mapsearch.client.domain.es.LocationPoint;
import com.yimayhd.mapsearch.client.query.PageQuery;
import com.yimayhd.mapsearch.client.service.CarService;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * CarServiceImplTest
 *
 * @author lilin
 * @date 16/10/8
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:test-application-consumer.xml"})
public class CarServiceImplTest {

    @Autowired
    private CarService carService;

    private final double maxLat = 40.017472;
    private final double minLat = 39.828952;

    private final double maxLon = 116.542984;
    private final double minLon = 116.216141;


    @Test
    public void bulkInsertTest() {
        int currentTime = 0;
        final int runTime = 100;
        final int perSize = 10000;
        List<CarVo> carList = new ArrayList<CarVo>(perSize);
        while (currentTime < runTime) {
            for (int i = currentTime * perSize; i < (currentTime + 1) * perSize; i++) {
                CarVo carVo = new CarVo();
                carVo.setId(i);
                carVo.setTitle("测试测量" + i);
                carVo.setRegisterDate(new Date());
                carVo.setOnline(i % 2);
                double[] bigDecimals = randomLonLat(minLon, maxLon, minLat, maxLat);
                LocationPoint locationPoint = new LocationPoint(bigDecimals[0], bigDecimals[1]);
                carVo.setLocationPoint(locationPoint);
                carList.add(carVo);

            }
            boolean result = carService.bulkIndex(carList);
            assertTrue(result);
            carList.clear();
            currentTime++;
        }

    }


    @Test
    public void bulkTest() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (true) {
            List<CarVo> carList = new ArrayList<CarVo>(10000);
            for (int i = 1; i <= 10000; i++) {
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
            stopWatch.reset();
            stopWatch.start();
            boolean result = carService.bulkIndex(carList);
            assertTrue(result);
            System.out.println("消耗时间" + stopWatch.getTime() + "ms");

            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static double[] randomLonLat(double minLon, double maxLon, double minLat, double maxLat) {
        BigDecimal db = new BigDecimal(Math.random() * (maxLon - minLon) + minLon);
        double lon = Double.parseDouble(db.setScale(6, BigDecimal.ROUND_HALF_UP).toString());//小数后6位
        db = new BigDecimal(Math.random() * (maxLat - minLat) + minLat);
        double lat = Double.parseDouble(db.setScale(6, BigDecimal.ROUND_HALF_UP).toString());
        return new double[]{lat, lon};
    }


    @Test
    public void geoSearchTest() {
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageNo(1);
        pageQuery.setPageSize(20);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<CarVo> carVos = carService.searchByGeoDistance(39.828952, 116.390549, 10d, pageQuery);
        stopWatch.stop();
        System.out.println("结果为" + carVos.size() + "耗时：" + stopWatch.getTime());
        System.out.println(JSONObject.toJSONString(carVos));
    }


//    @Test
//    public void setIndexTypeMappingTest() {
//        String mapping = "{\n" +
//                "            \"car\":{\n" +
//                "                \"properties\":{\n" +
//                "                    \"id\":{\n" +
//                "                        \"type\":\"long\"\n" +
//                "                    },\n" +
//                "                    \"locationPoint\":{\n" +
//                "                        \"type\":\"geo_point\"\n" +
//                "                    },\n" +
//                "                    \"registerDate\":{\n" +
//                "                        \"type\":\"long\"\n" +
//                "                    },\n" +
//                "                    \"title\":{\n" +
//                "                        \"type\":\"string\"\n" +
//                "                    },\n" +
//                "                    \"online\":{\n" +
//                "                        \"type\":\"integer\",\n" +
//                "                        \"index\":\"not_analyzed\"\n" +
//                "                    }\n" +
//                "                }\n" +
//                "            }}";
//        boolean b = carClient.setIndexTypeMapping(EsBasicEnum.CAR.getIndex(), EsBasicEnum.CAR.getType(), mapping);
//        assertTrue(b);
//    }


    @Test
    public void getById() {
        CarVo carVo = carService.searchById(1);
        assertNotNull(carVo);
    }

}
