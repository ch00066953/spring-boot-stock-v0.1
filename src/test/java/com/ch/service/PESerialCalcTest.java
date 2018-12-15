package com.ch.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ch.config.ThreadConfig;
import com.ch.mapper.StockInfoMapper;
import com.ch.pojo.StockInfo;
import com.ch.service.webcrawler.HttpUtil;

import download.Path;
import download.PathImpl;
import download.PathMap;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PESerialCalcTest {
	
	@Autowired
	StockInfoMapper StockInfoMapper;
	@Autowired
	PESerialCalc PESerialCalc;
	@Autowired
	ThreadConfig ThreadConfig;
	
	@Test
	public void testGetPerNetP() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPerNetP()  {
		PESerialCalc calc;
		try {
			calc = new PESerialCalc("300070");
			calc.netWorth(); 
			log.info(calc.getPerNetP());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPESerialCalc() throws Exception {
		
	}

	@Test
	public void testRunFAll() throws Exception {
		PESerialCalc calc = new PESerialCalc("002706");
		calc.netWorth(); 
		calc.runFAll();
	}

	@Test
	public void testRunMAll() throws Exception {
		PESerialCalc.init("300070"); 
		PESerialCalc.runMAll();
	}
	@Test
	public void testRunMAll2() throws Exception {
		for(StockInfo s : StockInfoMapper.selectByExample(null)){
			log.info(s.getId()+"-------------------");
			PESerialCalc.init(s.getId()); 
			PESerialCalc.runMAll();
		}
	}
	@Test
	public void testInitAsync() throws Exception {
		for(StockInfo s : StockInfoMapper.selectByExample(null)){
			log.info(s.getId()+"-------------------");
			PESerialCalc.initAsync(s.getId()); 
			Thread.sleep(100);
			int i = ThreadConfig.seeExecutor().getPoolSize();
			log.info(i+"");
			if(i >= 20){
				Thread.sleep(i*i);
			}
		}
		Thread.sleep(10000);
	}
	@Test
	public void testdownLoad() throws Exception {
		int i = 0;
		int k = 0;
		int a = 0;
		HttpUtil.setIsproxy(false);
		for(StockInfo s : StockInfoMapper.selectByExample(null)){
			log.info(s.getId()+"-------------------");
			PESerialCalc.downLoad(s.getId()); 
			Thread.sleep(100);
			i = ThreadConfig.seeExecutor().getPoolSize();
			k = ThreadConfig.seeExecutor().getCorePoolSize();
			a = ThreadConfig.seeExecutor().getActiveCount();
			log.info(i+"池内数量,"+k+"核心数,"+a+"活跃数,");
			log.info(ThreadConfig.seeExecutor().getThreadPoolExecutor().toString());
			if(i >= 20){
				for(;ThreadConfig.seeExecutor().getActiveCount() > 20;)
					Thread.sleep(i*i);
			}
		}
		for(int j = 0; i>0 && j < 10;j++){
			i = ThreadConfig.seeExecutor().getPoolSize();
			k = ThreadConfig.seeExecutor().getCorePoolSize();
			a = ThreadConfig.seeExecutor().getActiveCount();
			log.info(i+"池内数量,"+k+"核心数,"+a+"活跃数,"+j+"结束倒记");
			Thread.sleep(10000);
		}
	}
	@Test
	public void testdownLoadAsync() throws Exception {
		int i = 0;
		int k = 0;
		int a = 0;
		for(StockInfo s : StockInfoMapper.selectByExample(null)){
			log.info(s.getId()+"-------------------");
			PESerialCalc.downLoadAsync(s.getId()); 
			Thread.sleep(100);
			i = ThreadConfig.seeExecutor().getPoolSize();
			k = ThreadConfig.seeExecutor().getCorePoolSize();
			a = ThreadConfig.seeExecutor().getActiveCount();
			log.info(i+"池内数量,"+k+"核心数,"+a+"活跃数,");
			log.info(ThreadConfig.seeExecutor().getThreadPoolExecutor().toString());
			if(i >= 20){
				for(;ThreadConfig.seeExecutor().getActiveCount() > 20;)
					Thread.sleep(i*i);
			}
		}
		for(int j = 0; i>0 && j < 10;j++){
			i = ThreadConfig.seeExecutor().getPoolSize();
			k = ThreadConfig.seeExecutor().getCorePoolSize();
			a = ThreadConfig.seeExecutor().getActiveCount();
			log.info(i+"池内数量,"+k+"核心数,"+a+"活跃数,"+j+"结束倒记");
			Thread.sleep(10000);
		}
	}

	@Test
	public void testdownLoad1() throws Exception {
		HttpUtil.setIsproxy(false);
		String stock = "300070";
		log.info(stock + "-------------------");
		PESerialCalc.downLoad(stock);
		Thread.sleep(50000);
	}

	 /**
     * 生成[0,10)区间的整数
     * 
     */
    @Test
    public void Demo6(){
        Random r = new Random();
        int n2 = r.nextInt(10);
        int n3 = Math.abs(r.nextInt() % 10);
        System.out.println("n2:"+n2);
        System.out.println("n3:"+n3);
    }
    
	@Test
	public void testFinishMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testCaleMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testRunAll() {
		fail("Not yet implemented");
	}

}
