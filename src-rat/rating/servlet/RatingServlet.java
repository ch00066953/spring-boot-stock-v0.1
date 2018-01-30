package rating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wlgtext.ToDo;

/**
 * Servlet implementation class RatingServlet
 */
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RatingServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=gb2312");
		int time = 30;
		String sToday = request.getParameter("txtToday");
		String sNextday = request.getParameter("txtNextday");
		parameter.Para.setDate(sToday);
		parameter.Para.setNearDate(sNextday);
		System.out.println("Schedule something to do in 5 seconds.");
		ToDo t = wlgtext.ToDo.getInstance();
		t.run(time);
		System.out.println("Waiting.");
		if(t != null){
			response.getWriter().print("未结束");
//			request.
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
