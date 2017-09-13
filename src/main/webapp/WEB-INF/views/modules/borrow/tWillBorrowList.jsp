<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>借款意向列表</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
  }

  $(function() {
    $("a[name='handle']").click(function() {
      var id = $(this).parents("tr").find("td").eq(0).html();
      var html = "<div style='padding:10px;'> 处理结果描述（50字以内） <textarea style='margin: 0px 0px 10px; width: 310px; height: 140px;' id='remark' name='remark' /></div>";
      var submit = function(v, h, f) {
        if (f.remark == '') {
          $.jBox.tip("请输入内容。", 'error', {
            focusId : "remark"
          }); // 关闭设置 yourname 为焦点
          return false;
        }
        if (f.remark.length > 50) {
          $.jBox.tip("结果描述50字以内", 'error', {
            focusId : "remark"
          }); // 关闭设置 yourname 为焦点
          return false;
        }
        $.jBox.tip("保存并发送！");
        $("#remarks").val(f.remark);
        $("#id").val(id);
        $("#inputForm").submit();
        return true;
      };
      $.jBox(html, {
        title : "处理",
        submit : submit
      });
    });
    
    $("a[name='showHandle']").click(function() {
      var name = $(this).parents("tr").find("td").eq(10).html();
      var time = $(this).parents("tr").find("td").eq(9).html();
      var remarks = $(this).parents("tr").find("td").eq(8).html();
      var info = '操作人：'+ name +' <br /> 时间：'+ time +'  <br /> 处理结果：'+ remarks + '<br />';
      $.jBox(info);
    });
    
    $("#province").change(function(){ 
      var pid = $("#province option:selected").attr("id");
      $.ajax({
        type:"post",
        url:ctx+"/sys/place/getCity",
        data:{pid:pid},
        dataType:"json",
        success:function(data){
          $("#city").find("option").remove(); 
          $("#city").text("");
          $("#city").append("<option id='' value=''>请选择</option>");
          for(var i=0;i<data.length;i++){
            $("#city").append("<option id='"+data[i].code+"' value='"+data[i].name+"'>"+data[i].name+"</option>");
          }
        }
      });
    });
    
    $("#btnExport").click(function(){
      top.$.jBox.confirm("确认要导出借款意向吗？","系统提示",function(v,h,f){
        if(v=="ok"){
          $("#searchForm").attr("action","${ctx}/borrow/tWillBorrow/export");
          $("#searchForm").submit();
        }
      },{buttonsFocus:1});
      top.$('.jbox-body .jbox-icon').css('top','55px');
    });
    
    $("#city").change(function(){ 
      var pid = $("#city option:selected").attr("id");
      $.ajax({
        type:"post",
        url:ctx+"/sys/place/getArea",
        data:{pid:pid},
        dataType:"json",
        success:function(data){
          $("#area").find("option").remove(); 
          $("#area").text("");
          $("#area").append("<option id='' value=''>请选择</option>");
          for(var i=0;i<data.length;i++){
            $("#area").append("<option id='"+data[i].code+"' value='"+data[i].name+"'>"+data[i].name+"</option>");
          }
        }
      });
    });
    
    $("#area").change(function(){
      var province = $("#province").val();
      var city = $("#city").val();
      var area = $("#area").val();
      $("#workplace").val(province+" "+city+" "+area);
    });
    
    //初始化省
    getProvince();
  });
  
  function getProvince(){
    $.ajax({
      type:"post",
      url:ctx+"/sys/place/getProvince",
      dataType:"json",
      success:function(data){
        $("#province").find("option").remove(); 
        $("#province").text("");
        $("#province").append("<option id='' value=''>请选择</option>");
        for (var i = 0; i < data.length; i++) {
          $("#province").append("<option id='"+data[i].code+"' value='"+data[i].name+"'>" + data[i].name + "</option>");
        }
      }
    });
    
  }
</script>
</head>
<body>
  <ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/borrow/tWillBorrow/">借款意向列表</a></li>
  </ul>
  <form:form id="searchForm" modelAttribute="tWillBorrow" action="${ctx}/borrow/tWillBorrow/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
    <ul class="ul-form">
      <li><label>姓名：</label> <form:input path="name" htmlEscape="false" maxlength="32" class="input-medium" /></li>
      <li><label>手机号码：</label> <form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium" /></li>
      <li><label>状态：</label> 
        <form:select path="status" class="input-medium">
          <option value="">请选择</option>
          <form:option value="0">未处理</form:option>
          <form:option value="1">已处理</form:option>
        </form:select></li>
    </ul>
    <ul class="ul-form">
      <li>
      <label>省份：</label>
      <form:select id="province" path="province" class="input-medium">
        <option value="">请选择</option>
      </form:select>
      </li>
      <li>
      <label>城市：</label>
      <form:select id="city" path="city" class="input-medium">
        <option value="">请选择</option>
      </form:select>
      </li>
      <li>
      <label>地区：</label>
      <form:select id="area" path="area" class="input-medium">
        <option value="">请选择</option>
      </form:select>
      </li>
      <li style="display:none;"><label>工作地点：</label> <form:input id="workplace" path="workplace" htmlEscape="false" maxlength="128" class="input-medium" /></li>
      <li><label>申请时间：</label> <input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${tWillBorrow.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /> - <input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${tWillBorrow.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" /></li>
      <li class="btns">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
        <input id="btnExport" class="btn btn-info" type="button" value="导出"/>
      </li>
      <li class="clearfix"></li>
    </ul>
  </form:form>
  <sys:message content="${message}" />
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
      <tr>
        <th>姓名</th>
        <th>手机号码</th>
        <th>借款金额(万元)</th>
        <th>工作地点</th>
        <th>申请时间</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${page.list}" var="tWillBorrow">
        <tr>
          <td style="display:none">${tWillBorrow.id}</td>
          <td>${tWillBorrow.name}</td>
          <td>${tWillBorrow.mobile}</td>
          <td>${tWillBorrow.borrowamount}</td>
          <td>${tWillBorrow.workplace}</td>
          <td><fmt:formatDate value="${tWillBorrow.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          <td>
            <c:if test="${tWillBorrow.status == '0'}">未处理</c:if>
            <c:if test="${tWillBorrow.status == '1'}">已处理</c:if>
          </td>
          <td>
            <c:if test="${tWillBorrow.status == '0'}"><a name="handle" style="text-decoration:none; cursor:pointer;">处理</a></c:if>
            <c:if test="${tWillBorrow.status == '1'}"><a name="showHandle" style="text-decoration:none; cursor:pointer;">处理结果</a></c:if>
          </td>
          <td style="display:none">${tWillBorrow.remarks}</td>
          <td style="display:none"><fmt:formatDate value="${tWillBorrow.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          <td style="display:none">${tWillBorrow.updateBy.name}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <div class="pagination">${page}</div>
  <form:form id="inputForm" modelAttribute="tWillBorrow" action="${ctx}/borrow/tWillBorrow/save" method="post" class="form-horizontal">
    <form:hidden id="id" path="id"/>
    <div class="control-group" hidden="hidden">
      <label class="control-label">备注内容：</label>
      <div class="controls">
        <form:textarea id="remarks" path="remarks" htmlEscape="false" rows="4" class="input-xxlarge " />
      </div>
    </div>
  </form:form>
</body>
</html>