import com.alibaba.fastjson.JSON;
import com.yimayhd.mapsearch.client.domain.mongo.*;
import com.yimayhd.mapsearch.client.service.MongoLbsService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
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
	public void testGetPersonNearList(){

		PersonQuery personQuery = new PersonQuery();
		personQuery.setCount(50);
		personQuery.setDistance(0.01);
		personQuery.setLongitude(116.216141);
		personQuery.setLatitude(39.828952);
		PersonResult personList = mongoLbsService.getPersonList(personQuery);

		printResult(personList, "testGetPersonNearList");
	}

	@Test
	public void testUpdatePersonList(){
		UpdatePersonDTO updatePersonDTO = new UpdatePersonDTO();
		updatePersonDTO.setCount(2000);
		UpdatePersonResult uypdatePersonResult = mongoLbsService.batchUpdatePerson(updatePersonDTO);

		printResult(uypdatePersonResult, "testGetPersonNearList");
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
