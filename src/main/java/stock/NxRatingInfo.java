package stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import parameter.Para;

import tool.DateUtil;
import tool.StringFunction;

import connect.JdbcConnect;
/**
 * 评级
 * @author lgwang
 *
 */
public class NxRatingInfo {
	public List<String> getAllRating() throws SQLException {
		List<String> list = new ArrayList<String>() ;
		String sql = "select * from nx_rating_info";
		Connection conn = JdbcConnect.getConn();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("stockno"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;

	}
	
	public void insertRating(List<String[]> listArray) throws SQLException {
		String sql = "insert into nx_rating_info (stockno,SYN,Tech,Finan,Info,Trade,Base,Date) " +
				" values(?,?,?,?,?,?,?,?)";
		Connection conn = JdbcConnect.getConn();
		PreparedStatement pstmt = null;
		String date = Para.getDate();
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (String[] sRating  : listArray){
				System.out.println(sRating[0]); 
				if (sRating[0] == null){
					System.out.println("为空跳出！");
					continue;
				}
				pstmt.setString(1, sRating[0]);
				pstmt.setString(2, sRating[1]);
				pstmt.setString(3, sRating[2]);
				pstmt.setString(4, sRating[3]);
				pstmt.setString(5, sRating[4]);
				pstmt.setString(6, sRating[5]);
				pstmt.setString(7, sRating[6]);
				pstmt.setString(8, date);
				pstmt.addBatch();
//				pstmt.execute();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcConnect.close(conn, pstmt);
		}
		System.out.println("完成！！！"); 
	}
	public static void main(String[] args) throws SQLException {
		String[] sRating = new String[]{null,null,null,null,null,null,null};
		List<String[]> listArray = new ArrayList<String[]>();
		NxRatingInfo nri = new NxRatingInfo();
		nri.insertRating(listArray);
	}
}
