package com.yimayhd.mapsearch.client.domain;

import java.util.Date;

public class CarTraceDO extends BaseDO{
	
	private static final long serialVersionUID = -9184600435032718504L;

	private long id;

    private long carId;

    private long orderId;

    private double lat;

    private double lng;

    private double roadLng;

    private double roadLat;

    private String gpsTime;

    private int direction;

    private double speed;

    private Date gmtCreated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getRoadLng() {
		return roadLng;
	}

	public void setRoadLng(double roadLng) {
		this.roadLng = roadLng;
	}

	public double getRoadLat() {
		return roadLat;
	}

	public void setRoadLat(double roadLat) {
		this.roadLat = roadLat;
	}

	public String getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Date getGmtCreated() {
		return gmtCreated;
	}

	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
}