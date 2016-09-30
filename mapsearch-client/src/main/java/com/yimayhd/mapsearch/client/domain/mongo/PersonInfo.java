package com.yimayhd.mapsearch.client.domain.mongo;


public class PersonInfo {
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