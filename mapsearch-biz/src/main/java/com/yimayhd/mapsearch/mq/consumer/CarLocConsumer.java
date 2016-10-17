package com.yimayhd.mapsearch.mq.consumer;

import java.io.Serializable;

import com.yimayhd.mapsearch.client.domain.param.CarLocTO;
import com.yimayhd.mapsearch.client.topic.MapSearchTopic;
import com.yimayhd.mapsearch.mq.common.BaseConsumer;

public class CarLocConsumer extends BaseConsumer{

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
			System.out.println("保存位置");
		}
		
		return true;//丢弃
	}

}
