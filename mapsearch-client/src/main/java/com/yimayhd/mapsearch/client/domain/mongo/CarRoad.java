package com.yimayhd.mapsearch.client.domain.mongo;


import java.io.Serializable;

/**
 * 车在路上的信息
 */
public class CarRoad implements Serializable{
    private static final long serialVersionUID = 1l;
    //carId
    String id;
    //位置
    String locations;
    //时间
    String time;
    //方向
    String direction;
    //速度
    String speed;
    //状态
    int status;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}