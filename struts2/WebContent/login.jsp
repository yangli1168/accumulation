<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<script type="text/javascript" src="JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript">
$(function(){
	 $("[type='button']").click(function(){
		if($.trim($("[name='userName']").val())=="")
		{
			$("[name='userName']").select();
			$("[name='userName']").focus();
			$("#msg").html("用户名不能为空！");
			return;
		}
		if($.trim($("[name='pwd']").val())=="")
		{
			$("[name='pwd']").select();
			$("[name='pwd']").focus();
			$("#msg").html("密码不能为空！");
			return;
		}
		//验证用户密码
		$.post("user/checkLogin",{"user.userName":$("[name='userName']").val(),"user.pwd":$("[name='pwd']").val()},function(data){
			if(data=="1")
			{
				location.href="main.jsp";
			}
			else
			{
				$("#msg").html("用户名或密码错误，请重新输入！");
			}
		});
	}); 
});
</script>
</head>
<body>
<div id="b-div">
<div id="m-div">
用户登录
<br/><br/>
<form method="post" action="">
	<table border="1" cellpadding="1" cellspacing="1">
		<tr><td>用户名：</td><td><input type="text" name="userName"/></td></tr>
		<tr><td>密码：</td><td><input type="password" name="pwd"/></td></tr>
		<tr><td colspan="2" align="center"><input type="button" value="提交" onclick="login();"/></td></tr>
	</table>
</form>
<div id="msg"></div>
<br/><br/>
<a href="main.jsp">返回主页面</a>
</div>
<div id="f-div">
<jsp:include page="footer.jsp"></jsp:include>
</div>
</div>
</body>
</html>