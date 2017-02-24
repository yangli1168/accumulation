<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试页</title>
</head>
<body>
<c:if test="${BOOKS !=null}">
<table border="1" id="test">
	<tr><td>书名</td><td>作者</td><td>价格</td><td>出版社</td></tr>

		<c:forEach var="book" items="${BOOKS}">
			<tr>
				<td>${book.name}</td>
				<td>${book.author}</td>
				<td>${book.price}</td>
				<td>${book.publisher}</td>
			</tr>
		</c:forEach>	
</table>
</c:if>
</body>
</html>