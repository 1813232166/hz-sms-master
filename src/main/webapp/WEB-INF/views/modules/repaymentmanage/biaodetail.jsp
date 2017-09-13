<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>普享标还款列表详情</title>
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
			
		});
		function mingxi(period,borrowId) {
			var htmlD="";
			/* $('#myModal').modal('show'); */
			//location.href=ctx+"/repayment/repaymentManage/tolendDetailInfo?rbillNum="+period+"&borrowId="+apply_id;
			//location.href=ctx+"/repayment/repaymentManage/toMingxi";
			var url="${ctx}/borrow/tBorrow/repayplan";
			 $.ajax({
                url: url,
                type: "POST",
                data: {
                    "borrowId": borrowId,
                    "period": period
                },
                dataType: "JSON",
                success: function (result) {
			                     var dateRoot = result.list;
			                     if (dateRoot == null || dateRoot == '' || dateRoot == 'undefined') {
			                     }else{
			                    	 $("#payment").html("");
			                    	 $.each(dateRoot, function (i, a) {
			                    		 htmlD += "<tr><td>" +
	                                         a.realName
	                                         + "</td><td>" +
	                                         a.stcapi.toFixed(2)
	                                         + "</td><td>" +
	                                         a.sinte.toFixed(2)
	                                         + "</td><td>" +
	                                         (a.stcapi + a.sinte).toFixed(2)
	                                         + "</td><td>" +
	                                         a.sDate
	                                         + "</td></tr>"
	                                    });
	                                    $("#payment").append(htmlD);
			                    	 $("#repayment").html(result.html);
			                    	 //console.log(result.refundpage.html);
			                    	 $(".containbox").show();
			                     }
			                 }
			 });
		}
		function Lock_CheckForm() {
			$(".containbox").css('display','none');
			  return false;   
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div class="Mbox">
	<div><p>还款管理>还款列表->详情</p><a href="${ctx }/repayment/repaymentManage/toPuList" style="float: right;padding-right: 50px;font-size: 16px;">返回</a></div>
	<h3 style="margin: 20px 0px 10px 50px;font-size: 18px;">${puBiao.borrowAlias }</h3>
	<table style="margin-left: 80px;width: 850px;font-size: 14px;">
		<tr>
			<td style="width: 150px;">标的编号：</td><td style="width: 200px;">${puBiao.borrowaliasno }</td>
			<td style="width: 150px;padding-left: 50px;">标的名称：</td><td>${puBiao.borrowAlias }</td>
		</tr>
		<tr>
			<td>借款编号：</td><td>${puBiao.loanNumber}</td>
			<td style="width: 150px;padding-left: 50px;">批贷时间：</td><td><fmt:formatDate value="${puBiao.loanTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
	
		<tr>
			<td>借款期限：</td><td>${puBiao.deadline}</td>
			<td style="width: 150px;padding-left: 50px;">还款方式：</td><td>
				${puBiao.payMethod}
			
			</td>
		</tr>
		<tr>
			<td>还款日：</td><td>
			<c:if test="${!empty puBiao.repaymentDate}">每月&nbsp;${puBiao.repaymentDate}&nbsp;日（无此日期为本月最后一日）</c:if></td>
			<td style="width: 150px;padding-left: 50px;">借款金额：</td><td>${puBiao.borrowAmount}元 </td>
		</tr>
		<tr>
			<td>发布时间：</td><td>
				<fmt:formatDate value="${puBiao.openborrowDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td style="width: 150px;padding-left: 50px;">出借年化利率：</td><td>${puBiao.anualrate}</td>
		</tr>
		<tr>
			<td>最低出借金额：</td><td>${puBiao.minTenderSum}元</td>
			<td style="width: 150px;padding-left: 50px;">状态：</td><td>
						${puBiao.borrowStatus}
			</td>
		</tr>
		<tr>
			<td>放款时间：</td><td><fmt:formatDate value="${puBiao.lendingTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td style="width: 150px;padding-left: 50px;">募集期：</td><td>${puBiao.raiseTerm}天</td>
		</tr>
		<tr>
			<td>风险提示：</td><td>${puBiao.riskWarning}</td>
			<td style="width: 150px;padding-left: 50px;">满标时间：</td><td>
				<fmt:formatDate value="${puBiao.fullBorrowDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td>项目介绍：</td><td>${puBiao.usageofloan}</td>
		</tr>
	
	</table>
	<!-- <h2 style="margin: 10px;font-size: 18px;">还款计划</h2> -->
	<ul class="nav nav-tabs">
		<li class="active"><a href="">还款计划</a></li>
	</ul>
	<div class="Mtable2">
         <div class="tableBox">
	<table cellpadding="0" cellspacing="0" style="min-width: 500px" >
										 <tr>
									                      <td><strong>期数</strong></td>
									                      <td><strong>本金（元）</strong></td>
									                      <td><strong>利息（元）</strong></td>
									                      <td><strong>总额（元）</strong></td>
									                      <td><strong>还款时间</strong></td>
									                   	  <td><strong>操作</strong></td>
									      </tr>
									      <c:forEach items="${list}" var="p">
			<tr>
				<td>
					<c:if test="${'1' eq p.is_overdue }">【逾】</c:if>
	                <c:if test="${'6' eq p.repay_status }">【垫】</c:if>
					第${p.period }期
				</td>
				<td>${p.month_capital }</td>
				<td>${p.month_interest }</td>
				<td>${p.month_capital+p.month_interest }</td>
				<td>${p.repay_time }</td>
				<td>
					<a href="javascript:void(0)" onclick="mingxi('${p.period}','${puBiao.borrowId}')">明细</a>
				</td>
				
			</tr>	
		
		  </c:forEach>
                                    </table>
	</div>
	</div>
	<div style="width: 1150px;height:45px;">
	<div style="margin-left:550px;width: 100px;height:35px;margin-top:15px">总共${page.count}条记录</div>
	<div style="margin-left:650px;width: 500px;height:35px;margin-top:-40px" class="pagination">${page}</div>
	</div>
	<!--弹窗-->
                <div class="containbox" style="width: 700px;height:500px; margin-left: -350px; top: -2%; margin-top: 0;display: none;overflow:scroll; border:1px solid;position: fixed;background: #fff;z-index: 2px;">
                    <div class="containTitle2">还款明细<a href="javascript:;" class="canclebtn2" onclick="Lock_CheckForm();"></a><span style="float: right;height: 15px;width: 15px;" onclick="Lock_CheckForm();">X</span></div>
                    <div class="Mtable2">
                        <div class="tableBox">
                            <table cellpadding="0" cellspacing="0" style="min-width: 650px">
                                <thead>
                                	<tr>
											<th>出借人</th>
											<th>本金(元)</th>
											<th>利息(元)</th>
											<th>总额(元)</th>
											<th>时间</th>
									</tr>
                                </thead>
                                <tbody id="payment">
                                
                                </tbody>


                            </table>
                           


                            </div>
							<div class="pagination" id="repayment">${refundpage }</div>
                        </div>
                    </div><!--Mtable2-->
	</div>
	
	<form id="searchForm" action="${ctx}/repayment/repaymentManage/biaoDetail" method="post" >
	<!-- class="breadcrumb form-search" -->
		<input  name="borrowId" type="hidden" value="${borrowId}"/>
		<input  name="loanNumber" type="hidden" value="${loanNumber}"/>
		
		 <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		</form>
	
</body>
</html>