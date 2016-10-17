package com.yimayhd.mapsearch.client.result;



import java.io.Serializable;

import com.yimayhd.mapsearch.client.errorcode.ErrorCode;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public class ResultSupport implements Serializable {
    private static final long serialVersionUID = -2235152751651905167L;
    private boolean success = true;
    private String resultMsg;
    private int errorCode;

    public ResultSupport() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.resultMsg = errorCode.getErrorMsg();
        this.success = false;
        this.errorCode = errorCode.getErrorCode();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
