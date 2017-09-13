<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划审核详情</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		tr{
			height: 35px;
			line-height: 35px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#bespeakModal").hide();
			if('${bespeakDetail.status}'==2){//只有待审核可见
				$("#auditBtn").show();
			}else{
				$("#auditBtn").hide();
			}
			
			//确定
			$("#confirmreturn").click(function(){
				
				var auditRadio=$('input:radio:checked').val();
				var tFinanceBespeakId='${bespeakDetail.id}';
				var auditArea=$("#auditArea").val();
				$.post(ctx+'/finance/financeBespeak/bespeakAudit',{tFinanceBespeakId:tFinanceBespeakId,auditRadio:auditRadio,auditArea:auditArea},function(f){
					if(f){
						$("#confirmAddWeight").hide();
						$("#dialog2").hide();
						$(".modal-footer").hide();
						alert("审核成功");
						location.reload();
					}else{
						$("#confirmAddWeight").hide();
						$("#dialog2").hide();
						$(".modal-footer").hide();
						alert.text("审核失败");
					}
				},"json")
			});
			//取消
			$("#cancel").click(function(){
				$("#bespeakModal").modal('hide');
			});
			$("#close1").click(function(){
				$("#bespeakModal").modal('hide');
			});
			
		});
	 ////审核
	 function auditFun(){
		 $("#bespeakModal").modal('show');
	 }
	</script>
</head>
<body>
	<div class="Mbox">
	<div><p>当前位置：出借管理 > 产品审核管理>预约审核> 预约审核详情</p></div>
	<h3 style="margin: 20px 0px 10px 50px;font-size: 18px;"></h3>
	<table style="margin-left: 80px;width: 850px;font-size: 14px;">
		<tr>
			<td style="width: 150px; "align="right">计划名称:</td>
			<td style="width: 200px;">${bespeakDetail.name}</td>
			<td style="width: 150px;padding-left: 50px;" align="right">创建时间:</td>
			<td><fmt:formatDate value="${bespeakDetail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td  align="right">每日最高预约金额(元):</td><td>${bespeakDetail.collectAmount}元</td>
			<td style="width: 150px;padding-left: 50px;" align="right">*审核时间:</td>
			<td>
			<fmt:formatDate value="${bespeakDetail.auditCreatTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="1">*出借期限:</td>
			<td>${bespeakDetail.nper}个月</td>
		</tr>
		<tr>
			<td align="right">*出借方式:</td>
			<td >
			${fns:getDictLabel(bespeakDetail.lendingMethod, 'LENDING_METHOD', '')}
			</td>
			<td style="width: 350px;padding-left: 50px;" align="right">*提前赎回手续费(%):</td>
			<td style="width: 350px;">
			期限未过半${bespeakDetail.poundageMore}%,期限过半${bespeakDetail.poundage}%			
			</td>
		</tr>
		<tr>
			<td align="right">*年均出借回报率约:</td>
			<td>${bespeakDetail.rate}%</td>
			<td style="width: 150px;padding-left: 50px;" align="right">*退出方式:</td>
			<td>
			${fns:getDictLabel(bespeakDetail.exitMode, 'EXIT_MODE', '')}
			</td>
		</tr>
		<tr>
			<td align="right">*起息日:</td>
			<td>${fns:getDictLabel(bespeakDetail.interestDateType, 'INTEREST_DATE_TYPE', '')}</td>
			<td style="width: 150px;padding-left: 50px;" align="right">*保障方式:</td>
			<td>
			${fns:getDictLabel(bespeakDetail.guaranteeMode, 'GUARANTEE_MODE', '')}
			</td>
		</tr>
		<tr>
			<td style="width: 200px;" align="right">*最低预约金额(元):</td>
			<td>${bespeakDetail.minTenderSum}</td>
			<td style="width: 150px;" align="right">*介绍:</td>
			<td>
			${bespeakDetail.detail}
			</td>
		</tr>
		<tr>
			<td style="width: 200px;" align="right">*最高预约金额(元):</td>
			<td>${bespeakDetail.maxTenderSum}</td>
			<td style="width: 250px;" align="right">*备注:</td>
			<td>
			  ${bespeakDetail.cause}
			</td>
		</tr>
		<tr>
			<td align="right">*递增金额(元):</td>
			<td colspan="1">
			${bespeakDetail.incrementalAmount}
			</td>
		</tr>
		<tr>
			<td align="right">*协议:</td>
			<td >
			${bespeakDetail.lendingAgreement}
			</td>
		</tr>
	
	</table>
		<div class="NewListConfig4Button">
			<a href="javascript:void(0);" class="NewListConfig4Button1" id="auditBtn" onclick="auditFun()">审核</a> 
			<a href="javascript:void(0);" class="NewListConfig4Button2" style="background: #ccc" id="updateFirstMsg" onclick="history.go(-1)">返回</a> 
		</div>
	</div>
	<!-- 模态框声明 -->
	<div class="modal fade" id="bespeakModal" tabindex="-1" data-backdrop="static" style="width: 450px;">
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="close1"><span>&times;</span></button>
					<h4 class="modal-title">审核信息</h4>
				</div>
				<div class="modal-body"  style="height:100px;">
					<div  style="text-align: center;font-size: 18px;">
							<div id="dialog2">
							  <input name="auditRadio" id="auditYse" type="radio" value="1"/>审核通过并发布 
							  <input name="auditRadio" id="auditNo"  type="radio" value="2" checked="checked"/>审核不通过
							  <textarea id="auditArea"  maxlength="500" style="width: 292px;  height: 91px;resize: none;"></textarea>
							</div>
							<p id="dialog3"></p>
					</div>
				</div>
				<div class="modal-footer">
					<p class="text-center">
						<button class="btn btn-primary" id="confirmreturn" style="margin-left: 50px;">确认</button>
						<button class="btn btn-primary" id="cancel" style="margin-right: 50px;">取消</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>