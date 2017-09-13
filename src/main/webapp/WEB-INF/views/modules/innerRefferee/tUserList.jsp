<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户推荐管理</title>
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
		//修改弹框
		function go(mobile, realname, tStaffId, tmobile) {
			$("#oldtStaffId").val(tStaffId);
			$("#reffereerefferee").val(tmobile);
			$("#reffereemobile").text(mobile);
			$("#name").text(realname);
			$("#reffereeStaffId").val(tStaffId);
			$('#myModal').modal('show');
		};
		//提交修改
		function submit() {
			var oldtStaffId=$("#oldtStaffId").val();
			var reffereerefferee = $("#reffereerefferee").val();
			var reffereeStaffId = $("#reffereeStaffId").val();
			$.ajax({
				type : "post",
				url : "${ctx}/innerRefferee/tUser/updateRefferee",
				data : {
					"reffereeStaffId" : reffereeStaffId,
					"reffereerefferee" : reffereerefferee,
					"oldStaffId" :oldtStaffId
				},
				success : function(data) {
					if(data){
						alert("修改成功");
						window.location.reload();  
					}else{
						alert("修改失败");
					}
				}
			});
		};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/innerRefferee/tUser/">用户推荐列表</a></li>
		<shiro:hasPermission name="refferee:tUser:edit"><li><a href="${ctx}/innerRefferee/tUser/form">用户推荐添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tUser" action="${ctx}/innerRefferee/tUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>营业部：</label>
				<form:select path="departmentcode" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>营业部：</label>
				<form:input path="departmentcode" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>推荐人手机号：</label>
				<form:input path="tmobile" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>推荐人姓名：</label>
				<form:input path="tRealname" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>推荐人员工编号：</label>
				<form:input path="staffid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>被推荐人手机号：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>被推荐人注册时间：</label>
				<input name="beginRegdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tUser.beginRegdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endRegdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tUser.endRegdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>被推荐人姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>营业部</th>
				<th>推荐人手机号</th>
				<th>推荐人员工编号</th>
				<th>推荐人姓名</th>
				<th>被推荐人手机号</th>
				<th>被推荐人姓名</th>
				<th>被推荐人注册时间</th>
				
				<shiro:hasPermission name="refferee:tUser:edit"></shiro:hasPermission><th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tUser">
			<tr>
				<td>
					${tUser.tdepartmentinfo}
				</td>
				<td>
					${tUser.tmobile}
				</td>
				<td>
					${tUser.tStaffId}
				</td>
				<td>
					${tUser.tRealname}
				</td>
				<td>
					${tUser.mobile}
				</td>
				<td><a href="${ctx}/refferee/tUser/form?id=${tUser.id}">
					${tUser.realname}
				</a></td>
				
				<td>
					<fmt:formatDate value="${tUser.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				
				
				
				<%-- <shiro:hasPermission name="refferee:tUser:edit"></shiro:hasPermission> --%><td>
    				<a href="#" onclick="go('${tUser.mobile}','${tUser.realname}','${tUser.tStaffId}','${tUser.tmobile}');">修改</a>
					<a href="${ctx}/refferee/tUser/delete?id=${tUser.id}" onclick="return confirmx('确认要删除该用户推荐吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	
	<!-- 修改窗口 -->
	<div style="width: 400px;" class="modal fade" id="myModal" tabindex="-1"
		data-backdrop="static" keyboard="true">
		<div class="modal-dialog">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 style="text-align: center;">修改推荐人</h4>
				</div>
				<div  >
					<div>
						<form  action='${ctx}/innerRefferee/tUser/updateRefferee'>
							<input type='hidden' id="reffereerefferee" />
							<input type='hidden' id="oldtStaffId" />
							<div align="center">
								<table >
									</br>
									<tr>
										<td style='float: right;'>被推荐人手机号：</td>
										<td id="reffereemobile"></td>
									</tr>
									<tr>
										<td style='float: right;'>被推荐人姓名：</td>
										<td id="name"></td>
									</tr>
									<tr>
										<td style='float: right;'>推荐人员工编号：</td>
										<td><input id='reffereeStaffId' type='text' name='refferee' value=''></input></td>
									</tr>
								</table>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">返回</button>
					<button class="btn btn-primary" data-dismiss="modal"
						onclick="submit();">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>