package com.ch.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ch.pojo.StockInfo;
import com.ch.pojo.StockInfoExample;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockInfoMapperTest {
	@Autowired
	private StockInfoMapper StockInfoMapper;
	
	@Test
	public void testCountByExample() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteByExample() {
		StockInfoMapper.deleteByExample(new StockInfoExample());
		Assert.assertEquals(0, StockInfoMapper.countByExample(new StockInfoExample()));
	}

	@Test
	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
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
