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
		
		function auditBorrowList(borrowListId,borrowName){
			var b;
			var borrowstatus = $("input[name='shenhe']:checked").val();
			if(borrowstatus==4){
				b = confirm(borrowName+"确定通过审核?");
			}else{
				b = confirm(borrowName+"确定未通过审核?");
			}
			if(!b){
				return false;
			}

			var openBorrowType = $("input[name='openBorrowType']:checked").val();
			var openBorrowDate = $("#openBorrowDate").val();
			var yuanyin = $(".yy").val();
			$.ajax({
				type:"post",
				url:ctx+"/borrow/tBorrow/changeBorrowListStatus",
				data:{borrowstatus:borrowstatus,borrowListId:borrowListId,yuanyin:yuanyin,openBorrowType:openBorrowType,openBorrowDate:openBorrowDate},
				dataType:"text",
				success:function(data){
					if(data=="yes"){
						location.href = ctx+"/borrowlist/auditBorrowList/";
					}else{
						alert("审核失败！请稍后再试！")
					}
				}
			});
		}
		
		function auditBorrow(borrowId,borrowName){
			var b = confirm(borrowName+"确定通过审核?");
			if(!b){
				return false;
			}
			$.ajax({
				type:"post",
				url:ctx+"/borrow/tBorrow/changeBorrowStatusAgain",
				data:{borrowId:borrowId},
				dataType:"text",
				success:function(data){
					if(data=="yes"){
						window.location.reload();
					}else{
						alert("审核失败！请稍后再试！")
					}
				}
			});
		}
		
		function deleteBorrow(borrowId,borrowName){
			var b = confirm(borrowName+"确定删除?");
			if(!b){
				return false;
			}
			$.ajax({
				type:"post",
				url:ctx+"/borrowlist/auditBorrowList/deleteBorrow",
				data:{borrowId:borrowId},
				dataType:"text",
				success:function(data){
					if(data=="yes"){
						window.location.reload();
					}else{
						alert("删除失败！请稍后再试！")
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
			if(shenhe == 2){
				$("#weitongguo").show();
			}else{
				$("#weitongguo").hide();
			}
			
		}

		
		
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/borrowlist/auditBorrowList/">普享标集合待审核</a></li>
		<li class="active"><a href="${ctx}/borrowlist/auditBorrowList/auditform?id=${auditDetailmap.borrowListId}">审核</a></li>
	</ul>
	<br/>
	<h3>${auditDetailmap.borrowListTitle}</h3>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tr>
			<td>
				<label class="control-label">标的编号：</label>
			</td>
			<td>
				<div class="controls">
				${auditDetailmap.borrowListCode}
				</div>
			</td>
			<td>
				<label class="control-label">标的名称：</label>
			</td>
			<td>
				<div class="controls">
				${auditDetailmap.borrowListTitle}
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label">借款期限：</label>
			</td>
			<td>
				<div class="controls">
				${auditDetailmap.deadLine}
				</div>
			</td>
			<td>
				<label class="control-label">还款方式：</label>
			</td>
			<td>
				<div class="controls">
				<c:if test="${auditDetailmap.payMethod=='debx'}">等额本息</c:if>
				<c:if test="${auditDetailmap.payMethod=='xxhb'}">先息后本</c:if>
				<c:if test="${auditDetailmap.payMethod=='ychbx'}">一次性还本付息</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label">借款金额：</label>
			</td>
			<td>
				<div class="controls">
				${auditDetailmap.borrowAmount}
				</div>
			</td>
			<td>
				<label class="control-label">出借年化率：</label>
			</td>
			<td>
				<div class="controls">
				${auditDetailmap.anualRate}
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label">发布时间：</label>
			</td>
			<td>
				<div class="controls">
				<fmt:formatDate value="${auditDetailmap.OpenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</td>
			<td>
				<label class="control-label">最低出借金额：</label>
			</td>
			<td>
				<div class="controls">
				${auditDetailmap.minTenderSum}
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label">状态：</label>
			</td>
			<td colspan="3">
				<div class="controls">
				<c:if test="${auditDetailmap.borrowListStatus eq 1}">待审核</c:if>
				<c:if test="${auditDetailmap.borrowListStatus eq 2}">审核未通过</c:if>
				<c:if test="${auditDetailmap.borrowListStatus eq 3}">审核中</c:if>
				<c:if test="${auditDetailmap.borrowListStatus eq 4}">审核通过</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label">风险提示：</label>
			</td>
			<td colspan="3">
				<div class="controls">
				${auditDetailmap.riskWarning}
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<label class="control-label">项目介绍：</label>
			</td>
			<td colspan="3">
				<div class="controls">
				${auditDetailmap.projectBrief}
				</div>
			</td>
		</tr>
		</table>
		
		</br>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>借款编号</th>
				<th>散标编号</th>
				<th>借款期限</th>
				<th>借款利率</th>
				<th>借款金额</th>
				<th>借款人</th>				
				<th>还款方式</th>
				<th>紧急状态</th>
				<th>批贷时间</th>
				<c:if test="${auditDetailmap.borrowListStatus eq 3}">
				<th>审核状态</th>
				<th>审核操作</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${subBorrowList}" var="b">
			<tr>
				<td>
					<fmt:parseNumber integerOnly="true" value="${b.n}" />
				</td>
				<td>
					${b.loanNumber}
				</td>
				<td>
					${b.borrowAliasNo}
				</td>
				<td>
					${b.deadLine}
				</td>
				<td>
					${b.anualRate}
				</td>
				<td>
					${b.borrowAmount}
				</td>
				<td>
					${b.realName}
				</td>
				<td>
					<c:if test="${b.payMethod=='debx'}">等额本息</c:if>
					<c:if test="${b.payMethod=='xxhb'}">先息后本</c:if>
					<c:if test="${b.payMethod=='ychbx'}">一次性还本付息</c:if>
				</td>
				<td>
					<c:if test="${b.criticalId eq 0}">不紧急</c:if>
					<c:if test="${b.criticalId eq 1}">紧急</c:if>
				</td>
				<td>
					<fmt:formatDate value="${b.loanTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<c:if test="${auditDetailmap.borrowListStatus eq 3}">
				<td>
					<c:if test="${b.borrowStatus eq 9}">审核成功</c:if>
					<c:if test="${b.borrowStatus eq 8}">审核失败</c:if>
				</td>
				<td>
					<c:if test="${b.borrowStatus eq 8}">
					<a href="javascript:void(0);" onclick="deleteBorrow('${b.borrowId}','${b.borrowAliasNo}')">删除</a>
    				<a href="javascript:void(0);" onclick="auditBorrow('${b.borrowId}','${b.borrowAliasNo}')">审核</a>
    				</c:if>
    			</td>
    			</c:if>
			</tr>
		</c:forEach>
		</tbody>
		</table>
		
		<div class="control-group">
			<label class="control-label">理财产品发布处理意见</label><br>
			<label class="control-label">审核结果：</label>
			<div class="controls">
				<c:if test="${auditDetailmap.borrowListStatus ne 3}">
				<input type="radio" name="shenhe" value="4" onclick="openshenheCh()"/>审核通过&nbsp;&nbsp;&nbsp;
				<input type="radio" name="shenhe" value="2" onclick="openshenheCh()" checked="checked" />审核不通过
				</c:if>
				<c:if test="${auditDetailmap.borrowListStatus eq 3}">
				<input type="radio" name="shenhe" value="4" onclick="openshenheCh()" checked="checked"/>审核通过
				<c:if test="${subBorrowList== null || fn:length(subBorrowList) == 0}">
				<input type="radio" name="shenhe" value="2" onclick="openshenheCh()" checked="checked" />审核不通过
				</c:if>
				</c:if>
			</div>
		</div>
		<c:if test="${auditDetailmap.borrowListStatus ne 3}">
			<div class="control-group" id="weitongguo">
				<label class="control-label">未通过原因:</label>
				<div class="controls">
               		<input type="text" class="yy" style="margin-bottom: 15px;width:400px;height:40px"/>
				</div>
			</div>
		</c:if>
		<c:if test="${auditDetailmap.borrowListStatus eq 3}">
			<c:if test="${subBorrowList== null || fn:length(subBorrowList) == 0}">
			<div class="control-group" id="weitongguo">
				<label class="control-label">未通过原因:</label>
				<div class="controls">
               		<input type="text" class="yy" style="margin-bottom: 15px;width:400px;height:40px"/>
				</div>
			</div>
			</c:if>
		</c:if>
		<div class="control-group">
			<label class="control-label"><!-- <b>*</b> -->发布类型:</label>
			<div class="controls">
			<input type="radio" id="immediate" name="openBorrowType" value="0" checked onclick="openBorrowTypeCh()" />
			<label for="immediate">立即发布&nbsp;&nbsp;</label>
			<input type="radio" id="preparation" name="openBorrowType" value="1" onclick="openBorrowTypeCh()"/>
			<label for="preparation">预发布</label>
			</div>
		</div>
		<div class="control-group" id="preparationDateDiv" style="display: none">
		    <label class="control-label"><!-- <b>*</b> -->发布时间:</label>
			<div class="controls">
			 <input class="NewListConfig1Text data" id="openBorrowDate" name="openborrowdate" value="${openBorrowDate}" type="text" 
			 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',readOnly:true,minDate:CurentTime()})" style="width:155px" />			
		    </div>
		</div>
		
		<div class="form-actions">
			<input class="btn btn-primary" type="button" onclick="auditBorrowList('${auditDetailmap.borrowListId}','${auditDetailmap.borrowListTitle}')" value="确认审核"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	<!--弹窗-->
	<div class="refreshBg" style="position: fixed;top:0;left:0;opacity:0.3; background: black;width: 100%;height: 100%;display: block;"></div>
	
</body>
</html>