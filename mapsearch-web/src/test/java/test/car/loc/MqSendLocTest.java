package test.car.loc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.yimayhd.mapsearch.client.domain.param.CarLocTO;
import com.yimayhd.mapsearch.client.topic.MapSearchTopic;
import com.yimayhd.mapsearch.mq.common.MsgSender;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-mysqlCarLoc.xml")
public class MqSendLocTest {

	@Autowired
    private MsgSender msgSender; 
	
	@Test //发消息
	public void test1(){
		
		CarLocTO message=new CarLocTO();
		
		message.setCarId(199999);
		message.setDirection(271);
		message.setGpsTime("gps time");
		message.setLat(42);
		message.setLng(118);
		message.setSpeed(80);
		
		SendResult r = msgSender.sendMessage(message, MapSearchTopic.CARLOC.getTopic(), MapSearchTopic.CARLOC.getTags());
		System.out.println(r);
	}
	
	@Test
	public void test2(){
		
		CarLocTO message=new CarLocTO();
		
		message.setCarId(199999);
		message.setOrderId(123456);
		
		message.setDirection(271);
		message.setGpsTime("gps time");
		message.setLat(42);
		message.setLng(118);
		message.setSpeed(80);
		
		SendResult r = msgSender.sendMessage(message, MapSearchTopic.CARTRACE.getTopic(), MapSearchTopic.CARTRACE.getTags());
		System.out.println(r);
	}
}
