package com.yimayhd.mapsearch.client.domain.mongo;

/**
     * 坐标实体类
     */
public class MyLatLng {
    final static double Rc=6378137;
    final static double Rj=6356725;
    public double m_LoDeg,m_LoMin,m_LoSec;
    public double m_LaDeg,m_LaMin,m_LaSec;
    public double m_Longitude,m_Latitude;
    public double m_RadLo,m_RadLa;
    public double Ec;
    public double Ed;
    public MyLatLng(double longitude,double latitude){
        m_LoDeg=(int)longitude;
        m_LoMin=(int)((longitude-m_LoDeg)*60);
        m_LoSec=(longitude-m_LoDeg-m_LoMin/60.)*3600;

        m_LaDeg=(int)latitude;
        m_LaMin=(int)((latitude-m_LaDeg)*60);
        m_LaSec=(latitude-m_LaDeg-m_LaMin/60.)*3600;

        m_Longitude=longitude;
        m_Latitude=latitude;
        m_RadLo=longitude*Math.PI/180.;
        m_RadLa=latitude*Math.PI/180.;
        Ec=Rj+(Rc-Rj)*(90.-m_Latitude)/90.;
        Ed=Ec*Math.cos(m_RadLa);
    }
}