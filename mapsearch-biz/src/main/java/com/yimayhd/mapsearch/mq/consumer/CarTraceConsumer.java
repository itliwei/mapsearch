package com.yimayhd.mapsearch.mq.consumer;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.yimayhd.mapsearch.client.domain.param.CarLocTO;
import com.yimayhd.mapsearch.client.topic.MapSearchTopic;
import com.yimayhd.mapsearch.manager.CarTraceManager;
import com.yimayhd.mapsearch.mq.common.BaseConsumer;

public class CarTraceConsumer extends BaseConsumer{
	
	@Autowired
	private CarTraceManager carTraceManager;

	@Override
	public String getTopic() {
		return MapSearchTopic.CARTRACE.getTopic();
	}

	@Override
	public String getTags() {
		return MapSearchTopic.CARTRACE.getTags();
	}

	@Override
	public boolean doConsumeMessage(Serializable message) {
		
		if(message!=null && message instanceof CarLocTO){
			CarLocTO carLocTO=(CarLocTO)message;
			return carTraceManager.saveCarTrace(carLocTO);
		}
		
		return true;//丢弃
	}

}
