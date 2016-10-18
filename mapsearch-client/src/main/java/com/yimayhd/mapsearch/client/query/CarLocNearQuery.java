package com.yimayhd.mapsearch.client.query;

public class CarLocNearQuery extends PageQuery{

	private static final long serialVersionUID = 1L;
	
	private double minLat;
	private double maxLat;
	private double minLng;
	private double maxLng;
	public double getMinLat() {
		return minLat;
	}
	public void setMinLat(double minLat) {
		this.minLat = minLat;
	}
	public double getMaxLat() {
		return maxLat;
	}
	public void setMaxLat(double maxLat) {
		this.maxLat = maxLat;
	}
	public double getMinLng() {
		return minLng;
	}
	public void setMinLng(double minLng) {
		this.minLng = minLng;
	}
	public double getMaxLng() {
		return maxLng;
	}
	public void setMaxLng(double maxLng) {
		this.maxLng = maxLng;
	}
	
	
	public void setBoxArea(double [] p){
		minLat=p[0];
		maxLat=p[1];
		minLng=p[2];
		maxLng=p[3];
	}
}
