package com.yimayhd.mapsearch.client.domain.mongo;


public class Person{
    
    String id; 
    
    String name; 
    
    int age; 
    
    double latitude;
    
    double longitude;
    
    double[] location;
    
   
    
    
    public String getId() { 
       return id; 
    } 
    public void setId(String id) { 
       this.id = id; 
    } 
    public String getName() { 
       return name; 
    } 
    public void setName(String name) { 
       this.name = name; 
    } 
    public int getAge() { 
       return age; 
    } 
    public void setAge(int age) { 
       this.age = age; 
    }
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	
	public double[] getLocation() {
		return location;
	}
	public void setLocation(double[] location) {
		this.location = location;
	}
	
	
	
}