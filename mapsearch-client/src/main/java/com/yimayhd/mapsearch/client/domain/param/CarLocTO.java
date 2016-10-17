package com.yimayhd.mapsearch.client.domain.param;

import com.yimayhd.mapsearch.client.domain.BaseDO;

public class CarLocTO extends BaseDO {

	private static final long serialVersionUID = 2260175617245835689L;
	
	private long carId;/*车标识*/
	
	private long orderId;/*订单*/

	private double lat;

	private double lng;

	private String gpsTime;

	private Integer direction;/*车方向*/

	private double speed;/*车速*/

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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
