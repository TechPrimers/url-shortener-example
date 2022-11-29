package com.techprimers.encurtadorurl;


import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;



@SpringBootTest
class EncurtadorUrlControllerTest extends TestCase {
    @Autowired
    StringRedisTemplate redisTemplate;


    @Test
    void getUrl() {

    }

    @Test
    void getStatics() {
    }

    @Test
    void create() {
    }
}