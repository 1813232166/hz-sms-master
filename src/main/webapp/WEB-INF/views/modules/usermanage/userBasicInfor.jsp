<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
 <head>
    <title>用户基本信息</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
    $(document).ready(function() {
		 $('.tabsList ul li').click(function(){
            $(this).addClass('active').siblings().removeClass('active');
            $('.tabscontbox>div:eq('+$(this).index()+')').show().siblings().hide();
        })
	});
    </script>
 </head>
  <body>
       <div class="tabBOX">
       			 <div class="tabsList">
       			 		<ul>
       			 				<li class="active" >基本信息</li>
       			 				<li class="active" >用户资金</li>
       			 		</ul>
       			 </div>
       			 <div class="tabscontbox">
       			  		
       			 		<div id="p1" style="display: block;">
       			 				<p>
       			 					<span>当前位置：用户管理>用户信息->基本信息</span>
       			 					<button style="float: right;" onclick="history.go(-1)" class="btn btn-primary">返回</button>
       			 				</p>
       			 			    <h4>注册信息</h4>
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
									                 <tr>
									                      <%-- <td><strong>用户ID：</strong>${user.id}</td> --%>
									                      <td><strong>手机号：</strong>${fn:substring(user.mobile,0,3)}****${fn:substring(user.mobile,7,11)}</td>
									                      <td><strong>注册时间：</strong><fmt:formatDate value="${user.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									                      <td><strong>类型：</strong>
									                      	<c:if test="${user.userType == 1}">出借人</c:if>
									                      	<c:if test="${user.userType == 2}">借款人</c:if>
									                      </td>
									                      <td><strong>状态：</strong>
									                         <c:if test="${user.lockStatus == 1}">锁定</c:if>
									                         <c:if test="${user.lockStatus == 0}">正常</c:if>
									                      </td>
									                 </tr>
            									</table>
       			 						 </div>
       			 				</div>
       			 				
       			 				<h4>认证信息</h4>
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;"><strong>认证项目</strong></td>
										                  <td style="border: 1px solid black;width: 300px;"><strong>是否通过</strong></td>
										                  <td style="border: 1px solid black;"><strong>详情</strong></td>
										               </tr>
										               <tr>
										                  <td style="border: 1px solid black;">实名认证：</td>
										                  <td style="border: 1px solid black;"><c:if test="${user.idcardStatus ==0}">否</c:if>
										                      <c:if test="${user.idcardStatus ==1}">是</c:if>
										                  </td>
										                  <td style="border: 1px solid black;">
										                     <c:if test="${user.idcardStatus ==1}">
													                         姓名：<c:out value="${user.realName}"></c:out>
													                         ，身份证号：<c:out value="${user.idcard}"></c:out>
													         </c:if>
										                  </td>
										               </tr>
										               <tr>
										                   <td style="border: 1px solid black;">风险评测：</td>
										                   <td style="border: 1px solid black;">
										                   		<!-- 此处判断若user.risk=1,2,3,4,5中的一个，则通过，否则不通过 -->
											                     <c:choose>
											                     	<c:when test="${user.risk=='1'}">是</c:when>
											                     	<c:when test="${user.risk=='2'}">是</c:when>
											                     	<c:when test="${user.risk=='3'}">是</c:when>
											                     	<c:when test="${user.risk=='4'}">是</c:when>
											                     	<c:when test="${user.risk=='5'}">是</c:when>
											                     	<c:otherwise>否</c:otherwise>
											                     </c:choose>
										                   </td>
										                   <td style="border: 1px solid black;">风格：
										                     <c:if test="${user.risk=='1'}">保守型</c:if>
										                     <c:if test="${user.risk=='2'}">谨慎型</c:if>
										                     <c:if test="${user.risk=='3'}">稳健型</c:if>
										                     <c:if test="${user.risk=='4'}">积极型</c:if>
										                     <c:if test="${user.risk=='5'}">激进型</c:if>
										                   </td>
										               </tr>
										               <tr>
										                  <td style="border: 1px solid black;">邮箱认证：</td>
										                  <td style="border: 1px solid black;">
										                  	  <c:if test="${user.emailStatus ==0}">否</c:if>
										                      <c:if test="${user.emailStatus ==1}">是</c:if>
										                  </td>
										                  <td style="border: 1px solid black;">
										                     <c:if test="${user.emailStatus ==1}">
										                                                              邮箱：<c:out value="${user.email}"></c:out>
										                     </c:if>
										                  </td>
										               </tr>
										          </table>
       			 						 </div>
       			 				</div>
       			 				<h4>基本信息</h4>
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
										                     <tr>
											                     <td style="width:150px;"><strong>qq:</strong></td>
											                     <td style="width:150px;">${user.qq}</td>
											                     <td style="width:150px;"><strong>紧急联系人:</strong></td>
											                     <td style="width:150px;">${user.contractUser}</td>
											                 </tr>
											                 <tr>
											                     <td style="width:150px;"><strong>紧急联系电话:</strong></td>
											                     <td style="width:150px;">${user.contractPhone}</td>
											                     <td style="width:150px;"><strong>详细地址:</strong></td>
											                     <td style="width:150px;">${user.addressDetail}</td>
											                 </tr>
											                 <tr>
											                    <td><strong>现居住地:</strong></td>
											                    <td>${user.address}</td>
											                 </tr>
											        </table>
       			 						 </div>
       			 				</div>
       			 				 <h4>工作信息</h4>
       			 				 <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" style="min-width: 500px" >
       			 								       <tr>
										                    <td style="width:150px;"><strong>单位名称:</strong></td>
										                    <td style="width:150px;">${user.company}</td>
										                     <td style="width:150px;"><strong>单位性质:</strong></td>
										                    <td style="width:150px;">${user.companyNature}</td>
										                 </tr>
										                 <tr>
										                    <td style="width:150px;"><strong>职工级别:</strong></td>
										                    <td style="width:150px;">${user.workLevel}</td>
										                    <td style="width:150px;"><strong>工作年限:</strong></td>
										                    <td style="width:150px;">${user.workYear}</td>
										                 </tr>
										                 <tr>
										                    <td style="width:150px;"><strong>月收入:</strong></td>
										                    <td style="width:150px;">${user.monthIncome}</td>
										                 </tr>
       			 								 </table>
       			 						</div>
       			 				</div>
       			 				<h4>婚姻状况</h4>
       			 				 <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" style="min-width: 500px" >
       			 								       <tr>
										                    <td style="width:150px;"><strong>婚姻状况:</strong></td>
										                    <td style="width:150px;">${user.marriageState }</td>
										                    <td style="width:150px;"><strong>配偶姓名:</strong></td>
										                    <td style="width:150px;">${user.marryName}</td>
										                 </tr>
										                 <tr>
										                    <td style="width:150px;"><strong>配偶月收入:</strong></td>
										                    <td style="width:150px;">${user.marryMothIncome}</td>
										                 </tr>
       			 								 </table>
       			 						</div>
       			 				</div>
       			 				<h4>学历信息</h4>
       			 				 <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" style="min-width: 500px" >
       			 								       <tr>
										                    <td style="width:150px;"><strong>最高学历:</strong></td>
										                    <td style="width:150px;">${user.educationLevel}</td>
										                    <td style="width:150px;"><strong>最高学历学校:</strong></td>
										                    <td style="width:150px;">${user.school}</td>
										                 </tr>
										                 <tr>
										                    <td style="width:150px;"><strong>专业:</strong></td>
										                    <td style="width:150px;">${user.master}</td>
										                    <td style="width:150px;"><strong>时间:</strong></td>
										                    <td style="width:150px;">${user.time}</td>
										                 </tr>
										       	 </table>
       			 						</div>
       			 				</div>
       			 				<h4>其他信息</h4>
       			 				 <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" style="min-width: 500px" >
       			 								        <tr>
										                  <td style="width:150px;"><strong>是否购房：</strong></td>
										                  <td style="width:150px;">
										                     <c:if test="${user.isPurchase eq '0'}">否</c:if>
										                     <c:if test="${user.isPurchase eq '1'}">是</c:if>
										                  </td>
										                  <td style="width:150px;"><strong>是否购车：</strong></td>
										                  <td style="width:150px;">${user.isBuyCar}</td>
										                </tr>
										       	 </table>
       			 						</div>
       			 				</div>
       			 		</div><!-- 这是含有 style="display: block;的div -->
       			 		
       			 		<div id="p2" class="hide" style="display: none;">
                            <div class="Mtable2">
                                <div class="tableBox">
                                    <table cellpadding="0" cellspacing="0" style="min-width: 500px" >
                                         <tr>
                                         		<td>资产统计</td>
                                         </tr>
                                         <c:if test="${user.userType == 1}">
										 <tr>
									                      <td><strong>资产总额：</strong>${map.totalAssets}元</td>
									                      <td><strong>账户余额：</strong>${map.ableBalanceAmt}元</td>
									                      <td><strong>待收本金：</strong>${map.ucapi}元</td>
									                      <td><strong>待收利息：</strong>
									                      	${map.uinte}
									                      </td>
									                   
									      </tr>
										  <tr>
                                         		<td><strong>冻结金额：</strong>${map.frozenAmt}元</td>
                                         </tr>
                                         </c:if>
                                         <c:if test="${user.userType == 2}">
                                         <tr>
                                         		<td><strong>借款总额：</strong>${map.NumSum!=null?map.NumSum:0.00}元</td>
                                         		<td><strong>待还款金额：</strong>${map.repaymentNum!=null?map.repaymentNum:0.00}元</td>
                                         		<td><strong>已还款金额：</strong>${map.repaymentNumSum!=null?map.repaymentNumSum:0.00}元</td>
                                         </tr></c:if>
                                    </table>
                                    <!--统计-->
                                    <div class="foot foot_margin">
                                        <p></p>
                                        <style type="text/css">
                                            .foot .p2{
                                                padding-right: 61px;
                                            }
                                        </style>
                                        
                                    </div>
                                    <div class="pagination">${invests}</div>
                                </div>
                            </div><!--Mtable2-->
                        </div>
       			 		
       			 </div>
       
       </div>
  </body>
</html>
