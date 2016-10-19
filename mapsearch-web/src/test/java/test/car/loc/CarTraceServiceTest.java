package test.car.loc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yimayhd.mapsearch.client.domain.param.CarTraceDTO;
import com.yimayhd.mapsearch.client.domain.param.CarTraceQueryDTO;
import com.yimayhd.mapsearch.client.result.ResultSupport;
import com.yimayhd.mapsearch.client.result.TraceQueryResult;
import com.yimayhd.mapsearch.client.service.CarTraceService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-mysqlCarLoc.xml")
public class CarTraceServiceTest {

	@Autowired
	private CarTraceService carTraceService;

	@Test //保存
	public void test1() {
		
		CarTraceDTO carTraceDTO=new CarTraceDTO();
		carTraceDTO.setOrderId(123456);
		
		carTraceDTO.setCarId(199999);
		carTraceDTO.setDirection(85);
		carTraceDTO.setGpsTime("gps time");
		carTraceDTO.setLat(40);
		carTraceDTO.setLng(116);
		carTraceDTO.setSpeed(68);
		
		ResultSupport r = carTraceService.saveCarTrace(carTraceDTO);
		System.out.println(r);
	}

	@Test // 查询
	public void test2() {
		
		CarTraceQueryDTO carTraceQueryDTO =new CarTraceQueryDTO();
		carTraceQueryDTO.setOrderId(123456);
		carTraceQueryDTO.setCarId(199999);
		carTraceQueryDTO.setPageNo(1);
		carTraceQueryDTO.setPageSize(100);
		
		TraceQueryResult r = carTraceService.queryCarTrace(carTraceQueryDTO);
		System.out.println(r);
	}
}
