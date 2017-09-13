<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>优惠券管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
	  //获取单选框值
	    function  getValue(obj){
	        var value = obj.value;
	         $("#auditStatus").val(value);
	    }
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
            
          //弹出框
            $("#btnSumAudit").click(function(){
               // var id = $(this).parents("tr").find("td").eq(0).html();
                var html = "<div style='padding:10px;'>  <input type='radio' name='auditStatus'  onclick='getValue(this)' value='1'/>审核通过并生效    <input type='radio' name='auditStatus' onclick='getValue(this)' value='2' checked/>审核不通过<textarea style='margin: 0px 0px 10px; width: 310px; height: 140px;'  name='auditContent' id='auditContent' /></div>";
                var submit = function(v, h, f) {
                	if($('input:radio[name="auditStatus"]:checked').val()==2 && v == 'ok'){
                	if (f.auditContent.length > 50 ||f.auditContent.length<=0 ) {
                    $.jBox.tip("结果描述50字以内", 'error', {
                      focusId : "auditContent"
                    }); // 关闭设置 yourname 为焦点
                    return false;
                  }
                	}
                  //$.jBox.tip("保存");
                  $("#auditContentH").val(f.auditContent);
                  $("#auditStatusH").val(f.auditStatus);
                  if ('1'==f.auditStatus) {//通过
					$("#statusH").val('4');
				   }else {
					   $("#statusH").val('2');
				  }
                  if (v == 'ok'){
	                  $("#inputForm").submit();
                  }
                  return true;
                };//submit = function(v, h, f) 
                $.jBox(html, {
                  title : "审核信息",
                  buttons : {
                      '确定' : 'ok',
                      '取消' : 'cancel' 
                  },
                  submit : submit
                });
            });
        });
        function  getValue(obj) {
			$("#auditStatusH").val(obj.value);
		}
        $(function(){
          	 var cptype= $("#couponTypeId").val();//
          	 $("#fvText").text("");
               if ('cashBack'==cptype) {
                   $("#fvText").append("元");
               }
               if ('interest'==cptype) {
                   $("#fvText").append("%");
               }
      }) 
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/coupon/couponManage/couponManageList">优惠券列表</a></li>
        <li class="active"><a href="${ctx}/coupon/couponManage/couponAuditList">优惠券审核列表</a></li>
        <li class="active"><a href="javascript:;">优惠券审核详情</a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="coupon" action="${ctx}/coupon/couponManage/auditCoupon" method="post" class="form-horizontal">
        <sys:message content="${message}"/>   
          <input name="id" type="hidden"  value="${coupon.id}"/>
          <input name="auditContent" type="hidden" id='auditContentH'/>
          <input name="auditStatus" type="hidden"  id="auditStatusH"/>
          <input name="status" type="hidden"  id="statusH"/>
        <div class="control-group">
            <label class="control-label">*优惠券类型</label>
            <div class="controls">
            ${fns:getDictLabel(coupon.couponTypeId,'couponType','')}
                <input name="couponTypeId" id="couponTypeId" type="hidden"  value="${coupon.couponTypeId}"/>
                <input  type="hidden" readonly="readonly" value="${fns:getDictLabel(coupon.couponTypeId,'couponType','')}" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*优惠券面额：</label>
            <div class="controls">
            ${coupon.faceValue}
                <input type="hidden" name="faceValue" readonly="readonly" value="${coupon.faceValue}"   class="input-xlarge "/><em  id="fvText">  
					</em>
            </div>
        </div>
        <div class="control-group" <c:if test="${coupon.statusshow=='2' }">hidden</c:if>>
            <label class="control-label"><input <c:if test="${coupon.statusshow=='1' }">checked</c:if> type="radio"/>有效期：</label>
            <div class="controls">
             ${coupon.effectiveDays}<c:if test="${empty coupon.effectiveDays}">天</c:if>
                <input type="hidden"  name="effectiveDays" readonly="readonly" value="${coupon.effectiveDays}"   maxlength="3" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group"  <c:if test="${coupon.statusshow=='1' }">hidden</c:if>>
            <label class="control-label"><input <c:if test="${coupon.statusshow=='2' }">checked</c:if> type="radio"/>使用期限：</label>
            <div class="controls">
              <fmt:formatDate value="${coupon.beginTime}" pattern="yyyy-MM-dd"/>
                <input type="hidden" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                    value="<fmt:formatDate value="${coupon.beginTime}" pattern="yyyy-MM-dd HH:mm"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>至
                    <fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd HH:mm"/>
                <input type="hidden" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                    value="<fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd HH:mm"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*出借金额限制：</label>
            <div class="controls">
              <c:choose><c:when test="${'0.00'==coupon.loanAmountMin}">无限制</c:when><c:otherwise>${coupon.loanAmountMin}</c:otherwise></c:choose>
                <input  type="hidden"name="loanAmountMin" readonly="readonly" value="${coupon.loanAmountMin}"   class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*出借期限限制</label>
            <div class="controls">
            <c:choose><c:when test="${''==coupon.loanTermList  || '全部'==coupon.loanTermList }">无限制</c:when><c:otherwise> ${coupon.loanTermList}</c:otherwise></c:choose>
      		 
              
                <input type="hidden" name="loanTermList" readonly="readonly" value="${coupon.loanAmountMin}"   maxlength="60" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*出借端限制：</label>
            <div class="controls">
              <c:choose><c:when test="${''==coupon.loanChannelList || '全部'==coupon.loanChannelList}">无限制</c:when><c:otherwise> ${coupon.loanChannelList}</c:otherwise></c:choose>
                <input type="hidden" name="loanChannelList" readonly="readonly" value="${coupon.loanAmountMin}"   maxlength="20" class="input-xlarge "/>
            </div>
        </div>
        <%-- <div class="control-group">
            <label class="control-label">*优惠券上限：</label>
            <div class="controls">
                <c:choose><c:when test="${0==coupon.couponCount}">无限制</c:when><c:otherwise>${coupon.couponCount}</c:otherwise></c:choose>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*最多发送张数：</label>
            ${coupon.couponCount}<c:if test="${''!=coupon.couponCount}">张</c:if>
            <div class="controls">
                <input type="hidden" name="couponCount" readonly="readonly" value="${coupon.couponCount}" class="input-xlarge "/>
            </div>
        </div> --%>
        <div class="control-group">
            <label class="control-label">*创建时间：</label>
            <div class="controls">
            <fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm"/>
                <input type="hidden" name="createdate" type="text" readonly="readonly" maxlength="20" class="input-medium"
                    value="<fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*审核时间：</label>
            <div class="controls">
            <fmt:formatDate value="${coupon.auditTime}" pattern="yyyy-MM-dd HH:mm"/>
               <input type="hidden" name="auditTime" type="text" readonly="readonly" maxlength="20" class="input-medium"
                    value="<fmt:formatDate value="${coupon.auditTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*备注：</label>
            <div class="controls">
            ${coupon.remarks}
                <input  type="hidden" name="remarks"  readonly="readonly" value="${coupon.remarks}"   class="input-xlarge "/>
            </div>
        </div>
        <div class="form-actions">
        <c:if test="${ ('1'==coupon.status) and ('1'!=coupon.shixiao)}">
            <input id="btnSumAudit" class="btn btn-primary" type="button" value="审核"/>
        </c:if>
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
</body>
</html>