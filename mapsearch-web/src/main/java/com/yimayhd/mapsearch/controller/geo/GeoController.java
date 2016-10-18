package com.yimayhd.mapsearch.controller.geo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;
import com.yimayhd.mapsearch.client.domain.param.CarLocQueryDTO;
import com.yimayhd.mapsearch.client.service.CarLocService;

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
	private CarLocService carLocService;
 
	//查询
	@ResponseBody
    @RequestMapping(value="/query",method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public String testQuery(){
		Random r=new Random();
		double t = (double)r.nextInt(100000)/100000;
		
		CarLocQueryDTO carLocSearchDTO=new CarLocQueryDTO();
		
		carLocSearchDTO.setLat(lat+t);
		carLocSearchDTO.setLng(lng+t);
		carLocSearchDTO.setRadius((double)r.nextInt(1000));
		carLocSearchDTO.setTopN(100);
		
		return JSON.toJSONString(carLocService.nearSearch(carLocSearchDTO));
	}


	//随机插入个点
	@ResponseBody
    @RequestMapping(value="/add")
	public String testAdd(){

		Random r=new Random();
		double t = (double)r.nextInt(100000)/100000;

		CarLocDTO carLocDTO=new CarLocDTO();
		carLocDTO.setLat(lat+t);
		carLocDTO.setLng(lng+t);

		carLocService.saveCarLoc(carLocDTO);

		return "ok";
	}
	
	//随机更新一个点
	@ResponseBody
	@RequestMapping(value = "/update")
	public String testUpdate() {
		Random r = new Random();
		double t = (double) r.nextInt(100000) / 100000;
		long id=r.nextInt(100000);
		
		CarLocDTO carLocDTO=new CarLocDTO();
		carLocDTO.setLat(lat+t);
		carLocDTO.setLng(lng+t);
		carLocDTO.setId(id);
		
		carLocService.updateCarLoc(carLocDTO);

		return "ok";
	}
}
