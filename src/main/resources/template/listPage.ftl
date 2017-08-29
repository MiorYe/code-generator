<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/inc/common.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>XX列表</title>
<meta charset="utf-8">
</head>
<body>
<div>
	<!-- 查询区域start -->
	<div class="panel panel-primary panel-body">
    	<form class="form-horizontal form-groups-bordered" role="form" action="${'$'}{root}/${table.tableMeta.referenceName}/list.html" id="${table.tableMeta.referenceName}">
      		<div class="row">
        		<div>
          			<label for="" class="col-sm-1 control-label">查询条件：</label>
          			<div class="col-sm-2">
          				<input type="text" id="" name="" class="form-control" placeholder="查询条件" value="">
          			</div>
        		</div>
        		
        		<div>
          			<label for="" class="col-sm-1 control-label"></label>
          			<div class="col-sm-2">
          				<button type="submit" class="btn btn-info btn-icon" id="search-button">
              				查询<i class="entypo-search"></i>
            			</button>
          			</div>
        		</div>
       	 	</div>
		</form>
	</div>
	<!-- 查询区域end -->
	
	<!-- 工具栏start -->
	<div class="toolbar">
		<boss:checkAuth url="">
	    	<a href="${'$'}{root}/${table.tableMeta.referenceName}/edit.html" name="" id=""
	     			class="btn btn-blue ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
	     			role="button" aria-disabled="false">
	     		<span class="ui-button-text">创建</span>
	     	</a>
	  	</boss:checkAuth>
	</div>
	<!-- 工具栏end -->
	
	<!-- 列表start -->
	<boss:page pageBean="${'$'}{pageBean}">
	  	<table class="table table-striped responsive datagrid">
	  		<thead>
      			<tr>
      				<#list table.tableMeta.columns as column>
      				<th>${column.displayName}</th>
      				</#list>
      				<th>操作</th>
				</tr>
      		</thead>
      		
      		<c:forEach items="${'$'}{list}" var="item">
        		<tr id="RECORD_${'$'}{item.${table.tableMeta.primaryKeys[0].name}}">
          			<#list table.tableMeta.columns as column>
      				<td>
      					<#if column.javaType == 'java.util.Date'>
      					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${'$'}{item.${column.name}}"/>
      					<#else>
      					${'$'}{item.${column.name}}
      					</#if>
      				</td>
      				</#list>
      				<td>
      					<a class="handle entypo-search" title="详情" href="${'$'}{root}/${table.tableMeta.referenceName}/detail.html?id=${'$'}{item.${table.tableMeta.primaryKeys[0].name}}"></a>
      					<a class="handle entypo-pencil" title="修改" href="${'$'}{root}/${table.tableMeta.referenceName}/edit.html?cType=0&id=${'$'}{item.${table.tableMeta.primaryKeys[0].name}}"></a>
      					<a class="handle entypo-trash" title="删除" href="javascript: void(0);" onClick="del('${'$'}{item.${table.tableMeta.primaryKeys[0].name}}');"></a>
      				</td>
        		</tr>
      		</c:forEach>
	  	</table>
  	</boss:page>
  	<!-- 列表end -->
</div>
<script>
	/**
	 * 删除
	 */
	function del(id) {
		if (!confirm('确认要删除这条记录吗？')) {
			return;
		}
		
		ajaxPost('${'$'}{root}/${table.tableMeta.referenceName}/data_del.html', {id: id}, function(result) {
			if (result.ret.code != 0 ) {
				alert('操作失败');
				return;
			}
		   	$('#RECORD_' + id).fadeOut(500, function() {
		   		$('#RECORD_' + id).remove();
		   	});
	   });
	}
</script>
</body>
</html>