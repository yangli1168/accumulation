<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教室页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/icon.css">
<script type="text/javascript">
$(function(){
	//新教室信息表单验证
	$("[type='submit']").click(function(){
		//验证名称
		if($("[name='room.name']").val().length==0){
			$("#info").html("教室名称不能为空！");
			$("[name='room.name']").focus();
			return false;
		}
		//验证地址
		if($("[name='room.address']").val().length==0){
			$("#info").html("教室地址不能为空！");
			$("[name='room.adress']").focus();
			return false;
		}
		//验证成功后
		return true;
	});
	//删除
	$(".delete").click(function(){
		var flag=confirm("您确定要删除这个教室信息吗？");
		if(!flag)
		{
			return;
		}
		$.post("${pageContext.request.contextPath}/room/delete",{"room.id":this.lang},function(data){
			location.href="${pageContext.request.contextPath}/room/dataForRooms";   //未分页前
		});
	});
	//修改
	$(".modify").click(function(){
		$.post("${pageContext.request.contextPath}/room/getRoomById",{"room.id":this.lang},
			function(data){
				var msg=data.split("!");
				//将action传来的数据赋给w插件
				$("#w [name='room.id']").val(msg[0]);
				$("#w [name='room.name']").val(msg[1]);
				$("#w [name='room.address']").val(msg[2]);
		});
		//打开插件
		$("#w").window('open');
	});
	//w插件的保存功能
	$("#save").click(function(){
		$.post("${pageContext.request.contextPath}/room/modify",
			{"room.id":$("#w [name='room.id']").val(),
				"room.name":$("#w [name='room.name']").val(),
				"room.address":$("#w [name='room.address']").val()},
			function(data){
				//关闭插件
				$("#w").window('close');
				location.href="${pageContext.request.contextPath}/room/dataForRooms";   //未分页前
		});
	});
});
</script>
</head>
<body>
<h4>新增教室</h4>
<form method="post" action="${pageContext.request.contextPath}/room/update">
	教室名称:<input type="text" name="room.name"> <br/>
	教室位置:<input type="text" name="room.address"><br/>
	<!-- 配置防止重复提交的标签 -->
	<s:token></s:token>
	<input type="submit" value="提交"><div id="info"></div>
</form>
<br/>
<s:if test="#session.ROOMS==null">
	<h3>无教室信息</h3>
</s:if>
<s:else>
    <h4>现有教室信息表</h4>
	<table border="1" cellpadding="1" cellspacing="1" bgcolor="pink">
		<tr><th>序号</th><th>教室名称</th><th>教室位置</th><th>删除</th><th>修改</th></tr>
		<s:iterator value="#session.ROOMS" id="myroom" status="st">
			<tr><td>${st.index+1}</td><td>${myroom.name}</td><td>${myroom.address}</td>
				<td><input type="button" value="删除" class="delete" lang="${myroom.id}"></td>
				<td><input type="button" value="修改" class="modify" lang="${myroom.id}"></td>
			</tr>
		</s:iterator>
	</table>
<br/>
${BTNS}
<input type="hidden" id="page" value="${page}"/>
</s:else>
<br/>
<div id="w" class="easyui-window" title="教室信息修改" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
		教室名称:<input type="text" name="room.name"><br/>
       	教室地址:<input type="text" name="room.address"><br/>
        <input type="button" value="保存" id="save"/>
        <input type="hidden" name="room.id"/>
</div>
<br/>
<a href="${pageContext.request.contextPath}/admin.jsp">返回后台管理主页</a>
</body>
</html>