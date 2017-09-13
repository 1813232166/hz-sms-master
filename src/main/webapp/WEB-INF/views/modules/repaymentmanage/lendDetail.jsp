<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出借计划详情列表</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function showDetail(){
			location.href=ctx+"/repayment/repaymentManage/biaoDetail?rbillNum="+period+"&borrowId="+apply_id;
			//$('#myModal').modal('show');
		}
	</script>
</head>
<body>
      <ul class="nav nav-tabs">
		<li class="active"><a href="#">出借信息</a></li>
	 </ul>
	<div style="margin-left: 50px;font-size: 20px;"><span>${fin.name}</span></div>
	<table style="width:50%;height:400px;margin-left: 100px;">
			<tr>
				<td>理财产品：</td>
				<td>${fin.name}</td>
				<td>还款方式：</td>
				<td>
				   ${fin.repayStyle}
				</td>
			</tr>
			<tr>
				<td>借款金额：</td>
				<td>${fin.amount}</td>
				<td>出借年利化率：</td>
				<td>
				     ${fin.rate}
				</td>
			</tr>
			<tr>
				<td>借款期限：</td>
				<td>${fin.deadline}</td>
				<td>最低出借金额：</td>
				<td>
				    ${fin.minInvestAmount}
				</td>
			</tr>
			<tr>
				<td>状态：</td>
				<td>
					${fin.status}				   
				</td>
				<td>开始招募时间：</td>
				<td>
				    <fmt:formatDate value="${fin.startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
				</td>
			</tr>
			<tr>
				<td>结束时间：</td>
				<td><fmt:formatDate value="${fin.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>满标时间：</td>
				<td>
				     2016-10-12
				</td>
			</tr>
			<tr>
				<td>放款时间：</td>
				<td>2012-02-03</td>
				
			</tr>
	</table>
	<div style="margin-left: 50px;font-size: 20px;"><span>匹配债权</span></div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width:80%;margin-left: 100px;">
		<thead>
			<tr>
				<th>序号</th>
				<th>借款编号</th>
				<th>借款人</th>
				<th>借款人手机号</th>
				<th>借款期限</th>
				<th>借款利率</th>
				<th>借款金额</th>
				<th>还款日</th>
				<th>还款方式</th>
				<th>批货时间</th>
				<th>还款计划</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					1222
				</td>
				<td>
					<a href="#">1234562</a>
				</td>
				<td>
					张三
				</td>
				<td>
					150***2585
				</td>
				<td>
					13个月
				</td>
				<td>
					12.32%
				</td>
				<td>
					13320.00
				</td>
				<td>
					30
				</td>
				<td>
					等额本息
				</td>
				<td>
					2012-10-12 
				</td>
				<td>
					<a href="javascript:void(0)" onclick="showDetail('${fin.period}','${fin.repayStyle}')" >明细</a>
				</td>
				
			</tr>
		 
		</tbody>
	</table>
	
	<!-- 模态框声明 -->
	<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" style="width: 650px;">
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" data-dismiss="modal"><span>&times;</span></button>
					<h4 class="modal-title">匹配债券明细</h4>
				</div>
				<div class="modal-body"  style="height:280px;">
					<div  style="line-height: 280px;text-align: center;font-size: 18px;">
							<table id="contentTable" class="table table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>出借人</th>
											<th>本金(元)</th>
											<th>利息(元)</th>
											<th>总额(元)</th>
											<th>时间</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>张三</td>
											<td>65635.89</td>
											<td>635.89</td>
											<td>66635.89</td>
											<td>2013-11-11</td>
											
										</tr>	
										<tr>
											<td>李四</td>
											<td>65635.89</td>
											<td>635.89</td>
											<td>66635.89</td>
											<td>2013-11-11</td>
										</tr>	
									</tbody>
							</table>
					</div>
				</div>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="pagination">${page}</div>
</body>
</html>