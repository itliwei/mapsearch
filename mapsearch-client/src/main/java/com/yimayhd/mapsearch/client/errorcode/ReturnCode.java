package com.yimayhd.mapsearch.client.errorcode;

import net.pocrd.entity.AbstractReturnCode;

import java.io.Serializable;


/**
 * 
 * 3000000, 4000000
 */
public class ReturnCode extends AbstractReturnCode implements Serializable{
	private static final long serialVersionUID = 1L;
    protected ReturnCode(String desc, int code) {
        super(desc, code);
    }

    protected ReturnCode(int code, AbstractReturnCode display) {
        super(code, display);
    }

    /** ===============系统code================  */
    public static final int                      C_WRITE_DB_FAILED = 3000001;
    public static final ReturnCode WRITE_DB_FAILED   = new ReturnCode("写入数据库错误", C_WRITE_DB_FAILED);

    public static final int                      C_READ_DB_FAILED = 3000002;
    public static final ReturnCode READ_DB_FAILED   = new ReturnCode("数据库读取操作失败", C_READ_DB_FAILED);

    public static final int                      C_SYSTEM_EXCEPTION_CODE = 3000003;
    public static final ReturnCode SYSTEM_EXCEPTION_CODE = new ReturnCode("系统错误",C_SYSTEM_EXCEPTION_CODE);

    public static final int                      C_RISK_CODE = 3000004;
    public static final ReturnCode RISK_CODE = new ReturnCode("风控",C_RISK_CODE);

    public static final int                      C_CONTENT_CAN_NOT_BE_NULL = 3000005;
    public static final ReturnCode PARAMETER_ERROR = new ReturnCode("参数错误",C_CONTENT_CAN_NOT_BE_NULL);

    public static final int                      C_USER_HAVE_NO_PERMISSION = 3000006;
    public static final ReturnCode PERMISSION_ERROR = new ReturnCode("用户无权限",C_USER_HAVE_NO_PERMISSION);
}
