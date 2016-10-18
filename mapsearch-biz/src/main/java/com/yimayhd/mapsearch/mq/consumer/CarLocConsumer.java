package com.yimayhd.mapsearch.mq.consumer;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.mapsearch.client.domain.param.CarLocTO;
import com.yimayhd.mapsearch.client.topic.MapSearchTopic;
import com.yimayhd.mapsearch.manager.CarLocManager;
import com.yimayhd.mapsearch.mq.common.BaseConsumer;

public class CarLocConsumer extends BaseConsumer{

	@Autowired
	private CarLocManager carLocManager;
	
	@Override
	public String getTopic() {
		return MapSearchTopic.CARLOC.getTopic();
	}

	@Override
	public String getTags() {
		return MapSearchTopic.CARLOC.getTags();
	}

	@Override
	public boolean doConsumeMessage(Serializable message) {
		
		if(message!=null && message instanceof CarLocTO){
			CarLocTO carLocTO=(CarLocTO)message;
			return carLocManager.saveCarLoc(carLocTO);
		}
		
		return true;//丢弃
	}

}
