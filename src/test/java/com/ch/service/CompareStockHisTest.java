package com.ch.service;

import static org.junit.Assert.fail;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ch.pojo.StockInfoExample;
import com.ch.pojo.StockInfoExample.Criteria;

import lombok.extern.slf4j.Slf4j;
import tool.StringX;
import tool.fileAnalysis.bean.TableBean;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompareStockHisTest {
	
	@Autowired
	CompareStockHis CompareStock;
	@Autowired
	StockInfoExample example;

	@Test
	public void testCompare() throws Exception {
		Criteria criteria = example.createCriteria();
//		criteria.andIndustry2EqualTo("银行");
		criteria.andIdEqualTo("001965");
		CompareStock.setExample(example);
		CompareStock.setYear("2011-12-31");
		TableBean tb = CompareStock.compare();
		for(Map<String, String> m : tb.getList()){
			log.info(m.toString());
		}
	}
	
	@Test
	public void testCompareAll() throws Exception {
		Criteria criteria = example.createCriteria();
		/*criteria.andIndustry2EqualTo("环保工程");
		CompareStock.setExample(example);*/
		TableBean tb = CompareStock.compare();
		for(Map<String, String> m : tb.getList()){
			String score = m.get("score");
			if(Double.valueOf(score)> 0.3)
				log.info(m.toString());
		}
	}
	
	@Test
	public void testWrite() throws Exception {
		Criteria criteria = example.createCriteria();
		/*criteria.andIndustry2EqualTo("环保工程");
		CompareStock.setExample(example);*/

		CompareStock.setYear("2017-12-31");
		TableBean tb = CompareStock.write();
		for(Map<String, String> m : tb.getList()){
			String score = m.get("score");
			if(!StringX.isEmpty(score)&&Double.valueOf(score)> 0.3)
				log.info(m.toString());
		}
	}

	@Test
	public void testSetExample() {
		fail("Not yet implemented");
	}

}
