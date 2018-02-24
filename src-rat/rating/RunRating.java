package rating;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import parameter.Para;

import stock.NxRatingInfo;
import stock.StockInfo1;
import tool.DateUtil;
import tool.StringFunction;

public class RunRating {

	/**
	 * 执行
	 * @throws SQLException 
	 */
	public void excuse() throws SQLException {
		int i = 1;
		List<String> stockList = getAllStockNO();
		List<String[]> stockListArray = new ArrayList<String[]>();
		if(!checkDate()){
			return;
		}
		for (String stockno : stockList){
			stockListArray.add(rating(stockno));
			if(++i%100 == 1){
				insertRating(stockListArray);
				stockListArray.clear();
			}
		}
		insertRating(stockListArray);
		checkexcuse();
	}
	
	/**
	 * 检查日期
	 * @return
	 */
	public boolean checkDate(){
		System.out.println("当前设置日期为:"+Para.getDate());
		System.out.println("当前时间为:"+StringFunction.getTodayNow());
		String sYesterday = null;
		String sToday = StringFunction.getToday();
		try {
			sYesterday = DateUtil.getYesterday();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(!DateUtil.simplyValidate(Para.getDate(),"/")){
			System.out.println("日期格式错误！！！");
			return false;
		}
		if(Para.getDate().equals(sYesterday)){
			System.out.println("设置日期为昨天");
			System.out.println("当前时间为"+StringFunction.getNow());
			if(StringFunction.getNow().compareTo("19:10:00") < 1){
				System.out.println("小于19:10:00，设置日期为昨天，可以获取");
				return true;
			}
			System.out.println("大于19:10:00，设置日期为昨天，不可获取");
			return false;
		}else if (Para.getDate().equals(sToday)){
			System.out.println("设置日期为今天");
			System.out.println("当前时间为"+StringFunction.getNow());
			if(StringFunction.getNow().compareTo("17:10:00") > -1){
				System.out.println("大于17:10:00，设置日期为今天，可以获取");
				return true;
			}
			System.out.println("小于19:10:00，设置日期为今天，不可获取");
			return false;
		}else{
			System.out.println("当前时间为"+StringFunction.getTodayNow()+"，无法获取"+Para.getDate()+"数据");
			return false;
		}
	}
	
	/**
	 * 检查遗漏执行
	 * @throws SQLException 
	 */
	public int checkexcuse() throws SQLException {
		int i = 1;
		int iCheckNO = 0;
		List<String> stockList = getCheckStockNO();
		List<String[]> stockListArray = new ArrayList<String[]>();
		for (String stockno : stockList){
			stockListArray.add(rating(stockno));
			if(++i%100 == 1){
				insertRating(stockListArray);
				stockListArray.clear();
				System.out.println("第"+i+"条记录插入成功");
			}
		}
		insertRating(stockListArray);
		iCheckNO = Integer.valueOf(getUnstillCheckNO());
		System.out.println("还有"+iCheckNO+"条记录未检查");
		return iCheckNO;
	}
	
	/**
	 * 获取所有股票编号
	 * @return
	 * @throws SQLException 
	 */
	public List<String> getAllStockNO() throws SQLException{
		StockInfo1 si = new StockInfo1();
		return si.getAllStockNO();
	}
	/**
	 * 获取遗漏股票编号
	 * @return
	 * @throws SQLException 
	 */
	public List<String> getCheckStockNO() throws SQLException{
		StockInfo1 si = new StockInfo1();
		return si.getCheckStockNO();
	}
	/**
	 * 获取nx评价
	 * @param list
	 * @return 评价list
	 */
	public String[] rating(String stockNO){
		RatingStock rs = new RatingStock();
		return rs.rating(stockNO);
	}
	/**
	 * 插入数据库
	 * @param listArray
	 * @throws SQLException 
	 */
	public void insertRating(List<String[]> listArray) throws SQLException {
		NxRatingInfo nri = new NxRatingInfo();
		nri.insertRating(listArray);
	}
	
	public String getUnstillCheckNO() throws SQLException {
		StockInfo1 si = new StockInfo1();
		return si.getUnstillCheckNO();
	}
	public static void main(String[] args) throws SQLException {
		RunRating rr = new RunRating();
//		if(!rr.checkDate()){
//			return;
//		}
		rr.checkexcuse();
	}
}
