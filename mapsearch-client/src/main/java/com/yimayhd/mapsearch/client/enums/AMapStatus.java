package com.yimayhd.mapsearch.client.enums;

/**
 * 车状态
 * 
 * @author gaotingping
 *
 *         2016年10月17日 下午1:36:57
 */
public enum AMapStatus {

	FAILURE(0,"请求失败"),
	SUCCESS(1,"请求成功");

	private int status;

	private String des;

	private AMapStatus(int status, String des) {
        this.status = status;
        this.des = des;
    }

	public int getStatus() {
		return status;
	}

	public String getDes() {
		return des;
	}

	public boolean isEquals(int status) {
		return this.status == status;
	}
}
