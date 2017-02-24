<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>相册详细页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
</head>
<body>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/album/uploadphoto" >
	<table>
		<tr><td>图片名称：</td><td><input type="text" name="photo.name"></td></tr>
		<tr><td>图片选择：</td><td><input type="file" name="newfile"></td></tr>
		<!-- <input type="hidden" name="album.id" value="2"> -->
		<tr><td colspan="2"><input type="submit" value="图片上传"></td></tr>
	</table>
</form><br><br>
<h2 align="center">相册查看</h2>
<table border="1" >

<s:iterator value="#request.PHOTOSLIST" id="myphoto" status="st">
	
<%-- 	<tr>
		<td>${st.index+1}</td>
		<td>${myphoto.name}</td>
		<td><img alt="${myphoto.name}" src="${myphoto.cover}" width="320px"></td>
		<td><input type="checkbox" class="select" value="${myphoto.id}"/></td>		
	</tr> --%>
	<tr><td align="center">第【${st.index+1}】张</td></tr>
	<tr><td align="center"><img src="${myphoto.url}" height="500px" onclick="showphotos(${myphoto.id})"><br>${myphoto.name}</td></tr>

</s:iterator>
</table><br>
<a href="${pageContext.request.contextPath}/album.jsp">返回相册管理页面</a><br><br>
<jsp:include page="../footer.jsp"/>
</body>
</html>