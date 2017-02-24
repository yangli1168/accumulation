<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息修改页面</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript" src="JQuery Lib/uploadPreview.min.js"></script>
<script type="text/javascript">
$(function(){
	//图片预览：仅支持eclipse内置浏览器
	$("[name='image']").change(function(){
		$("#ImgPr").attr("src",$(this).val());
	});
	
	$("[type='submit']").click(function(){
		var myurl="${pageContext.request.contextPath}/student/checkPwd";
		var flag=true;
		$.ajax({
			type:"POST",
			async:false,
			url:myurl,
			data:{"student.id":"${LOGINSTUDENT.id}","student.pwd":$("#oldPwd").val()},
			success: function(data){
				if(data==0){
					$("#info").html("旧密码输入错误！");
					flag=false;
				}
			}
		});
		if(!flag){
			return flag;
		}
		//验证新密码
		if($.trim($("[name='student.pwd']").val()).length==0){
			$("#info").html("请输入新密码！");
			return false;
		}
		if($("[name='student.pwd']").val()!=$("#pwd1").val()){
			$("#info").html("新密码两次输入不一致！");
			return false;
		}
		//验证成功后
		return true;
	});
});
</script>
</head>
<script>
window.onload = function () 
{ 
	new uploadPreview({ UpBtn: "up", DivShow: "box", ImgShow: "ImgPr" });
}
</script>
<body>
学生信息修改
<br/>
<s:if test="#session.LOGINSTUDENT==null">
	<jsp:forward page="${pageContext.request.contextPath}/login.jsp"></jsp:forward>
</s:if>
<table>
<tr><td>
<br/>
<form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/student/modify">
	<table>
		<tr><td>用户名:</td><td><input type="text" name="student.name" value="${LOGINSTUDENT.name}"/><input type="hidden" name="student.id" value="${LOGINSTUDENT.id}"/></td></tr>
		<tr><td>旧密码:</td><td><input type="password" id="oldPwd"/></td></tr>
		<tr><td>新密码:</td><td><input type="password" name="student.pwd" value="${LOGINSTUDENT.pwd}"/></td></tr>
		<tr>
		  <td>重复新密码:</td>
		  <td><input type="password" id="pwd1" value="${LOGINSTUDENT.pwd}"/></td>
		</tr>
		<tr>
		  <td>性别</td>
		  <td>
		  	<s:radio list="#{'男':'男','女':'女'}" name="student.sex" value="#session.LOGINSTUDENT.sex"></s:radio>
		  </td>
		</tr>
		<tr>
		  <td>年级:</td>
		  <td>
		  	<s:select list="#{'一年级':'一年级','二年级':'二年级','三年级':'三年级','四年级':'四年级'}" name="student.grade" value="#session.LOGINSTUDENT.grade"></s:select>
		  </td>
		</tr>
		<tr>
		  <td>电话号码:</td> <td><input type="text" name="student.phone" value="${LOGINSTUDENT.phone}"/></td>
		</tr>
		<tr>
		  <td>上传照片:</td> <td><input type="file" name="image" id="up"/>
		  <input type="hidden" name="student.photo" value="${LOGINSTUDENT.photo}"/>
		  </td>
		</tr>
		<tr><td colspan="2"><input type="submit" value="提交"/></td></tr>
</table>
</form>
<div id="info"></div>
</td>
<br/>
<td>
	<!-- 图片显示模块 -->
	<div id='box' width="300">
	<s:if test="#session.LOGINSTUDENT.photo==null">
		<img src="${pageContext.request.contextPath}/images/upload.png" id="ImgPr"/>
	</s:if>
	<s:else>
		<img src="${LOGINSTUDENT.photo}" id="ImgPr"/>
	</s:else>
	</div>
</td>
</table>
<br/>
<a href="${pageContext.request.contextPath}/main.jsp">返回主页</a>
</body>
</html>