package com.yimayhd.mapsearch.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Administrator on 2016/9/30.
 * MongoDb工具类
 */
public class MongoUtil {

    private static final double EARTH_RADIUS = 6378137;
    //最大经纬度
    private static final double maxLat = 40.017472;
    private static final double minLat = 39.828952;
    //最小经纬度
    private static final double maxLon = 116.542984;
    private static final double minLon = 116.216141;

    /**
     * double类型相加函数
     * @param v1 double
     * @param v2 double
     * @return double
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }


    /**
     * 直线距离
     * @param lng1 double
     * @param lat1 double
     * @param lng2 double
     * @param lat2 double
     * @return double
     */
    public static double getLineDistance(double lng1,double lat1, double lng2,  double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double radDifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(radDifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        return distance;
    }


    /**
     * 获取随机经纬度
     * @return double[]
     */
    public static double[] getRandomLocation(){
        Random random = new Random();

        int nextLon = random.nextInt((int) ((maxLon-minLon)*10000));
        int nextLat = random.nextInt((int) ((maxLat-minLat)*10000));
        double lon = MongoUtil.add(minLon, 0.00001 * nextLon);
        double lat = MongoUtil.add(minLat, 0.00001 * nextLat);
        return new double[]{lon,lat};
    }


    /**
     * 获取弧度
     * @param d double
     * @return double
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static void main(String[] args) throws IOException {
        getLineDistance(116.48323761535383,39.99987900971187,116.4789,39.998603);
    }

}
