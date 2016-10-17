package com.yimayhd.mapsearch.client.domain.param;

import com.yimayhd.mapsearch.client.domain.BaseDO;

public class CarLocQueryDTO extends BaseDO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double lat;
	
	private double lng;
	
	private double radius;
	
	private int topN;
	
	private int status;

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

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public int getTopN() {
		return topN;
	}

	public void setTopN(int topN) {
		this.topN = topN;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
