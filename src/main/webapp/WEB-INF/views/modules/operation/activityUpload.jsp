<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
  <title>新建自定义活动</title>
  <meta name="decorator" content="default"/>
  <script type="text/javascript">
    $(document).ready(function() {
      //$("#name").focus();
      $("#inputForm").validate({
        submitHandler: function(form){
          loading('正在提交，请稍等...');
          form.submit();
        },
        errorContainer: "#messageBox",
        errorPlacement: function(error, element) {
          $("#messageBox").text("输入有误，请先更正。");
          if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
            error.appendTo(element.parent().parent());
          } else {
            error.insertAfter(element);
          }
        }
      });
      
      $("#btnImport").click(function(){
        $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
          bottomText:"允许导入“xls”或“xlsx”格式文件！"});
      });
    });

  </script>
</head>
<body>
  <ul class="nav nav-tabs">
    <li><a href="${ctx}/operation/activity/">自定义活动列表</a></li>
    <li class="active"><a href="${ctx}/operation/activity/form?id=${activity.id}">自定义活动<shiro:hasPermission name="operation:activity:edit">${not empty activity.id?'修改':'新建'}</shiro:hasPermission><shiro:lacksPermission name="operation:activity:edit">查看</shiro:lacksPermission></a></li>
  </ul><br/>
  
  <form:form class="breadcrumb form-search">
	  <ul class="ul-form">
	    <li><label>奖励名单 </label>
	    </li>
	    <li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
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
  
  <div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/operation/activity/import?id=${activity.id}" method="post" enctype="multipart/form-data"
      class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
      <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
      <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
      <a href="${ctx}/sys/user/import/template">下载模板</a>
    </form>
  </div>
  
</body>
</html>