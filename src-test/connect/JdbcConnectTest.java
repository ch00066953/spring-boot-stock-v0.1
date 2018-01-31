package connect;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;


public class JdbcConnectTest {

	@Test
	public void testGetORAConn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDB2Conn() {
		Connection conn = JdbcConnect.getDB2Conn();
		String sql = "values current sqlid";
		String result = "0";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			rs.next();
			result = rs.getString(1);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcConnect.close(conn,pstmt);
		}
		// 建立一个结果集,用来保存查询出来的结果
	}

	@Test
	public void testGetMYConn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStockConn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSAEConn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetConn() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testClosePreparedStatement() {
		fail("Not yet implemented");
	}

	@Test
	public void testCloseConnectionPreparedStatement() {
		fail("Not yet implemented");
	}

}
