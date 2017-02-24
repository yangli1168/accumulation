<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/icon.css">
<script type="text/javascript">
$(function(){
	//新教师信息表单验证
	$("[type='submit']").click(function(){
		//验证名称
		if($("[name='teacher.name']").val().length==0){
			$("#info").html("教师名称不能为空！");
			$("[name='teacher.name']").focus();
			return false;
		}
		//验证性别
		if($("[name='teacher.sex']").val().length==0){
			$("#info").html("教师性别不能为空！");
			$("[name='teacher.sex']").focus();
			return false;
		}
		//验证电话
		if($("[name='teacher.phone']").val().length==0){
			$("#info").html("教师电话不能为空！");
			$("[name='teacher.phone']").focus();
			return false;
		}
		//验证成功后
		return true;
	});
	//删除
	$(".delete").click(function(){
		var flag=confirm("您确定要删除这个教师信息吗？");
		if(!flag)
		{
			return;
		}
		$.post("${pageContext.request.contextPath}/teacher/delete",{"teacher.id":this.lang},function(data){
			location.href="${pageContext.request.contextPath}/teacher/dataForTeachers";   
		});
	});
	//修改
	$(".modify").click(function(){
		$.post("${pageContext.request.contextPath}/teacher/getTeacherById",{"teacher.id":this.lang},
			function(data){
				var msg=data.split("!");
				//将action传来的数据赋给w插件
				$("#w [name='teacher.id']").val(msg[0]);
				$("#w [name='teacher.name']").val(msg[1]);
				$("#w [name='teacher.sex']").val(msg[2]);
				$("#w [name='teacher.phone']").val(msg[3]);
		});
		//打开插件
		$("#w").window('open');
	});
	//w插件的保存功能
	$("#save").click(function(){
		$.post("${pageContext.request.contextPath}/teacher/modify",
			{"teacher.id":$("#w [name='teacher.id']").val(),
				"teacher.name":$("#w [name='teacher.name']").val(),
				"teacher.sex":$("#w [name='teacher.sex']").val(),
				"teacher.phone":$("#w [name='teacher.phone']").val()},
			function(data){
				//关闭插件
				$("#w").window('close');
				location.href="${pageContext.request.contextPath}/teacher/dataForTeachers";  
		});
	});
});
</script>
</head>
<body>
<h4>新增教师</h4>
<form method="post" action="${pageContext.request.contextPath}/teacher/update">
	教师姓名:<input type="text" name="teacher.name"> <br/>
	教师性别:<input type="text" name="teacher.sex"><br/>
	教师电话:<input type="text" name="teacher.phone"><br/>
	<!-- 配置防止重复提交的标签 -->
	<s:token></s:token>
	<input type="submit" value="提交"><div id="info"></div>
</form>
<br/>
<s:if test="#session.TEACHERS==null">
	<h3>无教师信息</h3>
</s:if>
<s:else>
    <h4>现有教师信息表</h4>
	<table border="1" cellpadding="1" cellspacing="1" bgcolor="pink">
		<tr><th>序号</th><th>教师姓名</th><th>教师性别</th><th>教师电话</th><th>删除</th><th>修改</th></tr>
		<s:iterator value="#session.TEACHERS" id="myteacher" status="st">
			<tr><td>${st.index+1}</td><td>${myteacher.name}</td><td>${myteacher.sex}</td>
				<td>${myteacher.phone}</td>
				<td><input type="button" value="删除" class="delete" lang="${myteacher.id}"></td>
				<td><input type="button" value="修改" class="modify" lang="${myteacher.id}"></td>
			</tr>
		</s:iterator>
	</table>
<br/>
${BTNS}
<input type="hidden" id="page" value="${page}"/>
</s:else>
<br/>
<div id="w" class="easyui-window" title="教师信息修改" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
		教师姓名:<input type="text" name="teacher.name"><br/>
       	教师性别:<input type="text" name="teacher.sex"><br/>
       	教师电话:<input type="text" name="teacher.phone"><br/>
        <input type="button" value="保存" id="save"/>
        <input type="hidden" name="teacher.id"/>
</div>
<br/>
<a href="${pageContext.request.contextPath}/admin.jsp">返回后台管理主页</a>
</body>
</html>