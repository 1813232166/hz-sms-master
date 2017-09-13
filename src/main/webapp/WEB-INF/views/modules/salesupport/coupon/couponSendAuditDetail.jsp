<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>优惠券管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/salesupport/couponsend.js"></script>
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
          
          //弹出框
            $("#btnSumAudit").click(function(){
                var html = "<div style='padding:10px;'>  <input type='radio' name='auditStatus'  onclick='getValue(this)' value='1'/>审核通过并发送    <input type='radio' name='auditStatus' onclick='getValue(this)' value='0'/>审核不通过<textarea style='margin: 0px 0px 10px; width: 310px; height: 140px;'  name='auditContent' /></div>";
                var submit = function(v, h, f) {
                	if(v=='cancel'){
                        $.jBox.close(true); 
                    }
                    if (f.auditContent.length > 50) {
                      $.jBox.tip("结果描述50字以内", 'error', {
                        focusId : "auditContent"
                      }); // 关闭设置 yourname 为焦点
                      return false;
                    }
                    //$.jBox.tip("保存  f.auditStatus="+f.auditStatus+";f.auditContent"+f.auditContent);
	                    if (f.auditStatus) {
	                    	$("#auditContentH").val(f.auditContent);
	                        $("#auditStatusH").val(f.auditStatus);
	                        if ('1'==f.auditStatus) {//通过已发送
		                          $("#statusH").val('3');
		                          if (v == 'ok'){
	                                  $("#inputForm").submit();
	                                  $("#btnSumAudit").hide();
	                              }
	                         }
	                        if ('0'==f.auditStatus){
	                             $("#statusH").val('2');
			                    if (v == 'ok'){
	                             if(f.auditContent && (""!=f.auditContent)){
			                            $("#inputForm").submit();
			                            $("#btnSumAudit").hide();
			                        }else{
		                                alert("请填写不通过原因！");
			                        }
		                        }
	                             
	                        }
						}
	                    
                    return false;
                  };//submit = function(v, h, f) 
                
                $.ajax({
                    type : "POST",
                    data:$('#inputForm').serialize(),
                    url : "${ctx}/coupon/couponSend/judgeCouponStatus",
                    dataType: "JSON",
                    success:function(dates){
                       // console.log(dates);
                        if ("1"==dates) {
                            $.jBox.tip("含有失效优惠券，请重新编辑");
                        }
                       if ("0"==dates) {
                            //$('#inputForm').submit();
			                $.jBox(html, {
			                    title : "审核信息",
			                    buttons : {
			                        '确定' : 'ok',
			                        '取消' : 'cancel' 
			                    },
			                    persistent : true,
			                    submit : submit
			                  });
			                
                        }
                    },
                    error: function() {
                        alert("失败，请稍后再试！");
                    }
                });
                
                
            });//弹出框
        });
        function Lock_CheckForm(){
            $(".refreshBg").hide();
            $("#containbox2Id").hide();
        }
        function  getValue(obj) {
            $("#auditStatusH").val(obj.value);
        }
        function reback(){
        	window.location.href="${ctx}/coupon/couponSend/couponSendAuditList";
        }
        function usedCoupon(obj){
            var couponGroupId='${couponGroup.couponGroupId}';
            var url="${ctx}/coupon/couponSend/getUsedCoupon"
            var couponSend = new CouponSend(obj,couponGroupId,"",url);
            couponSend.showUsedCoupon();
        }
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#staticsForm").submit();
            return false;
        }
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/coupon/couponSend/couponSendAuditList">优惠券发放审核列表</a></li>
        <li class="active"><a href="javascript:;">优惠券发放审核详情</a></li>
    </ul><br/>
<form:form id="inputForm" modelAttribute="couponGroup" action="${ctx}/coupon/couponSend/auditCouponGroup" method="post" class="form-horizontal">
        <sys:message content="${message}"/>     
        <div class="control-group">
            <label class="control-label">*优惠券名称：</label>
            <div class="controls">${couponGroup.couponGroupName}
            </div>
        </div>


        <div class="control-group">
            <label class="control-label">*发送优惠券：</label>
            <!--表格-->
                    <div class="Mtable2">
                        <div class="tableBox">
                             <table id="couponContent" style="text-align: center; width: 800px;" border="1">
                                <tr align="center"><td>优惠券信息</td>   </tr>
                                <tbody id="list">
                                <c:choose>
                                    <c:when test="${not empty couponGroup.couponList}">
                                        <c:forEach items="${couponGroup.couponList}" var="vo">
                                            <tr>
                                               <td>
                                                    ${fns:getDictLabel(vo.couponTypeId,'couponType','')}&nbsp;
                                                                                                                                                面额${vo.faceValue}&nbsp; 
                     <c:choose><c:when test="${0==vo.effectiveDays}">有效期&nbsp;无</c:when><c:otherwise>有效期${vo.effectiveDays}天&nbsp;</c:otherwise></c:choose>
                    <c:choose><c:when test="${empty vo.beginTime}">使用时间&nbsp;无</c:when><c:otherwise>使用时间<fmt:formatDate value="${vo.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>至<fmt:formatDate value="${vo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;</c:otherwise></c:choose>                                                                                             
                     <c:choose><c:when test="${'0.00'==vo.loanAmountMin}">出借金额限制&nbsp;无限制</c:when><c:otherwise>出借金额限制${vo.loanAmountMin}元&nbsp;</c:otherwise></c:choose>
                     <c:choose><c:when test="${'全部'==vo.loanTermList}"> 出借期限限制&nbsp;无限制</c:when><c:otherwise>出借期限限制${vo.loanTermList}&nbsp;</c:otherwise></c:choose>
                     <c:choose><c:when test="${'全部'==vo.loanChannelList}">出借端限制 &nbsp;无限制</c:when><c:otherwise>出借端限制${vo.loanChannelList}&nbsp;</c:otherwise></c:choose>                                                                                                                     
                                              </td>
                                           </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="main_info">
                                            <td colspan="100" class="">没有相关数据</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>
        </div>

        
        <div class="control-group">
            <label class="control-label">*是否发送短信：</label>
            <div class="controls">
                <c:if test="${'1'==couponGroup.isSendMessage}">是</c:if>
                <c:if test="${'0'==couponGroup.isSendMessage}">否</c:if>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">短信内容：</label>
            <div class="controls">${couponGroup.messageContent}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"> *发送人数：</label>
            <div class="controls">${couponGroup.userCount}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*创建时间：</label>
            <div class="controls">
                <fmt:formatDate value="${couponGroup.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*审核时间：</label>
            <div class="controls">
                <fmt:formatDate value="${couponGroup.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*发送时间：</label>
            <div class="controls">
                <fmt:formatDate value="${couponGroup.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*发送状态：</label>
            <div class="controls">
                ${fns:getDictLabel(couponGroup.couponGroupStatus,'CouponGroupStatus','')}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">*备注：</label>
            <div class="controls">
                   ${couponGroup.remarks} 
            </div>
        </div>
        <input type="hidden" name="couponGroupId" value="${couponGroup.couponGroupId}"/>
        <input type="hidden" name="couponIds" value="${couponGroup.couponIds}"/>
        <input type="hidden" name="holderType" value="${couponGroup.holderType}"/>
        <input type="hidden" name="mobilseList" value="${couponGroup.mobilseList}"/>
        <input type="hidden" name="remarks"  id="auditContentH"/>
        <input type="hidden" name="couponGroupStatus"  id="statusH"/>
        <input type="hidden" name="auditStatus"  id="auditStatusH"/>
        <input type="hidden" name="couponGroupName" value="${couponGroup.couponGroupName}"/>
        <input type="hidden" name="isSendMessage"  value="${couponGroup.isSendMessage}"/>
        <input type="hidden" name="messageContent" value="${couponGroup.messageContent}"/>
        <input type="hidden" name="userCount" value="${couponGroup.userCount}"/>
        <input type="hidden" name="createdate" value="${couponGroup.createdate}"/>
        <input type="hidden" name="auditTime" value="${couponGroup.auditTime}"/>
        <input type="hidden" name="sendTime" value="${couponGroup.sendTime}"/>
        <div class="form-actions">
            <c:if test="${'1'==couponGroup.couponGroupStatus}">
                <input id="btnSumAudit" class="btn btn-primary" type="button" value="审  核"/>
            </c:if>
            <input id="btnCancel" class="btn" type="button" value="取 消" onclick="reback()"/>
        </div>
    </form:form>
    <!--发送用户数：xA券使用数量：xB券使用数量：xC券使用数量：xD券使用数量：x-->
     <div class="Mtable2">
        <form id="staticsForm" action="${ctx}/coupon/couponSend/sendAuditDetail" method="post" >
            <input id="pageNo" name="pageNo" type="hidden" value="${couponpage.pageNo}"/>
            <input id="pageSize" name="pageSize" type="hidden" value="${couponpage.pageSize}"/>
            <input type="hidden" name="couponGroupId" value="${couponGroup.couponGroupId}"/>
           <!--  <input id="endLimit" name="endLimit" type="hidden" value="20000"/> -->
        </form>
        <ul class="ul-form">
            <li style="float: left;">发送用户数：${couponpage.count}</li>
            <!-- <li style="float: left;">A券使用数量：</li>
            <li style="float: left;">B券使用数量：</li>
            <li style="float: left;">C券使用数量：</li>
            <li style="float: left;">D券使用数量：</li> -->
        </ul>
       <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th>ID</th>
                <th>手机号</th>
                <th>发送优惠券数量</th>
                <th>已使用优惠券数量</th>
                <th>未使用优惠券数量</th>
                <th>已过期优惠券数量</th>
                <%-- <shiro:hasPermission name="compaccount:coupon:edit"><th>操作</th></shiro:hasPermission> --%>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${couponpage.list}" var="coupon" varStatus="tfdAcc">
            <tr>
                <td>${tfdAcc.count+page.pageSize*(page.pageNo-1)}</td>
                <td>${coupon.userMobile}</td>
                <td>${coupon.sentCouponSum}</td>
                <td><a href="javascript:;" id="usedCouponSum" onclick="usedCoupon(${coupon.allMobile})">${coupon.usedCouponSum}</a></td>
                <td>${coupon.unusedCouponSum} </td>
                <td>${coupon.expiredCouponSum}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${couponpage}</div>
  </div>
   <!--  弹窗  -->
            <div class="containbox3" id="containbox2Id" style="width: 900px;height:490px;z-index:16; border:1px solid;display: none;position: absolute;left: 0px; right:0; top:0;overflow:scroll; margin-left:100px; margin-top:450px;background: #fff;">
               <div class="containTitle2"><a style="float: right;height: 30px;width: 20px;margin-right: 10px;" onclick="Lock_CheckForm();">X</a></div>
               <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                   <tr>
                <th>ID</th>
                <th>优惠券类型</th>
                <th>优惠券面额（元/%）</th>
                <th>有效期</th>
                <th>使用期限</th>
                <th>出借金额限制（元）</th>
                <th>出借期限限制</th>
                <th>出借端口限制</th>
                <th>使用时间</th>
                <!-- <th>标的名称</th> -->
                <%-- <shiro:hasPermission name="compaccount:coupon:edit"><th>操作</th></shiro:hasPermission> --%>
            </tr>
        </thead>
                <tbody id="couponUseDet">
                
               </tbody> 
               </table>
     </div><!--  弹窗  -->
     <div class="refreshBg" style="display:none; position: fixed; top: 0; left: 0; opacity: 0.3; background: black; width: 100%; height: 100%;"></div>
</body>
</html>