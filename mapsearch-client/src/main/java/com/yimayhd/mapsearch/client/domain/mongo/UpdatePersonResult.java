package com.yimayhd.mapsearch.client.domain.mongo;

/**
 * Created by Administrator on 2016/9/30.
 */
public class UpdatePersonResult {
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
