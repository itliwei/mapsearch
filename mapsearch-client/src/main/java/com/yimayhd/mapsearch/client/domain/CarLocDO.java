package com.yimayhd.mapsearch.client.domain;

import java.util.Date;

public class CarLocDO extends BaseDO{
	
	private static final long serialVersionUID = -6416210048981404112L;

	private long id;

    private long carId;

    private int status;

    private double lat;

    private double lng;

    private String gpsTime;

    private int direction;

    private double speed;

    private Date gmtCreated;

    private Date gmtModified;
    
    
    //tmp
    private double distance;


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


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
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


	public Date getGmtModified() {
		return gmtModified;
	}


	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}
}