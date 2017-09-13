<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>优惠券发放管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic}/salesupport/couponsend.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
          //提交审核
            $("#btnSumAudit").click(function(){
                $("#dialog1").text("您确定要提交审核此条记录");
                $("#publishSuccess").show();
                $("#myModal").modal('show');
                
            });
            //确定提交审核
            $("#publishSuccess").click(function(){
                 $.ajax({
                     type : "POST",
                     data:$('#inputForm').serialize(),
                     url : "${ctx}/coupon/couponSend/judgeCouponStatus",
                     dataType: "JSON",
                     success:function(dates){
                    	 //console.log(dates);
                         if ("1"==dates) {
                        	 $.jBox.tip("含有失效优惠券，请重新编辑");
						 }
						if ("0"==dates) {
							 $('#inputForm').submit();
						 }
                     },
                     error: function() {
                         alert("失败，请稍后再试！");
                     }
                 });
            });
            
            //取消按钮
            $("#fanhui1").click(function(){
            	var couponGroupId=$("#couponGroupId").val();
                $("#myModal").hide();
                window.location.href="${ctx}/coupon/couponSend/sendDetail?couponGroupId=${couponGroup.couponGroupId}";
            });
            $("#close1").click(function(){
            	var couponGroupId=$("#couponGroupId").val();
                $("#myModal").hide();
                window.location.href="${ctx}/coupon/couponSend/sendDetail?couponGroupId=${couponGroup.couponGroupId}";
            });
            //导出统计数据
            $("#exportCoupon").click(function () {
            	var couponGroupId=$("#couponGroupId").val();
            	window.location.href="${ctx}/coupon/couponSend/exportCoupon?couponGroupId="+couponGroupId+"&&endLimit="+20000;
			})
        });
        function reback(){
            window.location.href="${ctx}/coupon/couponSend/couponSendList";
        }
        function Lock_CheckForm(){
            $(".refreshBg").hide();
            $("#containbox2Id").hide();
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
        <li class="active"><a href="${ctx}/coupon/couponSend/couponSendList">优惠券发放列表</a></li>
        <li class="active"><a href="javascript:;">优惠券发放详情</a></li>
    </ul><br/>
    <form:form id="inputForm" modelAttribute="couponGroup" action="${ctx}/coupon/couponSend/submitAudit" method="post" class="form-horizontal">
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
        <input type="hidden" name="couponGroupId" value="${couponGroup.couponGroupId}" id="couponGroupId"/>
        <input type="hidden" name="couponIds" value="${couponGroup.couponIds}"/>
        <input type="hidden" name="couponGroupName" value="${couponGroup.couponGroupName}"/>
        <input type="hidden" name="isSendMessage"  value="${couponGroup.isSendMessage}"/>
        <input type="hidden" name="messageContent" value="${couponGroup.messageContent}"/>
        <input type="hidden" name="userCount" value="${couponGroup.userCount}"/>
        <input type="hidden" name="createdate" value="${couponGroup.createdate}"/>
        <input type="hidden" name="auditTime" value="${couponGroup.auditTime}"/>
        <input type="hidden" name="sendTime" value="${couponGroup.sendTime}"/>
        <input type="hidden" name="couponGroupStatus" value="${couponGroup.couponGroupStatus}"/>
        <input type="hidden" name="remarks" value="${couponGroup.remarks}"/>
        <div class="form-actions">
            <c:if test="${'0'==couponGroup.couponGroupStatus or '2'==couponGroup.couponGroupStatus}">
                <input id="btnSumAudit" class="btn btn-primary" type="button" value="提交审核"/>
            </c:if>
            <input id="btnCancel" class="btn" type="button" value="取 消" onclick="reback()"/>
        </div>
    </form:form>
    <!--发送用户数：xA券使用数量：xB券使用数量：xC券使用数量：xD券使用数量：x-->
     <div class="Mtable2">
        <form id="staticsForm" action="${ctx}/coupon/couponSend/sendDetail" method="post" >
            <input id="pageNo" name="pageNo" type="hidden" value="${couponpage.pageNo}"/>
	        <input id="pageSize" name="pageSize" type="hidden" value="${couponpage.pageSize}"/>
	        <input type="hidden" name="couponGroupId" value="${couponGroup.couponGroupId}"/>
	       <!--  <input id="endLimit" name="endLimit" type="hidden" value="20000"/> -->
        </form>
        <ul class="ul-form">
            <li style="float: left;">发送用户数：${couponpage.count}</li>
            <li style="float: right;margin-right: 50px;"><input  style="width: 100px;" class="btn btn-primary" type="button" value="导 出" id="exportCoupon"/></li>
            <!-- 
            <li style="float: left;">A券使用数量：</li>
            <li style="float: left;">B券使用数量：</li>
            <li style="float: left;">C券使用数量：</li>
            <li style="float: left;">D券使用数量：</li>
            -->        
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
