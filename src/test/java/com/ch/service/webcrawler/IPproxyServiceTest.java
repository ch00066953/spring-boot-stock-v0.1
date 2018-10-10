package com.ch.service.webcrawler;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ch.mapper.IpProxyMapper;
import com.ch.pojo.IpProxyExample;
import com.ch.pojo.IpProxyExample.Criteria;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IPproxyServiceTest {

	@Autowired
	IPproxyService IPproxyService;
	@Autowired
	IpProxyMapper IpProxyMapper ;
	@Autowired
	IpProxyExample IpProxyExample;
	@Test
	public void testDownLoadIpProxy() throws IOException {
		IPproxyService.downLoadIpProxy();
	}

	@Test
	public void testInitIpProxy() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertIpProxy() throws Exception {
		IPproxyService.insertIpProxy();
	}

	@Test
	public void testTestIpProxy() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckIP() {
		IPproxyService.checkIP();
	}
	
	@Test
	public void testRecheckIP() {
		IPproxyService.recheckIP();
	}
	
	@Test
	public void testimportCheckIP(){
		Criteria criteria = IpProxyExample.createCriteria();
		criteria.andStatusNotEqualTo("0");
		log.error("有效数量"+IpProxyMapper.countByExample(IpProxyExample));
		for(int i = 0;IpProxyMapper.countByExample(IpProxyExample) <= 20 ; i++){
			
			try {
				Thread.sleep(1000);
				IPproxyService.downLoadIpProxy();
				IPproxyService.insertIpProxy();
			} catch (Exception e) {
				log.error("错误次数"+i);
			}
			
			log.info("次数"+i);
		}
	}

}
