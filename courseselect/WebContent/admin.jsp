<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理页面</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
<body>
后台管理
<br/>
<br/>
<a href="${pageContext.request.contextPath}/room/dataForRooms">教室管理</a> | 
<a href="${pageContext.request.contextPath}/teacher/dataForTeachers">教师管理</a> |
<a href="${pageContext.request.contextPath}/course/dataForCourses">课程管理</a> |
<a href="${pageContext.request.contextPath}/course/initMain">课程初始化</a> |
<br/>
<br/>
<a href="${pageContext.request.contextPath}/main.jsp">返回主页</a>
</body>
</html>