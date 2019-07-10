package cn.cy.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 主题消息发布者
 * Created by 30721 on 2019/7/9.
 */
@Service
public class ActiveMQTopicPublisher {

    private final static Logger logger = LoggerFactory.getLogger(ActiveMQTopicPublisher.class);

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendTopicMessage(Destination destination, String message) {
        logger.info("发布者发布一条话题：{}。", message);
        this.jmsTemplate.convertAndSend(destination,message);
    }
}
