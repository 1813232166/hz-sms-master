<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<html>
<head>
	<title>散标集列表保存成功管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		
		$(document).ready(function() {
			$("#newSubmit").click(function(){
				location.href = ctx+"/borrow/borrowList/queryBorrowListForEdit";
			});
			 $(".NewListConfig4ButtonClose").click(function(){
                 $(".containbox3,.refreshBg").hide();
             });
			 $("#exportSubmit").click(function(){
				 top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
						if(v=="ok"){
							$("#searchForm").attr("action","${ctx}/borrow/borrowList/exportBorrowList");
							$("#searchForm").submit();
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
			 })
		});
		function submits(){
			$("#searchForm").attr("action","${ctx}/borrow/borrowList/");
			$("#searchForm").submit();
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//补标
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
		
		// 普享标集详情 
		// borrowListId 普享标集id 
		// statusMemo 普享标集状态
		function sendDetailList(borrowListId, statusMemo,borrowListStatus){
		  statusMemo = encodeURI(encodeURI(statusMemo));
		  if ("12" != borrowListStatus) {
			  window.location.href="${ctx}/borrow/borrowList/detailList?tBorrowListId="+borrowListId+"&&statusMemo="+statusMemo;
			}
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/borrowList/">散标集列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tBorrowList"  method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>还款方式：</label>
				<form:select id="payMethod" path="payMethod" class="input-medium">
					<form:option value="" label="全部"/>
					<form:option value="debx" label="等额本息"/>	
					<form:option value="xxhb" label="先息后本"/>
					<form:option value="ychbx" label="一次性还本付息"/>				
				</form:select>
			</li>
			<li><label>标的编号：</label>
                <form:input id="borrowListCode" path="borrowListCode" htmlEscape="false" maxlength="50" class="input-medium"/>
            </li>
            <li><label>标的名称：</label>
                <form:input id="borrowListTitle" path="borrowListTitle" htmlEscape="false" maxlength="20" class="input-medium"/>
            </li>
            <%-- 
                <li style="float: left;"><label>状态：</label>
                <form:select id="borrowListStatus" path="borrowListStatus" class="input-medium">
                    <form:option value="" label="全部"/>
                    <form:option value="1" label="待审核 "/>
                    <form:option value="2" label="审核未通过 "/>
                    <form:option value="5" label="预发布"/>
                    <form:option value="6" label="招标中"/>
                    <form:option value="7" label="已满标"/>
                    <form:option value="8" label="还款中"/>
                    <form:option value="9" label="已逾期"/>
                    <form:option value="10" label="已结清"/>
                    <form:option value="11" label="已流标"/>
                    <form:option value="12" label="已撤销"/> 
        
                </form:select>
            </li>
         --%>
			<li><label>发布时间：</label>
				<input name="startOpenTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrowList.startOpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endOpenTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrowList.endOpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="clearfix"></li>
			
			<li style="float: right;margin-right: 150px;" class="btns"><input id="exportSubmit" class="btn btn-primary" type="button" value="导出"/></li>
			<li style="float: right;margin-right: 50px;" class="btns"><input id="btnSubmit" class="btn btn-primary" onclick="submits();" type="button" value="查询"/></li>
			
			<div style="float:left;padding-right:20px;">
				 <ul>
				     <li style="margin-right: 550px;" class="btns"><input id="newSubmit" class="btn btn-primary" type="button" value="新建普享标集合"/></li>
					<li>集合数量：${countMap.borrowNum }&nbsp;&nbsp;&nbsp;借款数量：${countMap.lendNum }个&nbsp;&nbsp;&nbsp;</li>
					<li>借款人：${countMap.lendUserNum }人&nbsp;&nbsp;&nbsp;</li>
					<li>借款总额：<fmt:formatNumber type="number" value="${countMap.lendSum }" /> 元</li>
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
				<th>发布时间</th>
				<th>状态</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBorrow">
			<tr>
				<td>
					${tBorrow.borrowListCode}
				</td>
				<td>
          <a href="###"  onclick="sendDetailList('${tBorrow.borrowListId}','${tBorrow.statusMemo}','${tBorrow.borrowListStatus}')"  >${tBorrow.borrowListTitle}</a>
				</td>
				<td>
					<fmt:formatNumber type="number" value="${tBorrow.borrowListAmount}" />
				</td>
				<td>
					${tBorrow.anualRate}
				</td>
				<td>
					${tBorrow.deadLine}
				</td>
				<td>
					${tBorrow.payMethodMemo}
				</td>
				<td>
					<fmt:formatDate value="${tBorrow.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${tBorrow.openTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<%-- <c:if test="${'21'==tBorrow.borrowListStatus}">${tBorrow.statusMemo}</c:if> --%>
					${tBorrow.statusMemo}
                            		
				</td>
				<td>
					${tBorrow.borrowListRemarks}
					<%-- ${ tBorrow.borrowListStatus}${tBorrow.statusMemo} --%>
					
				</td>
				<shiro:hasPermission name="borrow:tBorrow:edit"></shiro:hasPermission>
				<td>
   				     <c:if test="${('1'==tBorrow.borrowListStatus) or ('2'==tBorrow.borrowListStatus) or ('5'==tBorrow.borrowListStatus)}">
    				    <a href="${ctx}/borrow/borrowList/delete?borrowListId=${tBorrow.borrowListId}&borrowtype=1" onclick="return confirmx('您确定撤销${tBorrow.borrowListTitle}的募集？', this.href)">撤销</a>
     				</c:if>
    				<c:if test="${('1'==tBorrow.borrowListStatus)}">
    					<a href="${ctx}/borrowlist/auditBorrowList/auditform?id=${tBorrow.borrowListId}">审核</a>
    				</c:if>
    				<c:if test="${('2'==tBorrow.borrowListStatus)}">
    					<a href="${ctx}/borrow/borrowList/toEdit?borrowListIds=${tBorrow.borrowListCode}">编辑</a>
    				</c:if>
 
     				<%-- 
    				<a href="${ctx}/borrow/borrowList/detailList?id=${tBorrow.borrowListId}">详情</a> 
    							<input type="hidden" name="anualrate${var.borrowListCode }" id="anualrate${var.borrowListCode }" value="${var.anualrate} "/> 
								<input type="hidden" name="deadline${var.borrowListCode }" id="deadline${var.borrowListCode }" value="${var.deadline} "/> 
								<input type="hidden" name="loannumber${var.borrowListCode }" id="loannumber${var.borrowListCode }" value="${var.loannumber} "/>  --%>
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
