package com.yimayhd.mapsearch.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by liwei on 2016/10/18.
 * 获取随机点工具类
 */
public class RandomPointUtils {

    public static void main(String[] args){
//        MyLatLng A=new MyLatLng(116.352466,40.007721);
//        MyLatLng B=new MyLatLng(116.359075,40.008231);
//        double angle = getAngle(A, B);
//        System.out.println(angle);
//        getRoadLocation("116.478916,39.998672;116.47883,40.000308;116.478702,40.002067;116.481899,40.001853;116.484849,40.000407");
//        getRoadLocation("116.352809,40.00113;116.35253,40.005839;116.356382,40.007869;116.362325,40.008765");
//        getRoadLocation("116.347328,40.060039;116.35428,40.061369;116.359344,40.062273;116.363228,40.061632;116.365825,40.060548");
//        getRoadLocation("116.347328,40.060039;116.340097,40.05884;116.331042,40.055555;116.331213,40.051482;116.334432,40.047129;116.339989,40.040082;116.344109,40.03508");
//        getRoadLocation("116.374947,40.061588;116.385333,40.062458;116.386684,40.056464;116.395589,40.057351;116.406168,40.057597;116.412541,40.058041;116.413903,40.055807;116.413335,40.053598;116.41224,40.051996;116.411629,40.05014;116.411929,40.047668;116.412251,40.045254;116.413721,40.043052;116.415395,40.040843");
          getRoadLocation("116.479866,39.996515;116.479158,39.996794;116.478712,39.996872;116.478964,39.997945;116.478809,39.998175;116.478728,39.9973;116.478326,39.996577;116.477344,39.996108;116.475864,39.99709;116.475161,39.99716;116.473954,39.996092;116.472409,39.994686;116.473648,39.99377;116.474206,39.99342;116.474147,39.992742;116.472854,39.991579;116.470811,39.992483;116.470328,39.992783;116.469137,39.991715");
    }

    public static String getRoadLocation(String locationStr){
        String[] split = locationStr.split(";");
        int length = split.length;
        StringBuilder directionSB = new StringBuilder("1,");
        StringBuilder speedSB = new StringBuilder("20,");
        StringBuilder timeSB = new StringBuilder();
        Date date = new Date();
        long time = date.getTime() / 1000;
        timeSB.append(time);
        timeSB.append(",");
        double distance = 0;
        DecimalFormat df   = new DecimalFormat("######0");
        for (int i=1;i<length;i++){
            String str = split[i-1];
            String[] split1 = str.split(",");
            MyLatLng endLatLng = new MyLatLng(Double.parseDouble(split1[0]),Double.parseDouble(split1[1]));
            String str2 = split[i];
            String[] split2 = str2.split(",");
            MyLatLng startLatLng = new MyLatLng(Double.parseDouble(split2[0]),Double.parseDouble(split2[1]));
            //获取夹角
            double angle = getAngle(endLatLng,startLatLng);
            String angleStr = df.format(angle);
            directionSB.append(angleStr);
            directionSB.append(",");
            //获取直线距离
            double lineDistance = MongoUtil.getLineDistance(Double.parseDouble(split1[0]), Double.parseDouble(split1[1]), Double.parseDouble(split2[0]), Double.parseDouble(split2[1]));
            //获取速度
            double speed = lineDistance * 3.6 / 50>50?50:lineDistance * 3.6 / 50;
            String speedStr = df.format(speed);
            speedSB.append(speedStr);
            speedSB.append(",");
            distance = distance + lineDistance;
            //获取时间
            time = time+50;
            timeSB.append(time);
            timeSB.append(",");

        }
        String carIdLocation = null;
        try {
            carIdLocation = MongoUtil.getRoadLocation("abcd123456", locationStr, timeSB.toString(), directionSB.toString(), speedSB.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carIdLocation;
    }

    /**
     * 获取夹角
     * @param A MyLatLng
     * @param B MyLatLng
     * @return double
     */
    public  static double getAngle(MyLatLng A,MyLatLng B){
        double dx=(B.m_RadLo-A.m_RadLo)*A.Ed;
        double dy=(B.m_RadLa-A.m_RadLa)*A.Ec;
        double angle;
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

    /**
     * 坐标实体类
     */
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
