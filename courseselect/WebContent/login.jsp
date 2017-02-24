<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>欢迎使用选课系统</title>     
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(http://bkxk.xmu.edu.cn/xsxk/images/login_09.gif);
	background-repeat: repeat;
}

input {
	background-image: url(http://bkxk.xmu.edu.cn/xsxk/images/butbg.gif);
	background-repeat: repeat-x;
	height: 16px;
	border: 1px solid #cccccc;
	width: 140px;
}

.main {
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
	width: 1024px;
}

.content {
	background-image: url(http://bkxk.xmu.edu.cn/xsxk/images/login_12.gif);
	background-repeat: no-repeat;
	height: 343px;
	width: 330px;
	z-index: 10;
	margin-left: 612px;
	position: absolute;
	margin-top: -29px;
	padding-top: 30px;
	font-size: 12px;
	font-weight: normal;
	color: #990000;
}

.dl {
	font-size: 13px;
	font-weight: bold;
	color: #5687BE;
}

.ts { /*border:1px dashed #92ccf5;*/
	width: 280px;
	margin-top: 0px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
	padding: 5px;
	color: #000000;
	line-height: 20px;
}

.login {
	height: 100px;
	width: 100px;
}

.head {
	background-image: url(http://bkxk.xmu.edu.cn/xsxk/images/login_02.gif);
	background-repeat: no-repeat;
	height: 108px;
	width: auto;
}

.center {
	background-image: url(http://bkxk.xmu.edu.cn/xsxk/images/login_05.gif);
	background-repeat: repeat;
	height: 286px;
	width: 1024px;
}

.bottom {
	font-size: 12px;
	font-weight: bold;
	color: #01669a;
	height: auto;
	width: 600px;
	margin-top: 20px;
	float: left;
	margin-left: 15px;
	line-height: 18px;
}

.bottd {
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #000000;
}

.lcenter {
	background-image: url(http://bkxk.xmu.edu.cn/xsxk/images/login_06.gif);
	background-repeat: no-repeat;
	height: 286px;
	width: 973px;
}

.STYLE1 {
	color: #D82C27
}
-->
</style>

<script type="text/javascript">
	//表单提交
	
	function form_submit() {
		var userName = document.getElementById("username").value;
		if (userName == "" || userName == null) {
			alert("请输入用户名！");
			document.getElementById("username").focus();
			return false;
		}

		var strPass = document.getElementById("password").value;
		if (strPass == "" || strPass == null) {
			alert("请输入登录密码！");
			document.getElementById("password").focus();
			return false;
		}

		var checkCode = document.getElementById("checkCode").value;
		if (checkCode == "" || checkCode == null) {
			alert("请输入验证码！");
			return false;
		}

		document.getElementById("loginForm").submit();
	}

	//表单重置
	function form_reset() {
		document.getElementById("loginForm").reset();
	}

	//添加验证码
	var first = true;
	function addCheckCode() {
		if (first) {
			var yzmObj = document.getElementById("yzmObj");
			yzmObj.innerHTML = '<img src="http://bkxk.xmu.edu.cn/xsxk/getCheckCode" width="80px" height="20px" title="点击更新验证码" onclick="refreshCheckCode(this);" style="cursor: hand" />';
			first=false; 
		}
	}

	//刷新验证码 function
	function refreshCheckCode(checkCodeImg) {
		checkCodeImg.src="http://bkxk.xmu.edu.cn/xsxk/getCheckCode?now=" + new Date();
	}
	</script>
</head>

<body>
	<div class="main">
		<!-- <div class="head"></div> -->
		<h2 align="center">新趋势选课系统</h2>
		<div style="height: 1px; clear: both;"></div>
		<div class="content">
			<form id="loginForm" action="${pageContext.request.contextPath}/student/check" method="post">
				<table border="0" align="center" cellpadding="2" cellspacing="0">
					<tr>
						<td colspan="2" style="height: 30px">
							<div>&nbsp;&nbsp;</div>
						</td>
						<tr>
							<tr>
								<td align="right" class="dl">用户名：</td>
								<td style="width: 200px"><input type="text" id="username"
									name="student.name" />
								</td>
							</tr>
							<tr>
								<td align="right" class="dl">密 码：</td>
								<td><input type="password" id="password" name="student.pwd" />
								</td>
							</tr>
							<tr>
								<td align="right" class="dl">验证码：</td>
								<td style="line-height: 25px; height: 25px">
									<div>
										<div style="float: left"><input type="text" id="checkCode"
											onfocus="addCheckCode()" style="width: 60px" /></div>
										<div style="float: left" id="yzmObj"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td height="20" colspan="2"></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><img
									src="http://bkxk.xmu.edu.cn/xsxk/images/but1.gif" style="cursor: hand"
									onclick="form_submit()" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <img
									src="http://bkxk.xmu.edu.cn/xsxk/images/but2.gif" style="cursor: hand"
									onclick="form_reset()" />
								</td>
							</tr>							
				</table>
			</form>
		</div>

		<div class="center">
			<div class="lcenter"></div>
		</div>

		<div style="height: 1px; clear: both;"></div>
		<div class="bottom">
			选课须知：<br> 1. 选课前请务必仔细阅读选课规则和选课使用帮助。<br /> <span class="STYLE1">
					2. 本选课系统使用统一身份认证的用户名和密码,密码规则详见登录密码公告（与学工系统的登录密码一致）。
					<br /> </span> 如果密码不对，请带学生证到一卡通中心重置密码；<br />

				<span class="STYLE1">3.
					选课结束后，请点击选课界面右上方的“退出登录”或者关闭所有浏览器窗口，以免后面的同学篡改您的选课结果。</span>
		</div>
	</div>
<br/>
<br/><br/>
<a href="${pageContext.request.contextPath}/main.jsp">返回主页</a><br>
<s:if test="#session.INFO!=null">
	${INFO}
</s:if>
<!-- 将INFO置为空 -->
<s:set scope="session" name="INFO" value="null"></s:set>
<br/>
	<div style="height: 10px; clear: both;"></div>
	<div align="center">
		<jsp:include page="/footer.jsp"></jsp:include>
	</div>

</body>
</html>