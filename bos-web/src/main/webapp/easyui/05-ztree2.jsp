<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ztree</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">
	function addtab(){
		var e = $("#mytabs").tabs("exists","系统管理");
		if(e==false){
			$("#mytabs").tabs("add",{
				title:"系统管理",
				iconCls:'icon-save',
				closable:true,
				content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>'
			});
		}else{
			$("#mytabs").tabs("select","系统管理");
		}
	}
</script>

</head>
<body class="easyui-layout">
	<!-- 使用div元素描述每个区域 -->
	<div title="XXX管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
	<div title="系统菜单" style="width: 200px" data-options="region:'west'">
		<!-- 制作accordion折叠面板 
			fit:true----自适应(填充父容器)
		-->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="系统管理">
				<a href="javscript:void(0)" onclick="addtab()" class="easyui-linkbutton" icon="icon-add">添加一个选项卡</a>
			</div>
			<div title="面板二">
				<ul id="ztree1" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						var setting = {
								data: {
									simpleData: {
										enable: true,
										idKey: "id",
										pIdKey: "pId",
										rootPId: 0
									}
								}
							};
						var ztreeNode = [
						                 {"id":"1","pId":"0","name":"节点一"},
						                 {"id":"2","pId":"1","name":"节点二"},
						                 {"id":"3","pId":"2","name":"节点三"}
						                 ];
						$.fn.zTree.init($("#ztree1"), setting, ztreeNode);
					});
				</script>
			</div>
			<div title="面板三">
				<ul id="ztree2" class="ztree"></ul>
				<script type="text/javascript">
					$(function() {
						var setting = {
							data : {
								simpleData : {
									enable : true,
									idKey : "id",
									pIdKey : "pId",
									rootPId : 0
								}
							},
							callback : {
								onClick : function(event, treeId, treeNode) {
									var exists = $('#mytabs').tabs('exists',treeNode.name);
									//判断是选项卡是否存在
									if(exists==true){
										$('#mytabs').tabs('select',treeNode.name);
									}else{
										//判断是否需要打开选项卡 有menu.json中有page的就打开
										if(treeNode.page != undefined){
											$('#mytabs').tabs('add',{
												title: treeNode.name,
												selected: true,
												closable:true,
												content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>'
											});
										}
									}
								}
							}
						};

						var url = "${pageContext.request.contextPath}/json/menu.json";
						$.post(url, {}, function(data) {
							$.fn.zTree.init($("#ztree2"), setting, data);
						}, 'json');
					});
				</script>
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<!-- 制作一个tabs选项卡面板 -->
		<div id="mytabs" class="easyui-tabs" data-options="fit:true">
			<!-- 使用子div表示每个面板 -->
			<div data-options="iconCls:'icon-cut'" title="面板一"></div>
			<div data-options="closable:true" title="面板二"></div>
			<div title="面板三"></div>
		</div>
	</div>
	<div style="width: 100px" data-options="region:'east'">东部区域</div>
	<div style="height: 50px" data-options="region:'south'">南部区域</div>
</body>
</html>