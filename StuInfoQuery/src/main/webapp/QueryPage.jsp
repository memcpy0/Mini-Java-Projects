<%-- 输入学生的学号，输出学生信息 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>学生信息查询系统</title>
	</head>
	<body>
	    <div>
			<form action="QueryServlet" method="post"> 
				<table>
					<tr> <!-- colspan表示单元格可以横跨的列数 -->
						<th colspan="2">学生信息查询系统</th>
					</tr>
					<tr>
						<th>学生学号：</th>
						<td><input type="text" name="input_sno"></th>
						<td><input type="submit" name="submit_btn" value="提交" /> </td>
						<td>${error}</td>
					</tr>
					<tr>
						<th>学生姓名：</th>
						<td>${output_name}</td>
					</tr>
					<tr>
						<th>学生性别：</th>
						<td>${output_sex}</td>
					</tr>
					<tr>
						<th>学生年龄：</th>
						<td>${output_age}</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
