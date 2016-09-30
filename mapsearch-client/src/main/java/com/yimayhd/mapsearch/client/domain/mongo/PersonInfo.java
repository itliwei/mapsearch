package com.yimayhd.mapsearch.client.domain.mongo;


import java.io.Serializable;

public class PersonInfo implements Serializable {
    private static final long serialVersionUID = 1l;
    private Person person;
    private  double distance;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}