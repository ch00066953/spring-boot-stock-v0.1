package com.ch.service.collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CollectionServiceTest {

	@Autowired
	CollectionService CollectionService;
	@Test
	public void testAddCollection() {
		String url = "http://finance.ifeng.com/",title = "",usertype = "",
				userid = "",systemtype = "information";
		CollectionService.addCollection(url, title, usertype, userid, systemtype);
		log.debug(url);
	}

}
