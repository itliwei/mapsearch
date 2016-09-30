package com.yimayhd.mapsearch.client.domain.mongo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/30.
 */
public class UpdatePersonResult implements Serializable {
    private static final long serialVersionUID = 1l;
    private long costTime;
    private int count;

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
