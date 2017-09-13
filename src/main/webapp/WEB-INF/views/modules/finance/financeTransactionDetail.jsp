<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>交易单详情</title>
<meta name="decorator" content="default" />
<script type="text/javascript">

  $(document).ready(function() {
  });
  
  	function openListModel(investId) {
  		$.post(ctx + '/finance/financeTransaction/expectedRepayBillplans', 
  		{'investId': investId}
  		, 'json').then(function(data){
  			console.log(data);
  			$('#repayBillplans').html('');
  			$(data).each(function(index, repayBillplan){
  				var htmlText = '<tr>';
  				htmlText += '<td>' + repayBillplan.billnum + '</td>';
  				htmlText += '<td>' + repayBillplan.stcapi + '</td>';
  				htmlText += '<td>' + repayBillplan.sinte + '</td>';
  				htmlText += '<td>' + repayBillplan.sAmount + '</td>';
  				htmlText += '<td>' + repayBillplan.sdate + '</td>';
  				if(repayBillplan.payoffflag == '1') {
  					repayBillplan.payoffflag = '已还款';
  				} else {
  					repayBillplan.payoffflag = '未还款';
  				}
  				htmlText += '<td>' + repayBillplan.payoffflag + '</td>';
  				htmlText += '</tr>';
  				$('#repayBillplans').append(htmlText);
  			});
  		},function(data){
  			console.log(data);
  		});
		$(".containbox3, .refreshBg").show();
	}
  	
  	function closeListModel() {
  		$('#repayBillplans').html('');
  		$(".containbox3, .refreshBg").hide();
  	}
  
  function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
  }
  
</script>
</head>
<body>
  <ul class="nav nav-tabs">
    <li><a href="${ctx}/finance/financeTransaction/">出借计划交易单</a></li>
    <li class="active"><a href="###">订单详情</a></li>
  </ul>
  
  <form:form id="inputForm" modelAttribute="financeTransaction" action="${ctx}/borrow/borrowList/form" method="post" class="form-horizontal">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
    <input name="id" type="hidden" value="${tBorrowList.borrowListId}" />
    <fieldset>
      <legend>订单详情</legend>
      <table class="table-form">
        <tr>
          <td class="tit">计划名称</td>
          <td>${financeTransaction.financeName }</td>
          <td class="tit">出借金额（元）</td>
          <td><fmt:formatNumber value="${financeTransaction.capitailAmount}" pattern="#,#00.0#"/></td>
        </tr>
        <tr>
          <td class="tit">计划编号</td>
          <td>${financeTransaction.financeCode}</td>
          <td class="tit">优惠券类型</td>
          <td></td>
        </tr>
        <tr>
          <td class="tit">手机号</td>
          <td>${financeTransaction.mobile}</td>
          <td class="tit">优惠券面额</td>
          <td></td>
        </tr>
        <tr>
          <td class="tit">真实姓名</td>
          <td>${financeTransaction.userName }</td>
          <td class="tit">出借方式</td>
          <td>${fns:getDictLabel(financeTransaction.lendingMethod, 'LENDING_METHOD', '')}</td>
        </tr>
        <tr>
          <td class="tit">交易单号</td>
          <td>${financeTransaction.capitalCode}</td>
          <td class="tit">是否提前退出</td>
          <td>${financeTransaction.earlyExit}</td>
        </tr>
        <tr>
          <td class="tit">出借时间</td>
          <td><fmt:formatDate value="${financeTransaction.capitalInvestTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          <td class="tit">状态</td>
          <td>
          	<c:forEach items="${capitalStatuses }" var="capitalStatus">
				<c:if test="${financeTransaction.capitalStatus == capitalStatus.value }">
					${capitalStatus.label }
				</c:if>
			</c:forEach>
		</td>
        </tr>
        <tr>
          <td class="tit">到期日</td>
          <td><fmt:formatDate value="${financeTransaction.endDate}" pattern="yyyy-MM-dd"/></td>
          <td class="tit">匹配失败金额（元）</td>
          <td><fmt:formatNumber value="${financeTransaction.returnedFunds}" pattern="#,#00.0#"/></td>
        </tr>
        <tr>
          <td class="tit">预计收益（元）</td>
          <td><fmt:formatNumber value="${financeTransaction.projectedEarnings}" pattern="#,#00.0#"/></td>
          <td class="tit">已收收益（元）</td>
          <td><fmt:formatNumber value="${financeTransaction.paidIncome}" pattern="#,#00.0#"/></td>
        </tr>
        <tr>
          <td class="tit">申请退出时间</td>
          <td><fmt:formatDate value="${financeTransaction.quitTime}" pattern="yyyy-MM-dd"/></td>
          <td class="tit">退出日</td>
          <td><fmt:formatDate value="${financeTransaction.realQuitTime}" pattern="yyyy-MM-dd"/></td>
        </tr>
      </table>
    </fieldset>
  </form:form>
  
  <hr style="height:10px;" />
  
  <div class="tabsPage">
    <ul class="nav nav-tabs">
      <li class="active"><a href="###">投标记录</a></li>
    </ul>
  </div>
  
  <br />
  <div class="tabscontbox">

    <div id="p1" style="display: block;">
      <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>借款编号</th>
            <th>交易单号</th>
            <th>出借时间</th>
            <th>匹配成功时间</th>
            <th>出借金额（元）</th>
            <th>复投状态</th>
            <th>起息日</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${financeInvests}" var="financeInvest" varStatus="status">
            <tr>
              <td>${financeInvest.loanNumber}</td>
              <td>${financeInvest.investCode}</td>
              <td><fmt:formatDate value="${financeInvest.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td><fmt:formatDate value="${financeInvest.matchedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td><fmt:formatNumber value="${financeInvest.investAmount}" pattern="#,#00.0#"/></td>
              <td>
              	<c:forEach items="${investStatuses }" var="investStatus">
					<c:if test="${financeInvest.investStatus == investStatus.value }">
						${investStatus.label }
					</c:if>
				</c:forEach>
              </td>
              <td><fmt:formatDate value="${financeInvest.interestDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td><a href="javascript:void(0)" onclick="openListModel('${financeInvest.investId}')">回款计划</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </div>
  
  <p style="text-align: center;">
	<button class="btn btn-success" onclick="history.go(-1)">返回</button>
  </p>
  
	<div id="listModal" class="containbox3" style="display:none; overflow-y: scroll; height: 350px; position: fixed; top: 50%; 
			left: 50%; width: 1000px; padding: 45px 30px;margin-left:-530px; margin-top: -220px; z-index: 11; background: #fff;">
		<a href="javascript:void(0);" style="position: absolute;right: 0px;top: 12px;font-size: 40px;display: inline-block; width: 45px;
			height: 45px;text-align: center;text-decoration:none" onclick="closeListModel()">×</a>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>期数</th>
            <th>本金（元）</th>
            <th>利息（元）</th>
            <th>总额（元）</th>
            <th>还款日</th>
            <th>状态</th>
          </tr>
        </thead>
        <tbody id="repayBillplans">
          <%-- <c:forEach items="${financeInvests}" var="financeInvest" varStatus="status">
            <tr>
              <td>${financeInvest.loanNumber}</td>
              <td>${financeInvest.investCode}</td>
              <td><fmt:formatDate value="${financeInvest.investTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td><fmt:formatDate value="${financeInvest.matchedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
              <td><fmt:formatNumber value="${financeInvest.investAmount}" pattern="#,#00.0#"/></td>
              <td></td>
            </tr>
          </c:forEach> --%>
        </tbody>
      </table>
	</div>
	<div class="refreshBg" style="display:none; position: fixed; top: 0; left: 0; opacity: 0.3; background: black; width: 100%; height: 100%;"></div>
	  	
</body>
</html>