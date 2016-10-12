package com.yimayhd.mapsearch.testmysql.model;

public class GeoPosition {
	
	private long id;

	private String name;

	private double lat;

	private double lng;

	private double distance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public GeoPosition() {
		super();
	}

	public GeoPosition(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	
	//

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + ", lat=" + lat + ", lng=" + lng + ", distance=" + distance
				+ "]";
	}
}
