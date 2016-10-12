package com.yimayhd.mapsearch.controller.mongo;

import com.alibaba.fastjson.JSON;
import com.yimayhd.mapsearch.client.domain.mongo.CarPoint;
import com.yimayhd.mapsearch.client.domain.mongo.CarPointNearQuery;
import com.yimayhd.mapsearch.client.service.MongoLbsService;
import com.yimayhd.mapsearch.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * mongo测试
 */
@Controller
@RequestMapping("/mongo")
public class
		MongoController {

	@Autowired
	private MongoLbsService mongoLbsService;


	//查询
	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public String testQuery() {
		List<CarPoint> carPoints = mongoLbsService.queryTopN(1);
		return JSON.toJSONString(carPoints);
	}
	//查询
	@ResponseBody
	@RequestMapping(value = "/queryTopN", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public String testQueryTopN() {
		List<CarPoint> carPoints = mongoLbsService.queryTopN(2000);
		return JSON.toJSONString(carPoints);
	}


	//查询附近
	@ResponseBody
	@RequestMapping(value = "/geoNearN", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
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
		return JSON.toJSONString(mongoLbsService.geoNear(personQuery));
	}

	//批量修改 单个记录
	@ResponseBody
	@RequestMapping(value = "/batchUpdateMongo", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public String testBatchUpdate() {
		long t1 = System.currentTimeMillis();
		List<CarPoint> carPoints = mongoLbsService.queryTopN(2000);
		for (CarPoint carPoint : carPoints) {
			double[] randomLocation = MongoUtil.getRandomLocation();
			carPoint.setLongitude(randomLocation[0]);
			carPoint.setLatitude(randomLocation[1]);
			carPoint.setLocation(randomLocation);

			mongoLbsService.saveCarPoint(carPoint);
		}
		long t2 = System.currentTimeMillis();
		return carPoints.size() + " cost time:" + (t2 - t1);
	}

	//随机插入1个点
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public String testInsert() {
		Random random = new Random();
		CarPoint carPoint = new CarPoint();
		double[] arr = MongoUtil.getRandomLocation();
		carPoint.setCarId(random.nextInt(100000) + "");
		carPoint.setTime(System.currentTimeMillis() + "");
		carPoint.setStatus(1);
		carPoint.setLongitude(arr[0]);
		carPoint.setLatitude(arr[1]);
		carPoint.setLocation(arr);
		int i = mongoLbsService.saveCarPoint(carPoint);
		return "" + i;
	}

	//随机插2000入个点
	@ResponseBody
	@RequestMapping(value = "/batchInsert", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
	public String testBatchInsert() {
		long t1 = System.currentTimeMillis();
		List<CarPoint> list = new ArrayList<CarPoint>();
		for (int i = 0; i < 2000; i++) {
			CarPoint carPoint = new CarPoint();

			double[] arr = MongoUtil.getRandomLocation();
			carPoint.setCarId("test" + i);
			carPoint.setTime(System.currentTimeMillis() + "");
			carPoint.setStatus(1);
			carPoint.setLongitude(arr[0]);
			carPoint.setLatitude(arr[1]);
			carPoint.setLocation(arr);
			list.add(carPoint);
		}

		int count = mongoLbsService.batchInsertCarPoint(list);
		long t2 = System.currentTimeMillis();
		return ("cost time:" + (t2 - t1) + " 记录数" + count);
	}
}
