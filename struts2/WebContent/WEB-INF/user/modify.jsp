<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mystyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/JQuery Lib/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	//表单内容判断
	$("[type='submit']").click(function(){
		if($.trim($("[name='user.userName']").val())=="")
		{
			$("#msg").html("用户名不能为空！");
			$("[name='user.userName']").select();
			$("[name='user.userName']").focus();
			return false;
		}
		if($.trim($("[user.name='pwd']").val())=="")
		{
			$("#msg").html("密码不能为空！");
			$("[name='user.pwd']").select();
			$("[name='user.pwd']").focus();
			return false;
		}
		return true;
	});
	//图片删除
/* 	$(".delete").click(function(){
		var str=this.lang.split("!");
		if(!confirm("您确定要删除["+str[1]+"]这张照片吗？"))
		{
			return;
		}
		$.post("${pageContext.request.contextPath}/picture/delete",{"picture.id":str[0]},function(){
			location.href="${pageContext.request.contextPath}/user/list";
		});
	}); */
	//图片显示
	$(".display").click(function(){
		//this.lang取得id
		layer.use('extend/layer.ext.js');
		$.getJSON("${pageContext.request.contextPath}/picture/getPics",{"picture.id":this.lang,"idType":"picture"},function(data){
			layer.photos({
	            html: "",
	            photos:data
	        });
		});	
	});
});
//删除方法
function del(pictureId,prictureName){
	if(!confirm("您确定要删除[" + prictureName + "]这张照片吗？"))
	{
		return;
	}
	$.post("${pageContext.request.contextPath}/picture/delete",{"picture.id":pictureId},function(){
		location.href="${pageContext.request.contextPath}/user/list";
	});
}
</script>
</head>
<body>
<br/>
用户信息修改
<br/><br/>
<!-- jsp页面尽量不用java代码 
<
	String id=request.getParameter("id");
	UserDAO dao=new UserDAO();
	User user=dao.getUserById(Integer.parseInt(id));
	
>
-->
<form method="post" action="user/save">
	<table>
		<tr><td>用户名：</td><td><input type="text" name="user.userName" readonly value="<s:property value="user.userName"/>"/></td></tr>
		<tr><td>密码：</td><td><input type="text" name="user.pwd" value="<s:property value="user.pwd"/>"/></td></tr>
		<tr><td colspan="2"><input type="hidden" name="user.id" value="<s:property value="user.id"/>"/><input type="submit" value="提交" /></td></tr>
	</table>
</form>
<div id="msg"></div>
<br/><br/>
<s:if test="%{#PICTURESLIST.size()==0}">
	用户[<s:property value="user.userName"/>]从未上传照片，上传后即可显示！
</s:if>
<s:else>
	用户[<s:property value="user.userName"/>]相册信息表<br>
	<table border="1" cellpadding="20" cellspacing="5" bgcolor="#99CC66">
		<th>序号</th><th>照片名称</th><th>显示</th><th>删除</th>
		<s:iterator value="#PICTURESLIST" id="cpic" status="s">
		<tr>
			<td><s:property value="#s.index+1"/></td>
			<td><s:property value="#cpic.name"/></td>
	   	 	<td><a href="#" class="display" lang="<s:property value="#cpic.id"/>">显示</a></td>
			<td><a href="#" class="delete" onclick="del('${cpic.id}','${cpic.name}')" lang="<s:property value="#cpic.id"/>!<s:property value="#cpic.name"/>">删除</a></td>
		</tr>
		</s:iterator>
	</table>
</s:else>
<br/><br/>
上传照片
<form method="post"  enctype="multipart/form-data" action="${pageContext.request.contextPath}/picture/add">
<table>
<tr><td>选择照片</td><td><input type="file" name="image"></tr>
<!-- 批量上传第一张 -->
<tr>
	<td>照片名称</td>
	<td><input type="text" name="pictures[0].name"/><input type="hidden" name="pictures[0].uid" value="<s:property value="user.id"/>"/></td>
</tr>
<!-- 批量上传第二张 -->
<tr><td>选择照片</td><td><input type="file" name="image"></tr>
<tr>
	<td>照片名称</td>
	<td><input type="text" name="pictures[1].name"/><input type="hidden" name="pictures[1].uid" value="<s:property value="user.id"/>"/></td>
</tr>
<tr><td colspan="2"><input type="submit" value="提交" /></td></tr>
</table>
</form>
<br/><br/>
<a href="${pageContext.request.contextPath}/main.jsp">返回主页面</a>
<br/><br/>
<div id="f-div">
<jsp:include page="../../footer.jsp"></jsp:include>
</div>
</body>
</html>