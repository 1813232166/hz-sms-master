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
  //产品权重 编辑
  function editProduct(tdid){
	 $("#input"+tdid).removeAttr("style");
	 $("#input"+tdid).attr("style","margin: 0px");
	 $("#span"+tdid).attr("style","visibility: hidden !important;");
  }
  //产品权重 保存
  function saveProduct(tdid){
	 $("#input"+tdid).attr("style","margin: 0px ");
	 $("#input"+tdid).attr("style","visibility: hidden !important");
	 $("#span"+tdid).attr("style","visibility: blok !important;");
	 var updateWeight=$("#input"+tdid).val();
	 updateWeightProduct(tdid,updateWeight);
  }
  //金额权重 编辑
  function moneyEdit(tdid){
		 $("#moneyInput"+tdid).removeAttr("style");
		 $("#moneyInput"+tdid).attr("style","margin: 0px");
		 $("#moneySpan"+tdid).attr("style","visibility: hidden !important;");
  }
//金额权重 保存
  function moneySave(tdid){
		 $("#moneyInput"+tdid).attr("style","margin: 0px ");
		 $("#moneyInput"+tdid).attr("style","visibility: hidden !important");
		 $("#moneySpan"+tdid).attr("style","visibility: blok !important;");
		 var moneyUpdateWeight=$("#moneyInput"+tdid).val();
		 updateWeightProduct(tdid,moneyUpdateWeight);
  }
  //资产等待时间权重  编辑
  function assetEdit(tdid){
		 $("#assetInput"+tdid).removeAttr("style");
		 $("#assetInput"+tdid).attr("style","margin: 0px");
		 $("#assetSpan"+tdid).attr("style","visibility: hidden !important;");
  }
  //资产等待时间权重  保存
  function assetSave(tdid){
		 $("#assetInput"+tdid).attr("style","margin: 0px ");
		 $("#assetInput"+tdid).attr("style","visibility: hidden !important");
		 $("#assetSpan"+tdid).attr("style","visibility: blok !important;");
		 var assetUpdateWeight=$("#assetInput"+tdid).val();
		 updateWeightProduct(tdid,assetUpdateWeight);
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
       			  		
       			 		<div id="p1" style="display: block;">
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 	      <tr>
       			 						 	      	  <td colspan="2"><font size="5">产品权重</font></td>
       			 						 	      	  <td align="center"><a href="${ctx}/match/operationLog/ASSET/productType">修改记录</a></td>
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
														<td id="td${weightInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="span${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='input${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="editProduct('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="saveProduct('${weightInfo.id}')" >保存</a>
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
       			 						 	      	  <td align="center"><a href="${ctx}/match/operationLog/ASSET/productAmount" >修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 500px;" align="center"><strong>借款类别</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>借款金额(万)</strong></td>
										                  <td style="border: 1px solid black;width: 200px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>操作时间</strong></td>
										                  <td style="border: 1px solid black;width: 200px;" align="center"><strong>操作</strong></td>
										               </tr>
										            <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
										            	<c:choose>
													  <c:when test="${weightInfo.filed=='productAmount'}">
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<c:choose>
															  <c:when test="${weightInfo.filed=='productAmount'}">
														        <c:if test="${weightInfo.borrowType=='1'}">
														                                     企业借款
														        </c:if>
														        <c:if test="${weightInfo.borrowType=='2'}">
														                                    个人借款
														        </c:if>
															  </c:when>
															</c:choose>
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															${weightInfo.description}
														</td>
														<td id="moneyTd${weightInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="moneySpan${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='moneyInput${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 500px;" align="center">
														    <fmt:formatDate value="${weightInfo.updateTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /> 
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="moneyEdit('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="moneySave('${weightInfo.id}')" >保存</a>
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
       			 						 	       	  <td colspan="2"><font size="5">资产等待时间权重</font></td>
       			 						 	      	  <td align="center"><a href="${ctx}/match/operationLog/ASSET/productTime" >修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>等待时间(天)</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;"><strong align="center">操作</strong></td>
										               </tr>
										         <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
										          	<c:choose>
													  <c:when test="${weightInfo.filed=='productTime'}">
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
																${weightInfo.description}
														</td>
														<td id="assetTd${weightInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="assetSpan${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='assetInput${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="assetEdit('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="assetSave('${weightInfo.id}')" >保存</a>
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