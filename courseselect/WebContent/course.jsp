<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/icon.css">
<script type="text/javascript">
$(function(){
	//新课程信息表单验证
	$("[type='submit']").click(function(){
		//验证名称
		if($("[name='course.name']").val().length==0){
			$("#info").html("课程名称不能为空！");
			$("[name='course.name']").focus();
			return false;
		}
		//验证类型
		if($("[name='course.type']").val().length==0){
			$("#info").html("课程类型不能为空！");
			$("[name='course.type']").focus();
			return false;
		}
		//验证课时
		if($("[name='course.hours']").val().length==0){
			$("#info").html("课程课时不能为空！");
			$("[name='course.hours']").focus();
			return false;
		}
		//验证成功后
		return true;
	});
	//删除
	$(".delete").click(function(){
		var flag=confirm("您确定要删除这个课程信息吗？");
		if(!flag)
		{
			return;
		}
		$.post("${pageContext.request.contextPath}/course/delete",{"course.id":this.lang},function(data){
			location.href="${pageContext.request.contextPath}/course/dataForCourses";   
		});
	});
	//修改
	$(".modify").click(function(){
		$.post("${pageContext.request.contextPath}/course/getCourseById",{"course.id":this.lang},
			function(data){
				var msg=data.split("!");
				//将action传来的数据赋给w插件
				$("#w [name='course.id']").val(msg[0]);
				$("#w [name='course.name']").val(msg[1]);
				$("#w [name='course.type']").val(msg[2]);
				$("#w [name='course.hours']").val(msg[3]);
		});
		//打开插件
		$("#w").window('open');
	});
	//w插件的保存功能
	$("#save").click(function(){
		$.post("${pageContext.request.contextPath}/course/modify",
			{"course.id":$("#w [name='course.id']").val(),
				"course.name":$("#w [name='course.name']").val(),
				"course.type":$("#w [name='course.type']").val(),
				"course.hours":$("#w [name='course.hours']").val()},
			function(data){
				//关闭插件
				$("#w").window('close');
				location.href="${pageContext.request.contextPath}/course/dataForCourses";  
		});
	});
});
</script>
</head>
<body>
<h4>新增课程</h4>
<form method="post" action="${pageContext.request.contextPath}/course/update">
	课程名称:<input type="text" name="course.name"> <br/>
	课程类型:<input type="text" name="course.type"><br/>
	课程课时:<input type="text" name="course.hours"><br/>
	<!-- 配置防止重复提交的标签 -->
	<s:token></s:token>
	<input type="submit" value="提交"><div id="info"></div>
</form>
<br/>
<s:if test="#session.COURSES==null">
	<h3>无课程信息</h3>
</s:if>
<s:else>
    <h4>现有课程信息表</h4>
	<table border="1" cellpadding="1" cellspacing="1" bgcolor="pink">
		<tr><th>序号</th><th>课程名称</th><th>课程类型</th><th>课程课时</th><th>删除</th><th>修改</th></tr>
		<s:iterator value="#session.COURSES" id="mycourse" status="st">
			<tr><td>${st.index+1}</td><td>${mycourse.name}</td><td>${mycourse.type}</td>
				<td>${mycourse.hours}</td>
				<td><input type="button" value="删除" class="delete" lang="${mycourse.id}"></td>
				<td><input type="button" value="修改" class="modify" lang="${mycourse.id}"></td>
			</tr>
		</s:iterator>
	</table>
<br/>
${BTNS}
<input type="hidden" id="page" value="${page}"/>
</s:else>
<br/>
<div id="w" class="easyui-window" title="课程信息修改" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
		课程姓名:<input type="text" name="course.name"><br/>
       	课程类型:<input type="text" name="course.type"><br/>
       	课程课时:<input type="text" name="course.hours"><br/>
        <input type="button" value="保存" id="save"/>
        <input type="hidden" name="course.id"/>
</div>
<br/>
<a href="${pageContext.request.contextPath}/admin.jsp">返回后台管理主页</a>
</body>
</html>