<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript">
$(function(){
	$.ajaxSetup({async:false});
	
});
</script>
</head>
<body>
<div id="m-div">
<form action="${pageContext.request.contextPath}/user/login" method="post">
<table>
<tr><td>用户名:</td><td><input type="text" name="user.userName"></td></tr>
<tr><td>密码:</td><td><input type="password" name="user.userPwd"></td></tr>
<tr><td colspan="2"><input type="submit" value="登录"></td></tr>
</table>
<div id="info"></div>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>