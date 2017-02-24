<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
</head>
<body>
<h3>主页面</h3><br/>
<div id="m-div">
<s:if test="#session.LOGINUSER==null">
	<h5>未登录</h5>
 	<a href="${pageContext.request.contextPath}/register.jsp">注册</a>
	<a href="${pageContext.request.contextPath}/login.jsp">登录</a>    
</s:if>
<s:else>	
	<h4><s:property value="#session.LOGINUSER.userName"/>! 欢迎回来！[OGNL方法]</h4><br/>
	<h4>${LOGINUSER.userName}! 欢迎回来！[EL表达式方法]</h4><br/>
    <img src="${pageContext.request.contextPath}/image-default/yin.jpg"><br/><br/>
    <a href="${pageContext.request.contextPath}/album.jsp">相册管理</a>  &nbsp&nbsp| &nbsp&nbsp 
	<a href="${pageContext.request.contextPath}/user/logout">注销</a> 
</s:else>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>