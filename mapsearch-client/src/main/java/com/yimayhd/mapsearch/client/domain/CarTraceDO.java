package com.yimayhd.mapsearch.client.domain;

import java.util.Date;

public class CarTraceDO {
	
    private Long id;

    private long carId;

    private long orderId;

    private Double lat;

    private Double lng;

    private Double roadLng;

    private Double roadLat;

    private String gpsTime;

    private Integer direction;

    private Double speed;

    private Date gmtCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getRoadLng() {
        return roadLng;
    }

    public void setRoadLng(Double roadLng) {
        this.roadLng = roadLng;
    }

    public Double getRoadLat() {
        return roadLat;
    }

    public void setRoadLat(Double roadLat) {
        this.roadLat = roadLat;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}