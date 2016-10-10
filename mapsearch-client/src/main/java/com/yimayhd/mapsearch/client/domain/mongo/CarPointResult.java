package com.yimayhd.mapsearch.client.domain.mongo;


import java.io.Serializable;

public class CarPointResult implements Serializable {
    private static final long serialVersionUID = 1l;
    private CarPoint carPoint;
    private  double distance;

    public CarPoint getCarPoint() {
        return carPoint;
    }

    public void setCarPoint(CarPoint carPoint) {
        this.carPoint = carPoint;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}