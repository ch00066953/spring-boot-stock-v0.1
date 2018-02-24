package filter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import download.Path;
import download.PathMap;

import jxl.read.biff.BiffException;
import report.EReport;
import stock.StockInfo1;
import tool.DownLoad;
import analyse.mainreport.ReturnEquity;
import analyse.mainreport.ShareCapitalFund;

/**
 * 查询器 查询特定指标
 * @author lgwang
 *
 */
public class Filter {

	String path = "E:\\stock\\20150115\\MAINREPORT\\";
	private List<String> getAllStcok() throws SQLException {
		// TODO Auto-generated method stub
		StockInfo1 si = new StockInfo1();
		List<String> s = si.getAllStockNO();
		return s;
	}
	
	public void filter() throws SQLException, FileNotFoundException, BiffException, IOException {
		List<String> s = getAllStcok();
		List<String> l = new ArrayList<String>();
		String p = null;
		EReport er = new EReport();
		for (String so : s){
			p = path + so +".xls";
			er.getReport(p);
			ShareCapitalFund scf = new ShareCapitalFund(er);
			if(scf.isEnough(3))
				l.add(so);
		}
		System.out.println(l);
		er.close();
	}
	
	public void filterYear() throws Exception {
		List<String> s = getAllStcok();
		List<String> l = new ArrayList<String>();
		int i = 1;
		Path p;
		for (String so : s){
			System.out.println(so+":"+i++ );
			boolean scf = getRE(so);
			if(scf)
				l.add(so);
		}
		System.out.println(l);
	}

	public boolean getRE( String so) throws IOException {
		Path p;
		boolean result;
		EReport er = new EReport();
		p = PathMap.getPath("mainyear");
		p.setReMap(so);
		DownLoad.downLoanMPath(p,so);
		ReturnEquity scf = null;
		try {
			er.getReport(p.getM());
			scf = new ReturnEquity(er);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			result = scf != null &&scf.isEnough(25);
			er.close();
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		Filter f = new Filter();
		f.filterYear();
	}
}
