package com.ch.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch.component.HttpCellReader;
import com.ch.mapper.StockInfoMapper;
import com.ch.pojo.StockInfo;

import download.Path;
import download.PathMap;
import lombok.extern.slf4j.Slf4j;
import tool.DownLoad;
import tool.StringX;
import tool.fileAnalysis.XlsReader;
import tool.fileAnalysis.XlsxReader;


@Slf4j
@Service
@Transactional
public class CheckStock {
	
	@Autowired
	StockInfoMapper StockInfoMapper;
	@Autowired
	HttpCellReader HttpCellReader;

	public void downloanSZ() throws IOException{
		DownLoad.downLoanPath("szstock","");
	}
	
	public void downloanSH() throws IOException{
		Map<String,String> headerMap = new HashMap<>();
		headerMap.put("Referer", "http://www.sse.com.cn/assortment/stock/list/share/");
		DownLoad.downLoanPathByHead("shstock",headerMap,"");
	}
	
	public void downloanIndustry() throws IOException{
		for (StockInfo si : StockInfoMapper.selectByExample(null))
			DownLoad.downLoanPath("industry",si.getId());
		
	}
	
	public void importSZ() throws IOException {
		Path p = PathMap.getPath("szstock");
		XlsxReader xr = new XlsxReader(p.getMain());
		for(int i = 1;xr.getIRow() > i ; i++){
			log.debug(i+"");
			String no = xr.getCell(i, 0);
			if(StockInfoMapper.selectByPrimaryKey(no) != null){
				continue;
			}
			
			StockInfo si = new StockInfo(no);
			si.setCompabbre(xr.getCell(i, 1));
			si.setCompname(xr.getCell(i, 2));
			si.setEnglishname(xr.getCell(i, 3));
			si.setRegadd(xr.getCell(i, 4));
			si.setSharescode(xr.getCell(i, 5));
			si.setSharesaddre(xr.getCell(i, 6));
			si.setTimemarket(xr.getCell(i, 7));
			si.setTotalequity(xr.getCell(i, 8));
			si.setFlowequity(xr.getCell(i, 9));
			si.setBsharescode(xr.getCell(i, 10));
			StockInfoMapper.insert(si);
		}

	}
	
	public void importSH() throws IOException {
		Path p = PathMap.getPath("shstock");
		XlsReader xr = new XlsReader(p.getMain());
		for(int i = 1;xr.getIRow() > i ; i++){
			//log.debug(i+"");
			String no = xr.getCell(i, 0);
			if(StockInfoMapper.selectByPrimaryKey(no) != null){
				continue;
			}
			
			StockInfo si = new StockInfo(no);
			si.setCompabbre(xr.getCell(i, 1));
			si.setCompname(xr.getCell(i, 2));
//			si.setEnglishname(xr.getCell(i, 3));
//			si.setRegadd(xr.getCell(i, 4));
			si.setSharescode(xr.getCell(i, 1));
			si.setSharesaddre(xr.getCell(i, 2));
			si.setTimemarket(xr.getCell(i, 4));
			StockInfoMapper.insert(si);
		}
	}
	
	public void importIndustry() throws IOException{
		Path p = PathMap.getPath("industry");
		for (StockInfo si : StockInfoMapper.selectByExample(null)){
			log.info(si.getId()+"_industry");
			insertIndustry(p, si);
		}
		
	}

	public void insertIndustry(Path p, StockInfo si) throws IOException {
		String text;
		String[] industry;
		p.setReMap(si.getId());
		HttpCellReader.readHtmlCell(p, "p.threecate.fl span.tip.f14");
		text = HttpCellReader.getText();
		if(StringX.isEmpty(text))
			return;
		text = text.substring(0,text.indexOf("（共")).replace(" ", "");
		industry = text.split("--");
		si.setIndustry(industry[0]);
		si.setIndustry2(industry[1]);
		si.setIndustry3(industry[2]);
		StockInfoMapper.updateByPrimaryKeySelective(si);
	}
}
