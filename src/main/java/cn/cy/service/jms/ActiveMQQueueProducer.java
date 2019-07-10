package cn.cy.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 生产者
 * Created by 30721 on 2019/7/9.
 */
@Service
public class ActiveMQQueueProducer {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMQQueueProducer.class);

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendQueueMessage(Destination destination, String message) {
        logger.info("生产者生成一条队列消息：{}。", message);
        this.jmsTemplate.convertAndSend(destination,message);
    }
}
