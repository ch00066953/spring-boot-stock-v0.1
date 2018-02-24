package download;

import java.util.List;

import stock.StockInfo1;
import tool.StringFunction;
import tool.fileAnalysis.HttpUtil;
import tool.rep.Replace;

public class DownLoadMarket {
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
		String sStockRep = "stock";
		String sStockAll = sStockNo;
		Replace p = new Replace(this.urlStr);
		if (sStockNo.startsWith("6"))
			sStockAll = sStockNo + ".ss";
		else
			sStockAll = sStockNo + ".sz";
		String urlStr=p.rep(sStockRep,sStockAll).getUrl();
		tool.DownLoad.createPath(ourputFile);
		allFilePath=this.ourputFile+"\\"+sStockNo+".csv";
		
		HttpUtil.downloadFile(allFilePath,urlStr);
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
		DownLoadMarket d = new DownLoadMarket();
		d.getMarket("600535");
	}
}
