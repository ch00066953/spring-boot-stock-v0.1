package com.ch.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.component.CSVAnalysis;
import com.ch.mapper.StockInfoMapper;
import com.ch.pojo.StockInfo;
import com.ch.pojo.StockInfoExample;

import download.Path;
import download.PathMap;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import report.EReport;
import tool.StringX;
import tool.fileAnalysis.CSVUtils;
import tool.fileAnalysis.bean.TableBean;

/**
 * compareallpeg评分算法
 * @author admin
 *
 */
@Service
@Slf4j
public class CompareStock {
	
	@Autowired
	StockInfoMapper StockInfoMapper;
	@Setter
	StockInfoExample example;
	
	public TableBean write() throws Exception {
		Path p = PathMap.getPath("compareallpeg");
		TableBean tb = compare();
		CSVUtils.createCSVFile(tb,tb.getColAlias(), p);
		return tb;

	}
	public TableBean compare() throws Exception {
		TableBean tb = new TableBean();
		tb.initHead("ID,name,industry2,industry3,netrate,peg,pegs,timeMarket,timeMarkets,netDisparity,netDisparitys,score");
		for(StockInfo s : StockInfoMapper.selectByExample(example)){
			log.info(s.getId());
			tb.add(s.getId());
			tb.add(s.getCompabbre());
			tb.add(s.getIndustry2());
			tb.add(s.getIndustry3());

			peg(tb, s);
			timeMarket(tb, s);
			netDisparity(tb, s);
			score(tb, s);
		}
		return tb;
	}
	
	private void peg(TableBean tb,StockInfo stock) throws Exception {
		String peg ,netrate;
		double dpeg,pegs;
		Path p = PathMap.getPath("pegs");
		p.setReMap(stock.getId());
		CSVAnalysis csv = new CSVAnalysis(p.getM());
		if(csv.getIRow() <= 1){
			tb.add("0");
			tb.add("0");
			tb.add("0");
			return;
		}
		netrate = csv.getCell(1, "增长率");
		tb.add(netrate);
		peg = csv.getCell(1, csv.getICol()-1);
		tb.add(peg);
		
		dpeg = Double.valueOf(peg);
		
		if(dpeg < 0.4 || dpeg > 1.6)
			pegs = 0;
		else
			pegs = 1 - Math.abs(dpeg - 0.8);
		tb.add(String.valueOf(pegs));

	}
	
	private void timeMarket(TableBean tb,StockInfo stock) {
		try {
			int toyear = DateTime.now().getYear();
			int tmyear = DateTime.parse(stock.getTimemarket()).getYear();
			
			int y = toyear-tmyear;
			double s = 0;
			
			if(y > 5)
				s = 1;
			else if(y > 4)
				s = 0.9;
			else if(y > 3)
				s = 0.8;
			else if(y > 2)
				s = 0.6;
			else if(y > 1)
				s = 0.5;
			else
				s= 0;
			
			tb.add(String.valueOf(y));
			tb.add(String.valueOf(s));
		} catch (Exception e) {
			log.error(e.toString());
			tb.add("0");
			tb.add("0");
		}
		
	}
	
	private void netDisparity(TableBean tb,StockInfo stock) throws Exception {
		Map<String,String> m = tb.getList().get(tb.getIRow()-1);
		if("0".equals(m.get("pegs"))){
			tb.add("0");
			tb.add("0");
			return;
		}
				
		String r = "0";
		double rd = 0,nds = 0,netrate = Double.valueOf(m.get("netrate"));
		
		Path p = PathMap.getPath("mainreport");
		p.setReMap(stock.getId());
		EReport er = new EReport(p.getM());
		List<Double> l = new ArrayList<>();
		for(int i = 1; er.getYearNo(i) !=0 && i <= 5; i ++){
			r = er.getCell("净利润同比增长率", er.getYearNo(i));
			if(StringX.isEmpty(r)){
				break;
			}
			l.add(Double.valueOf(r));
		}
		
		if(l.size() == 0){
			tb.add("0");
			tb.add("0");
			return;
		}
		
		
		for(Double d : l){
			rd += Math.abs(netrate - d)/netrate;
		}
		rd = rd / l.size();
		tb.add(String.valueOf(rd));
		if(rd > 1)
			nds = 0;
		else if (rd < 0)
			nds = rd ;
		else
			nds = 1 - rd;
		
		tb.add(String.valueOf(nds));
	}
	
	/**
	 * 
	 */
	private void score(TableBean tb,StockInfo stock) {
		Map<String, String> m = tb.getList().get(tb.getIRow()-1);
		double pegs =Double.valueOf(m.get("pegs"));
		double[] d = new double[2];
		d[0] = Double.valueOf(m.get("timeMarkets"));
		d[1] = Double.valueOf(m.get("netDisparitys"));
		
		Min min = new Min();
		pegs = pegs * min.evaluate(d);
		
		tb.add(String.valueOf(pegs));
	}

}
