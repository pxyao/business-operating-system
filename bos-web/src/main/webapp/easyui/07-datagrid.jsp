<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>data-grid</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<!-- 将静态页面渲染为datagrid样式 -->
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>01</d>
				<td>小明</d>
				<td>50</d>
			</tr>
			<tr>
				<td>02</td>
				<td>老王</td>
				<td>50</td>
			</tr>
		</tbody>
	</table>
	
	<!-- 使用easyui api 创建datagrid -->
	<table id="mytab">
	</table>
	<script type="text/javascript">
	$(function(){
		$('#mytab').datagrid({    
		    columns:[[    
		        {field:'id',title:'编号',checkbox:true},    
		        {field:'name',title:'姓名'},    
		        {field:'age',title:'年龄'}    
		    ]], 
		    url:'${pageContext.request.contextPath}/json/datagrid_test.json',
		   	rownumbers:true,
		   	toolbar:[
		   	         {text:"添加",iconCls:"icon-add",handler: function(){alert('编辑按钮')}},
		   	         {text:"删除",iconCls:"icon-remove"},
		   	         {text:"修改",iconCls:"icon-edit"},
		   	         {text:"搜索",iconCls:"icon-search"}
		   	         ],
		   	pagination:true
		});  	
	});
	</script>
	
</body>
</html>