package com.yimayhd.mapsearch.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Administrator on 2016/9/30.
 */
public class MongoUtil {

    private static double EARTH_RADIUS = 6378137;
    //最大经纬度
    private static final double maxLat = 40.017472;
    private static final double minLat = 39.828952;
    //最小经纬度
    private static final double maxLon = 116.542984;
    private static final double minLon = 116.216141;

    /**
     * double类型相加函数
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }


    /**
     * 直线距离
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getLineDistance(double lng1,double lat1, double lng2,  double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
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
        double[] arr =new double[]{lon,lat};
        return arr;
    }

    /**
     * 获取抓路信息
     * @param carid String
     * @param locations String
     * @param time String
     * @param direction String
     * @param speed String
     * @return
     * @throws IOException
     */
    public static  String  getRoadLocation(String carid,String locations,String time,String direction,String speed ) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        long t = System.currentTimeMillis();
        long t1 = System.currentTimeMillis();

        HttpGet httpget = new HttpGet(
                "http://restapi.amap.com/v3/autograsp?key=959e9ee93388f4bd5a144aabcc884a2e&carid="+carid+"&locations="+locations+"&time="+time+"&direction="+direction+"&speed="+speed);
        // 执行
        HttpResponse response = httpclient.execute(httpget);
        long t2 = System.currentTimeMillis();
        System.out.println("一次请求时长："+(t2-t1));
        //返回状态
        int code = response.getStatusLine().getStatusCode();

        StringBuilder sb = new StringBuilder();
        if(code==200){
            // 显示结果
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity);
            JSONObject jo =JSONObject.parseObject(string);
            String status = jo.getString("status");
            if ("1".equals(status)){
                JSONArray roads = jo.getJSONArray("roads");
                for (int i=0;i<roads.size();i++){
                    JSONObject road = (JSONObject) roads.get(i);
                    sb.append(road.getString("crosspoint"));
                    sb.append(";");
                }
            }


        }
        return sb.toString();
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }




    public static void main(String[] args){
        double distance = 0.0;

        /*for ()
        getLineDistance();*/
    }

}
