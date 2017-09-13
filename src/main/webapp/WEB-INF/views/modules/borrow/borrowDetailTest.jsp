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

</script>
</head>
<body>
  <ul class="nav nav-tabs">
    <li><a href="${ctx}/borrow/tBorrow/">普享标集列表</a></li>
    <li class="active"><a href="###">普享标集详情</a></li>
  </ul>
  
  <form:form id="inputForm" modelAttribute="###" action="####" method="post" class="form-horizontal">
    <fieldset>
      <legend>普享标集合9870</legend>
      <table class="table-form">
        <tr>
          <td class="tit">标的编号</td>
          <td>${tBorrow.borrowtitle}</td>
          <td class="tit">标的名称</td>
          <td>${tBorrow.borrowtitle}</td>
        </tr>
        <tr>
          <td class="tit">借款期限</td>
          <td>${tBorrow.borrowtitle}</td>
          <td class="tit">还款方式</td>
          <td>${tBorrow.borrowtitle}</td>
        </tr>
        <tr>
          <td class="tit">借款金额</td>
          <td>${tBorrow.borrowtitle}</td>
          <td class="tit">出借年化利率</td>
          <td>${tBorrow.borrowtitle}</td>
        </tr>
        <tr>
          <td class="tit">发布时间</td>
          <td>${tBorrow.borrowtitle}</td>
          <td class="tit">最低出借金额</td>
          <td>${tBorrow.borrowtitle}</td>
        </tr>
        <tr>
          <td class="tit">状态</td>
          <td>${tBorrow.borrowtitle}</td>
          <td class="tit">募集期</td>
          <td>${tBorrow.borrowtitle}</td>
        </tr>
        <tr>
          <td class="tit">放款时间</td>
          <td>${tBorrow.borrowtitle}</td>
          <td class="tit">满标时间</td>
          <td>${tBorrow.borrowtitle}</td>
        </tr>
        <tr>
          <td class="tit">风险提示</td>
          <td colspan="5">
<%--             <form:textarea path="content" class="required" rows="5" maxlength="200" cssStyle="width:500px"/> --%>
          </td>
        </tr>
        <tr>
          <td class="tit">项目介绍</td>
          <td colspan="5">
<%--             <form:textarea path="content" class="required" rows="5" maxlength="200" cssStyle="width:500px"/> --%>
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
      <li><a href="###">还款计划</a></li>
    </ul>
  </div>
  
  <br />
  <div class="tabscontbox">

    <div id="p1" style="display: block;">
      <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>接收人</th>
            <th>类型</th>
            <th>短信内容</th>
            <th>更新时间</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>23114</td>
            <td>23423114234</td>
            <td>234234231114234234</td>
            <td>234234234111234234</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div id="p2" class="hide" style="display: none;">
      <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>接收人</th>
            <th>类型</th>
            <th>短信内容</th>
            <th>更新时间</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>234234</td>
            <td>234234234</td>
            <td>234234234234234</td>
            <td>234234234234234</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div id="p3" class="hide" style="display: none;">
      <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
          <tr>
            <th>接收人</th>
            <th>类型</th>
            <th>短信内容</th>
            <th>更新时间</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>88884</td>
            <td>2388234</td>
            <td>2342348888234234234</td>
            <td>2342342888834234234</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

</body>
</html>