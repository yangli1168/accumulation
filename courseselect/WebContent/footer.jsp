<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试页-footer</title>
</head>
<body>
<hr width="80%" align="center"/>
<%
if(request.getAttribute("footerName")!=null)
{	
%>
<p align="center">版权所有 <%=request.getAttribute("footerName")%>   <2015-2025></p>
<%
}
else if(request.getParameter("firm")==null)
{
%>
	<p align="center">版权所有 新趋势有限公司【默认】 <2015-2025></p>
<%
}
else
{
%>
<p align="center">版权所有 <%=request.getParameter("firm")%> <2015-2025></p>
<%
}
%>

</body>
</html>