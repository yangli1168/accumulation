<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新相册创建页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
</head>
<body>
<h4>创建新相册</h4><br/>
<div id="m-div">
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/album/upload" >
	<table>
		<tr><td>相册名称：</td><td><input type="text" name="album.name"></td></tr>
		<tr><td>相册封面：</td><td><input type="file" name="newfile"></td></tr>
		<tr><td colspan="2"><input type="submit" value="创建"></td></tr>
	</table>
</form><br/><br/>
<a href="${pageContext.request.contextPath}/main.jsp">返回主页</a>
</div>

<jsp:include page="../footer.jsp"/>

</body>
</html>