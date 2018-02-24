package download;

import java.io.File;
import java.util.List;

import stock.StockInfo1;
import tool.DownLoad;
import tool.StringFunction;
import tool.fileAnalysis.HttpUtil;

public class DownLoadReport {
	
	//本地路径
	String ourputFile = PathUtil.LOCALPATH+"\\"+StringFunction.getToday("")+PathUtil.MAINREPORTPATH;
	//本地路径
	String allFilePath = "";
	//下载路径
	String urlStr = PathUtil.DOWNLOADMRPATH;

	/**
	 * 获取main报表
	 * @param sStockNo
	 * @throws Exception
	 */
	public void getReport(String sStockNo) throws Exception {
		String sStockRep = "StockNo";
		String urlStr=tool.DownLoad.rep(this.urlStr, sStockNo, sStockRep);
//		tool.DownLoad.createPath(ourputFile);
		allFilePath=this.ourputFile+"\\"+sStockNo+".xls";
		if(new File(allFilePath).exists()){
			System.out.println("exists");
			return;
		}
		HttpUtil.downloadFile(urlStr,allFilePath);
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
			getReport(stockno);
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
	
	PathMap pm = new PathMap();
	/**
	 * 财报
	 * 
	 * @throws Exception
	 */
	public void getNetProfit(String stock) throws Exception {

		System.out.println("获取报表");
//		EReport er = new EReport();
		Path p = PathMap.getPath("mainreport");
		DownLoad.downLoanPath(p,stock);
//		er.getReport(p.getM());
	}
	
	public static void main(String[] args) throws Exception {
		StockInfo1 si = new StockInfo1();
		List<String> s = si.getAllStockNO();
		DownLoadReport dlr = new DownLoadReport();
		for(String stock : s)
			dlr.getNetProfit(stock);
	}
}
