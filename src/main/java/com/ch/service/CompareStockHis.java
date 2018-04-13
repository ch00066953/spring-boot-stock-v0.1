package com.ch.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
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
public class CompareStockHis {
	
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
		Path p = PathMap.getPath("compareallpeghis");
		p.setReMap("year", year);
		TableBean tb = compare();
		CSVUtils.createCSVFile(tb,tb.getColAlias(), p);
		return tb;

	}
	public TableBean compare() throws Exception {
		TableBean tb = new TableBean();
		tb.initHead("ID,name,year,industry2,industry3,net,netrate,netDisparity,netDisparitys,peg,pegs,timeMarket,timeMarkets,score");
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
			
			netDisparity(tb, s);
			peg(tb, s);
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
		netrate = m.get("netDisparity");
		pe = getpe(csv,net);
		if("0".equals(pe) || Double.valueOf(netrate) == 0){
			tb.add("0");
			tb.add("0");
			return;
		}
		peg = String.valueOf(Double.valueOf(pe)/(Double.valueOf(netrate)*100));
		tb.add(peg);
		
		dpeg = Double.valueOf(peg);
		
		if(dpeg < 0.0 || dpeg > 1.6)
			pegs = 0;
		else
			pegs = 1 - Math.abs(dpeg - 0.8);
		tb.add(String.valueOf(pegs));

	}
	
	private String getpe(CSVAnalysis csv,String net) {
		int i = 1;
		for(String cyear :csv.getRowhead()){
			if(i==1){
				i++;
				continue;
			}
			if(DateUtil.compareDate(year, cyear) < -200){
				
				Map<String,String> m = csv.getRowM(cyear);
				String mv = m.get("最低价格");
				String mar = m.get("股本");
				return String.valueOf(calcPE(mv, mar, net));
			}
			
		}
		
		return "0";
	}
	

	/**
	 * 计算PE
	 * mv * mar / net
	 * 
	 * @param mv
	 * @param mar
	 * @param net
	 */
	private double calcPE(String mv, String mar, String net) {
		double dmar = changeNumWan(mar);
		if (StringX.isEmpty(net) || net.equals("0.0")|| net.equals("0"))
			return 0;
		return Double.valueOf(mv) * dmar / Double.valueOf(net);
		// TODO Auto-generated method stub

	}

	/**
	 * 万元基础转换
	 * @param num
	 * @return
	 */
	public double changeNumWan(String num) {
		double dnum = 0;
		String regex ="[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regex);    
	    Matcher mat = pat.matcher(num); 
	    String repickStr = mat.replaceAll("");
		if(num.indexOf("亿")>0){
			dnum = Double.valueOf(repickStr) * 10000;
		}else if(num.indexOf("万")>0){
			dnum = Double.valueOf(repickStr);
		}
		return dnum;
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
		String r = "0",net = "0";
		double m,rd = 0,nds = 0;
		double[] md;			
		Path p = PathMap.getPath("mainreport");
		p.setReMap(stock.getId());
		EReport er = new EReport(p.getM());
		List<Double> l = new ArrayList<>();
		Mean mean = new Mean();
		int yearno = 1;
		if(DateUtil.compareDate(year, er.getsFristY())>1){
			yearno = er.getCol(year);
			yearno = (yearno - 1)/4 +2;
		}
		for(int i = 0; er.getYearNo(yearno+i) !=0 && i <= 4; i ++){
			if(i == 0){
				net = er.getCell("净利润", er.getYearNo(yearno));
			}
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
		m = mean.evaluate(md);
		tb.add(String.valueOf(Double.valueOf(net) * m/100));
		tb.add(String.valueOf(m));
		
		for(Double d : l){
			rd += Math.abs(m - d)/m;
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
