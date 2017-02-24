<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相册预览页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript">
function showphotos(albumId){
	window.open("${pageContext.request.contextPath}/album/detail?albumId=" + albumId);
}
</script>
</head>
<body bgcolor="pink">
<h2 align="center">相册预览</h2>
<table border="1" >

<s:iterator value="#request.ALBUMLIST" id="myalbum" status="st">
	
<%-- 	<tr>
		<td>${st.index+1}</td>
		<td>${myalbum.name}</td>
		<td><img alt="${myalbum.name}" src="${myalbum.cover}" width="320px"></td>
		<td><input type="checkbox" class="select" value="2"/></td>		
	</tr> --%>
	<tr><td align="center">【${st.index+1}】</td></tr>
	<tr><td align="center"><img src="${myalbum.cover}" width="320px" onclick="showphotos('${myalbum.id}')"><br>${myalbum.name}</td></tr>

</s:iterator>
</table><br>
<a href="${pageContext.request.contextPath}/album.jsp">返回相册管理页面</a><br><br>
<jsp:include page="../footer.jsp"/>
</body>
</html>