package rating.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.NxRatingInfo;

import connect.JdbcConnect;

/**
 * Servlet implementation class Rs
 */
public class Rs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=gb2312");
		response.getWriter().print("未结束");
		response.getWriter().print(System.currentTimeMillis());
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into nx_rating_info (stockno,SYN,Tech,Finan,Info,Trade,Base,Date) " +
		" values(?,?,?,?,?,?,?,?)";
		try {
			conn = JdbcConnect.getConn();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "test");
			pstmt.setString(2, "test");
			pstmt.setString(3, "test");
			pstmt.setString(4, "test");
			pstmt.setString(5, "test");
			pstmt.setString(6, "test");
			pstmt.setString(7, "test");
			pstmt.setString(8, "test");
			pstmt.addBatch();
			pstmt.executeBatch();
			conn.commit();
			System.out.println("============"+conn.getAutoCommit());;
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn != null)
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] sRating = new String[]{null,null,null,null,null,null,null};
		String[] sRating1 = new String[]{"test1",null,null,null,null,null,null};
		String[] sRating2 = new String[]{"test2",null,null,null,null,null,null};
		String[] sRating3 = new String[]{"test3",null,null,null,null,null,null};
		String[] sRating4 = new String[]{"test4",null,null,null,null,null,null};
		String[] sRating5 = new String[]{"test5",null,null,null,null,null,null};
		List<String[]> listArray = new ArrayList<String[]>();
		listArray.add(sRating);
		listArray.add(sRating1);
		listArray.add(sRating2);
		listArray.add(sRating3);
		listArray.add(sRating4);
		listArray.add(sRating5);
		NxRatingInfo nri = new NxRatingInfo();
		try {
			nri.insertRating(listArray);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
