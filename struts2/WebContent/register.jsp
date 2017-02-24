<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<script type="text/javascript" src="JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript">
$(function(){
	//设置焦点事件【当失去焦点时会执行 blur 事件】
	$("[name='user.userName']").blur(function(){
		if($.trim($(this).val())=="")
		{
			return;
		}	
		$.post("user/checkExists",{"user.userName":$("[name='user.userName']").val()},function(data){
			//为click事件提供数据
			$("[name='isExists']").val(data);
			if(data=="1")
			{
				$("#msg").html("该用户名已经注册！【from 焦点事件】");
			}
			else
			{
				$("#msg").html("恭喜！该用户名可以注册【焦点事件】！");
			}
		});
	});
	$("[type='submit']").click(function(){
		if($.trim($("[name='user.userName']").val())=="")
		{
			$("#msg").html("用户名不能为空！");
			$("[name='user.userName']").select();
			$("[name='user.userName']").focus();
			return false;
		}
		if($("[name='isExists']").val()=="1")
		{
			$("#msg").html("该用户名已经注册【from 点击事件】！");
			return false;
		}
		if($.trim($("[name='user.pwd']").val())=="")
		{
			$("#msg").html("密码不能为空！");
			$("[name='user.pwd']").select();
			$("[name='user.pwd']").focus();
			return false;
		}
		return true;
	});
});
</script>
</head>
<body>
<div id="b-div">
<div id="m-div">
用户注册
<br/><br/>
<form  method="post" action="user/add">
	<table border="1" cellpadding="1" cellspacing="1">
		<tr><td>用户名：</td><td><input type="text" name="user.userName"/></td></tr>
		<tr><td>密码：</td><td><input type="password" name="user.pwd"/></td></tr>
		<tr><td colspan="2" align="center"><input type="submit" value="提交" /></td></tr>       
	</table>
</form>
<input type="hidden" name="isExists" value="0"/>
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