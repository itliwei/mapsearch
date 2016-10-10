package com.yimayhd.mapsearch.controller.geo;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yimayhd.mapsearch.testmysql.model.GeoPosition;
import com.yimayhd.mapsearch.testmysql.service.GeoPositionService;

/**
 * 
 * @author gaotingping
 *
 * 2016年10月10日 下午1:08:34
 */
@Controller
@RequestMapping("/geo")
public class GeoController {
	
	private final static double lat=40;
	private final static double lng=116;
	
	@Autowired
	private GeoPositionService geoPositionService;
 
	//查询
	@ResponseBody
    @RequestMapping(value="/query")
	public List<GeoPosition> testQuery(){
		Random r=new Random();
		double t = (double)r.nextInt(100000)/100000;
		return geoPositionService.nearSearch(lat+t, lng+t,(double)r.nextInt(1000));
	}
	
	//随机插入个点
	@ResponseBody
    @RequestMapping(value="/add")
	public String testAdd(){
		
		Random r=new Random();
		double t = (double)r.nextInt(100000)/100000;
		
		GeoPosition geoPosition=new GeoPosition();
		geoPosition.setLat(lat+t);
		geoPosition.setLng(lng+t);
		
		geoPositionService.add(geoPosition);
		
		return "ok";
	}
}
