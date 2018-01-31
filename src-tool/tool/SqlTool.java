package tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connect.JdbcConnect;

public class SqlTool {

	public static String getSingle(String sql) throws SQLException {
		Connection conn = JdbcConnect.getConn();
		String result = "0";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcConnect.close(conn,pstmt);
		}
		// 建立一个结果集,用来保存查询出来的结果
		return result;
	}
	
	/**
	 * 获取一个字段
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static List<String> getSingleList(String sql) throws SQLException {
		Connection conn = JdbcConnect.getConn();
		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcConnect.close(conn,pstmt);
		}
		// 建立一个结果集,用来保存查询出来的结果
		return list;
	}
	
	/**
	 * 遍历sql中的所有字段
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public static List<Map<String,String>> getMapList(String sql) throws SQLException {
		Connection conn = JdbcConnect.getConn();
		int i = 1;
		int ColumnCount = 0;
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String,String>() ;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSetMetaData rm = pstmt.getMetaData();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ColumnCount = rm.getColumnCount();
				map.clear();
				for (i = 1;i<=ColumnCount;i++){
					map.put(rm.getColumnLabel(i), rs.getString(i));
				}
				
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcConnect.close(conn,pstmt);
		}
		// 建立一个结果集,用来保存查询出来的结果
		return list;
	}
	
	/**
	 * 获取单个记录
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static Map<String,String> getSingleMap(String sql) throws SQLException {
		Connection conn = JdbcConnect.getConn();
		int i = 1;
		int ColumnCount = 0;
		Map<String,String> map = new HashMap<String,String>() ;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSetMetaData rm = pstmt.getMetaData();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ColumnCount = rm.getColumnCount();
				map.clear();
				for (i = 1;i<=ColumnCount;i++){
					map.put(rm.getColumnLabel(i), rs.getString(i));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcConnect.close(conn,pstmt);
		}
		// 建立一个结果集,用来保存查询出来的结果
		return map;
	}
	
	/**
	 * 执行sql
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static int executeSql(String sql) throws SQLException {
        Connection conn = JdbcConnect.getConn();
        int i = 0;
        PreparedStatement pstmt = null; // 建立一个查询对象
        try {
        	
            pstmt = conn.prepareStatement(sql);
            // Statement stat = conn.createStatement();
            i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally{
			JdbcConnect.close(conn,pstmt);
		}

        return i;
    }
}
