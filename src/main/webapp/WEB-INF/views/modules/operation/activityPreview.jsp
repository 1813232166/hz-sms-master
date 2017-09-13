<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>新建自定义活动</title>
  <meta name="decorator" content="default"/>
</head>
<body>
  <ul class="nav nav-tabs">
    <li><a href="${ctx}/operation/activity/">自定义活动列表</a></li>
    <li class="active"><a href="${ctx}/operation/activity/form?id=${activity.id}">自定义活动<shiro:hasPermission name="operation:activity:edit">${not empty activity.id?'修改':'新建'}</shiro:hasPermission><shiro:lacksPermission name="operation:activity:edit">查看</shiro:lacksPermission></a></li>
  </ul><br/>
  <form:form id="inputForm" modelAttribute="activity" action="${ctx}/operation/activity/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>   
    <div class="control-group">
      <label class="control-label">活动名称：</label>
      <div class="controls">
        <form:input path="activityname" htmlEscape="false" maxlength="50" class="input-xlarge required" readonly="true"/>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">奖励类型：</label>
      <div class="controls">
        <form:select path="rewardtype" class="input-xlarge ">
          <form:option value="" label=""/>
          <form:options items="${fns:getDictList('activity_rewardType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
        </form:select>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">奖励额度：</label>
      <div class="controls">
        <form:input path="rewardcount" htmlEscape="false" maxlength="10" class="input-xlarge " readonly="true"/>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">加息券ID：</label>
      <div class="controls">
        <form:input path="couponid" htmlEscape="false" maxlength="32" class="input-xlarge " readonly="true"/>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label">活动说明：</label>
      <div class="controls">
        <form:textarea path="activitydescribe" htmlEscape="false" rows="4" class="input-xxlarge " readonly="true"/>
      </div>
    </div>
  </form:form>

  <form:form class="breadcrumb form-search">
    <ul class="ul-form">
      <li><label>奖励名单 </label>
      </li>
      <li class="clearfix"></li>
    </ul>
  </form:form>
    
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th>客户姓名</th>
        <th>手机号</th>
        <th>状态</th>
        <th>备注</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="point">
     <tr>
       <td>${point.realName}</td>
       <td>${point.mobile}</td>
       <td>待发</td>
       <td>--</td>
     </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="pagination">${page}</div> 

</body>
</html>