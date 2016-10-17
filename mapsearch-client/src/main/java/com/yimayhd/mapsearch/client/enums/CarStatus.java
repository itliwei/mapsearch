package com.yimayhd.mapsearch.client.enums;

/**
 * 车状态
 * 
 * @author gaotingping
 *
 *         2016年10月17日 下午1:36:57
 */
public enum CarStatus {
	
	AFTERWORK(1,"司机已下班");
	
	private int status;
	
	private String des;

	private CarStatus(int status, String des) {
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
