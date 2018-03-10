package download;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stock.StockInfo1;
import tool.DateUtil;
import tool.DownLoad;
import tool.fileAnalysis.CSVAnalysis;

public class DownLoadMarketWY {
	//本地路径
	String ourputFile = PathUtil.MarketPath;
	//本地路径
	String allFilePath = "";
	//下载路径
	String urlStr = PathUtil.MarketUrl;

	/**
	 * 获取main报表
	 * @param sStockNo
	 * @throws Exception
	 */
	public void getMarket(String sStockNo) throws Exception {
		Path p = PathMap.getPath("allmarket_wy");
		Map<String,String> m = new HashMap<String, String>();
		if (sStockNo.startsWith("6"))
			m.put("hstype","0");
		else
			m.put("hstype","1");
		m.put("stock",sStockNo);
		m.put("begindate","2000/01/01");
		m.put("enddate",DateUtil.getToday(""));
		m.put("row","TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
		DownLoad.delete(p,m);
		DownLoad.downLoanPath("allmarket_wy",m);
		CSVAnalysis market = new CSVAnalysis(p.getM());
		System.out.println(market.getTabList());
	}
	
	/**
	 * 获取所有股票报表
	 * @throws Exception 
	 */
	public void getAllReport() throws Exception {
		StockInfo1 si = new StockInfo1();
		List<String> stockList = si.getAllStockNO();
		int i = 0;
		for(String stockno : stockList){
			getMarket(stockno);
			System.out.println(stockno+" finish "+i++);
		}
	}

	public String getOurputFile() {
		return ourputFile;
	}

	public void setOurputFile(String ourputFile) {
		this.ourputFile = ourputFile;
	}

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public String getAllFilePath() {
		return allFilePath;
	}

	public void setAllFilePath(String allFilePath) {
		this.allFilePath = allFilePath;
	}
	
	public static void main(String[] args) throws Exception {
		DownLoadMarketWY d = new DownLoadMarketWY();
		d.getMarket("002450");
	}
}
