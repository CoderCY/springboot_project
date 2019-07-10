package cn.cy.jms;

import cn.cy.config.ActiveMQConfig;
import cn.cy.service.jms.ActiveMQQueueProducer;
import cn.cy.service.jms.ActiveMQTopicPublisher;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by 30721 on 2019/7/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsTest {

    @Resource
    private ActiveMQQueueProducer activeMQQueueProducer;
    @Resource
    private ActiveMQTopicPublisher activeMQTopicPublisher;

    @Test
    public void test() {
        activeMQQueueProducer.sendQueueMessage(new ActiveMQQueue(ActiveMQConfig.JMS_QUEUE), "我是队列");
        activeMQTopicPublisher.sendTopicMessage(new ActiveMQTopic(ActiveMQConfig.JMS_TOPIC), "我是话题");
    }
}
