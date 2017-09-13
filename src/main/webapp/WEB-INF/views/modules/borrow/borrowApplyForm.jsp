<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款申请列表管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/borrow/provident.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${ctxStatic}/borrow/provident.js"></script> 
	<c:set var="project" value="${pageContext.request.contextPath}"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".NewListConfig4Button1").click(function(){
                $(".NewListConfig4Button1Popup1,.refreshBg").show();
            })
            $(".NewListConfig4Button2").click(function(){
                $(".NewListConfig4Button1Popup2,.refreshBg").show();
            })
            $(".NewListConfig4Button3").click(function(){
                $(".NewListConfig4Button1Popup3,.refreshBg").show();
            })
            $(".NewListConfig4ButtonClose").click(function(){
                $(".containbox3,.refreshBg").hide();
            });
			
		});
		
		  //点击图片  左右滑动
		 $(function() {
		 	//删除节点
		    $('.infor_bottom').find('.del_element').each(function(i,v){
		        $(v).on('click',function(){
		            $(this).parent().remove();
		        })
		    })
			var slick = function() {
				var flag = 0;
				$('.left').click('on',function() {
					$_that = $(this).siblings('.id_card').children('.slick').children('li').length;
					//alert("左点");
					ulevery = $(this).siblings('.id_card').children('.slick').children('li').outerWidth(true);
					if($_that>4){
						if (flag <= -$_that+4) {
							flag = -$_that+5;
						}
						flag -= 1;
						$(this).siblings('.id_card').children('.slick').css("left", flag * ulevery + 'px');
					}
				})
				$('.right').click('on',function() {
					//alert("右点");
					if($_that>4){
						if (flag == 0) {
							flag = -1;
						}
						flag += 1;
						$(this).siblings('.id_card').children('.slick').css("left", flag * ulevery + 'px');
					}
				})
			}
			slick(); 
		}) 
		
		//下载图片
		function downLoad(t){
			//用户的borrowId
// 			var borrowId = $("#downImageBorrowId").val();
// 			location.href=ctx+"/borrow/borrowApply/borrowApplyDownLoad?id="+borrowId;
			var id=$("#downImageBorrowId").val();
			location.href=ctx+"/borrow/borrowApply/downloadZipImage?id="+id;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/borrow/borrowApply/">借款申请列表</a></li>
		<li class="active"><a href="${ctx}/borrow/borrowApply/find?id=${detail.borrowId}">借款申请列表详情</a></li>
	</ul><br/>
	
	<div class="tabBOX">
       			 
       			 <div class="tabscontbox">
       			  		
       			 		<div style="display: block;">
       			 		<%@ include file="/WEB-INF/views/include/borrowApplyDetail.jsp"%>
<%--        			 				
       			 						 <div class="tableBox">
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
									                 <tr>
									                      <td><strong>借款类型：</strong>
									                      <c:if test="${detail.type=='1' }">信用标</c:if>
									                      <c:if test="${detail.type=='2' }">抵押标</c:if>
									                      <c:if test="${detail.type=='3' }">公积金贷款</c:if>
									                      
									                      </td>
									                      <td><strong>借款用途：</strong>
									                      		${detail.usageOfLoanType}
									                      		${detail.usageOfLoan}
									                      </td>
									                      <td></td>
									                 </tr>
									                 <tr>
									                      <td><strong>申请金额：</strong>
									                      	${detail.MINTENDERSUM}元 ---
									                      	${detail.MAXTENDERSUM}元 
									                      </td>
									                      <td><strong>申请时长：</strong>
									                      		${detail.DEADLINE}个月 
									                      </td>
									                      <td>
									                      	  <strong>借款利率：</strong>
									                      			${detail.ANUALRATE}	%
									                      </td>
									                 </tr>
									                 <tr>
									                      <td><strong>最高月还款：</strong>
									                      	${detail.monthPrepaymentAmount}元/月
									                      
									                      </td>
									                      <td><strong>是否加急：</strong>
							                  					<c:if test="${detail.criticalId =='1'}">是</c:if>
							                  					<c:if test="${detail.criticalId =='2'}">否</c:if>
									                      </td>
									                      <td></td>
									                 </tr>
            									</table>
       			 						 </div>
       			 				
       			 				<h3>个人资料</h3>
       			 				
       			 						 <div class="tableBox">
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
									                 <tr>
									                      <td><strong>姓名：</strong>${detail.name }</td>
									                      <td><strong>性别：</strong>
									                      		${detail.sex=='1'?'男':'女'}
									                      		
									                      </td>
									                 </tr>
									                 <tr>
									                      <td><strong>最高学历：</strong>
									                      		<c:if test="${detail.heducation =='10'}">
									                      		        硕士及以上
									                      		</c:if>
									                      		<c:if test="${detail.heducation =='20'}">
									                      		        本科
									                      		</c:if>
									                      		<c:if test="${detail.heducation =='30'}">
									                      		        专科
									                      		</c:if>
									                      		<c:if test="${detail.heducation =='60'}">
									                      		        高中
									                      		</c:if>
									                      		<c:if test="${detail.heducation =='70'}">
									                      		      初中及以下
									                      		</c:if>
									                      </td>
									                      <td><strong>出生日期：</strong>
									                      		${detail.birthday}
									                      		
									                      </td>
									                 </tr>
									                 <tr>
									                      <td><strong>身份证号码：</strong>
									                      	${detail.idCardNo}
									                      </td>
									                      <td><strong>证件有效期：</strong>
									                      		${detail.validityofDocumnets}
									                      		
									                      </td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>婚姻状况：</strong>
									                 			<c:if test="${detail.maritalStauts =='10'}">
									                 			     已婚
									                 			</c:if>
									                 			<c:if test="${detail.maritalStauts =='20'}">
									                 			     未婚
									                 			</c:if>
									                 			<c:if test="${detail.maritalStauts =='40'}">
									                 			    离异
									                 			</c:if>
									                 			<c:if test="${detail.maritalStauts =='30'}">
									                 			   丧偶
									                 			</c:if>
									                 		</td>
									                 		<td>
									                 			<strong>有无子女：</strong>
									                 			${detail.hasChildren=='1'?'有':'没有'}
									                 			
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>房产状况：</strong>
									                 			<c:if test="${detail.realeStateSituation =='1'}">
									                 			    有房无贷款
									                 			</c:if>
									                 			<c:if test="${detail.realeStateSituation =='2'}">
									                 			  有房有贷款
									                 			</c:if>
									                 			<c:if test="${detail.realeStateSituation =='3'}">
									                 			 其他
									                 			</c:if>
									                      			
									                 		</td>
									                 		<td>
									                 			<strong>共同居住者：</strong>
									                 			<c:if test="${detail.coResident =='1'}">
									                 			    父母
									                 			</c:if>
									                 			<c:if test="${detail.coResident =='2'}">
									                 			  配偶及子女
									                 			</c:if>
									                 			<c:if test="${detail.coResident =='3'}">
									                 			    朋友
									                 			</c:if>
									                 			<c:if test="${detail.coResident =='4'}">
									                 			    独居
									                 			</c:if>
									                 			<c:if test="${detail.coResident =='5'}">
									                 			  其他
									                 			</c:if>
									                 			
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>个人年收入：</strong>${detail.annualIncome}
									                 			元
									                 		</td>
									                 		<td>
									                 			<strong>信用卡最高额度：</strong>${detail.highCredit}
									                 			元
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>户籍地址：</strong>
									                 			${detail.registryProvince}
									                 			${detail.registryCity}
									                 			${detail.registryQu}
									                 		</td>
									                 		<td>
									                 			<strong>户籍详细地址：</strong>${detail.registryAddress}
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>户籍邮政编码：</strong></strong>${detail.registryCode}
									                 		</td>
									                 		<td>
									                 			<strong></strong>
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>现居住地址：</strong>
									                 			${detail.familyProvince}
									                 			${detail.familyCity}
									                 			${detail.familyQu}
									                 		</td>
									                 		<td>
									                 			<strong>现居住地详细地址：</strong>${detail.familyAddress }
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>现居住地邮编：</strong>${detail.familyCode}
									                 		</td>
									                 		<td>
									                 			<strong>现居住地电话号码：</strong>${detail.familyTel}
									                 		</td>
									                 </tr>
            									</table>
       			 						 </div>
       			 				
       			 				<h3>单位信息</h3>
       			 				
       			 						 <div class="tableBox">
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>工作单位全称：</strong>${detail.companyName}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>单位性质：</strong>
       			 						 		 			<c:if test="${detail.companyStyle =='1'}">
       			 						 		 			     机关及事业单位
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.companyStyle =='2'}">
       			 						 		 			   国营
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.companyStyle =='3'}">
       			 						 		 			     民营
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.companyStyle =='4'}">
       			 						 		 			  三资企业
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.companyStyle =='5'}">
       			 						 		 			    其他
       			 						 		 			</c:if>
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>进入单位时间：</strong>
       			 						 		 			${detail.companyInTime }
									                      		
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>所在部门/处室：</strong>${detail.companyDepartment}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>担任职务：</strong>
       			 						 		 			<c:if test="${detail.companyAssume =='1'}">创始人/负责人</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='2'}">高层管理人员</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='3'}">中层管理人员</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='4'}">一般管理人员</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='5'}">普通员工</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='6'}">临时员工</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='7'}">其他</c:if>
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>单位邮政编码：</strong>${detail.companyCode}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>单位地址：</strong>
       			 						 		 				${detail.companyPorvince}
       			 						 		 				${detail.companyCity}
       			 						 		 				${detail.companyQu}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>单位详细地址：</strong>${detail.companyAddress}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>单位电话号码：</strong>
       			 						 		 			</strong>${detail.companyTel}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>单位电话分机号：</strong>${detail.companySuffix}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	
       			 						 		 </table>
       			 						 </div>
       			 				
       			 				<h3>联系人信息</h3>
       			 				
       			 						  <div class="tableBox">
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
       			 						 		 	<c:forEach items="${detail.list}" var="borrowcontacts">
       			 						 		 	<tr>
       			 						 		 			<strong>
       			 						 		 				<c:if test="${borrowcontacts.type=='3'}">
       			 						 		 				   <td><strong>家庭联系人关系：</strong>${borrowcontacts.relation}</td>
       			 						 		 				   <td><strong>姓名：</strong>${borrowcontacts.name}</td>
       			 						 		 				   <td><strong>手机/电话：</strong>${borrowcontacts.mobile}</td>
       			 						 		 				</c:if>
       			 						 		 				<c:if test="${borrowcontacts.type=='5'}">
       			 						 		 				   <td><strong>工作联系人关系：</strong>${borrowcontacts.relation}</td>
       			 						 		 				   <td><strong>姓名：</strong>${borrowcontacts.name}</td>
       			 						 		 				   <td><strong>手机/电话：</strong>${borrowcontacts.mobile}</td>
       			 						 		 				</c:if>
       			 						 		 				<c:if test="${borrowcontacts.type=='4'}">
       			 						 		 				   <td><strong>紧急联系人关系：</strong>${borrowcontacts.relation}</td>
       			 						 		 				   <td><strong>姓名：</strong>${borrowcontacts.name}</td>
       			 						 		 				   <td><strong>手机/电话：</strong>${borrowcontacts.mobile}</td>
       			 						 		 				</c:if>
       			 						 		 			</strong>
       			 						 		 	</tr>
       			 						 		 	</c:forEach>
       			 						 		 </table>
       			 						 </div>
       			 				
       			 				<h3>公积金贷款资料</h3>
       			 				
       			 						 <div class="tableBox">
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
       			 						 		 	
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>查询网址：</strong>${detail.queryNet}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>帐户类型：</strong>
       			 						 		 			<c:if test="${detail.accountType =='1'}">
       			 						 		 			     身份证
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.accountType =='2'}">
       			 						 		 			    联名卡
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.accountType =='3'}">
       			 						 		 			    用户名
       			 						 		 			</c:if>
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>账号：</strong>${detail.accountNo}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>密码：</strong>${detail.accountPassword}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 </table>
       			 						 </div>
       			 				
 --%>       		<div class="infor_bottom">
           
               <h3>审核信息：
 <!--                     <input type="button"  class="btn btn-primary" onclick="downLoad()" value="一键下载" style="margin-left:300px;"/>
 -->               </h3>
                   <div class="infor_operation">
                       <span class="infor_idcard">身份证证明</span>
                   </div>
			     <div class="infor_pic" style="width:1150px;"><!--身份证明开始  -->
                       <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="idcardList1" class="slick" onclick="addImgSrc('idcardList1');">
                                <c:forEach items="${idCardList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48 ">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li> 
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                      <a href="javascript:;" class="slick_right right" class="right"></a>
                      <input type="hidden" value="${detail.borrowId}" id="downImageBorrowId"/>  <!--borrowId  -->
                      <input type="hidden" value="idcard"  id="idcards"/>  <!-- 每种图片的唯一类型标示 -->
                      
                 </div><!--身份证明结尾  -->
				 <div class="infor_operation">
	                        <span class="infor_idcard">个人征信报告</span>
	              </div>
                      <div class="infor_pic" style="width:1150px;"><!-- 个人征信报告 -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="creditList1" class="slick" onclick="addImgSrc('creditList1');">
                                <c:forEach items="${creditList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                    
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                            <input type="hidden" value="credit"  id="credits"/>  <!-- 每种图片的唯一类型标示 -->
             </div><!-- 个人征信报告 -->	
             
				<div class="infor_operation">
                            <span class="infor_idcard">工作以及收入证明</span>
                </div>
                        <div class="infor_pic" style="width:1150px;"><!-- 工作证明 -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="workList1" class="slick" onclick="addImgSrc('workList1');">
                                <c:forEach items="${workAndincomeList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                            <input type="hidden" value="workAndIncom"  id="workAndIncome"/> <!-- 每种图片的唯一类型标示 -->
                   </div><!-- 工作证明 -->				
								
<%-- 			 <div class="infor_operation">
                        <span class="infor_idcard">其他证明</span>
             </div>
                       <div class="infor_pic" style="width:1150px;"><!--其他证明  -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="incomeList1" class="slick" onclick="addImgSrc('incomeList1');">
                                <c:forEach items="${otherList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
												<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                           <input type="hidden" value="other"  id="others"/><!-- 每种图片的唯一类型标示 -->
                      </div><!--其他证明  -->	
                      
            <div class="infor_operation">
	                        <span class="infor_idcard">手机通话记录详单</span>
	              </div>
                      <div class="infor_pic" style="width:1150px;"><!-- 手机通话记录详单 -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul class="slick">
                                <c:forEach items="${mobileList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                    
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                            
             </div><!-- 手机通话记录详单 -->	
 --%>             
						      </div><!--这是审核资料    左右滑动的效果-->
       			 		</div>
       				</div>
       		</div>
       		<br>
       		<p style="text-align: center;">
							<button class="btn btn-success" onclick="history.go(-1)">关闭</button>
			</p>	 				
	
	<div class="inforMask"></div>
	<!--图片弹窗部分 start-->
	<div class="inforWindow">
		<div class="contentImg">
			<!-- <a class="arrow left" href="javascript:void(0)"> <span></span></a>
			<a class="arrow right" href="javascript:void(0)"> <span></span></a> -->
			<div class="card">
				<ul class="flashImg" id="ziLioaImages"></ul>
			</div>
		</div>
		<div class="inforClose">
			<a href="javascript:;" onclick="delImgSrc();">×</a>
		</div>
	</div>
	<!--图片弹窗部分 end-->		
	
</body>
</html>