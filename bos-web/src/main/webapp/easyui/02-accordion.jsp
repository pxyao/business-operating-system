<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>accordion</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>

</head>
<body class="easyui-layout">
	<div title="XX管理系统" style="height:100px;" data-options="region:'north'">北部区域</div>
	<div title="系统菜单" style="width:200px;" data-options="region:'west'">
		<div class="easyui-accordion" data-options="fit:true">
			<div data-options="iconCls:'icon-edit'" title="面板">123</div>
			<div title="面板">123</div>
			<div title="面板">123</div>
		</div>
	</div>
	<div data-options="region:'center'">中部区域</div>
	<div style="width:100px;" data-options="region:'east'">东部区域</div>
	<div style="height:50px;" data-options="region:'south'">南部区域</div>
</body>
</html>