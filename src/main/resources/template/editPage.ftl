<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<%@ include file="/WEB-INF/views/inc/common.jsp" %>    
<html lang="zh-en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>
<c:if test="${'$'}{cType == 0 }">修改</c:if>
<c:if test="${'$'}{cType != 0 }">新增</c:if>
</title>
</head>
<body>
<div class="panel panel-primary" data-collapsed="0">
	<div class="panel-body">
    	<form role="form" id="form2" class="form-horizontal form-groups-bordered" onsubmit="return submitForm(this);" >
      		<input type="hidden" name="${table.tableMeta.primaryKeys[0].name}" value="${'$'}{detail.${table.tableMeta.primaryKeys[0].name}}" />
      		
      		<#list table.tableMeta.columns as column>
      		<div class="form-group">
		 		<label class="col-sm-3 control-label">${column.displayName}</label>
		 		<div class="col-sm-5">
		 			<input type="text" class="form-control" id="${column.name}" name="${column.name}" placeholder="请输入${column.displayName}" value="${'$'}{detail.${column.name}}" />
	     		</div>
	       	</div>
	       	
			</#list>
       		<div class="form-group">
	      		<div class="col-sm-offset-3 col-sm-5" >
		    		<button type="submit" class="btn btn-blue" id=""> 提交</button>
		    		<button type="button" class="btn btn-default" onclick="javascript: history.back(-1);" id="back">返回</button>
	      		</div>
	   		</div>
    	</form>
  	</div>
</div>
  
<script>
  	function submitForm(thisForm) {
  		var url = '${'$'}{root}/${table.tableMeta.referenceName}/data_update.html';
		if (${'$'}{cType} != 0) {
			url = '${'$'}{root}/${table.tableMeta.referenceName}/data_save.html';
		} 
		ajaxPostForm(url, thisForm, function(result) {
			window.location = '${'$'}{root}/${table.tableMeta.referenceName}/list.html';
		});
		return false;
	}
</script>
</body>
</html>