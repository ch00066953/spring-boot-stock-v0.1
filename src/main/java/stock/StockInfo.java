package stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parameter.Para;

import tool.DateUtil;
import tool.SqlTool;
import tool.StringFunction;

import connect.JdbcConnect;

public class StockInfo {

	/**
	 * 查询所有stock
	 * @return
	 * @throws SQLException 
	 */
	public List<String> getAllStockNO() throws SQLException {
		List<String> list = new ArrayList<String>() ;
		String sql = "select * from stock_info where status = '1'";
		Connection conn = JdbcConnect.getConn();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("stockno"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcConnect.close(conn, pstmt);
		}
		
		return list;

	}
	
	/**
	 * 查询未检查的stock
	 * @return
	 * @throws SQLException 
	 */
	public List<String> getCheckStockNO() throws SQLException {
		List<String> list = new ArrayList<String>() ;
		String date = Para.getDate();
		String sql = "select * from stock_info si " +
				"where not exists (select *from nx_rating_info nr " +
				"where nr.stockno = si.stockno and nr.date = '" + date +
				"') and status = '1'";
		Connection conn = JdbcConnect.getConn();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("stockno"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcConnect.close(conn, pstmt);
		}
		
		return list;

	}
	
	/**
	 * 当天还未检测数量
	 * @return
	 * @throws SQLException
	 */
	public String getUnstillCheckNO() throws SQLException{
		String date = Para.getDate();
		String count = "0";
		String sql = "select count(1) from stock_info si " +
		"where not exists (select *from nx_rating_info nr " +
		"where nr.stockno = si.stockno and nr.date = '" + date +
		"') and status = '1'" ;
		count = SqlTool.getSingle(sql);
		return count;
	}
	
	/**
	 * 获取单个记录
	 * @param stockno
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getStock(String stockno) throws SQLException{
		String date = Para.getDate();
		String sql = "select *from nx_rating_info nr " +
		"where nr.stockno = '"+stockno+"' and nr.date = '" + date +
		"'" ;
		System.out.println(sql);
		return SqlTool.getSingleList(sql);
	}
	
	public static Map<String, String> getTodayAvg() throws SQLException {
		String date = Para.getDate();
		String sql = "select date,avg(tech),avg(finan),avg(nri.trade) from nx_rating_info nri,stock_info si " +
				"where nri.stockno = si.stockno  " +
		" and date = '" + date +
		"'" ;
		System.out.println(sql);
		return SqlTool.getSingleMap(sql);
		// TODO Auto-generated method stub

	}
}
