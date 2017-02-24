<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>紧急联系人页面</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript">
$(function(){
	if($("[name='contact.sex']:checked").length==0)
	{
		$("[name='contact.sex']:eq(0)").attr("checked",true);
	}
});
</script>
</head>
<body>
<s:if test="#session.LOGINSTUDENT==null">
	<jsp:forward page="login.jsp"></jsp:forward>
</s:if>
<form action="${pageContext.request.contextPath}/contact/updateContact" method="post"> 
<input type="hidden" name="contact.id" value="${LOGINSTUDENT.contact.id}"/>
<input type="hidden" name="student.id" value="${LOGINSTUDENT.id}"/>
<table>
<tr><td>姓名:</td><td><input type="text" name="contact.name" value="${LOGINSTUDENT.contact.name}"/></td></tr>
<tr><td>性别:</td><td><s:radio list="#{'男':'男','女':'女'}" name="contact.sex" value="#session.LOGINSTUDENT.contact.sex"/></td></tr>
<tr><td>关系:</td><td><input type="text" name="contact.relation" value="${LOGINSTUDENT.contact.relation}"/></td></tr>
<tr><td>电话:</td><td><input type="text" name="contact.phone" value="${LOGINSTUDENT.contact.phone}"/></td></tr>

<tr><td colspan="2"><input type="submit" value="提交"/></td></tr>
</table>
</form>
<br/>
<br/>
<a href="${pageContext.request.contextPath}/main.jsp">返回主页</a>
</body>
</html>