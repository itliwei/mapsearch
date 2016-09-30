package com.yimayhd.mapsearch.client.domain;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class SnsPOI implements Serializable {

    private static final long serialVersionUID = -150636182886804724L;
//    //    @Description("省")
//    private String province;
////    @Description("市")
//    private String city;
////    @Description("县")
//    private String county;
////    @Description("区")
//    private String area;
////    @Description("详细")
    private String detail;
//    @Description("经度")
    private double longitude;
//    @Description("纬度")
    private double latitude;


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public static String toJsonString(SnsPOI snsPOI) {
        if (snsPOI == null) {
            return "";
        }
        return JSON.toJSONString(snsPOI);
    }

    public static SnsPOI toObject(String snsPOIString) {
        if (snsPOIString == null || snsPOIString.isEmpty()) {
            return null;
        }
        return JSON.parseObject(snsPOIString,SnsPOI.class);
    }



}