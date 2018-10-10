package com.ch.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.ch.component.CSVAnalysis;

import download.Path;
import download.PathMap;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import report.EReport;
import tool.ArithUtil;
import tool.DateUtil;
import tool.DownLoad;
import tool.StringX;
import tool.fileAnalysis.CSVUtils;
import tool.fileAnalysis.bean.TableBean;
import wlgtext.jsoup.HtmlTableReader;
/**
 * 按时序计算PE
 * @author lgwang
 *
 */
@Service
@Slf4j
public class PESerialCalc {

	EReport er;
	CSVAnalysis market;
	HtmlTableReader htr;
	@Setter @Getter
	private String stock = "";
	private String perNetP = "1";
	public List exportData = new ArrayList<Map>();
	public LinkedHashMap map = new LinkedHashMap();
	public TableBean tb = new TableBean();

	public String getPerNetP() {
		return perNetP;
	}

	public void setPerNetP(String perNetP) {
		this.perNetP = perNetP;
	}

	public PESerialCalc(String stock) throws Exception {
		init(stock);
	}

	public boolean hasFinish(String stock,String pathname) throws IOException {
		Path path = PathMap.getPath(pathname);
		path.setReMap(stock);
		if(path.isDownLoanToday())
			return true;
		return false;

	}
	public PESerialCalc() {
	}
	/**
	 * 财报
	 * 
	 * @throws Exception
	 */
	public void getNetProfit() throws Exception {

		log.info("获取报表");
		er = new EReport();
		Path p = getNetProfitP(stock);
		er.getReport(p.getM());
	}

	@Async
	public void dlNetProfit(String stock) throws Exception {
		Path p = getNetProfitP(stock);
		DownLoad.downLoanPath(p);
	}
	
	private Path getNetProfitP(String stock) throws IOException {
		Path p = PathMap.getPath("mainreport");
		p.setReMap(stock);
		return p;
	}

	/**
	 * 市场价格
	 * @throws Exception 
	 */
	public void getMarketValue() throws Exception {
		log.info("获取市场价格");
		
		Path p = getMarketValueP(stock);
		market = new CSVAnalysis(p.getM());
	}

	@Async
	public void dlMarketValue(String stock) throws Exception {
		Path p = getMarketValueP(stock);
		DownLoad.downLoanPath(p);
	}
	
	private Path getMarketValueP(String stock) throws IOException {
		Path p = PathMap.getPath("allmarket_wy");
		Map<String,String> m = new HashMap<String, String>();
		if (stock.startsWith("6"))
			m.put("hstype","0");
		else
			m.put("hstype","1");
		m.put("stock",stock);
		m.put("begindate","20000101");
		m.put("enddate",DateUtil.getToday(""));
		m.put("row","TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
		p.setReMap(m);
		return p;
	}
	
	/**
	 * 股本
	 * 
	 * @throws IOException
	 */
	public void getCapitalStock() throws IOException {
		log.info("获取股本");
		Path p = getCapitalP(stock);
		String id = "astockchange";
		htr = new HtmlTableReader(p, id);
	}

	@Async
	public void dlCapitalStock(String stock) throws Exception {
		Path p = getCapitalP(stock);
		DownLoad.downLoanPath(p);
	}
	
	private Path getCapitalP(String stock) throws IOException {
		Path p = PathMap.getPath("equity");
		p.setReMap(stock);
		return p;
	}
	
	/**
	 * 网上利润预计
	 * @throws IOException 
	 */
	public void netWorth() throws IOException {
		log.info("网上利润预计");
		Path p = getnetWorthP(stock);

		String id = "forecast";
		String para = "div.fr.yjyc";
		HtmlTableReader htrNw = new HtmlTableReader(p ,id ,para);
		if(htrNw.colNo.size() !=0 && isNumeric(htrNw.getRowCol(0, 2)))
			perNetP = Double.valueOf(htrNw.getRowCol(0, 2)) *10000 +"" ;
		else
			perNetP = changeNum(er.getCell("净利润", er.getsFristY()))+"";
		log.info("网上利润预计:"+perNetP);
	}

	@Async
	public void dlnetWorth(String stock) throws Exception {
		Path p = getnetWorthP(stock);
		DownLoad.downLoanPath(p);
	}
	
	private Path getnetWorthP(String stock) throws IOException {
		Path p = PathMap.getPara("worth");
		p.setReMap(stock);
		return p;
	}

	@Async
	public void downLoadAsync(String stock) throws Exception {
		downLoad(stock);
	}
	public void downLoad(String stock) throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		
		this.stock = stock;
		log.info(stock+"pegs开始下载！");
		dlNetProfit(stock);
		dlMarketValue(stock);
		dlCapitalStock(stock);
		dlnetWorth(stock); 
		sw.stop();
		log.info(stock+"pegs下载完成！耗时间：" + sw.getTotalTimeMillis());
	}
	
	@Async
	public void initAsync(String stock) throws Exception {
		this.stock = stock;
		if(hasFinish(stock,"pegs")){
			log.info(stock+"pegs已完成！");
			return;
		}
		getNetProfit();
		getMarketValue();
		getCapitalStock();
		netWorth(); 
		
		log.info("初始化完成:"+stock);
	}
	
	public void init(String stock) throws Exception {
		this.stock = stock;
		if(hasFinish(stock,"pegs")){
			log.info(stock+"pegs已完成！");
			return;
		}
		getNetProfit();
		getMarketValue();
		getCapitalStock();
		netWorth(); 

		log.info("初始化完成:"+stock);
	}
	
	
	/**
	 * 五天为计算周期的方式
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void runFAll() throws IOException {
		log.info("开始runFAll");
		log.info("日期,价格,股本,净利润,PE,增长率,PEG");
		List<List<String>> marketList = market.getTabList();
		String mar = null;
		String net = null;
		double addrate = 0;
		double pe = 0;
		double peg = 0;
		int cnt = 0; // 第一行为表头
		for (List<String> market : marketList) {
			if (cnt % 5 == 1 || cnt <= 4 && cnt > 0) {
				try {
					
					mar = htr.getRCByDate(market.get(0), "变动后A股总股本(股)");
					net = er.getCell("净利润", market.get(0).substring(0, 4)
					                 + "-12-31");
					addrate = Double.valueOf(er.getCell("净利润同比增长率", market.get(0)
					                                    .substring(0, 4) + "-12-31"));
						
				} catch (NullPointerException e) {
					net = perNetP;
					addrate = calcAddRate(perNetP, er
					                      .getCell("净利润", er.getsFristY()));
				}catch (Exception e) {
					log.info(e.getMessage());
					continue;
				}
				pe = calcPE(market.get(4), mar, net);
				if(addrate != 0)
					peg = ArithUtil.div(pe, addrate, 2);
				pe = ArithUtil.round(pe, 2);
				addrate = ArithUtil.round(Double.valueOf(addrate), 2);

			    Map row1 = new LinkedHashMap<String, String>();
				row1.put("日期", market.get(0));
				row1.put("价格", market.get(4));
				row1.put("股本", mar);
				row1.put("利润", net);
				row1.put("PE", pe);
				row1.put("增长率", addrate);
				row1.put("PEG", peg);
				exportData.add(row1);
				log.info(market.get(0) + "," + market.get(4) + ","
						+ mar + "," + net + ",\t" + pe + "," + addrate + "," + peg);
			}
			cnt++;
		}
		Path path = PathMap.getPath("pegs");
		path.setReMap(stock);
		File file = CSVUtils.createCSVFile(exportData, null, path);
	    String fileName2 = file.getName();
	    log.info("文件名称：" + fileName2);
	}
	
	/**
	 * 以每月为节点的汇总计算方式
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("unchecked")
	public void runMAll() throws Exception {
		log.info(stock+"开始runMAll");
		
		Path path = PathMap.getPath("pegs");
		path.setReMap(stock);
		if(hasFinish(stock,"pegs"))
			return;

		for(int i = 1 ;!hasFinish(stock,"mainreport");i++){
			Thread.sleep(500);
			if(i > 10){
				log.error("报表初始化未成功");
				return;
			}
		}
		
		
		log.info("日期（月）,最高价格,最低价格,股本,净利润,最高PE,最低PE,增长率,最高PEG,最低PEG");
		tb = new TableBean();
		tb.initHead("日期-前,日期-后,最高价格,最低价格,股本,净利润,最高PE,最低PE,增长率,最高PEG,最低PEG");
		tb.addTableHead();
		LinkedHashMap<String,String> allRow = new LinkedHashMap<String,String>();
		
		List<List<String>> marketList = market.getTabList();
		String mar = null;
		String net = null;
		
		double addrate = 0;
		double pe = 0;
		double peg = 0;
		int cnt = 0; // 第一行为表头
		StopWatch sw = new StopWatch();
		sw.start();  
		// 业务操作  
		for (List<String> market : marketList) {
			if (cnt > 0 && !"0".equals(market.get(4))) {
				try {
					net = er.getCell("净利润", market.get(0).substring(0, 4)
							+ "-12-31");
					addrate = Double.valueOf(er.getCell("净利润同比增长率", market.get(0)
							.substring(0, 4) + "-12-31"));
				} catch (NullPointerException e) {
					net = perNetP;
					addrate = calcAddRate(perNetP, er
							.getCell("净利润", er.getsFristY()));
				}catch (Exception e) {
					log.info(e.getMessage());
					continue;
				}
//				log.info(market.get(0));
				mar = htr.getRCByDate(market.get(0), "变动后A股总股本(股)");
				pe = calcPE(market.get(4), mar, net);
				addrate = ArithUtil.round(Double.valueOf(addrate), 2);
				
				pe = ArithUtil.round(pe, 2);
				if(addrate != 0)
					peg = ArithUtil.div(pe, addrate, 2);
				
				Map row1 = new LinkedHashMap<String, String>();
				row1.put("日期", market.get(0));
				row1.put("价格", market.get(4));
				row1.put("股本", mar);
				row1.put("净利润", net);
				row1.put("PE", pe);
				row1.put("增长率", String.valueOf(addrate));
				row1.put("PEG", peg);
				
				if(finishMonth(allRow,row1)){
					caleMonth(allRow);
					tb.add(allRow);
					allRow = new LinkedHashMap<String,String>();
				}
				addMonth(allRow,row1);
				
				exportData.add(row1);
			}
			
			cnt++;
		}
		sw.stop();  
		log.info("耗时间：" + sw.getTotalTimeMillis()+"次数"+cnt);  	
		
		File file = CSVUtils.createCSVFile(tb.getList(), null, path);
	    String fileName2 = file.getName();
	    log.info("文件名称：" + fileName2);
	}

	public boolean finishMonth(Map<String,String> allRow,Map<String,String> row){
		
		if(allRow == null || allRow.size() == 0){
			return false;
		}
		boolean result = false;
		if(!row.get("日期").substring(5, 7).equals(allRow.get("日期-前").substring(5, 7))){
			result =  true;
		}
		if(!allRow.get("股本").equals(row.get("股本"))){
			result =  true;
		}
		return result;
	}
	
	public void addMonth(Map<String,String> allRow,Map<String,String> row){
		allRow.put("日期-后", row.get("日期"));
		if(allRow.get("日期-前") == null){
			allRow.put("日期-前", row.get("日期"));
		}
		if(allRow.get("最高价格") == null){
			allRow.put("最高价格", row.get("价格"));
			allRow.put("最低价格", row.get("价格"));
		}else if (allRow.get("最高价格").compareTo(row.get("价格"))<0){
			allRow.put("最高价格", row.get("价格"));
		}
		if ((allRow.get("最低价格").compareTo(row.get("价格"))>0 || Double.valueOf(allRow.get("最低价格")) == 0)
				 && Double.valueOf(row.get("价格"))!=0){
			allRow.put("最低价格", row.get("价格"));
		}
		if(allRow.get("净利润") == null){
			allRow.put("净利润", row.get("净利润"));
		}
		if(allRow.get("股本") == null){
			allRow.put("股本", row.get("股本"));
		}
		if(allRow.get("增长率") == null){
			allRow.put("增长率", row.get("增长率"));
		}
	}
	
	public void caleMonth(Map<String,String> allRow){
		double peH = 0;
		double peL = 0;
		double pegH = 0;
		double pegL = 0;
		double addrate = Double.valueOf(allRow.get("增长率"));
		peH = calcPE(allRow.get("最高价格"), allRow.get("股本"), allRow.get("净利润"));
		peL = calcPE(allRow.get("最低价格"), allRow.get("股本"), allRow.get("净利润"));
		pegH = ArithUtil.round(peH, 2);
		pegL = ArithUtil.round(peL, 2);
		
		if(addrate != 0){
			pegH = ArithUtil.div(peH, addrate, 2);
			pegL = ArithUtil.div(pegL, addrate, 2);
		}
		allRow.put("最高PE", String.valueOf(peH));
		allRow.put("最低PE", String.valueOf(peL));
		allRow.put("最高PEG", String.valueOf(pegH));
		allRow.put("最低PEG", String.valueOf(pegL));
	}
	/**
	 * 所有日期都进行计算
	 * @throws IOException
	 */
	public void runAll() throws IOException {

		log.info("开始runAll");
		log.info("日期,价格,股本,净利润,PE,增长率");
		List<List<String>> marketList = market.getTabList();
		String mar = null;
		String net = null;
		String addrate = null;
		double pe = 0;
		int cnt = 0;
		for (List<String> market : marketList) {
			if (cnt > 0) {
				mar = htr.getRCByDate(market.get(0), "变动后A股总股本(万股)");
				String toyear = String.valueOf(new DateTime().getYear());
				if (toyear.equals(market.get(0).substring(0, 4))) {
					net = perNetP;
					addrate = String.valueOf(calcAddRate(perNetP, er
							.getCell("净利润", "2015-12-31")));
				} else {
					net = er.getCell("净利润", market.get(0).substring(0, 4)
							+ "-12-31");
					addrate = er.getCell(	"净利润同比增长率",
											market.get(0).substring(0, 4)
													+ "-12-31");

				}
				pe = calcPE(market.get(4), mar, net);
			}
			if (cnt > 0)
				log.info(market.get(0) + "," + market.get(4) + ","
						+ mar + "," + net + ",\t" + pe + "," + addrate);
			else
				log.info("日期,价格,股本,净利润,PE,增长率");
			cnt++;
		}

		/*
		 * for (List<String> table :tableList){
		 * log.info(table.get(0)+","+table.get(2)); }
		 */

		/*
		 * for(int i = 0;i<er.row.size();i++){ for(int j =
		 * 0;j<er.col.size();j++){ System.out.print(er.getCell(j, i)); }
		 * log.info(); }
		 */
	}

	/**
	 * // (perNetP - toNetP)/toNetP
	 * 
	 * @param mv
	 * @param mar
	 * @param net
	 */
	private double calcAddRate(String perNetP, String toNetP) {
		if("0".equals(toNetP))
			return 0;
		return 100*(Double.valueOf(perNetP) / Double.valueOf(toNetP) - 1);
		// TODO Auto-generated method stub

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
	
	 /**
     * 匹配是否为数字
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     * @author yutao
     * @date 2016年11月14日下午7:41:22
     */
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    
	public double changeNum(String num) {
		double dnum = 0;
		String regex ="[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regex);    
		Matcher mat = pat.matcher(num); 
		String repickStr = mat.replaceAll("");
		if(num.indexOf("亿")>0){
			dnum = Double.valueOf(repickStr) * 100000000;
		}else if(num.indexOf("万")>0){
			dnum = Double.valueOf(repickStr) * 10000;
		}else if(num.indexOf("千")>0){
			dnum = Double.valueOf(repickStr) * 1000;
		}else
			dnum = Double.valueOf(repickStr);
		return dnum;
	}

	public static void main(String[] args) throws Exception {
		PESerialCalc p = new PESerialCalc("300070");
		p.setPerNetP("256300");//万
		p.runMAll();
	}

	/*
	 * PESerialCalc p = new PESerialCalc("600535"); p.setPerNetP("175000");
	 * p.runFAll(); PESerialCalc p = new PESerialCalc("300017");
	 * p.setPerNetP("80000"); p.run();
	 */

}
