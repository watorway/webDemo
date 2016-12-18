<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/import.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>




<style>
	.footer {
		width: 100%;
		text-align: center;
		line-height: 35px;
	}
	
	.top-bg {
		background-color: #d8e4fe;
		height: 80px;
	}
</style>

</head>
<body class="easyui-layout">
	
	<input type="hidden" value="${basePath}" id="basePath"/>
	<div data-options="region:'north'" style="width:100%;height:70px;background-color: #e8f1ff;">
		<table cellspacing=0 cellpadding=0 width="100%"
			background="${basePath}/images/demo1/header_bg.jpg" border="0">
			<tr height="56">
				<td width="260">
					<img height="56" src="${basePath}/images/demo1/header_left.jpg" width="260"></td>
				<td style="font-weight: bold; color: #fff; padding-top: 20px"
					align="middle">当前用户：${currUser.username} &nbsp;&nbsp; <a style="color: #fff"
					href="" target="${basePath}/sys/editUser.html">修改口令</a> &nbsp;&nbsp; <a style="color: #fff"
					onclick="if (confirm('确定要退出吗？')) return true; else return false;"
					href="" target="_top">退出系统</a>
				</td>
				<td align="right" width="268"><img height="56"
					src="${basePath}/images/demo1/header_right.jpg" width="268">
				</td>
			</tr>
		</table>
		<table cellspacing=0 cellpadding=0 width="100%" border=0>
			<tr bgcolor=#1c5db6 height=4>
				<td></td>
			</tr>
		</table>
	</div>
		
		
	<div data-options="region:'west',title:'菜单导航',split:true" title="West" style="width:155px;height:90%">
		<!-- 
		<div region="west" split="true" title="导航菜单" style="width: 200px;height:100%;">
			
		</div> 
		-->
		<div id="menuAccordion" class="easyui-accordion" data-options="fit:true" ><!-- style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;" -->
				
		</div>
	</div>
		
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="homeTab">
				<h1>Welcome to jQuery UI!</h1>
			</div>
			
		</div>
	</div>
		
	<div data-options="region:'south',split:true" style="width:100%;height:50px;background-color: #e8f1ff;">
	${currUser.username }
	</div>
	
	
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	 </div>

	<script type="text/javascript" src="${basePath}/js/demo1/main.js"></script>
	
</body>
</html>
	