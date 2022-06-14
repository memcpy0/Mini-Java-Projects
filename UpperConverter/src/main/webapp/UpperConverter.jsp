<%-- 输入一个英文字符串，输出其全大写形式 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>My Uppercase Converter</title>
		<link href="favicon.ico" rel="icon" type="image/x-icon" /> 
	</head>
	<body>
		<!-- 输出Tomcat版本号 -->
	    <h1 style="font-size: 18pt; margin: 0.3em 0 0; height: 40px">${pageContext.servletContext.serverInfo}</h1>
	    <br />
	    <div>
			<img src="tomcat.svg" alt="[tomcat logo]" width="40%" />    
			<form method="post"> 
				请输入一个英文字符串：
				<br />
				<input type="text" name="input_value">
				<input type="submit" id="submit_btn" value="提交" /> 
	    		<br />
		        
		        字符串全大写形式如下：
		        <br /> 
				<%
					String str = request.getParameter("input_value");
					if (str != null) out.println(str.toUpperCase());
				%> 
			</form>
		</div>
	</body>
</html>



