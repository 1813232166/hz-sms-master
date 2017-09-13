<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
 <head>
    <title>策略设置</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	.Mtable2{
	width: 900px
	
	}
	.Mtable2 table tr td {
	    height: 24px;
	    line-height: 24px;
	    font-size: 14px;
	    border-bottom: 1px dashed rgb(220, 220, 220);
	}
	user agent stylesheet
	td, th {
	    display: table-cell;
	    vertical-align: inherit;
	}
	</style>
    <script type="text/javascript">
     //出借产品权重
    function lendpRoductRecord(){
    	$("#lendpRoductModal").modal('show');
    }
  //金额权重2 修改记录
    function moneyRecord2(){
    	$("#moneyRecordModal2").modal('show');
    }
  //资金等待时间权重
    function capitalAwaitRecord(){
    	$("#capitalAwaitModal").modal('show');
    }
  //出借产品权重    编辑
    function lendEdit(tdid){
		 $("#lendInput"+tdid).removeAttr("style");
		 $("#lendInput"+tdid).attr("style","margin: 0px");
		 $("#lendSpan"+tdid).attr("style","visibility: hidden !important;");
  }
  //出借产品权重  保存
    function lendSave(tdid){
		 $("#lendInput"+tdid).attr("style","margin: 0px ");
		 $("#lendInput"+tdid).attr("style","visibility: hidden !important");
		 $("#lendSpan"+tdid).attr("style","visibility: blok !important;");
		 var lendUpdateWeight=$("#lendInput"+tdid).val();
		 updateWeightProduct(tdid,lendUpdateWeight);
  }
    //金额权重  资金策略  编辑
    function moneyEditTwo(tdid){
  		 $("#moneyInputTwo"+tdid).removeAttr("style");
  		 $("#moneyInputTwo"+tdid).attr("style","margin: 0px");
  		 $("#moneySpanTwo"+tdid).attr("style","visibility: hidden !important;");
    }
  //金额权重  资金策略  保存
    function moneySaveTwo(tdid){
  		 $("#moneyInputTwo"+tdid).attr("style","margin: 0px ");
  		 $("#moneyInputTwo"+tdid).attr("style","visibility: hidden !important");
  		 $("#moneySpanTwo"+tdid).attr("style","visibility: blok !important;");
  		 var moneyUpdateWeight=$("#moneyInputTwo"+tdid).val();
  		 updateWeightProduct(tdid,moneyUpdateWeight);
    }
  //资金等待时间权重 编辑
    function capitalEdit(tdid){
  		 $("#capitalInput"+tdid).removeAttr("style");
  		 $("#capitalInput"+tdid).attr("style","margin: 0px");
  		 $("#capitalSpan"+tdid).attr("style","visibility: hidden !important;");
    }
    //资金等待时间权重   保存
    function capitalSave(tdid){
 		 $("#capitalInput"+tdid).attr("style","margin: 0px ");
 		 $("#capitalInput"+tdid).attr("style","visibility: hidden !important");
 		 $("#capitalSpan"+tdid).attr("style","visibility: blok !important;");
 		 var moneyUpdateWeight=$("#capitalInput"+tdid).val();
 		 updateWeightProduct(tdid,moneyUpdateWeight);
   }
    //资金等待时间权重 编辑
    function repaymentTimeEdit(tdid){
  		 $("#repaymentTimeInput"+tdid).removeAttr("style");
  		 $("#repaymentTimeInput"+tdid).attr("style","margin: 0px");
  		 $("#repaymentTimeSpan"+tdid).attr("style","visibility: hidden !important;");
    }
    //资金等待时间权重   保存
    function repaymentTimeSave(tdid){
 		 $("#repaymentTimeInput"+tdid).attr("style","margin: 0px ");
 		 $("#repaymentTimeInput"+tdid).attr("style","visibility: hidden !important");
 		 $("#repaymentTimeSpan"+tdid).attr("style","visibility: blok !important;");
 		 var moneyUpdateWeight=$("#repaymentTimeInput"+tdid).val();
 		 updateWeightProduct(tdid,moneyUpdateWeight);
   }
    
  //更新权重
  function updateWeightProduct(tdid,updateWeight){
		$.post(ctx+'/match/updateWeightStrategy',{id:tdid,updateWeight:updateWeight},function(f){
			if(f){
			  alert("修改成功");
			  window.location.reload();
			}else{
			  alert("修改失败");
			}
		},"json")
  }
    </script>
 </head>
  <body>
       <div class="tabBOX">
       			 <div class="tabsList">
       			 		<ul>
       			 				<li class="active" > <a href="${ctx}/match/strategySetList">资产策略</a></li>
       			 				<li class="active" > <a href="${ctx}/match/capitalStrategy">资金策略</a></li>
       			 				<li class="active" > <a href="${ctx}/match/matchStrategy">匹配策略</a></li>
       			 		</ul>
       			 </div>
       			 <div class="tabscontbox">     			  		
       			 		<div id="p2" style="display: block;">
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr>
       			 						 	       	  <td colspan="2"><font size="5">出借产品权重</font></td>
       			 						 	      	  <td align="center"><a href="${ctx}/match/operationLog/CAPITAL/productType">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>产品类型</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
                                                   <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
                                                   <c:choose>
												   <c:when test="${weightInfo.filed=='productType'}">
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															${weightInfo.description}
														</td>
														<td id="lendTd${weightInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="lendSpan${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='lendInput${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="lendEdit('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="lendSave('${weightInfo.id}')" >保存</a>
														</td>
													</tr>
													</c:when>
													</c:choose>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				   <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		     <tr>
       			 						 	       	  <td colspan="4"><font size="5">金额权重</font></td>
       			 						 	      	  <td align="center"><a href="${ctx}/match/operationLog/CAPITAL/productAmount">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;"  align="center"><strong>出借类型</strong></td>
										                  <td style="border: 1px solid black;width: 550px;"  align="center"><strong>出借金额(万)</strong></td>
										                  <td style="border: 1px solid black; width: 200px;"  align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black; width: 300px;"  align="center"><strong>操作时间</strong></td>
										                  <td style="border: 1px solid black; width: 200px;"  align="center"><strong>操作</strong></td>
										               </tr>
										               <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
										                    <c:choose>
												   <c:when test="${weightInfo.filed=='productAmount'}">
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															个人出借
														</td>
														<td style="border: 1px solid black;width: 350px;" align="center">
																${weightInfo.description}
														</td>
														<td id="moneyTdTwo${weightInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="moneySpanTwo${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='moneyInputTwo${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 500px;" align="center">
															<fmt:formatDate value="${weightInfo.updateTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /> 
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="moneyEditTwo('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="moneySaveTwo('${weightInfo.id}')" >保存</a>
														</td>
													</tr>
														</c:when>
													</c:choose>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				     <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr>
       			 						 	       	  <td colspan="2"><font size="5">资金等待时间权重</font></td>
       			 						 	      	  <td align="center"><a href="${ctx}/match/operationLog/CAPITAL/productTime">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>等待时间(天)</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
										           <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
										                     <c:choose>
												   <c:when test="${weightInfo.filed=='productTime'}">
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															${weightInfo.description}
														</td>
														<td id="capitalTd${weightInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="capitalSpan${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='capitalInput${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="capitalEdit('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="capitalSave('${weightInfo.id}')" >保存</a>
														</td>
													</tr>
														</c:when>
													</c:choose>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr>
       			 						 	       	  <td colspan="2"><font size="5">距离还款日权重</font></td>
       			 						 	      	  <td align="center"><a href="${ctx}/match/operationLog/CAPITAL/repaymentTime">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>距离还款日时间</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
										           <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
										                     <c:choose>
												   <c:when test="${weightInfo.filed=='repaymentTime'}">
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															${weightInfo.description}
														</td>
														<td id="repaymentTimeTd${weightInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="repaymentTimeSpan${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='repaymentTimeInput${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="repaymentTimeEdit('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="repaymentTimeSave('${weightInfo.id}')" >保存</a>
														</td>
													</tr>
														</c:when>
													</c:choose>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				
                        </div>
                        </div>
       			 </div>
  </body>
</html>