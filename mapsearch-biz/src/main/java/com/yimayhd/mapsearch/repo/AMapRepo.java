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
        String locations = paramMap.get("locations");
        String times = paramMap.get("times");
        String directions = paramMap.get("directions");
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
     * @param origins 起始位置（可以是多个）
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
}
