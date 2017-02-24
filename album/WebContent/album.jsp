<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相册管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
</head>
<body>
<div id="m-div">
<s:if test="#session.LOGINUSER==null">
	<jsp:forward page="/main.jsp"></jsp:forward>
</s:if>
<s:else>
	<a href="${pageContext.request.contextPath}/album/new.jsp">创建新相册</a> &nbsp&nbsp| &nbsp&nbsp
	<a href="${pageContext.request.contextPath}/album/total">相册查看</a> &nbsp&nbsp| &nbsp&nbsp
	<a href="${pageContext.request.contextPath}/main.jsp">返回主页</a> &nbsp&nbsp
</s:else>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>