package com.yimayhd.mapsearch.client.domain;

import java.io.Serializable;

/**
 * 活动更多信息
 * 
 * @authorlyl
 * 
 */
public class ActivityJsonDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3570475402450341578L;

	private String activityTitle;

	private String activityDec;

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityDec() {
		return activityDec;
	}

	public void setActivityDec(String activityDec) {
		this.activityDec = activityDec;
	}

}
