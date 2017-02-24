<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript">
$(function(){
	//设置ajax为同步进行
	$.ajaxSetup({async:false});
	//表单验证
	$("form").submit(function(){
		var flag;
		$.post("${pageContext.request.contextPath}/user/checkExists",
				{"user.userName":$("[name='user.userName']").val()},function(data){
			if(data==1){
				$("#info").html("该用户名已经存在，请重新输入！");
				$("[name='user.userName']").focus();
				flag=false;
			}else{
				flag=true;
			}
		});
		//验证
		return flag;
	});
});
</script>
</head>
<body>
<div id="m-div">
<form action="${pageContext.request.contextPath}/user/add" method="post">
<table>
<tr><td>用户名:</td><td><input type="text" name="user.userName"></td></tr>
<tr><td>密码:</td><td><input type="password" name="user.userPwd"></td></tr>
<tr><td colspan="2"><input type="submit" value="注册"></td></tr>
</table>
<div id="info"></div>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>