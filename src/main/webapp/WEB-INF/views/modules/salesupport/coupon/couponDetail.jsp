<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>优惠券管理</title>
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
/*             //弹出框
            $("#btnSumAudit").click(function(){
            	var id = $(this).parents("tr").find("td").eq(0).html();
                var html = "<div style='padding:10px;'> 您确定要提交审核词条记录 </div>";
                var submit = function(v, h, f) {
                  $.jBox.tip("提交审核!");
                  $("#inputForm").submit();
                  return true;
                };
                $.jBox(html, {
                  title : "提示信息",
          	        submit : submit
                });
            }); */
            
            //提交审核
			$("#btnSumAudit").click(function(){
				$("#dialog1").text("您确定要提交审核此条记录");
				$("#publishSuccess").show();
				$("#myModal").modal('show');
				
			});
			
			
			//确定提交审核
			$("#publishSuccess").click(function(){
			     $("#inputForm").submit();
                  return true;
			});
			
			//取消按钮
			$("#fanhui1").click(function(){
				$("#myModal").hide();
				
				var ids=$("#id").val();
				window.location.href= ctx+"/coupon/couponManage/couponDetail?id="+ids+"";
			});
			//取消按钮
			$("#close1").click(function(){
				$("#myModal").hide();
				var ids=$("#id").val();
				window.location.href= ctx+"/coupon/couponManage/couponDetail?id="+ids+"";
			});
			
            
        });
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
        <li class="active"><a href="javascript:;">优惠券详情</a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="coupon" action="${ctx}/coupon/couponManage/submitAudit" method="post" class="form-horizontal">
        <sys:message content="${message}"/>   
          <input id="id" name="id" type="hidden"  value="${coupon.id}"/>
        <div class="control-group">
            <label class="control-label">*优惠券类型</label>
            <div class="controls">
            ${fns:getDictLabel(coupon.couponTypeId,'couponType','')}
                <input name="couponTypeId"  id="couponTypeId" type="hidden"  value="${coupon.couponTypeId}"/>
                <input type="hidden" readonly="readonly" value="${fns:getDictLabel(coupon.couponTypeId,'couponType','')}" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*优惠券面额：</label>
            <div class="controls">
            ${coupon.faceValue}
                <input  type="hidden" name="faceValue" readonly="readonly" value="${coupon.faceValue}"   class="input-xlarge "/><em  id="fvText">  
					</em>
            </div>
        </div>
        <div class="control-group" <c:if test="${coupon.statusshow=='2' }">hidden</c:if>>
            <label class="control-label"><input <c:if test="${coupon.statusshow=='1' }">checked</c:if> type="radio"/>有效期：</label>
            <div class="controls">
            ${coupon.effectiveDays}天
                <input  type="hidden" name="effectiveDays" readonly="readonly" value="${coupon.effectiveDays}"   maxlength="3" class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group" <c:if test="${coupon.statusshow=='1' }">hidden</c:if>>
            <label class="control-label"><input  <c:if test="${coupon.statusshow=='2' }">checked</c:if> type="radio"/>使用期限：</label>
            <div class="controls">
            <fmt:formatDate value="${coupon.beginTime}" pattern="yyyy-MM-dd"/>
                <input type="hidden" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                    value="<fmt:formatDate value="${coupon.beginTime}" pattern="yyyy-MM-dd HH:mm"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>至
                    <fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd HH:mm"/>
                <input  type="hidden" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                    value="<fmt:formatDate value="${coupon.endTime}" pattern="yyyy-MM-dd HH:mm"/>"
                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*出借金额限制：</label>
            <div class="controls">
               <c:choose><c:when test="${'0.00'==coupon.loanAmountMin}">无限制</c:when><c:otherwise>${coupon.loanAmountMin}</c:otherwise></c:choose>
                <input type="hidden" name="loanAmountMin" readonly="readonly" value="${coupon.loanAmountMin}"   class="input-xlarge "/>
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
                <c:if test="${0==coupon.couponCount}">无限制</c:if>
                <c:choose><c:when test="${0==coupon.couponCount}">无限制</c:when><c:otherwise>${coupon.couponCount}</c:otherwise></c:choose>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*最多发送张数：</label>
            <div class="controls">
            ${coupon.couponCount}<c:if test="${!empty coupon.couponCount}">张</c:if>
               <c:choose><c:when test="${0==coupon.couponCount}">无限制</c:when><c:otherwise>${coupon.couponCount}</c:otherwise></c:choose>
                <input type="hidden" name="couponCount" readonly="readonly"   value="${coupon.couponCount}" class="input-xlarge "/>
            </div>
        </div> --%>
        <div class="control-group">
            <label class="control-label">*创建时间：</label>
            <div class="controls">
            <fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm"/>
                <input  type="hidden" name="createdate" type="text" readonly="readonly" maxlength="20" class="input-medium"
                    value="<fmt:formatDate value="${coupon.createDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*审核时间：</label>
            <div class="controls">
            <fmt:formatDate value="${coupon.auditTime}" pattern="yyyy-MM-dd HH:mm"/>
               <input  type="hidden" name="auditTime" type="text" readonly="readonly" maxlength="20" class="input-medium"
                    value="<fmt:formatDate value="${coupon.auditTime}" pattern="yyyy-MM-dd HH:mm"/>"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*备注：</label>
            <div class="controls">
            ${coupon.remarks}
                <input type="hidden" name="remarks"  readonly="readonly" value="${coupon.remarks}"   class="input-xlarge "/>
            </div>
        </div>
        <div class="form-actions">
            <c:if test="${('0'==coupon.status or '2'==coupon.status) and '1'!=coupon.shixiao}">
	            <input id="btnSumAudit" class="btn btn-primary" type="button" value="提交审核"/>
            </c:if>
	            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
    
     <!-- 模态框声明 -->
	<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" style="width: 450px;">
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					
					<button class="close" id="close1"><span>&times;</span></button>
					<h4 class="modal-title">友好提示</h4>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="line-height: 80px;text-align: center;font-size: 18px;">
							<p id="dialog1"></p>
					</div>
				</div>
				<div class="modal-footer" align="center">
					<p class="text-center">
						<button class="btn btn-primary" id="publishSuccess" >确定</button>
						<button class="btn btn-primary" id="fanhui1" >取消</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>