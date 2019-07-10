package cn.cy.service.jms;

import cn.cy.config.ActiveMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 主题消息订阅者
 * Created by 30721 on 2019/7/9.
 */
@Component
public class ActiveMQTopicSubscriber {

    private Logger logger = LoggerFactory.getLogger(ActiveMQQueueConsumer.class);

    @JmsListener(destination = ActiveMQConfig.JMS_TOPIC,containerFactory = ActiveMQConfig.JMS_LISTENER_CONTAINER_FACTORY_TOPIC)
    public void onTopicMessage(String msg) {
        logger.info("订阅者收到一条主题：{}",msg);
    }
}
