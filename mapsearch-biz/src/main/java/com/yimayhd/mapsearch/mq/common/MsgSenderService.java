package com.yimayhd.mapsearch.mq.common;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.yimayhd.mapsearch.client.topic.MapSearchTopic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.yimayhd.mapsearch.util.HessianUtils;

/**
 * 
 * @author wuzhengfei
 *
 */
public class MsgSenderService implements MsgSender {
    protected static final Logger logger = LoggerFactory.getLogger(MsgSenderService.class);
    private TransactionMQProducer producer;
    private String nameServer;
    private boolean test = false;

    public static List<Checker> checkerList = new ArrayList<Checker>();
    static {
//         checkerList.add(new CreateOrderDoneChecker());
    }

    public void init() throws MQClientException {
        //为本地测试关闭metaq的接收
        if ("localTest".equals(nameServer)) {
            return;
        }
        //设置nameserver 地址
        if (StringUtils.isBlank(System.getProperty("rocketmq.namesrv.domain"))) {
            System.setProperty("rocketmq.namesrv.domain", nameServer);
        }
        TransactionCheckListener transactionCheckListener = new TransactionCheckListener() {
            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt messageExt) {
                try {
                    Object msg = HessianUtils.decode(messageExt.getBody());
                    return getCommitStatus(messageExt.getTopic(),messageExt.getTags(), msg);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
            }
        };
        producer = new TransactionMQProducer("p_sns_trans");
        //事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        //事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        //队列数
        producer.setCheckRequestHoldMax(2000);

        producer.setNamesrvAddr(nameServer);

        //设置事务会查监听器
        producer.setTransactionCheckListener(transactionCheckListener);

        producer.start();

        logger.info(MessageFormat.format("事务metaq消息启动成功!nameServer={0},group={1}",
                System.getProperty("rocketmq.namesrv.domain"), "p_tc_trans"));
    }

    private LocalTransactionState getCommitStatus(String topic, String tags, Object msg) {
        for (Checker checker :checkerList) {
            if (checker.in(topic, tags)) {
                return checker.check(msg);
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    public static void main(String[] args) {
        String a = MessageFormat.format("事务metaq消息启动成功!nameServer={0},group={1}",
                "1", "p_tc_trans");
        System.out.println(a);
    }

    /**
     * 发送消息服务
     *
     * @param message 消息对象
     * @param topic   topic名称
     * @param tag     tag名称
     * @return 发送结果
     */
    @Override
    public SendResult sendMessage(Serializable message, String topic, String tag) {
        if (test) {
            SendResult sendResult = new SendResult();
            sendResult.setSendStatus(SendStatus.SEND_OK);
            return sendResult;
        }
        try {
            logger.info("发送MQ消息, message={}", message.toString());
            Message msg = new Message(topic, tag, HessianUtils.encode(message));
            SendResult sendResult = producer.send(msg);
            logger.info("sendResult={},message={}", sendResult, message.toString());
            return sendResult;
        } catch (IOException ioe) {
            logger.error(MessageFormat.format("meta 发送消息失败 序列化失败   message={0} ", message), ioe);
            throw new RuntimeException("meta 发送消息失败 序列化失败   message=" + message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public TransactionSendResult sendMessage(Serializable msg, String topic, String tag, LocalTransactionExecuter transactionExecuter) {
        logger.info(MessageFormat.format("准备发送事务消息!topic={0},tags={1},message={2}",
                topic, tag, msg));
        TransactionSendResult sendResult = null;
        try {
            Message message = new Message(topic, tag,
                    HessianUtils.encode(msg));
            //发送事务性消息
            sendResult = producer.sendMessageInTransaction(message, transactionExecuter, null);
            if (sendResult.getLocalTransactionState() == LocalTransactionState.ROLLBACK_MESSAGE) {
                logger.warn("发送事务消息失败!topic={},tags={},message={}", topic,
                        tag, msg);
                return sendResult;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return sendResult;
        }

        return sendResult;
    }

    @Override
    public TransactionSendResult sendMessage(Serializable message, MapSearchTopic mapSearchTopic, LocalTransactionExecuter transactionExecuter) {
        return sendMessage(message, mapSearchTopic.getTopic(), mapSearchTopic.getTags(),transactionExecuter);
    }


    public void destroy() {
        if (producer != null) {
            producer.shutdown();
            logger.info("metaq 发送端关闭成功;rocketmq.namesrv.domain={}",
                    System.getProperty("rocketmq.namesrv.domain"));
        }
    }

    /**
     * Setter method for property <tt>nameServer</tt>.
     *
     * @param nameServer value to be assigned to property nameServer
     */
    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }
}
