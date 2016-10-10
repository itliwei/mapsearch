package com.yimayhd.mapsearch.controller.geo;

import java.util.Random;

import com.yimayhd.mapsearch.client.domain.mongo.CarPointNearQuery;
import com.yimayhd.mapsearch.client.domain.mongo.CarPointNearResult;
import com.yimayhd.mapsearch.client.service.MongoLbsService;
import com.yimayhd.mapsearch.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
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
public class
		GeoController {
	
	private final static double lat=40;
	private final static double lng=116;
	
	@Autowired
	private GeoPositionService geoPositionService;

	@Autowired
	private MongoLbsService mongoLbsService;
 
	//查询
	@ResponseBody
    @RequestMapping(value="/query",method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String testQuery(){
		Random r=new Random();
		double t = (double)r.nextInt(100000)/100000;
		return JSON.toJSONString(geoPositionService.nearSearch(lat+t, lng+t,(double)r.nextInt(1000)));
	}
	
	//查询
	@ResponseBody
	@RequestMapping(value = "/queryTopN", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String testQueryTopN() {
		Random r = new Random();
		double t = (double) r.nextInt(100000) / 100000;
		return JSON.toJSONString(geoPositionService.nearSearchTopN(lat + t, lng + t, (double) r.nextInt(10000)));
	}

	//查询
	@ResponseBody
	@RequestMapping(value = "/queryMongoTopN", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String testQueryMongoTopN() {

		CarPointNearQuery personQuery = new CarPointNearQuery();
		Random random = new Random();

		double[] arr = MongoUtil.getRandomLocation();
		//最多查100条记录
		personQuery.setCount(100);
		//随机1km米到10km
		int distance = random.nextInt(10);
		personQuery.setDistance(distance);
		personQuery.setLongitude(arr[0]);
		personQuery.setLatitude(arr[1]);

		return JSON.toJSONString( mongoLbsService.geoNear(personQuery));
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
