package com.yimayhd.mapsearch.repo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.mapsearch.client.enums.AMapStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by liwei on 2016/10/24.
 * 高德地图调用类
 */
public class AMapRepo {
    private static final Logger logger = LoggerFactory.getLogger(AMapRepo.class);
    private static String AMAP_KEY = "959e9ee93388f4bd5a144aabcc884a2e";

    /**
     * 获取抓路信息
     * @param paramMap paramMap
     * @return String 道路抓取的点：以“;”切分
     */
    public static  String  getRoadLocation(Map<String ,String> paramMap)  {
        String carId = paramMap.get("carId");
        //相邻经纬度gps时间间隔小于300s，距离小于1km。格式为：X1,Y1;X2,Y2……，最多支持20个经纬度
        String locations = paramMap.get("locations");
        //gps时间，要求为utc时间，与参数locations指定的坐标点一一对应。多个时间之间使用半角逗号“,”分隔
        String times = paramMap.get("times");
        //车辆的方位角，与参数locations指定的坐标点一一对应,以正北方向为0度，沿顺时针方向为正值，取值范围[0,360)，精确到小数点后一位浮点型，多个方位角之间使用半角逗号“,”分隔
        String directions = paramMap.get("directions");
        //车辆行驶速度，与参数locations指定的坐标点一一对应，单位：km/h 浮点型，多个速度值之间使用半角逗号“,”分隔
        String speeds = paramMap.get("speeds");
        HttpClient httpclient = new DefaultHttpClient();
        long t1 = System.currentTimeMillis();

        HttpGet httpget = new HttpGet(
                "http://restapi.amap.com/v3/autograsp?key="+AMAP_KEY+"&carid="+carId+"&locations="+locations+"&time="+times+"&direction="+directions+"&speed="+speeds);
        // 执行
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        long t2 = System.currentTimeMillis();
        System.out.println("一次请求时长："+(t2-t1));
        //返回状态
        int code = 0;
        if (response != null) {
            code = response.getStatusLine().getStatusCode();
        }

        StringBuilder resultSB = new StringBuilder();
        if(code==200){
            // 显示结果
            HttpEntity entity = response.getEntity();
            String string = null;
            try {
                string = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }

            JSONObject jo =JSONObject.parseObject(string);
            int status = jo.getIntValue("status");
            if (status == AMapStatus.SUCCESS.getStatus()){
                JSONArray roads = jo.getJSONArray("roads");
                for (Object road1 : roads) {
                    JSONObject road = (JSONObject) road1;
                    resultSB.append(road.getString("crosspoint"));
                    resultSB.append(";");
                }
            }else {
                logger.error(jo.getString("infocode")+" "+jo.getString("info") );
            }
        }
        return resultSB.toString();
    }

    /**
     * 获取驾车行驶距离(多个司机距用户的距离)
     * @param origins 起始位置（可以是多个，坐标对见用“| ”分隔；经度和纬度用","分隔）
     * @param destination 终点位置（只能一个）
     * return   JSONArray
     */
    public static  JSONArray  getDriveDistance(String origins,String destination ){
        HttpClient httpclient = new DefaultHttpClient();
        long t1 = System.currentTimeMillis();

        HttpGet httpget = new HttpGet(
                "http://restapi.amap.com/v3/distance?key="+AMAP_KEY+"&origins="+origins+"&destination="+destination);
        // 执行
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        long t2 = System.currentTimeMillis();
        System.out.println("一次请求时长："+(t2-t1));
        //返回状态
        int code = 0;
        if (response != null) {
            code = response.getStatusLine().getStatusCode();
        }
        if(code==200){
            // 显示结果
            HttpEntity entity = response.getEntity();
            String string = null;
            try {
                string = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            JSONObject jo =JSONObject.parseObject(string);
            int status = jo.getIntValue("status");
            if (status == AMapStatus.SUCCESS.getStatus()){
                return jo.getJSONArray("results");
            }else {
                logger.error(jo.getString("infocode")+" "+jo.getString("info") );
            }
        }
        return null;
    }


    /**
     * 获取驾车行驶路线（距离、时间）
     * @param origin 起始位置（只能一个）
     * @param destination 终点位置（只能一个）
     * @param waypoints 终点位置（可以多个，以";"切割，最多16个坐标点）
     * return   JSONArray
     */
    public static  JSONObject  getDrivePath(String origin,String destination, String waypoints){
        HttpClient httpclient = new DefaultHttpClient();
        long t1 = System.currentTimeMillis();

        HttpGet httpget = new HttpGet(
                "http://restapi.amap.com/v3/direction/driving?key="+AMAP_KEY+"&origin="+origin+"&destination="+destination+"&waypoints"+waypoints);
        // 执行
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        long t2 = System.currentTimeMillis();
        System.out.println("一次请求时长："+(t2-t1));
        //返回状态
        int code = 0;
        if (response != null) {
            code = response.getStatusLine().getStatusCode();
        }
        if(code==200){
            // 显示结果
            HttpEntity entity = response.getEntity();
            String string = null;
            try {
                string = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            JSONObject jo =JSONObject.parseObject(string);
            int status = jo.getIntValue("status");
            if (status == AMapStatus.SUCCESS.getStatus()){
                JSONObject route = jo.getJSONObject("route");
                if (route != null ){
                    JSONArray paths = route.getJSONArray("paths");
                    if (paths != null && paths.size() > 0){
                        return  (JSONObject)paths.get(0);
                    }
                }
            }else {
                logger.error(jo.getString("infocode")+" "+jo.getString("info") );
            }
        }
        return null;
    }
}
