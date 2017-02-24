<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试页-2016.9.18</title>
</head>
<body>
<h3>测试web.xml为jsp页面配置初始化参数</h3>
<br>
<%
	String initparam=this.getInitParameter("pageSize");
	out.print("pageSize = "+initparam);
%>
</body>
</html>