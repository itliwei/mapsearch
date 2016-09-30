package com.yimayhd.mapsearch.client.domain.top;

import java.io.Serializable;

/**
 * Created by menhaihao on 15/7/14.
 *首页的
 */
public class HomeSubjectDO implements Serializable {
    private static final long serialVersionUID = 5587568651459646542L;

    private long subjectId;

    private int weight;

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HomeSubjectDO{" +
                "subjectId=" + subjectId +
                ", weight=" + weight +
                '}';
    }


}
