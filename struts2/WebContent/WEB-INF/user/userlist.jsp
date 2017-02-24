<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	//删除事件[遍历]
	/* $(".delete").each(function(index, element) {
        //被点击时
		$(this).click(function(){
			if(!confirm("您真的要删除【"+element.lang+"】这个用户吗？"))
			{
				return;
			}
			$.post("user/delete",{"user.userName":element.lang},function(data){
				location.href="${pageContext.request.contextPath}/user/list";
			});
		});
    }); */
	//修改事件【遍历】
//	$(".modify").each(function(index, element) {
       //被点击时
//	   $(this).click(function(){
//		   location.href="modify.jsp?id="+element.lang;
//		   location.href="${pageContext.request.contextPath}/user/modify.action?user.id="+this.lang;
//	   }); 
//   });
	/* $(".modify").click(function(){
		location.href="${pageContext.request.contextPath}/user/modify.action?user.id="+this.lang;
	}); */

	//照片单元格显示数量
	$(".picture").each(function(index, element) {
       $.post("${pageContext.request.contextPath}/picture/getNum",{"picture.uid":element.lang},function(data){
		   element.innerHTML=element.innerHTML+"["+data+"]";
	   }); 
    });
	//照片显示
	$(".picture").click(function(){
		layer.use('extend/layer.ext.js');
		$.getJSON("${pageContext.request.contextPath}/picture/getPics",{"picture.uid":this.lang,"idType":"user"},function(data){
			layer.photos({
					html:"",
					photos:data
				});
		});
	});
});
//表格效果
function altRows(id){
	if(document.getElementsByTagName){  
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
					rows[i].className = "oddrowcolor";
				 }      
	    }
	}
}
//页面加载
window.onload=function(){
	altRows('alternatecolor');
}
//修改方法
function modify(userId){
	//modify.action可以缩写为modify
	location.href="${pageContext.request.contextPath}/user/modify.action?user.id="+userId;
}
//删除方法
function del(userName){
	if(!confirm("您真的要删除【"+userName+"】这个用户吗？"))
	{
		return;
	}
	$.post("user/delete",{"user.userName":userName},function(data){
		location.href="${pageContext.request.contextPath}/user/list";
	});
}
</script>
</head>
<body>
<div id="b-div">
<div id="m-div">
<s:if test="#session.USER==null">
	<h3>请登录后再查看用户信息！</h3>
</s:if>
<s:else>
	<table class="altrowstable" id="alternatecolor" align="center">
	<caption>用户信息表</caption>
	<th>序号</th><th>用户名</th><th>密码</th><th>照片</th><th>删除</th><th>修改</th>
	<s:iterator value="#USERLIST" id="cuser" status="s">
	<tr>
		<td><s:property value="#s.index+1"/></td>
	<!-- EL表达式方式--OGNL表达式方式 -->
		<td><s:property value="#cuser.userName"/></td>
		<td>${cuser.pwd}</td>
		<td><a href="#" class="picture" lang="${cuser.id}">照片</a></td>
		<td><a href="#" class="delete" lang="${cuser.userName}" onclick="del('${cuser.userName}')">删除</a></td>
		<td><a href="#" class="modify" lang="<s:property value="#cuser.id"/>" onclick="modify('${cuser.id}');">修改</a></td>
	</tr>	
	</s:iterator>
	</table>
</s:else>
<br/><br/>
<p align="center"><a href="${pageContext.request.contextPath}/main.jsp" >返回主页面</a></p>
</div>
<div id="f-div">
<jsp:include page="../../footer.jsp"></jsp:include>
</div>
</div>
</body>
</html>