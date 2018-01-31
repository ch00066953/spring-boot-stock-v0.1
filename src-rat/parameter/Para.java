package parameter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import tool.DateUtils;
import tool.SqlTool;
import tool.StringFunction;
import tool.StringX;
import tool.fileAnalysis.HttpUtil;

public class Para {

	private static String Date = "2017/02/27";
	private static String NearDate = null;
	
	
	public static String getDate() {
		if(Date == null /*||  !StringFunction.getToday().equals(Date)*/){
			setDate(null);
			setNearDate(NearDate);
		}
		
		return Date;
	}
	public static void setDate(String date) {
		if(date == null){
			Map<String, String> params = new HashMap<String, String>();
			params.put("s", "399001.SZ");
			params.put("f", "d1");
			try {
				String a = HttpUtil.getUrlAsString("http://download.finance.yahoo.com/d/quotes.csv",params);
				Date = DateUtils.simplePDate(a);
				System.out.printf("Date= %s",Date);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Date = date;
	}
	public static String getNearDate() {
		if(NearDate == null){
			setNearDate(null);
		}
		return NearDate;
	}
	public static void setNearDate(String nearDate) {
		if(nearDate == null){
			String sql = "select distinct date from nx_rating_info where date <'" + Para.getDate() +"' order by date desc limit 1";
			try {
				NearDate = SqlTool.getSingle(sql);
				System.out.printf("Date= %s",NearDate);
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		NearDate = nearDate;
	}
	
	
}
