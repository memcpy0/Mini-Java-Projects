import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理QueryPage.jsp发送的查询请求
 */
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DataConnection dc;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
		dc = new DataConnection("com.mysql.cj.jdbc.Driver", 
			"jdbc:mysql://localhost:3306/stu_info?useSSL=false&useUnicode=true&characterEncoding=utf-8",
			"root", 
			"wdmysqlmm123");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("input_sno");	// 得到要查询的学号
		Connection conn = dc.getConnection();
		try {
			// 将查询发来的信息包装为Student对象
			Student inInfo = new Student();
			inInfo.setSno(sno);
			// 使用DAO类进行实际的查询操作
			Student outInfo = StuDao.query(conn, inInfo);
			if (outInfo != null) {
				request.setAttribute("output_name", outInfo.getSname());
				request.setAttribute("output_sex", outInfo.getSsex());
				request.setAttribute("output_age", outInfo.getSage());
			} else {
				request.setAttribute("error", "输入的学号不存在！请重新输入");
			}
			request.getRequestDispatcher("QueryPage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dc.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
