<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程初始化页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/icon.css">
<script type="text/javascript">
$(function(){
	var tcell="";
	//教师信息单元格绑定事件【单击】
	$(".teachercell").click(function(){
		tcell=this;
		//1、将mycourse.id[lang属性]传入窗口的隐藏变量
		$("#tw [name='course.id']").val(this.lang);
		//2、根据id查询到课程名称并在窗口标题中显示
		$.post("${pageContext.request.contextPath}/course/getCourseById",{"course.id":this.lang},function(data){
			$("#courseName").html(data.split("!")[1]);
		});
		//3、打开窗口
		$("#tw").window("open");
	});
	//窗口中选择教师信息绑定事件
	$(".teacherselect").click(function(){
		//将myteacher.id传入窗口的隐藏变量
		$("#tw [name='teacher.id']").val(this.lang);
	});
	//保存设置的教师信息
	$("#saveteacher").click(function(){
		//让ajax同步执行
		$.ajaxSetup({async:false});
		//获取老师姓名
		var teacherName="";
		$.post("${pageContext.request.contextPath}/teacher/getTeacherById",
			{"teacher.id":$("#tw [name='teacher.id']").val()},function(data){
				teacherName=data.split("!")[1];
		});
		//将姓名写入课程信息单元格
		$.post("${pageContext.request.contextPath}/course/updateTeacher",
			{"teacher.id":$("#tw [name='teacher.id']").val(),"course.id":$("#tw [name='course.id']").val()},		        		function(){
					tcell.innerHTML=teacherName;
		});
		//关闭窗口
		$("#tw").window("close");
	});
	//类似教师方面设置教室相关
	var rsell="";
	//教室信息单元格绑定事件【单击】
	$(".roomcell").click(function(){
		rcell=this;
		//1、将mycourse.id[lang属性]传入窗口的隐藏变量
		$("#rw [name='course.id']").val(this.lang);
		//2、根据id查询到课程名称并在窗口标题中显示
		$.post("${pageContext.request.contextPath}/course/getCourseById",{"course.id":this.lang},function(data){
			$("#courseName2").html(data.split("!")[1]);
		});
		//3、打开窗口
		$("#rw").window("open");
	});
	//窗口中选择教室信息绑定事件
	$(".roomselect").click(function(){
		//将myroom.id传入窗口的隐藏变量
		$("#rw [name='room.id']").val(this.lang);
	});
	//保存设置的教室信息
	$("#saveroom").click(function(){
		//让ajax同步执行
		$.ajaxSetup({async:false});
		//获取老师姓名
		var roomName="";
		$.post("${pageContext.request.contextPath}/room/getRoomById",
			{"room.id":$("#rw [name='room.id']").val()},function(data){
				roomName=data.split("!")[1];
		});
		//将名称写入课程信息单元格
		$.post("${pageContext.request.contextPath}/course/updateRoom",
			{"room.id":$("#rw [name='room.id']").val(),"course.id":$("#rw [name='course.id']").val()},		        		function(){
					rcell.innerHTML=roomName;
		});
		//关闭窗口
		$("#rw").window("close");
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
	<table border="1" cellpadding="1" cellspacing="1" bgcolor="pink" >
		<tr><th>序号</th><th>课程姓名</th><th>课程类型</th><th>课程课时</th><th>授课教师</th><th>上课教室</th></tr>
		<s:iterator value="#session.COURSES" id="mycourse" status="st">
			<tr><td>${st.index+1}</td><td>${mycourse.name}</td><td>${mycourse.type}</td>
				<td>${mycourse.hours}</td>
				<td class="teachercell" lang="${mycourse.id}">${mycourse.teacher.name}</td>
				<td class="roomcell" lang="${mycourse.id}">${mycourse.room.name}</td>
			</tr>
		</s:iterator>
	</table>
<br/>
${BTNS}
<input type="hidden" id="page" value="${page}"/>
</s:else>
<br/>
<!--教师信息设置 -->
<div id="tw" class="easyui-window" title="正在为课程【<span id='courseName' style='color:red;'></span>】设置教师信息..." data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
	<s:if test="#session.TEACHERS==null">
		<h3>无教师信息</h3>
	</s:if>
    <s:else>
		<h2 align="center">教师信息表</h2>
		<table border="1" cellpadding="1" cellspacing="1" bgcolor="pink" align="center">
			<tr><th>选择</th><th>序号</th><th>教师姓名</th><th>性别</th><th>电话</th></tr>
			<s:iterator value="#session.TEACHERS" id="myteacher" status="st">
                <tr><td><input type="radio" name="teacher" class="teacherselect" lang="${myteacher.id}"/></td>
                <td>${st.index+1}</td><td>${myteacher.name}</td>
                <td>${myteacher.sex}</td><td>${myteacher.phone}</td></tr>
			</s:iterator>		
		</table>	
   		<p align="center"><input type="button" value="保存设置" id="saveteacher" /></p>
        <input type="hidden" name="course.id"/>
        <input type="hidden" name="teacher.id"/>
	</s:else>  
</div>
<!--教室信息设置 -->
<div id="rw" class="easyui-window" title="正在为课程【<span id='courseName2' style='color:red;'></span>】设置教室信息..." data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:300px;padding:10px;">
	<s:if test="#session.ROOMS==null">
		<h3>无教室信息</h3>
	</s:if>
	<s:else>
		<h2 align="center">教室信息表</h2>
		<table border="1" cellpadding="1" cellspacing="1" bgcolor="pink" align="center">
			<tr><th>选择</th><th>序号</th><th>教室名称</th><th>教室位置</th></tr>
			<s:iterator value="#session.ROOMS" id="myroom" status="st">
                <tr><td><input type="radio" name="room" class="roomselect" lang="${myroom.id}"/></td>
                <td>${st.index+1}</td><td>${myroom.name}</td><td>${myroom.address}</td></tr>
			</s:iterator>		
		</table>	
        <p align="center"><input type="button" value="保存设置" id="saveroom" /></p>
        <input type="hidden" name="course.id"/>
        <input type="hidden" name="room.id"/>
	</s:else>  
</div>
<br/>
<a href="${pageContext.request.contextPath}/admin.jsp">返回后台管理主页</a>
</body>
</html>