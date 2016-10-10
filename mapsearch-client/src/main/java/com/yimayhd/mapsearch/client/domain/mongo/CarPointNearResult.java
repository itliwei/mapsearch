package com.yimayhd.mapsearch.client.domain.mongo;

import com.yimayhd.mapsearch.client.result.BaseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class CarPointNearResult extends BaseResult<CarPoint> implements Serializable {
    private static final long serialVersionUID = 1l;

    CarPointNearQuery carPointNearQuery;

    List<CarPointResult> carPointList;

    public CarPointNearQuery getCarPointNearQuery() {
        return carPointNearQuery;
    }

    public void setCarPointNearQuery(CarPointNearQuery carPointNearQuery) {
        this.carPointNearQuery = carPointNearQuery;
    }

    public List<CarPointResult> getCarPointList() {
        return carPointList;
    }

    public void setCarPointList(List<CarPointResult> carPointList) {
        this.carPointList = carPointList;
    }
}
