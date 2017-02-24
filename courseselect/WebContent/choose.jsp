<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生选课页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/icon.css">
<script type="text/javascript">
$(function(){
	$.ajax({async:false});
	$(".select").each(function(i, e) {
        $.post("${pageContext.request.contextPath}/course/checkSelect",{"course.id":e.lang},function(data){
			eval("e.checked ="+data);
		});
    });
	$(".select").click(function(){
		$.post("${pageContext.request.contextPath}/course/checkIt",{"course.id":this.lang});
	});
	$("#seeall").click(function(){
		$.post("${pageContext.request.contextPath}/course/getSelect",{});
		$("#cw").window('open');
	});
	$("#confir").click(function(){
		$("#cw").window('close');
	});
});
</script>
</head>
<body>
<br/>
<s:if test="#session.COURSES==null">
	<h3>无课程信息</h3>
</s:if>
<s:else>
    <h4>现有课程信息表</h4>
	<table border="1" cellpadding="1" cellspacing="1" bgcolor="pink">
		<tr><th>序号</th><th>课程姓名</th><th>课程类型</th><th>课程课时</th><th>授课教师</th><th>上课教室</th><th>选择</th></tr>
		<s:iterator value="#session.COURSES" id="mycourse" status="st">
			<tr><td>${st.index+1}</td><td>${mycourse.name}</td><td>${mycourse.type}</td>
				<td>${mycourse.hours}</td>
				<td class="teachercell" lang="${mycourse.id}">${mycourse.teacher.name}</td>
				<td class="roomcell" lang="${mycourse.id}">${mycourse.room.name}</td>
				<td><input type="checkbox" class="select" lang="${mycourse.id}"/></td>
			</tr>
		</s:iterator>
	</table>
<br/>
${BTNS}
<input type="hidden" id="page" value="${page}"/><br/>
<input type="button" id="seeall" value="查看已选课程信息"/>
</s:else>
<br/>
<!--课程信息 -->
<div id="cw" class="easyui-window" title="已选课程信息..." data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
	<s:if test="#session.SELECTCOURSE==null">
		<h3 align="center">无已选课程信息</h3>
	</s:if>
    <s:else>
		<h2 align="center">已选课程信息表</h2>
		<table align="center" border="1" cellpadding="1" cellspacing="1" bgcolor="pink">
		<tr><th>序号</th><th>课程名称</th><th>课程类别</th><th>课程学时</th><th>授课教师</th><th>上课教室</th></tr>
		<s:iterator value="#session.SELECTCOURSE" id="scourse" status="st">
 		<tr><td>${st.index+1}</td>
 		<td>${scourse.name}</td>
 		<td>${scourse.type}</td>
 		<td>${scourse.hours}</td>
 		<td>${scourse.teacher.name}</td>
 		<td>${scourse.room.name}</td></tr>
		</s:iterator>		
	</table>	   		
	</s:else>
    <p align="center"><input type="button" value="确认" id="confir" /></p>  
</div>

<br/>
<a href="${pageContext.request.contextPath}/main.jsp">返回主页</a>
</body>
</html>