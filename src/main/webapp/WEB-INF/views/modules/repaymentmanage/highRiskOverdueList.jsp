<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>还款列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">

  function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
  }

  /*全选*/
  function checkAll(obj) {
    if (obj.checked) {
      $("input[name='check']").attr('checked', true);
    } else {
      $("input[name='check']").attr('checked', false);
    }
  }
  
  $(function() {
    //设置全选复选框
    $("#list :checkbox").click(function() {
      allchk();
    });
  });
  
  function allchk() {
    var chknum = $("#list :checkbox").size();//选项总个数
    var chk = 0;
    $("#list :checkbox").each(function() {
      if ($(this).prop("checked") == true) {
        chk++;
      }
    });
    if (chknum == chk) {//全选
      $("#check").prop("checked", true);
    } else {//不全选
      $("#check").prop("checked", false);
    }
  }
  
  $(function() {
    //批量垫付
    $("#advanceBatch").click(function() {
      if ($("input[name='check']:checked").length <= 0) {
        $(".modal-title").text("友好提示");
        $("#content").text("请选择要垫付的期数");
        $("#confirm").hide();
        $("#myModal").modal('show');
      } else {
        $(".modal-title").text("友好提示");
        $("#confirm").show();
        $("#myModal").modal('show');
        var money = 0.00;
        var count = 0;
        $("input[name='check']:checked").each(function(){ 
          var tmp = $(this).parents("tr").find("td");
          money = money + parseFloat(tmp.eq(6).html()) + parseFloat(tmp.eq(7).html());
          count = count + 1;
        });
        $("#content").text("您本次垫付金额为：" + money.toFixed(2) + "垫付期数为：" + count + "期");
      }
    });
    
    $("#confirm").click(function(){
      var repaymentIdList = new Array();
      $("input[name='check']:checked").each(function(){ 
        repaymentIdList.push($(this).val());
      });
      $("#repaymentId").val(repaymentIdList);
      $("#searchForm").attr("action","${ctx}/repayment/OverdueManage/toHighRiskRepayment");
      $("#searchForm").submit();
    });
    
  });
  
  //垫付
  function advance(repaymentId){
    if (confirm("确认垫付吗？")) {
    	$("#repaymentId").val(repaymentId);
        $("#searchForm").attr("action","${ctx}/repayment/OverdueManage/toHighRiskRepayment");
        $("#searchForm").submit();
	}
  }
</script>
</head>
<body>
  <ul class="nav nav-tabs">
    <li class="active"><a href="###">高风险列表</a></li>
  </ul>
  <form id="searchForm" modelAttribute="overdueVO" action="${ctx}/repayment/OverdueManage/toHighRiskOverdueList" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" /> 
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
    <div>
      <label>标的编号：</label><input type="text" name="borrowCode" value="${view.borrowCode}" htmlEscape="false" maxlength="32" class="input-medium">
      <label>借款人：</label><input type="text" name="name" value="${view.name }" htmlEscape="false" maxlength="32" class="input-medium">
      <label>借款编号：</label><input type="text" name="loanNumber" value="${view.loanNumber }" htmlEscape="false" maxlength="32" class="input-medium">
      <label>逾期天数：</label>
      <input type="text" name="overdueDayStart" value="${view.overdueDayStart }" htmlEscape="false" maxlength="5" class="input-mini"> 至 
      <input type="text" name="overdueDayEnd" value="${view.overdueDayEnd }" htmlEscape="false" maxlength="5" class="input-mini">
      <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
    </div>
    <div style="margin-top:8px;overflow:hidden">
      <input id="repaymentId" name="repaymentIdList" type="hidden" /> 
      <span style="font-weight:bold;">逾期总额：${overdueTotalAmount}元</span>
      <input id="advanceBatch" class="btn btn-info" type="button" value="垫付" style="display: block;float:right;"/>
    </div>
  </form>
  <sys:message content="${message}"/>
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th><input type="checkbox" id="check" onclick="checkAll(this)"/>期数</th>
        <th>标的编号</th>
        <th>借款编号</th>
        <th>借款人</th>
        <th>借款人手机号</th>
        <th>借款金额</th>
        <th>本金</th>
        <th>利息</th>
        <th>罚息</th>
        <th>违约金</th>
        <th>总额</th>
        <th>垫付金额</th>
        <th>垫付时间</th>
<!--         <th>冲抵金额</th> -->
<!--         <th>冲抵时间</th> -->
        <th>还款时间</th>
        <th>逾期天数</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${overdueVOList}" var="overdue">
        <tr>
          <td>
            <c:if test="${overdue.advanceStatus =='0' }">
              <input type="checkbox" name="check" value="${overdue.repaymentId}" /> ${overdue.period}
            </c:if>
            <c:if test="${overdue.advanceStatus !='0' }">
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${overdue.period}
            </c:if>
          </td>
          <td>${overdue.borrowCode}</td>
          <td><a href="${ctx }/borrow/borrowApply/toBorrowDetail?id=${overdue.borrowId}">${overdue.loanNumber}</a></td>
          <td>${overdue.name}</td>
          <td>${overdue.mobile}</td>
          <td>${overdue.borrowAmount}</td>
          <td>${overdue.monthCapital }</td>
          <td>${overdue.monthInterest}</td>
          <td>${overdue.lateChargeOrigin}</td>
          <td>${overdue.failsChargeOrigin}</td>
          <td>${overdue.totalAmount}</td>
          <td>${overdue.advancesAmount}</td>
          <td><fmt:formatDate value="${overdue.advancesTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
<%--           <td>${overdue.offsetAmount}</td> --%>
<%--           <td><fmt:formatDate value="${overdue.offsetTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>  --%>
          <td><fmt:formatDate value="${overdue.repaymentDate}" pattern="yyyy-MM-dd"/></td>
          <td>${overdue.overdueDay}</td>
          <td>
            <c:if test="${overdue.advanceStatus =='0' }">未垫付</c:if>
            <c:if test="${overdue.advanceStatus !='0' && overdue.advanceStatus !='4'}">垫付中</c:if>
            <c:if test="${overdue.advanceStatus =='4' }">已经垫付</c:if>
          </td>
          <td>
            <c:if test="${overdue.advanceStatus =='0' }"><a href="javascript:void(0)" onclick="advance(${overdue.repaymentId})" >垫付</a></c:if>
          </td>
      </c:forEach>

    </tbody>
  </table>
  <div class="pagination">${page}</div>

  <!-- 模态框声明 -->
  <div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" keyboard="true" style="display:none">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
      <!-- 内容声明 -->
      <div class="modal-content">
        <div class="modal-header">
          <button class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body" style="height: 180px;">
          <div class="container-fluid">
            <div style="line-height: 80px; text-align: center; font-size: 18px;">
              <p id="content"></p>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-default" data-dismiss="modal">返回</button>
          <button class="btn btn-primary" data-dismiss="modal" id="confirm">确认</button>
        </div>
      </div>
    </div>
  </div>
</body>

</html>