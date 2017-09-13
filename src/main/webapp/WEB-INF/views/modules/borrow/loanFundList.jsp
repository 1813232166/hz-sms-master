<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款列表保存成功管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/jquery-plugin/ajaxupload.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var button = $('#loanFile');  //定义能够上传文件的按钮,就是一个普通的button  
	        var fileType = "xls",fileNum = "one";   //定义能够上传的文件类型,当然要靠后面的onSubmit中的js去做判断  
	        new AjaxUpload(button,{  
	            action: '${ctx}/borrow/loanFund/loanInfo',  
	            name: 'file',   //这相当于<input type = "cover" name = "cover"/>  
	            onSubmit : function(file, ext){  
	            if (!(ext && /^(xls)$/.test(ext))) {
	                alert("您上传的文件格式不对，只支持2003版！");
	                        return false;
	                 }
	            },
	            onComplete: function(file, response){ //上传完毕后的操作  
	                   var rtn = response.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '').replace('</pre>', '').replace('<pre>', '');
	                   if(rtn==1||rtn=="1"){
	                       alert("文件上传成功");
	                       location.reload() 
	                   }else{
	                       alert("文件上传失败,请检查网络后重试");
	                   }

	            }  
	        });
	        
	        var buttoncj = $('#impRepay');  //定义能够上传文件的按钮,就是一个普通的button  
	        /*  var fileType = "xls",fileNum = "one";   */ //定义能够上传的文件类型,当然要靠后面的onSubmit中的js去做判断  
	         new AjaxUpload(buttoncj,{  
	             action: '${ctx}/borrow/loanFund/billPlan',  
	             name: 'file',   //这相当于<input type = "cover" name = "cover"/>  
	             onSubmit : function(file, ext){  
	             if (!(ext && /^(xls)$/.test(ext))) {
	                 alert("您上传的文件格式不对，只支持2003版！");
	                         return false;
	                  }
	             },
	             onComplete: function(file, response){ //上传完毕后的操作  
	                    var rtn = response.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '').replace('</pre>', '').replace('<pre>', '');
	                    if(rtn==1||rtn=="1"){
	                        alert("文件上传成功");
	                        location.reload() 
	                    }else if(rtn==2||rtn=="2"){
	                 	   alert("已导入过出借还款计划");
	                    }else{
	                        alert("文件上传失败,请检查网络后重试");
	                    }

	             }  
	         }); 
	        
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function subjectDetail(borrowId,loannumber) {
			window.location.href="${ctx}/borrow/loanFund/loanDetail?borrowId="+borrowId+"&loannumber="+loannumber+"&flag=1";
		}
		function modify(borrowId,loannumber) {
			window.location.href="${ctx}/borrow/loanFund/loanDetail?borrowId="+borrowId+"&loannumber="+loannumber+"&flag=2";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/loanFund/toLoan/">借款列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tBorrow" action="${ctx}/borrow/loanFund/toLoan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>还款方式：</label>
				<form:select path="payMethod" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('pay_method')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>借款形式：</label>
				<form:select path="borrowtype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('borrowType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="borrowstatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('BORROWSTATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>紧急状态：</label>
				<form:select path="criticalid" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('CRITICALID')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>编辑状态：</label>
				<form:select path="isEdit" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('isEdit')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>保障方式：</label>
				<form:select path="bztype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('bztype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			
			<li><label>批贷时间：</label>
				<input name="beginLoantime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrow.beginOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endLoantime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrow.endOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>借款编号：</label>
				<form:input path="loannumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			
			<li><label>借款标题：</label>
				<form:input path="loanName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>借款人：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜素"/></li>
			<li class="btns"><input id="loanFile" class="btn btn-primary"  onclick="myfile.click()" type="button" value="上传标的信息"/></li>
			<li class="btns">
				<input type="file" id="myfile" onchange="value=this.value" style="display:none">
				<input id="impRepay" class="btn btn-primary" onclick="myfile.click()" type="button" value="导入还款计划"/>
			</li>
			 
			<li class="clearfix"></li>
			<div style="float:right;padding-right:20px;">
				<ul>
					<li>借款数量：${countMap.count }个&nbsp;&nbsp;</li>&nbsp;&nbsp;&nbsp;<li>借款人：${countMap.USERID }人&nbsp;&nbsp;</li>&nbsp;&nbsp;&nbsp;<li>借款总额：${countMap.BORROWAMOUNT }元</li>
				</ul>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
						<th>借款编号</th>
		                <th>借款标题</th>
		                <th>借款人</th>
		                <th>借款金额(元)</th>
		               <th>借款利率</th>
		               <th>借款期限</th>
		              <th>还款方式</th>
		              <th>保障方式</th>
		              <th>借款形式</th>
		              <th>更新时间</th>
		              <th>批贷时间</th>
		              <th>状态</th>
		              <th>紧急状态</th>
		              <th>编辑状态</th>
				<shiro:hasPermission name="borrow:tBorrow:edit"></shiro:hasPermission><th>操作</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${page.list }" var = "borrow">
                                 <tr >
                                     <td><a href="#" onclick="subjectDetail('${borrow.borrowId }','${borrow.loannumber }');">${borrow.loannumber }</a> </td>
                                     <td><a href="#" onclick="subjectDetail('${borrow.borrowId }','${borrow.loannumber }');">${borrow.borrowtitle }</a> </td>
                                    <td>${borrow.name }</td>
                                    <td>${borrow.borrowamount }</td>
                                    <td>${borrow.anualrate }%</td>
                                    <td>${borrow.deadline }个月</td>
                                    <c:choose>
                                    	<c:when test="${borrow.payMethod == 'debx' }"><td>等额本息</td></c:when>
                                    	<c:when test="${borrow.payMethod == 'xxhb' }"><td>先息后本</td></c:when>
                                    	<c:when test="${borrow.payMethod == 'ychbx' }"><td>一次性还本付息</td></c:when>
                                    	<c:otherwise><td>不详</td></c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                    	<c:when test="${borrow.bztype == 'dbgs' }"><td>担保金保障</td></c:when>
                                    	<c:when test="${borrow.bztype == 'fxj' }"><td>风险金保障</td></c:when>
                                    	<c:when test="${borrow.bztype == 'no' }"><td>不保障</td></c:when>
                                    	<c:otherwise><td>不详</td></c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                    	<c:when test="${borrow.borrowtype == 'xy'}"> <td>信用借款</td></c:when>
                                    	<c:when test="${borrow.borrowtype == 'fd'}"> <td>房贷借款</td></c:when>
                                    	<c:when test="${borrow.borrowtype == 'cd'}"> <td>车贷借款</td></c:when>
                                    	<c:otherwise><td>不详</td></c:otherwise>
                                    </c:choose>
                                    <td> <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${borrow.createTime }" /></td>
                                    <td><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${borrow.loantime }" /> </td>
                                    <c:choose>
                                    	<c:when test="${borrow.isMatch eq 0 }"><td>未匹配</td></c:when>
                                    	<c:when test="${borrow.isMatch eq 1 }"><td>匹配</td></c:when>
                                    	<c:otherwise><td>不详</td></c:otherwise>
                                    </c:choose>
                                     <c:choose>
                                    	<c:when test="${borrow.criticalid eq 2 }"><td>不紧急</td></c:when>
                                    	<c:when test="${borrow.criticalid eq 1 }"><td>紧急</td></c:when>
                                    	<c:otherwise><td>不详</td></c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                    	<c:when test="${borrow.isEdit eq 0 }"><td>未编辑</td></c:when>
                                    	<c:when test="${borrow.isEdit eq 1 }"><td>编辑</td></c:when>
                                    	<c:otherwise><td>不详</td></c:otherwise>
                                    </c:choose>
                                    <td>
                                   		 <a href="#" <c:if test="${borrow.isFinish == 1 }">style="display:none"</c:if> onclick="modify('${borrow.borrowId }','${borrow.loannumber }')">编辑</a>
                                   	
                                     </td>
                                </tr>
                                </c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>