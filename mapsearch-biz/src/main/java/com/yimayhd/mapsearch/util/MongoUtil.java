package com.yimayhd.mapsearch.util;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/9/30.
 */
public class MongoUtil {


    /**
     * dubbo类型相加函数
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
}
