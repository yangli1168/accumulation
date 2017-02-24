<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
</head>
<body>
<div id="m-div">
<form action="${pageContext.request.contextPath}/user/check.do" method="post">
<table>
<tr><td>学生姓名</td><td><input type="text" name="name"></td></tr>
<tr><td>密码</td><td><input type="password" name="pwd"></td></tr>
<tr><td colspan="2"><input type="submit" value="登录"></td></tr>
</table>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>