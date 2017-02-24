<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${NAME},欢迎登录!</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript">
$(function(){	
//	$.ajaxSetup({async:false});
	$("#get").click(function(){
		//每次点击时重载页面
//		window.location("welcome.jsp");
		$.post("${pageContext.request.contextPath}/book/get.do",{pattern:$("#pattern").val()},function(data){
//			alert(data.length);  测试功能
			$("#books").append("<tr><th>序号</th><th>名称</th><th>价格</th><th>作者</th><th>出版社</th></tr>");
			for(i=0;i<data.length;i++){
				$("#books").append("<tr><td>"+(i+1)+"</td><td>"+data[i].name+"</td><td>"+data[i].price+"</td><td>"+data[i].author+"</td><td>"+data[i].publisher+"</td></tr>");
			}
		},"json");   //此行","json""可省略
	});
	$("#re").click(function(){
		window.location.href = "${pageContext.request.contextPath}/book/find.do";
	});
});

</script>
</head>
<body>
<h2>图书查询页面</h2><br/>
<br/>
<br/>
<div id="m-div">
请输入书名进行模糊查询:<input type="text" id="pattern" >
<input type="button" id="get" value="获取图书"> | <a href="${pageContext.request.contextPath}/book/test.do">获取图书2</a> | <input type="button" id="re" value="重置页面"><br/><br/>
<table id="books" cellspacing="1" cellpadding="1" border="1" bgcolor="pink" >
</table>
</div><br/>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>
