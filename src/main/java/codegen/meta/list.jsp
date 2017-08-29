<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/inc/common.jsp" %>
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
    	<form class="form-horizontal form-groups-bordered" role="form" action="${root}/genTest/list.html" id="genTest">
      		<div class="row">
        		<div>
          			<label for="" class="col-sm-1 control-label">查询条件�?/label>
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
	    	<a href="${root}/genTest/edit.html" name="" id="" target="_blank"
	     			class="btn btn-blue ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
	     			role="button" aria-disabled="false">
	     		<span class="ui-button-text">创建</span>
	     	</a>
	  	</boss:checkAuth>
	</div>
	<!-- 工具栏end -->
	
	<!-- 列表start -->
	<boss:page pageBean="${pageBean}">
	  	<table class="table table-striped responsive">
	  		<thead>
      			<tr>
      				<th>Test Name</th>
      				<th>Create Time</th>
      				<th>操作</th>
				</tr>
      		</thead>
      		
      		<c:forEach items="${list}" var="item">
        		<tr>
      				<td>${item.testName}</td>
      				<td>${item.createTime}</td>
      				<td>
      					<a href="${root}/genTest/edit.html?cType=1">修改</a>
      					<a href="javascript: void(0);" onClick="delete(${item.PrimaryKeyMeta [name=testId, displayName=Test Id, columnName=test_id, dataType=4, jdbcTypeName=INT]});">删除</a>
      				</td>
        		</tr>
      		</c:forEach>
	  	</table>
  	</boss:page>
  	<!-- 列表end -->
</div>
<script>
	$(function() {
		
	});
</script>
</body>
</html>