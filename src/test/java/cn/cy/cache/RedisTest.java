package cn.cy.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 30721 on 2019/7/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisCacheService redisCacheService;

    @Test
    public void test() {
        String key = "key1";
        //测试数据添加和获取
        redisCacheService.set(key, "hello");
        System.out.println(redisCacheService.get(key));
        //删除key
        redisCacheService.delete(key);
        System.out.println(redisCacheService.get(key));
        //设置时间
        redisCacheService.set(key, "HELLO WORLD", 2L);
        System.out.println(redisCacheService.get(key));
        try {
            Thread.sleep(2000L);//休眠2秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(redisCacheService.get(key));
    }
}
