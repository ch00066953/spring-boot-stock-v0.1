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
import tool.DateUtil;
import tool.StringX;
import tool.fileAnalysis.CSVUtils;
import tool.fileAnalysis.bean.TableBean;

@Service
@Slf4j
public class CompareStockRealHis {
	
	@Autowired
	StockInfoMapper StockInfoMapper;
	@Setter
	StockInfoExample example;
	/**
	 * 预测年报日期
	 */
	@Setter
	String year ;
	
	public TableBean write() throws Exception {
		Path p = PathMap.getPath("compareallpegrealhis");
		p.setReMap("year", year);
		TableBean tb = compare();
		CSVUtils.createCSVFile(tb,tb.getColAlias(), p);
		return tb;

	}
	public TableBean compare() throws Exception {
		TableBean tb = new TableBean();
		tb.initHead("ID,name,year,industry2,industry3,netrate,peg,pegs,netDisparity,netDisparitys,timeMarket,timeMarkets,score");
		for(StockInfo s : StockInfoMapper.selectByExample(example)){
			log.info(s.getId());
			tb.add(s.getId());
			tb.add(s.getCompabbre());
			tb.add(year);
			tb.add(s.getIndustry2());
			tb.add(s.getIndustry3());
			if(DateUtil.compareDate(year, s.getTimemarket())>0){
				tb.finishRow();
				continue;
			}
			
			peg(tb, s);
			netDisparity(tb, s);
			timeMarket(tb, s);
			score(tb, s);
		}
		return tb;
	}
	
	private void peg(TableBean tb,StockInfo stock) throws Exception {
		Map<String, String> m = tb.getList().get(tb.getIRow()-1);
		String pe,peg ,netrate,net = m.get("net");
		double dpeg,pegs;
		Path p = PathMap.getPath("pegs");
		p.setReMap(stock.getId());
		CSVAnalysis csv = new CSVAnalysis(p.getM());
		Map<String,String> mcsv = getpe(csv);
		if(mcsv == null ){
			tb.add("0");
			tb.add("0");
			tb.add("0");
			return;
		}
		netrate = mcsv.get("增长率");
		tb.add(netrate);
		peg = mcsv.get("最低PEG");
		tb.add(peg);
		
		dpeg = Double.valueOf(peg);
		
		if(dpeg < 0.0 || dpeg > 1.6)
			pegs = 0;
		else
			pegs = 1 - Math.abs(dpeg - 0.8);
		tb.add(String.valueOf(pegs));

	}
	
	private Map getpe(CSVAnalysis csv) {
		int i = 1;
		for(String cyear :csv.getRowhead()){
			if(i==1){
				i++;
				continue;
			}
			if(DateUtil.compareDate(year, cyear) < -200){
				
				Map<String,String> m = csv.getRowM(cyear);
				return m;
			}
			
		}
		
		return null;
	}
	
	
	private void timeMarket(TableBean tb,StockInfo stock) {
		try {
			int toyear = DateTime.parse(year).getYear();
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
		Map<String, String> m = tb.getList().get(tb.getIRow()-1);
		String r = "0";
		double rd = 0,nds = 0,netrate = Double.valueOf(m.get("netrate"));
		double[] md;			
		Path p = PathMap.getPath("mainreport");
		p.setReMap(stock.getId());
		EReport er = new EReport(p.getM());
		List<Double> l = new ArrayList<>();
		int yearno = 1;
		if(DateUtil.compareDate(year, er.getsFristY())>1){
			yearno = er.getCol(year);
			yearno = (yearno - 1)/4 +2;
		}
		for(int i = 0; er.getYearNo(yearno+i) !=0 && i <= 4; i ++){
			r = er.getCell("净利润同比增长率", er.getYearNo(yearno+i));
			if(StringX.isEmpty(r)){
				break;
			}
			l.add(Double.valueOf(r));
		}
		
		if(l.size() == 0){
			tb.add("0");
			tb.add("0");
			tb.add("0");
			tb.add("0");
			return;
		}else{
			md = new double[l.size()];
			for(int i = 0;i < l.size();i++)
				md[i] = l.get(i);
		}
//		tb.add(String.valueOf(m));
		
		rd += Math.abs(netrate -  l.get(0))/netrate;
//		rd = rd / l.size();
		tb.add(String.valueOf(rd));
		if(rd > 1)
			nds = 0;
		else if (rd <= 0)
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
