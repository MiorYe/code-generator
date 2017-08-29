<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>详情页面</title>
</head>
<body>
<!-- 工具栏start -->
<div class="toolbar">
	<boss:checkAuth url="">
    	<a href="${'$'}{root}/${table.tableMeta.referenceName}/edit.html?cType=1&id=${'$'}{detail.${table.tableMeta.primaryKeys[0].name}}" name="${table.tableMeta.referenceName}" id="${table.tableMeta.referenceName}"
     			class="btn btn-blue ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
     			role="button" aria-disabled="false">
     		<span class="ui-button-text">修改</span>
     	</a>
     	<a href="javascript: history.back(-1);" class="btn btn-default ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
     			role="button" aria-disabled="false">
     		<span class="ui-button-text">返回</span>
     	</a>
  	</boss:checkAuth>
</div>

<!-- 详细信息 -->
<div class="tab-content">
	<div class="tab-pane active" id="driver-info">
		<table class="table table-striped detail-view">
			<#list table.tableMeta.columns as column>
			<tr>
				<th class="col-sm-3">${column.displayName}</th>
				<td>
					<#if column.javaType == 'java.util.Date'>
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${'$'}{detail.${column.name}}"/>
					<#else>
					${'$'}{detail.${column.name}}
					</#if>
				</td>
			</tr>
			</#list>
		</table>
	</div>
</div>
</body>
</html>