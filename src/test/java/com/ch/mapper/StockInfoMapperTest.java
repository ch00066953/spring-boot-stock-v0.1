package com.ch.mapper;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ch.pojo.StockInfo;
import com.ch.pojo.StockInfoExample;

import junit.framework.Assert;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StockInfoMapperTest {
	@Autowired
	private StockInfoMapper StockInfoMapper;
	
    //private Logger logger = LoggerFactory.getLogger(StockInfoMapperTest.class);
	
	@Test
	public void testCountByExample() {
		int i  = StockInfoMapper.countByExample(null);
		log.info(i+"");
		log.debug(i+"");
		log.debug(i+"");
	}

	@Test
	public void testDeleteByExample() {
		StockInfoMapper.deleteByExample(new StockInfoExample());
		Assert.assertEquals(0, StockInfoMapper.countByExample(new StockInfoExample()));
	}

	@Test
	public void testDeleteByPrimaryKey() {
		if(StockInfoMapper.selectByPrimaryKey("111")!= null)
			log.debug(StockInfoMapper.selectByPrimaryKey("111").getArea());
		else
			log.error("error");
		
		StockInfo Stock = StockInfoMapper.selectByPrimaryKey("000001");
		Assert.assertEquals("000001", Stock.getId());
	}

	@Test
	public void testInsert() {
		
		StockInfoMapper.insert(new StockInfo("600031"));
		StockInfoMapper.insert(new StockInfo("600032"));
		StockInfoMapper.insert(new StockInfo("600033"));
		Assert.assertEquals(3, StockInfoMapper.countByExample(new StockInfoExample()));
	}

	@Test
	public void testInsertSelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByExample() {
		List<StockInfo> l = StockInfoMapper.selectByExample(null);
	}

	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByExampleSelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKey() {
		fail("Not yet implemented");
	}

}
