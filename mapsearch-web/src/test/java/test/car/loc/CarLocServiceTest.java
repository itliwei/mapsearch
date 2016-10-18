package test.car.loc;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yimayhd.mapsearch.client.domain.param.CarLocDTO;
import com.yimayhd.mapsearch.client.domain.param.CarLocQueryDTO;
import com.yimayhd.mapsearch.client.domain.param.CarStatusDTO;
import com.yimayhd.mapsearch.client.result.BatchQueryResult;
import com.yimayhd.mapsearch.client.result.ResultSupport;
import com.yimayhd.mapsearch.client.result.SingleQueryResult;
import com.yimayhd.mapsearch.client.service.CarLocService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-mysqlCarLoc.xml")
public class CarLocServiceTest {

	@Autowired
	private CarLocService carLocService;

	@Test // 查询
	public void test1() {
		System.out.println(carLocService);
		CarLocDTO carLocDTO = new CarLocDTO();
		carLocDTO.setCarId(1);
		SingleQueryResult l = carLocService.getCarByCarId(carLocDTO);
		System.out.println(JSON.toJSON(l));
	}

	@Test // 修改状态
	public void test2() {
		CarStatusDTO carStatusDTO = new CarStatusDTO();
		carStatusDTO.setCarId(1);
		carStatusDTO.setStatus(3);
		ResultSupport r = carLocService.updateCarStatus(carStatusDTO);
		System.out.println(r);
	}

	@Test // 修改车位置信息
	public void test3() {
		CarLocDTO carLocDTO = new CarLocDTO();

		carLocDTO.setCarId(1);
		carLocDTO.setDirection(90);
		carLocDTO.setGpsTime("gps time");
		carLocDTO.setLat(40);
		carLocDTO.setLng(116);
		carLocDTO.setSpeed(80.5);

		ResultSupport r = carLocService.updateCarLoc(carLocDTO);
		System.out.println(r);
	}

	@Test // 保存车
	public void test4() {
		CarLocDTO carLocDTO = new CarLocDTO();

		carLocDTO.setCarId(199999);
		carLocDTO.setDirection(270);
		carLocDTO.setGpsTime("gps time");
		carLocDTO.setLat(41);
		carLocDTO.setLng(117);
		carLocDTO.setSpeed(80);

		ResultSupport r = carLocService.saveCarLoc(carLocDTO);
		System.out.println(r);
	}
	
	@Test //附近搜索
	public void test5(){
		CarLocQueryDTO carLocSearchDTO=new CarLocQueryDTO();
		Random r=new Random();
		double t = (double)r.nextInt(100000)/100000;
		carLocSearchDTO.setLat(40+t);
		carLocSearchDTO.setLng(116+t);
		carLocSearchDTO.setTopN(50);
		carLocSearchDTO.setRadius(1000);
		//carLocSearchDTO.setStatus(status);
		BatchQueryResult list = carLocService.nearSearch(carLocSearchDTO);
		System.out.println(list);
	}
	
	@Test //区域搜索
	public void test6(){
		CarLocQueryDTO carLocSearchDTO=new CarLocQueryDTO();
		Random r=new Random();
		double t = (double)r.nextInt(100000)/100000;
		carLocSearchDTO.setLat(40+t);
		carLocSearchDTO.setLng(116+t);
		carLocSearchDTO.setTopN(50);
		carLocSearchDTO.setRadius(1000);
		//carLocSearchDTO.setStatus(status);
		BatchQueryResult list = carLocService.areaSearch(carLocSearchDTO);
		System.out.println(list);
	}
}
