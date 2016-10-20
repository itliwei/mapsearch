import com.alibaba.fastjson.JSON;
import com.yimayhd.mapsearch.client.domain.mongo.*;
import com.yimayhd.mapsearch.client.service.MongoLbsService;

import com.yimayhd.mapsearch.util.MongoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"classpath:test-application-consumer.xml"})
public class MongoLbsServiceTest {
	public static final Logger LOGGER = LoggerFactory.getLogger(MongoLbsServiceTest.class);

	@Autowired
	private MongoLbsService mongoLbsService;




	@Test
	public void testSave(){

		long t1 = System.currentTimeMillis();
		Random random = new Random();
		List<CarPoint> list = new ArrayList<CarPoint>();


		for(int i = 0;i<100000;i++){
			CarPoint carPoint =new CarPoint();
			int nextInt = random.nextInt(50)+20;

			double[] arr = MongoUtil.getRandomLocation();
			carPoint.setId(i);
			carPoint.setCarId("car"+i);
			carPoint.setTime(System.currentTimeMillis()+"");
			carPoint.setStatus(1);
			carPoint.setLongitude(arr[0]);
			carPoint.setLatitude(arr[1]);
			carPoint.setLocation(arr);
			list.add(carPoint);
		}

		int count = mongoLbsService.batchInsertCarPoint(list);
		long t2 = System.currentTimeMillis();
		System.out.println("耗时:"+(t2-t1)+" 记录数"+count);

	}

	@Test
	public void testGetPersonNearList(){
		mongoLbsService.createGeoIndex();
		CarPointNearQuery personQuery = new CarPointNearQuery();
		Random random = new Random();
		for (;;){
			int count = random.nextInt(100);
			double[] arr = MongoUtil.getRandomLocation();
			personQuery.setCount(50);
			personQuery.setDistance(count/10);
			personQuery.setLongitude(arr[0]);
			personQuery.setLatitude(arr[1]);
			CarPointNearResult personList = mongoLbsService.geoNearCarPoint(personQuery);
			LOGGER.info("**************************   数量：" +count +" 范围："+count/100 +JSON.toJSONString(personList));
//			printResult(personList, "testGetPersonNearList");
		}
	}

	@Test
	public void testUpdate() throws IOException {
		CarPoint carPoint = mongoLbsService.updateCarPoint();
		System.out.print(JSON.toJSONString(carPoint));
	}

	@Test
	public void testGetDistance() throws IOException {
		long t1 = System.currentTimeMillis();
		String locationStr = MongoUtil.getRoadLocation("abcd123456", "116.496167,39.917066;116.496149,39.917205;116.496149,39.917326", "1434077500,1434077501,1434077510", "1,1,2", "1,1,2");
		String[] locationArr = locationStr.split(";");
		double distance = 0.0;
		for (int i=locationArr.length;i>1;i--){
			String endLocation = locationArr[i-1];
			String startLocation = locationArr[i - 2];
			String[] endSplit = endLocation.split(",");
			String[] startSplit = startLocation.split(",");
			distance =  distance+ MongoUtil.getLineDistance(Double.parseDouble(startSplit[0]),Double.parseDouble(startSplit[1]),Double.parseDouble(endSplit[0]),Double.parseDouble(endSplit[1]));
		}
		long t2 = System.currentTimeMillis();
		System.out.println(distance+"...................................."+(t2-t1));
	}


	@Test
	public void testDLong() throws IOException {
		long t1 = System.currentTimeMillis();
		String locationStr = MongoUtil.getRoadLocation("abcd123456", "116.496167,39.917066;116.496149,39.917205;116.496149,39.917326", "1434077500,1434077501,1434077510", "1,1,2", "1,1,2");
		String[] locationArr = locationStr.split(";");
		double distance = 0.0;
		for (int i=locationArr.length;i>1;i--){
			String endLocation = locationArr[i-1];
			String startLocation = locationArr[i - 2];
			String[] endSplit = endLocation.split(",");
			String[] startSplit = startLocation.split(",");
			distance =  distance+ MongoUtil.getLineDistance(Double.parseDouble(startSplit[0]),Double.parseDouble(startSplit[1]),Double.parseDouble(endSplit[0]),Double.parseDouble(endSplit[1]));
		}
		long t2 = System.currentTimeMillis();
		System.out.println(distance+"...................................."+(t2-t1));
	}



	/**
	 * print result
	 * @param result
	 * @param method
	 */
	private void printResult(Object result, String method) {
		String json = JSON.toJSONString(result);
		LOGGER.info("**************************   " + method + " start");
		LOGGER.info(json);
		LOGGER.info("**************************   " + method + " end\n\n");
	}
}
