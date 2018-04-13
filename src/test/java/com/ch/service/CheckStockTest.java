package com.ch.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.ch.mapper.StockInfoMapper;
import com.ch.pojo.StockInfo;

import download.Path;
import download.PathMap;
import tool.DownLoad;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckStockTest {


	@Autowired
	StockInfoMapper StockInfoMapper;
	@Autowired
	CheckStock CheckStock;
	

	@Test
	public void testDownloanSZ() throws IOException {
		CheckStock.downloanSZ();
	}

	@Test
	public void testDownloanSH() throws IOException {
		CheckStock.downloanSH();
	}
	
	@Test
	public void testdownloanIndustry() throws IOException {
		CheckStock.downloanIndustry();
	}
	
	@Test
	public void testInsertIndustry() throws IOException {
		Path p = PathMap.getPath("industry");
		StockInfo si = StockInfoMapper.selectByPrimaryKey("000001");
		CheckStock.insertIndustry(p,si);
	}
	
	@Test
	public void testImportIndustry() throws IOException {
		CheckStock.importIndustry();
	}

	@Test
	public void testImportSZ() throws IOException {
		CheckStock.importSZ();
	}

	@Test
	public void testImportSH() throws IOException {
		CheckStock.importSH();
	}

}
