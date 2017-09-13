<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
 <head>
    <title>用户基本信息</title>
    <meta name="decorator" content="default"/>
 </head>
<style>
 /*  .table tr td{width:250px;}
  table tr td{width:300px;} */
</style>
<script type="text/javascript">
 
</script>
  <body>
       <div style="margin: 10px;padding: 10px;font-size: 20px;">
             <ul class="ul-form">
                 <li style="float:left;padding-right: 30px;list-style-type:none" class="active">
                 <a href="${ctx}/user/userManage/baseInfo?id=${id}">基本信息</a></li>
                 <li style="list-style-type:none;">
                 <a href="${ctx}/user/userManage/userSalaryCount">用户资产</a></li>
             </ul>
       </div>
  </body>
</html>
