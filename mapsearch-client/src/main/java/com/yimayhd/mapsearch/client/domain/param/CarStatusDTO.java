package com.yimayhd.mapsearch.client.domain.param;

import com.yimayhd.mapsearch.client.domain.BaseDO;

public class CarStatusDTO extends BaseDO {
	
	private static final long serialVersionUID = -8202752555747572237L;
	
	private int status;
	
	private long carId;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}
}
