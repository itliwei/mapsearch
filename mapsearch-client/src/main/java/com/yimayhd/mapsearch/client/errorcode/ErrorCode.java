package com.yimayhd.mapsearch.client.errorcode;

import com.yimayhd.mapsearch.client.domain.BaseDO;

/**
 * 
 * 3000000, 4000000
 */
public class ErrorCode extends BaseDO {
	
	public static final ErrorCode PARAM_ERROR = new ErrorCode(3000000,"参数错误");
	
	public static final ErrorCode SYSTEM_ERROR = new ErrorCode(3000001,"系统错误");

	private static final long serialVersionUID = 4452158444156167788L;
	
	private int errorCode;
	private String errorMsg;

	public ErrorCode() {

	}

	public ErrorCode(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
