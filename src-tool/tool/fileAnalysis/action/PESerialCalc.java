package tool.fileAnalysis.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import download.Path;
import download.PathMap;
import report.EReport;
import tool.ArithUtil;
import tool.DownLoad;
import tool.StringFunction;
import tool.StringX;
import tool.fileAnalysis.CSVAnalysis;
import tool.fileAnalysis.CSVUtils;
import tool.fileAnalysis.bean.TableBean;
import tool.rep.Replace;
import wlgtext.jsoup.HtmlTableReader;
/**
 * 按时序计算PE
 * @author lgwang
 *
 */
public class PESerialCalc {

	EReport er;
	CSVAnalysis market;
	HtmlTableReader htr;
	String stock = "";
	String perNetP = "1";
	public List exportData = new ArrayList<Map>();
	public LinkedHashMap map = new LinkedHashMap();

	public String getPerNetP() {
		return perNetP;
	}

	public void setPerNetP(String perNetP) {
		this.perNetP = perNetP;
	}

	public PESerialCalc(String stock) throws Exception {
		this.stock = stock;
		init();
	}

	/**
	 * 财报
	 * 
	 * @throws Exception
	 */
	private void getNetProfit() throws Exception {

		System.out.println("获取报表");
		er = new EReport();
//		DownLoadReport dlr = new DownLoadReport();
//		dlr.getReport(stock);
		Path p = PathMap.getPath("mainreport");
		DownLoad.downLoanPath(p,stock);
		er.getReport(p.getM());
	}

	/**
	 * 市值
	 * @throws Exception 
	 */
	private void getMarketValue() throws Exception {
		System.out.println("获取市场价格");
//		String strMarketURL = "http://ichart.finance.yahoo.com/table.csv?s={$stock}";
//		Replace p = new Replace(strMarketURL);
//		if (stock.startsWith("6"))
//			strMarketURL = p.rep("stock", stock + ".ss").getUrl();
//		else
//			strMarketURL = p.rep("stock", stock + ".sz").getUrl();
		
		Path p = PathMap.getPath("allmarket_wy");
		Map<String,String> m = new HashMap<String, String>();
		if (stock.startsWith("6"))
			m.put("hstype","0");
		else
			m.put("hstype","1");
		m.put("stock",stock);
		m.put("begindate","2000/01/01");
		m.put("enddate",StringFunction.getToday(""));
		m.put("row","TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
		DownLoad.downLoanPath(p,m);
		market = new CSVAnalysis(p.getM());
	}

	/**
	 * 股本
	 * 
	 * @throws IOException
	 */
	private void getCapitalStock() throws IOException {
		System.out.println("获取股本");
		String sCapitalurl = "http://basic.10jqka.com.cn/{$stock}/equity.html#astockchange";
		Replace p = new Replace(sCapitalurl);
		sCapitalurl = p.rep("stock", stock).getUrl();
		String id = "astockchange";
		htr = new HtmlTableReader(sCapitalurl, id);
	}

	private void init() throws Exception {
		getNetProfit();
		getMarketValue();
		getCapitalStock();

		System.out.println("初始化完成:"+stock);
	}

	/**
	 * 五天为计算周期的方式
	 * @throws IOException
	 */
	public void runFAll() throws IOException {
		System.out.println("开始runFAll");
		System.out.println("日期,价格,股本,净利润,PE,增长率,PEG");
	    map.put("日期", "日期");
	    map.put("价格", "价格");
	    map.put("股本", "股本");
	    map.put("利润", "利润");
	    map.put("PE", "PE");
	    map.put("增长率", "增长率");
	    map.put("PEG", "PEG");
		List<List<String>> marketList = market.listFile;
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
					String toyear = StringFunction.getToday().substring(0, 4);
						net = er.getCell("净利润", market.get(0).substring(0, 4)
						                 + "-12-31");
						addrate = Double.valueOf(er.getCell("净利润同比增长率", market.get(0)
						                                    .substring(0, 4) + "-12-31"));
						
					pe = calcPE(market.get(4), mar, net);
				} catch (NullPointerException e) {
					net = perNetP;
					addrate = calcAddRate(perNetP, er
					                      .getCell("净利润", er.getsFristY()));
					pe = calcPE(market.get(4), mar, net);
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					continue;
				}
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
				System.out.println(market.get(0) + "," + market.get(4) + ","
						+ mar + "," + net + ",\t" + pe + "," + addrate + "," + peg);
			}
			cnt++;
		}
		String path = "D:/export/";
		File file = CSVUtils.createCSVFile(exportData, map, path, stock);
	    String fileName2 = file.getName();
	    System.out.println("文件名称：" + fileName2);
	}
	
	/**
	 * 以每月为节点的汇总计算方式
	 * @throws IOException
	 */
	public void runMAll() throws IOException {
		System.out.println("开始runMAll");
		System.out.println("日期（月）,最高价格,最低价格,股本,净利润,最高PE,最低PE,增长率,最高PEG,最低PEG");
		TableBean tb = new TableBean();
		tb.initHead("日期-前,日期-后,最高价格,最低价格,股本,净利润,最高PE,最低PE,增长率,最高PEG,最低PEG");
		tb.addTableHead();
		LinkedHashMap<String,String> allRow = new LinkedHashMap<String,String>();
		
		List<List<String>> marketList = market.listFile;
		String mar = null;
		String net = null;
		String sMonth = null;
		
		double addrate = 0;
		double pe = 0;
		double peg = 0;
		int cnt = 0; // 第一行为表头
		for (List<String> market : marketList) {
			if (cnt > 0) {
				try {
					mar = htr.getRCByDate(market.get(0), "变动后A股总股本(股)");
					String toyear = StringFunction.getToday().substring(0, 4);
					net = er.getCell("净利润", market.get(0).substring(0, 4)
							+ "-12-31");
					addrate = Double.valueOf(er.getCell("净利润同比增长率", market.get(0)
							.substring(0, 4) + "-12-31"));
					
					pe = calcPE(market.get(4), mar, net);
				} catch (NullPointerException e) {
					net = perNetP;
					addrate = calcAddRate(perNetP, er
							.getCell("净利润", er.getsFristY()));
					pe = calcPE(market.get(4), mar, net);
				}catch (Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
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
				/*System.out.println(market.get(0) + "," + market.get(4) + ","
						+ mar + "," + net + ",\t" + pe + "," + addrate + "," + peg);*/
			}
			cnt++;
		}
		String path = "D:/export/";
		File file = CSVUtils.createCSVFile(tb.getList(), tb.getList().get(0), path, stock);
		String fileName2 = file.getName();
		System.out.println("文件名称：" + fileName2);
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
		}else if ((allRow.get("最低价格").compareTo(row.get("价格"))>0 || Double.valueOf(allRow.get("最低价格")) == 0)
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

		System.out.println("开始runAll");
		System.out.println("日期,价格,股本,净利润,PE,增长率");
		List<List<String>> marketList = market.listFile;
		String mar = null;
		String net = null;
		String addrate = null;
		double pe = 0;
		int cnt = 0;
		for (List<String> market : marketList) {
			if (cnt > 0) {
				mar = htr.getRCByDate(market.get(0), "变动后A股总股本(万股)");
				String toyear = StringFunction.getToday().substring(0, 4);
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
				System.out.println(market.get(0) + "," + market.get(4) + ","
						+ mar + "," + net + ",\t" + pe + "," + addrate);
			else
				System.out.println("日期,价格,股本,净利润,PE,增长率");
			cnt++;
		}

		/*
		 * for (List<String> table :tableList){
		 * System.out.println(table.get(0)+","+table.get(2)); }
		 */

		/*
		 * for(int i = 0;i<er.row.size();i++){ for(int j =
		 * 0;j<er.col.size();j++){ System.out.print(er.getCell(j, i)); }
		 * System.out.println(); }
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
		double dmar = 0;
		String regex ="[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regex);    
	    Matcher mat = pat.matcher(mar); 
	    String repickStr = mat.replaceAll("");
		if(mar.indexOf("亿")>0){
			dmar = Double.valueOf(repickStr) * 10000;
		}else if(mar.indexOf("万")>0){
			dmar = Double.valueOf(repickStr);
		}
		return Double.valueOf(mv) * dmar / Double.valueOf(net);
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws Exception {
		PESerialCalc p = new PESerialCalc("300070");
		p.setPerNetP("235000");//万
		p.runMAll();
	}

	/*
	 * PESerialCalc p = new PESerialCalc("600535"); p.setPerNetP("175000");
	 * p.runFAll(); PESerialCalc p = new PESerialCalc("300017");
	 * p.setPerNetP("80000"); p.run();
	 */

}
