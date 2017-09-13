<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vaildate-editor</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<!-- 使用easyui api 创建datagrid -->
	<table id="mytab">
	</table>
	<script type="text/javascript">
	$(function(){
		var myIndex = -1;
		$('#mytab').datagrid({    
		    columns:[[    
		        {width:150,field:'id',title:'编号',checkbox:true},    
		        {width:150,field:'name',title:'姓名',editor:{
		        	type:'validatebox',
		        	options:{}
		        }},    
		        {width:150,field:'age',title:'年龄',editor:{
		        	type:'numberbox',
		        	options:{}
		        }}
		    ]], 
		    url:'${pageContext.request.contextPath}/json/datagrid_test.json',
		   	rownumbers:true,
		   	toolbar:[
		   	         {text:"添加",iconCls:"icon-add",handler: function(){
						$("#mytab").datagrid("insertRow",{
							index: myIndex,	// 索引从0开始
							row: {
								name: '新名称',
								age: 30,
							}
						});
						myIndex = 0;
						$("#mytab").datagrid("beginEdit",0);
		   	         }},
		   	         {text:"删除",iconCls:"icon-remove",handler:function(){
		   	        	var rows = $("#mytab").datagrid("getSelections");
		   	        	if(rows.length == 1){
		   	        		var row = rows[0];
		   	        		myIndex = $("#mytab").datagrid("getRowIndex",row);
		   	        	}
		   	        	$("#mytab").datagrid("deleteRow",myIndex);
		   	         }},
		   	         {text:"修改",iconCls:"icon-edit",handler:function(){
		   	        	var rows = $("#mytab").datagrid("getSelections");
		   	        	if(rows.length == 1){
		   	        		var row = rows[0];
		   	        		myIndex = $("#mytab").datagrid("getRowIndex",row);
		   	        	}
		   	        	 $("#mytab").datagrid("beginEdit",myIndex);
		   	         }},
		   	         {text:"搜索",iconCls:"icon-search"},
		   	      	 {text:"保存",iconCls:"icon-save",handler:function(){
			   	      	var rows = $("#mytab").datagrid("getSelections");
		   	        	if(rows.length == 1){
		   	        		var row = rows[0];
		   	        		myIndex = $("#mytab").datagrid("getRowIndex",row);
		   	        	}
		   	        	 $("#mytab").datagrid("endEdit",myIndex);
		   	         }}
		   	         ],
		   	pagination:true,
		   	pageList:[3,5,7,10],
		   	//监听结束编辑事件
		   	onAfterEdit:function(index,data,changes){
		   		console.info(data);
		   	}
		});  	
	});
	</script>
	
</body>
</html>