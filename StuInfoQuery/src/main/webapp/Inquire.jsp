<%-- 输入学生的学号，输出学生信息 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>学生信息查询系统</title>
	</head>
	<body>
		<%!
			// JDBC驱动名
			String driver = "com.mysql.cj.jdbc.Driver";
			// 数据库路径,stu_info是数据库名
			String url = "jdbc:mysql://localhost:3306/stu_info?useSSL=false&useUnicode=true&characterEncoding=utf-8";
			// 数据库用户名
			String username = "root";
			// 数据库密码
			String password = "wdmysqlmm123";
		%>
	    <br />
	    <div>
			<form method="post"> 
				请输入学生学号：
				<br />
				<input type="text" name="input_id">
				<input type="submit" name="submit_btn" value="提交" /> 
	    		<br />
		        
		        该学生的信息如下：
				<br />
				<% 
					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;
					try {
						// 反射加载JDBC的Driver类
						Class.forName(driver);
						// 通过DriverManager获取Connection对象
						conn = DriverManager.getConnection(url, username, password);
						String param = request.getParameter("input_id");
						if (param != null) {
							String sqlStr = "SELECT * FROM STUDENT WHERE Sno = '" + // Sno是学号
								request.getParameter("input_id") + "';";							
							// 准备SQL语句
							stmt = conn.createStatement();
							// 执行并获取结果集
							rs = stmt.executeQuery(sqlStr);
							// 得到结果集的列数
							int col = rs.getMetaData().getColumnCount();
							// 遍历结果集
							out.print("<TABLE>");
							while (rs.next()) {
								out.print("<TR>");
								for (int i = 1; i <= col; ++i)
									out.println("<TD>" + rs.getString(i) + "</TD>");
								out.print("</TR>");
							}
							out.print("</TABLE>");
						}
					} catch (ClassNotFoundException e) {
						out.print("ClassNotFoundException: " + e);
					} catch (SQLException e) {
						out.print("SQLException: " + e);
					} catch (Exception e) {
						out.print(e);
					} finally {
						// 关闭连接
						if (rs != null) rs.close();
						if (stmt != null) stmt.close();
						if (conn != null) conn.close();	
					}
				%>
			</form>
		</div>
	</body>
</html>
