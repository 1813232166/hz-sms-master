<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Banner管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function uploadSubmit(){
			location.href= ctx+"/banner/appBanner/form";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/banner/appBanner/">Banner列表</a></li>
		<%-- <shiro:hasPermission name="banner:appBanner:edit"><li><a href="${ctx}/banner/appBanner/form">Banner添加</a></li></shiro:hasPermission> --%>
	</ul>
	
	
	<form:form id="searchForm" modelAttribute="appBanner" action="${ctx}/banner/appBanner/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>启用状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:option value="1" label="启用"/>
					<form:option value="0" label="禁用"/>
				</form:select>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:option value="0" label="APP"/>
					<form:option value="1" label="PC"/>
					<form:option value="2" label="微信"/>
					<form:option value="3" label="APP1.0"/>
				</form:select>
			</li>
			<li><label>名称：</label>
				<form:input path="bannerName" htmlEscape="false" maxlength="2000" class="input-medium" placeholder="请输入banner名称"/>
			</li>
			<li style="float: right;" class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li style="float: right;" class="btns"><input class="btn btn-primary" type="button" value="上传" onclick="uploadSubmit();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<!-- 上传 -->
	<%-- <div style="float:right;padding-right:20px;">
		<form id="upload"   method="post" action="${ctx}/banner/appBanner/upload" enctype="multipart/form-data">
        	<input type="button"  value="上传Banner" size="30" onclick="f.click()" />
        	<input type="file" id="f" onchange="this.form.submit()" name="files" style="position:absolute; filter:alpha(opacity=0); opacity:0; width:50px; " size="1" />
		</form>
	</div> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center;">名称</th>
				<th style="text-align: center;">图片</th>
				<th style="text-align: center;">类型</th>
				<th style="text-align: center;">排序</th>
				<th style="text-align: center;">启用状态</th>
				<th style="text-align: center;">创建人</th>
				<th style="text-align: center;">更新时间</th>
				<th style="text-align: center;">备注</th>
				<shiro:hasPermission name="banner:appBanner:edit"><th style="text-align: center;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="appBanner">
			<tr>
				<td style="text-align: center;"><a href="${ctx}/banner/appBanner/form?id=${appBanner.id}">
					${appBanner.bannerName}
				</a></td>
				<td style="text-align: center;">
				<div>
					<img style="width: 107px;height: 60px;" src="${baseurl_img }${appBanner.picurl}">
				</div>
				</td>
				<td style="text-align: center;">
					${appBanner.type}
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${appBanner.status=='启用'}">${appBanner.orders}</c:when>
						<c:when test="${appBanner.status=='禁用'}">禁用</c:when>
					</c:choose>
					
				</td>
				<td style="text-align: center;">
					${appBanner.status}
				</td>
				<td style="text-align: center;">
					${appBanner.creator}
				</td>
				<td style="text-align: center;">
					<fmt:formatDate value="${appBanner.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="text-align: center;">
					${appBanner.remarks}
				</td>
				<shiro:hasPermission name="banner:appBanner:edit"><td style="text-align: center;">
    				<a href="${ctx}/banner/appBanner/form?id=${appBanner.id}">修改</a>
					<a href="${ctx}/banner/appBanner/delete?id=${appBanner.id}" onclick="return confirmx('确认要删除该Banner吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>