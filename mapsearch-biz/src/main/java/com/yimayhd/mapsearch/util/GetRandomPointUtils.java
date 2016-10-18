package com.yimayhd.mapsearch.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/18.
 * liwei
 */
public class GetRandomPointUtils {

    public static void main(String[] args){
        calcute(116.4789,39.998603,0.5,5);
    }

    public static String calcute(double startlongitude,double startlatitude,double maxdist,int count){


        double radiusEarth=6372.796924;
        maxdist=maxdist/radiusEarth;
        double cosdif = Math.cos(maxdist) - 1;
        double rad360=2*Math.PI;
        double dist ;
        double[] brg = {0,180,0};
        JSONArray jsonArr = new JSONArray();
        StringBuilder locationSB =new StringBuilder();
        StringBuilder timeSB =new StringBuilder();
        StringBuilder speedSB =new StringBuilder();
        StringBuilder directionSB =new StringBuilder();
        double distance = 0;
        jsonArr = getJsonStr(startlongitude, startlatitude, radiusEarth, cosdif, rad360, brg, count, jsonArr);
        for (int i=0;i<jsonArr.size();i++){
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String lat = (String) jsonObject.get("lat");
            String lon = (String) jsonObject.get("lon");
            String speed = (String) jsonObject.get("speed");
            String direction = (String) jsonObject.get("brg");
            locationSB.append(lon);
            locationSB.append(",");
            locationSB.append(lat);
            locationSB.append(";");

            timeSB.append(new Date().getTime()/1000+50*i);
            timeSB.append(".");

            speedSB.append(speed);
            speedSB.append(",");

            directionSB.append(direction);
            directionSB.append(",");

            distance = distance + (Integer)jsonObject.get("dist");
        }
        System.out.println(distance);
        String locationStr = locationSB.toString();
        String timeStr = timeSB.toString();
        String speedStr = speedSB.toString();
        String directionStr = directionSB.toString();
        String location = null;
        try {
            location = MongoUtil.getRoadLocation("abcd123456", locationStr, timeStr, speedStr, directionStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  location;

    }

    private static JSONArray getJsonStr(double startlongitude, double startlatitude, double radiusEarth, double cosdif, double rad360, double[] brg,int size,JSONArray jsonArr) {
        double dist;
        double lat;
        double lon;
        double speed;
        double longitude=rad(startlongitude);
        double latitude = rad(startlatitude);
        double sinstartlat = Math.sin(latitude);
        double cosstartlat = Math.cos(latitude);
        dist = Math.acos(Math.random() * cosdif + 1);
        brg[0] = rad360*Math.random();

        lat=Math.asin(sinstartlat * Math.cos(dist) + cosstartlat * Math.sin(dist) * Math.cos(brg[0]));
        lon=deg(normalizeLongitude(longitude*1 + Math.atan2(Math.sin(brg[0]) * Math.sin(dist) * cosstartlat, Math.cos(dist) - sinstartlat * Math.sin(lat))));
        lat = deg(lat);
        dist=Math.round(dist * radiusEarth * 1000);
        speed = Math.round(dist * 3.6/50) ;
        brg[0]=Math.round(deg(brg[0]));
        Map map = new HashMap();
        map.put("lat",lat+"");
        map.put("lon",lon+"");
        map.put("dist",dist);
        map.put("speed",speed+"");
        map.put("brg", brg[0]+"");
        String s = JSONObject.toJSONString(map);
        System.out.println(s);
        JSONObject parse = JSON.parseObject(s);

        jsonArr.add(parse);
        size--;
        if (size > 0){
            getJsonStr(lon, lat, radiusEarth, cosdif, rad360, brg, size,jsonArr);
        }
        return jsonArr;
    }

    public static double rad(double dg){
        return (dg* Math.PI / 180);
    }

    public static double deg(double rd) {
        return (rd* 180 / Math.PI);
    }

    public static double normalizeLongitude(double lon) {
        double n=Math.PI;
        if (lon > n) {
            lon = lon - 2*n;
        } else if (lon < -n) {
            lon = lon + 2*n;
        }
        return lon;
    }



}
