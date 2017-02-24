<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页面-2016.9.24</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
<body>
<div id="b-div">
<div id="m-div">
<s:if test="#session.USER==null">
	<h3>未登录</h3>
</s:if>
<s:else>
	<h3>系统主页面</h3>
	<h4>当前登录用户为[EL表达式方式]：${USER.userName}</h4>
	<h4>当前登录用户为[Struts2标签+OGNL表达式方式]：
		<s:property value="#session.USER.userName"/>
	</h4>
	<img src="http://59.110.16.27:8088/image-web/upload/qq.jpg" height="320" width="240">
</s:else>

<br/><br/>
<a href="register.jsp">注册</a>
<a href="login.jsp">登录</a>
<!-- action方法跳转页面 -->
<a href="${pageContext.request.contextPath}/user/list">显示用户</a>

</div>
<div id="f-div">
<jsp:include page="footer.jsp"></jsp:include>
</div>
</div>
</body>
</html>