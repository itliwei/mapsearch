import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yimayhd.mapsearch.client.domain.mongo.*;
import com.yimayhd.mapsearch.client.service.MongoLbsService;

import com.yimayhd.mapsearch.util.MongoUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
 * User : jianjun.xu
 * Date : 2016/9/6
 * Time : 15:50
 * Desc :
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
		List<Person> list = new ArrayList<Person>();


		for(int i = 0;i<100;i++){
			Person person =new Person();
			int nextInt = random.nextInt(50)+20;

			double[] arr = MongoUtil.getRandomLocation();

			person.setAge(nextInt);
			person.setLongitude(arr[0]);
			person.setLatitude(arr[1]);
			person.setLocation(arr);
			person.setName("user"+i);
			list.add(person);
		}
		PersonListDO personListDO = new PersonListDO();
		personListDO.setPersonList(list);
		mongoLbsService.batchInsert(personListDO);
		long t2 = System.currentTimeMillis();
		System.out.println("耗时:"+(t2-t1)+" 记录数"+list.size());

	}

	@Test
	public void testGetPersonNearList(){

		PersonQuery personQuery = new PersonQuery();
		Random random = new Random();
		for (;;){
			int count = random.nextInt(100);
			double[] arr = MongoUtil.getRandomLocation();
			personQuery.setCount(50);
			personQuery.setDistance(count/10);
			personQuery.setLongitude(arr[0]);
			personQuery.setLatitude(arr[1]);
			PersonResult personList = mongoLbsService.getPersonList(personQuery);
			LOGGER.info("**************************   数量：" +count +" 范围："+count/100 +JSON.toJSONString(personList));
//			printResult(personList, "testGetPersonNearList");
		}
	}

	@Test
	public void testUpdatePersonList(){
		UpdatePersonDTO updatePersonDTO = new UpdatePersonDTO();
		updatePersonDTO.setCount(2000);
		UpdatePersonResult uypdatePersonResult = mongoLbsService.batchUpdatePerson(updatePersonDTO);

		printResult(uypdatePersonResult, "testGetPersonNearList");
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
