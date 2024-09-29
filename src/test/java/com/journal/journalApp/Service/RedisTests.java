package com.journal.journalApp.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {


    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplete;

    @Disabled
    @Test
    void testSendMail() {

        redisTemplete.opsForValue().set("email", "Sanya@123");
        Object email = redisTemplete.opsForValue().get("email");
        int a= 1;
    }
}
