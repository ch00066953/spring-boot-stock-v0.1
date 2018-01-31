package parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.JdbcConnect;

public class ParaContral {

	public static void setNearDate() throws SQLException {
		String sql = "select distinct date from nx_rating_info where date <'" + Para.getDate() +"' order by date limit 1";
		Connection conn = JdbcConnect.getConn();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Para.setNearDate(rs.getString("date"));
			System.out.println("设置NearDate:"+Para.getNearDate());
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
