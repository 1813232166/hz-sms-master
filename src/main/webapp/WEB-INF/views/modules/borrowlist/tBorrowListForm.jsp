<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>普享标集详情</title>
<meta name="decorator" content="default" />
<script type="text/javascript">

  $(document).ready(function() {
    $('.tabsPage ul li').click(function() {
      $(this).addClass('active').siblings().removeClass('active');
      $('.tabscontbox>div:eq(' + $(this).index() + ')').show().siblings().hide();
    })
  });
  
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
    <li><a href="${ctx}/borrow/borrowList/">普享标集列表</a></li>
    <li class="active"><a href="###">普享标集详情</a></li>
  </ul>
  
  <form:form id="inputForm" modelAttribute="tBorrowList" action="${ctx}/borrow/borrowList/form" method="post" class="form-horizontal">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
    <input name="id" type="hidden" value="${tBorrowList.borrowListId}" />
    <fieldset>
      <legend>${tBorrowList.borrowListTitle}</legend>
      <table class="table-form">
        <tr>
          <td class="tit">标的编号</td>
          <td><form:input path="borrowListCode" htmlEscape="false" maxlength="50" readonly="true"/></td>
          <td class="tit">标的名称</td>
          <td><form:input path="borrowListTitle" htmlEscape="false" maxlength="50" readonly="true"/></td>
        </tr>
        <tr>
          <td class="tit">借款期限</td>
          <td><form:input path="deadLine" htmlEscape="false" maxlength="10" readonly="true" class="input-mini"/>个月</td>
          <td class="tit">还款方式</td>
          <td>${fns:getDictLabel(tBorrowList.payMethod, 'pay_method', '')}</td>
        </tr>
        <tr>
          <td class="tit">借款金额</td>
          <td><form:input path="borrowListAmount" htmlEscape="false" maxlength="10" readonly="true" class="input-small"/>元</td>
          <td class="tit">出借年化利率</td>
          <td><form:input path="anualRate" htmlEscape="false" maxlength="50" readonly="true" class="input-mini"/></td>
        </tr>
        <tr>
          <td class="tit">发布时间</td>
          <td><fmt:formatDate value="${tBorrowList.openTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          <td class="tit">最低出借金额</td>
          <td><form:input path="minTenderSum" htmlEscape="false" maxlength="50" readonly="true" class="input-small"/></td>
        </tr>
        <tr>
          <td class="tit">状态</td>
          <td>${statusMemo}</td>
          <td class="tit">募集期</td>
          <td><form:input path="raiseDay" htmlEscape="false" maxlength="50" readonly="true" class="input-small"/></td>
        </tr>
        <tr>
          <td class="tit">风险提示</td>
          <td colspan="5">
            <form:textarea path="riskWarning" class="required" rows="5" maxlength="200" cssStyle="width:500px"/>
          </td>
        </tr>
        <tr>
          <td class="tit">项目介绍</td>
          <td colspan="5">
            <form:textarea path="projectBrief" class="required" rows="5" maxlength="200" cssStyle="width:500px"/>
          </td>
        </tr>
      </table>
    </fieldset>
  </form:form>
  
  <hr style="height:10px;" />
  
  <div class="tabsPage">
    <ul class="nav nav-tabs">
      <li class="active"><a href="###">匹配债权</a></li>
      <li><a href="###">出借记录</a></li>
    </ul>
  </div>
  
  <br />
  <div class="tabscontbox">

    <div id="p1" style="display: block;">
      <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>序号</th>
            <th>借款编号</th>
            <th>借款期限</th>
            <th>借款利率</th>
            <th>借款金额</th>
            <th>借款人</th>
            <th>还款方式</th>
            <th>紧急状态</th>
            <th>批贷时间</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${tborrow_List}" var="tBorrow" varStatus="status">
            <tr>
              <td>${status.index + 1}</td>
              <td><a href="${ctx}/borrow/borrowApply/toBorrowDetail?id=${tBorrow.borrowId}">${tBorrow.loannumber}</a></td>
              <td>${tBorrow.deadline}个月</td>
              <td>${tBorrow.anualrate}%</td>
              <td>${tBorrow.borrowamount}</td>
              <td>${tBorrow.userName}</td>
              <td>
                <c:if test="${'debx'==tBorrow.payMethod}">等额本息</c:if> 
                <c:if test="${'ychbx'==tBorrow.payMethod}">一次性还本付息</c:if> 
                <c:if test="${'xxhb'==tBorrow.payMethod}">先息后本</c:if>
              </td>
              <td>
                <c:choose>
                  <c:when test="${'2' eq tBorrow.criticalid}">不紧急</c:when>
                  <c:when test="${'1' eq tBorrow.criticalid}">紧急</c:when>
                  <c:otherwise>--</c:otherwise>
                </c:choose>
              </td>
              <td><fmt:formatDate value="${tBorrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

    <div id="p2" class="hide" style="display: none;">
      <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>序号</th>
            <th>出借人</th>
            <th>出借金额（元）</th>
            <th>出借时间</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${page.list}" var="inv" varStatus="status">
          <tr>
            <td>${status.count + page.pageSize*(page.pageNo-1)}</td>
            <td>${inv.realName}</td>
            <td>${inv.investamount}</td>
            <td><fmt:formatDate value="${inv.investtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
      <div class="pagination">${page}</div>
    </div>

  </div>

</body>
</html>