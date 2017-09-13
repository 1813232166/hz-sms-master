<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctxStatic}/borrow/standardpowder.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			 $(".containbox3,.refreshBg").hide();
			 $(".NewListConfig4ButtonClose").click(function(){
                 $(".containbox3,.refreshBg").hide();
             });
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
		});
		
		function tborrowSh(borrowId,borrowName){
			var b;
			var borrowstatus = $("input[name='shenhe']:checked").val();
			if(borrowstatus==3){
				b = confirm(borrowName+"确定未通过审核?");				
			}else{
				b = confirm(borrowName+"确定通过审核?");				
			}
			if(!b){
				return false;
			}

			var openBorrowType = $("input[name='openBorrowType']:checked").val();
			var openBorrowDate = $("#openBorrowDate").val();
			var yuanyin = $(".yy").val();
			$.ajax({
				type:"post",
				url:ctx+"/borrow/tBorrow/changeBorrowstatus",
				data:{borrowstatus:borrowstatus,borrowId:borrowId,yuanyin:yuanyin,openBorrowType:openBorrowType,openBorrowDate:openBorrowDate},
				dataType:"text",
				success:function(data){
					if(data=="yes"){
						location.href = ctx+"/borrow/tBorrow/list";
					}else{
						alert("审核失败！请稍后再试！")
					}
				}
			});
		}
		
		function shenhe111(){
			var borrowstatus = $("input[name='shenhe']:checked").val();
			if(borrowstatus == 4){
				$(".containbox3").hide();
				var borrowId = $("#borrowId").val();
				tborrowSh(borrowId)
			}else{
				$(".containbox3,.refreshBg").show();
			}
		}
		function openBorrowTypeCh(){
			var openBorrowType = $("input[name='openBorrowType']:checked").val();
			if(openBorrowType == 0){
				$("#openBorrowDate").val("");
				$("#preparationDateDiv").hide();
			}else{
				$("#preparationDateDiv").show();
			}
			
		}
		
		function openshenheCh(){
			var shenhe = $("input[name='shenhe']:checked").val();
			if(shenhe == 4){
				$("#weitongguo").hide();
			}else{
				$("#weitongguo").show();
			}
			
		}

		
		
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/borrow/tBorrow/">标的列表</a></li>
		<li class="active"><a href="${ctx}/borrow/tBorrow/shenheform?borrowId=${tBorrow.borrowId}">审核</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tBorrow" action="${ctx}/borrow/tBorrow/save" method="post" class="form-horizontal">
		<form:hidden path="borrowId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标的编号：</label>
			<div class="controls">
			${tBorrow.borrowcode}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款编号：</label>
			<div class="controls">
			${tBorrow.loannumber}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保障方式：</label>
			<div class="controls">
			${fns:getDictLabel(tBorrow.bztype, 'bz_type', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款形式：</label>
			<div class="controls">
			${fns:getDictLabel(tBorrow.borrowtype, 'borrowType', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">债权来源：</label>
			<div class="controls">
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款期限：</label>
			<div class="controls">
			${tBorrow.deadline}<c:if test="${tBorrow.deadline!=''}">个月</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款日：</label>
			<div class="controls">
			${tBorrow.repaymentdate}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发布时间：</label>
			<div class="controls">
			<%-- ${tBorrow.} --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最低出借金额：</label>
			<div class="controls">
			${tBorrow.mintendersum}<c:if test="${tBorrow.mintendersum!=''}">元</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标的名称：</label>
			<div class="controls">
			${tBorrow.borrowalias}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批贷时间：</label>
			<div class="controls">
			<fmt:formatDate value="${tBorrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss"/>			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">还款方式：</label>
			<div class="controls">
			${fns:getDictLabel(tBorrow.payMethod, 'pay_method', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">紧急状态：</label>
			<div class="controls">
			 <c:choose>
			 <c:when test="${'2' eq tBorrow.criticalid}">不紧急</c:when>
			 <c:when test="${'1' eq tBorrow.criticalid}">紧急</c:when>
			 <c:otherwise>--</c:otherwise>
			 </c:choose>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">借款金额：</label>
			<div class="controls">
			${tBorrow.borrowamount}<c:if test="${tBorrow.borrowamount!=''}">元</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出借年化利率：</label>
			<div class="controls">
			${tBorrow.anualrate}<c:if test="${tBorrow.anualrate!=''}">%</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
			${fns:getDictLabel(tBorrow.borrowstatus, 'BORROWSTATUS', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">募集期：</label>
			<div class="controls">
			${tBorrow.raiseterm}<c:if test="${tBorrow.raisetermunit==0}">小时</c:if><c:if test="${tBorrow.raisetermunit==1}">天</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">到期补标：</label>
			<div class="controls">
			${fns:getDictLabel(tBorrow.fillMark, 'FILL_MARK', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><b>*</b>发布类型:</label>
			<div class="controls">
			<input type="radio" id="immediate" name="openBorrowType" value="0" checked onclick="openBorrowTypeCh()" />
			<label for="immediate">立即发布</label>
			<input type="radio" id="preparation" name="openBorrowType" value="1" onclick="openBorrowTypeCh()"/>
			<label for="preparation">预发布</label>
			</div>
		</div>
		<div class="control-group" id="preparationDateDiv" style="display: none">
		    <label class="control-label"><b>*</b>发布时间:</label>
			<div class="controls">
			 <input class="NewListConfig1Text data" id="openBorrowDate" name="openborrowdate" value="${openBorrowDate}" type="text" 
			 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',readOnly:true,minDate:CurentTime()})" style="width:155px" />			
		    </div>
		</div>
		
		<div class="control-group">
			<label class="control-label">理财产品发布处理意见</label><br>
			<label class="control-label">审核结果：</label>
			<div class="controls">
				<input type="radio" name="shenhe" value="4" onclick="openshenheCh()"/>审核通过&nbsp;&nbsp;&nbsp;<input type="radio" name="shenhe" value="3" checked="checked" onclick="openshenheCh()"/>审核不通过
			</div>
		</div>
		<div class="control-group" id="weitongguo">
			<label class="control-label">未通过原因:</label>
			<div class="controls">
               <input type="text" class="yy" style="margin-bottom: 15px;"/>
			</div>
		</div>
		
		
		<div class="form-actions">
			<input class="btn btn-primary" type="button" onclick="tborrowSh('${tBorrow.borrowId}','${tBorrow.borrowalias}')" value="确认审核"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<!--弹窗-->
	<div class="refreshBg" style="position: fixed;top:0;left:0;opacity:0.3; background: black;width: 100%;height: 100%;display: block;"></div>
	
	<input id="borrowId" type="hidden" value="${tBorrow.borrowId}">
</body>
</html>