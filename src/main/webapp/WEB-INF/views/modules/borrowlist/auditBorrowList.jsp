<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<html>
<head>
	<title>待审核列表</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		
		$(document).ready(function() {
			$("#newSubmit").click(function(){
				location.href = ctx+"/borrow/tBorrow/newBorrow";
			});
			 $(".NewListConfig4ButtonClose").click(function(){
                 $(".containbox3,.refreshBg").hide();
             });
			 $("#exportSubmit").click(function(){
				 top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
						if(v=="ok"){
							$("#searchForm").attr("action","${ctx}/borrowlist/auditBorrowList/export");
							$("#searchForm").submit();
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
			 })
		});
		function submits(){
			$("#searchForm").attr("action","${ctx}/borrowlist/auditBorrowList/list");
			$("#searchForm").submit();
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function fillBorrow(borrowListId,borrowListCode,borrowListTitleno) {
			$(".refreshBg").show();
            $.ajax({
			    url: "${ctx}/borrow/BbtBorrow/findInvestAmount",    //请求的url地址
			    dataType: "json",   //返回格式为json
			    async: false, //请求是否异步，默认为异步，这也是ajax重要特性
			    data: { "borrowListId": borrowListId },    //参数值
			    type: "post",   //请求方式
			    beforeSend: function() {
			        //请求前的处理
			    },
			    success: function(req) {
			        //请求成功时处理
			        if(req.success == "1"){
			        	 if(confirm("普享标"+borrowListTitleno+"的补标金额为 :"+req.amount+" 元。确定补标？ ")){
			        		 sureFillBorrow(borrowListCode,borrowListId,req.amount);
			        	 }
			        }
			    },
			    complete: function() {
			        //请求完成的处理
			    },
			    error: function() {
			        //请求出错处理
			        alert("请求服务器失败！");
			    }
			});
		}
		function sureFillBorrow(borrowListCode,borrowListId,lendAmt){
			
			var anualrate = $("#anualrate"+borrowListCode).val();
			var	deadline = $("#deadline"+borrowListCode).val();
			var loannumber = $("#loannumber"+borrowListCode).val();
				$.post(
						"${ctx}/borrow/BbtBorrow/fillBorrow",
						{ "borrowListId": borrowListId,"borrowListCode":borrowListCode,"lendAmt":lendAmt,"anualrate":anualrate,"deadline":deadline,"loannumber":loannumber },
						function(res){
							$(".refreshBg").hide();
							if(res.result==0){
								alert("补标失败");
							}else{
								alert("补标成功");	
								location.reload();
							}
						}
				);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrowlist/auditBorrowList/">待审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="auditBorrowList" action="${ctx}/borrowlist/auditBorrowList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标的编号：</label>
                <form:input id="borrowListCode" path="borrowListCode" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <li><label>标的名称：</label>
                <form:input id="borrowListTitle" path="borrowListTitle" htmlEscape="false" maxlength="20" class="input-medium"/>
            </li>
			<li><label>还款方式：</label>
				<form:select id="payMethod" path="payMethod" class="input-medium">
					<form:option value="" label="全部"/>
					<form:option value="debx" label="等额本息"/>					
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="startOpenTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${auditBorrowList.startOpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endOpenTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${auditBorrowList.endOpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="clearfix"></li>
			<li style="float: right;margin-right: 100px;" class="btns"><input id="exportSubmit" class="btn btn-primary" type="button" value="导出"/></li>
			<li style="float: right;margin-right: 50px;" class="btns"><input id="btnSubmit" class="btn btn-primary" onclick="submits();" type="button" value="查询"/></li>
			<div style="float:right;padding-right:20px;">
			<ul>
				<li>借款数量：${countMap.borrowCount}个</li><li>&nbsp;&nbsp;&nbsp;借款人：${countMap.userCount}人</li><li>&nbsp;&nbsp;&nbsp;借款总额：<fmt:formatNumber type="number" value="${countMap.borrowAmount}" /> 元</li>
			</ul>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款金额（元）</th>
				<th>出借年化利率</th>
				<th>出借期限</th>				
				<th>还款方式</th>
				<th>创建时间</th>
				<th>紧急状态</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="auditBL">
			<tr>
				<td>
					${auditBL.borrowListCode}
				</td>
				<td>
					${auditBL.borrowListTitle}
				</td>
				<td>
					<fmt:formatNumber type="number" value="${auditBL.borrowListAmount}" />
				</td>
				<td>
					${auditBL.anualRate}
				</td>
				<td>
					${auditBL.deadLine}
				</td>
				<td>
					<c:if test="${auditBL.payMethod=='debx'}">等额本息</c:if>
					<c:if test="${auditBL.payMethod=='xxhb'}">先息后本</c:if>
					<c:if test="${auditBL.payMethod=='ychbx'}">一次性还本付息</c:if>
				</td>
				<td>
					<fmt:formatDate value="${auditBL.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${auditBL.criticalId eq 0}">不紧急</c:if>
					<c:if test="${auditBL.criticalId eq 1}">紧急</c:if>
				</td>
				<td>
					<c:if test="${auditBL.borrowListStatus eq 1}">待审核</c:if>
					<c:if test="${auditBL.borrowListStatus eq 2}">审核未通过</c:if>
					<c:if test="${auditBL.borrowListStatus eq 3}">审核中</c:if>
					<c:if test="${auditBL.borrowListStatus eq 4}">审核通过</c:if>
				</td>
				<td>
    				<%-- <a href="${ctx}/borrowlist/auditBorrowList/auditform?id=${auditBL.borrowListId}">撤销</a> --%>
    				<a href="${ctx}/borrowlist/auditBorrowList/auditform?id=${auditBL.borrowListId}">审核</a>
    			</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="pagination">${page}</div>
	<!--弹窗-->
            <div></div>
            <div  class="containbox3 NewListConfig4Button1Popup1" style="position:fixed; top:25%;left:35%; width: 257px;padding:70px;  display: none;z-index: 11;background: #fff;">
                <div class="con-cent">
                    <h2 class="dalateH2">确定保存的修改？</h2>
                    <div class="con overHide mt46">
                        <input type="button" value="确定"  onclick="sureBorrow('1');"  class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                        <input type="button" value="取消" class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                    </div>
                </div>
            </div>
	<div class="refreshBg" style="position: fixed;top:0;left:0;opacity:0.3; background: black;width: 100%;height: 100%;display: none;"></div>
</body>
</html>