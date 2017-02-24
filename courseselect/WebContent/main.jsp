<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选课系统主页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
</head>
<body>
主页面
<br/>
<s:if test="#session.LOGINSTUDENT==null">
	<h3>未登录，请登录后再进行操作！</h3>
	<br/><br/>
	<a href="${pageContext.request.contextPath}/register.jsp">注册</a>  <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
</s:if>
<br/>
<s:else>
	<h3>${LOGINSTUDENT.name}! 欢迎回来！</h3>
	<img src="${LOGINSTUDENT.photo}" width="320">
	<p><a href="${pageContext.request.contextPath}/modify.jsp">修改个人信息</a> | <a href="${pageContext.request.contextPath}/contact.jsp">紧急联系人信息</a></p>
	<p><a href="${pageContext.request.contextPath}/course/chooseMain">选课</a> | <a href="${pageContext.request.contextPath}/admin.jsp">进入后台管理</a></p><br/>	
	<a href="${pageContext.request.contextPath}/student/logout">注销</a>
</s:else>
<br/>
</body>
</html>