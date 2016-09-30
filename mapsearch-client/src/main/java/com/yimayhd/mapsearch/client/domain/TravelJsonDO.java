package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;
import java.util.List;

import com.yimayhd.fhtd.validator.annot.StringValidator;

/**
 * 游记图文信息
 * 
 * @author lyl
 * 
 */
public class TravelJsonDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@StringValidator(notBlank = true, lengthMin = 1, lengthMax = 500)
	private String travelDes;

	private List<String> travelImg;

	private String detail;
	// @Description("经度")
	private double longitude;
	// @Description("纬度")
	private double latitude;

	public String getTravelDes() {
		return travelDes;
	}

	public void setTravelDes(String travelDes) {
		this.travelDes = travelDes;
	}

	public List<String> getTravelImg() {
		return travelImg;
	}

	public void setTravelImg(List<String> travelImg) {
		this.travelImg = travelImg;
	}

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


}
