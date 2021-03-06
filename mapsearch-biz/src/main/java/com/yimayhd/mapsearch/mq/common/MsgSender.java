package com.yimayhd.mapsearch.mq.common;

import java.io.Serializable;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.yimayhd.mapsearch.client.topic.MapSearchTopic;

/**
 * 
 * @author wuzhengfei
 *
 */
public interface MsgSender {
    /**
     * 发送消息服务
     *
     * @param message 消息对象
     * @param topic topic名称
     * @param tag tag名称
     * @return 发送结果
     */
    SendResult sendMessage(Serializable message, String topic, String tag);

    /**
     * 发送事务消息
     * @param message
     * @param topic
     * @param tag
     * @param transactionExecuter
     * @return
     */
    TransactionSendResult sendMessage(Serializable message, String topic, String tag, LocalTransactionExecuter transactionExecuter);

    /**
     * 发送事务消息
     * @param message
     * @param transactionExecuter
     * @return
     */
    TransactionSendResult sendMessage(Serializable message, MapSearchTopic mapSearchTopic, LocalTransactionExecuter transactionExecuter);
}
