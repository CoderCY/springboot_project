package cn.cy.log;

import cn.cy.SpringbootProjectApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.slf4j.Logger;

/**
 * Created by 30721 on 2019/7/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {

    private Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() {
        log.error("出现异常需要发送邮件");
    }
}
