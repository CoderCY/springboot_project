package cn.cy.service.jms;

import cn.cy.config.ActiveMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 消费者
 * Created by 30721 on 2019/7/9.
 */
@Component
public class ActiveMQQueueConsumer {

    private Logger logger = LoggerFactory.getLogger(ActiveMQQueueConsumer.class);

    @JmsListener(destination = ActiveMQConfig.JMS_QUEUE,containerFactory = ActiveMQConfig.JMS_LISTENER_CONTAINER_FACTORY_QUEUE)
    public void onQueueMessage(String msg) {
        logger.info("接收到一条队列消息：{}",msg);
    }

}
