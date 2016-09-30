package com.yimayhd.mapsearch.client.domain.mongo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/30.
 */
public class UpdatePersonDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    /**
     * 修改条数
     */
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
