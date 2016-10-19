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
//        calcute(116.4789,39.998603,0.5,5);
//        rad1(116.410778,39.907623,116.239117,39.970799);
//        MyLatLng A=new MyLatLng(116.410778,39.907623);
//        MyLatLng B=new MyLatLng(116.239117,39.970799);
//        getAngle(A,B);
        getRoadLocation("116.478916,39.998672;116.47883,40.000308;116.478702,40.002067;116.481899,40.001853;116.484849,40.000407");
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
            timeSB.append(",");

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

    private static JSONArray getJsonStr(String lonlatArr){

        return null;
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

    public  static double getAngle(MyLatLng A,MyLatLng B){
        double dx=(B.m_RadLo-A.m_RadLo)*A.Ed;
        double dy=(B.m_RadLa-A.m_RadLa)*A.Ec;
        double angle=0.0;
        angle=Math.atan(Math.abs(dx/dy))*180./Math.PI;
        double dLo=B.m_Longitude-A.m_Longitude;
        double dLa=B.m_Latitude-A.m_Latitude;
        if(dLo>0&&dLa<=0){
            angle=(90.-angle)+90;
        }
        else if(dLo<=0&&dLa<0){
            angle=angle+180.;
        }else if(dLo<0&&dLa>=0){
            angle= (90.-angle)+270;
        }
        return angle;
    }

    public static String getRoadLocation(String locationStr){
        String[] split = locationStr.split(";");
        int length = split.length;
        StringBuffer dircetionSB = new StringBuffer("1,");
        StringBuffer speedSB = new StringBuffer("1,");
        StringBuffer timeSB = new StringBuffer();
        Date date = new Date();
        long time = date.getTime() / 1000;
        timeSB.append(time);
        timeSB.append(",");
        double distance = 0;
        for (int i=1;i<length;i++){
            String str = split[i-1];
            String[] split1 = str.split(",");
            MyLatLng endLatLng = new MyLatLng(Double.parseDouble(split1[0]),Double.parseDouble(split1[1]));
            String str2 = split[i];
            String[] split2 = str2.split(",");
            MyLatLng startLatLng = new MyLatLng(Double.parseDouble(split2[0]),Double.parseDouble(split2[1]));
            double angle = getAngle(endLatLng,startLatLng);
            dircetionSB.append(angle);
            dircetionSB.append(",");
            double lineDistance = MongoUtil.getLineDistance(Double.parseDouble(split1[0]), Double.parseDouble(split1[1]), Double.parseDouble(split2[0]), Double.parseDouble(split2[1]));
            double speed = lineDistance * 3.6 / 50;
            speedSB.append(speed);
            speedSB.append(",");
            time = time+50;
            distance = distance + lineDistance;

            timeSB.append(time);
            timeSB.append(",");

        }
        String abcd123456 = null;
        try {
            abcd123456 = MongoUtil.getRoadLocation("abcd123456", locationStr, timeSB.toString(), speedSB.toString(), dircetionSB.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return abcd123456;
    }

    static class MyLatLng {
        final static double Rc=6378137;
        final static double Rj=6356725;
        double m_LoDeg,m_LoMin,m_LoSec;
        double m_LaDeg,m_LaMin,m_LaSec;
        double m_Longitude,m_Latitude;
        double m_RadLo,m_RadLa;
        double Ec;
        double Ed;
        public MyLatLng(double longitude,double latitude){
            m_LoDeg=(int)longitude;
            m_LoMin=(int)((longitude-m_LoDeg)*60);
            m_LoSec=(longitude-m_LoDeg-m_LoMin/60.)*3600;

            m_LaDeg=(int)latitude;
            m_LaMin=(int)((latitude-m_LaDeg)*60);
            m_LaSec=(latitude-m_LaDeg-m_LaMin/60.)*3600;

            m_Longitude=longitude;
            m_Latitude=latitude;
            m_RadLo=longitude*Math.PI/180.;
            m_RadLa=latitude*Math.PI/180.;
            Ec=Rj+(Rc-Rj)*(90.-m_Latitude)/90.;
            Ed=Ec*Math.cos(m_RadLa);
        }
    }



}
