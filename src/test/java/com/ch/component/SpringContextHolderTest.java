package com.ch.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringContextHolderTest {

	@Test
	public void testSetApplicationContext() {
		RedisTemplate test = (RedisTemplate) SpringContextHolder.getBean("redisTemplate");
		System.out.println(test);
	}

}
