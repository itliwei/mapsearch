package com.yimayhd.mapsearch.client.domain.mongo;

/**
 * Created by Administrator on 2016/9/30.
 */
public class PersonQuery {
    /** 经度 （小数点后六位）*/
    private double longitude;
    /** 纬度 （小数点后六位）*/
    private double latitude;
    /** 距离 (米)*/
    private double distance;
    /** 记录数*/
    private int count;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
