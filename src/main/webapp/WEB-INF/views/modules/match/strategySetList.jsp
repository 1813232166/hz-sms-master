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
    $(document).ready(function() {
    	 $("#productRecordModal").hide();
    	 $("#moneyRecordModal").hide();
    	 $("#moneyRecordModal").hide();
    	 $("#assetRecordModal").hide();
    	 $("#lendpRoductModal").hide();
    	 $("#moneyRecordModal2").hide();
    	 $("#capitalAwaitModal").hide();
    	 $("#deadlineMatchModal").hide();
    	 $("#capitalWhetherModal").hide();
		 $('.tabsList ul li').click(function(){
            $(this).addClass('active').siblings().removeClass('active');
            $('.tabscontbox>div:eq('+$(this).index()+')').show().siblings().hide();
        })
        $("#closeProductRecordModal").click(function(){
        	 $("#productRecordModal").modal('hide');
        });
        $("#closeMoneyRecord").click(function(){
        	 $("#moneyRecordModal").modal('hide');
        });
        $("#closeAssetRecord").click(function(){
        	 $("#assetRecordModal").modal('hide');
        });
        $("#closeLendpRoduct").click(function(){
        	 $("#lendpRoductModal").modal('hide');
        });
        $("#closemoneyRecordtwo").click(function(){
       	 $("#moneyRecordModal2").modal('hide');
        });
        $("#closeCapitalAwaitModal").click(function(){
       	 $("#capitalAwaitModal").modal('hide');
        });
        $("#closeDeadlineMatch").click(function(){
       	 $("#deadlineMatchModal").modal('hide');
        });
        $("#closeCapitalWhether").click(function(){
       	 $("#capitalWhetherModal").modal('hide');
        });
	});
    //产品权重   修改记录
    function productRecord(){
    	$("#productRecordModal").modal('show');
    }
    //金额权重 修改记录
    function moneyRecord(){
    	$("#moneyRecordModal").modal('show');
    }
    //资产等待时间权重 修改记录
    function moneyRecord(){
    	$("#assetRecordModal").modal('show');
    }
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
  //期限匹配列表
    function deadlineMatchRecord(){
    	$("#deadlineMatchModal").modal('show');
    }
  //资金是否可匹配其他出借产品
    function capitalWhetherRecord(){
    	$("#capitalWhetherModal").modal('show');
    }
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
    //期限匹配列表 编辑
    function deadlineEdit(tdid){
  		 $("#deadlineInput"+tdid).removeAttr("style");
  		 $("#deadlineInput"+tdid).attr("style","margin: 0px");
  		 $("#deadlineSpan"+tdid).attr("style","visibility: hidden !important;");
    }
    //期限匹配列表 保存
    function deadlineSave(tdid){
		 $("#deadlineInput"+tdid).attr("style","margin: 0px ");
 		 $("#deadlineInput"+tdid).attr("style","visibility: hidden !important");
 		 $("#deadlineSpan"+tdid).attr("style","visibility: blok !important;");
 		 var deadline=$("#deadlineInput"+tdid).val();
 		$.post(ctx+'/match/deadlineStrategy',{id:tdid,deadline:deadline},function(f){
			if(f){
			  alert("修改成功");
			  window.location.reload();
			}else{
			  alert("修改失败");
			}
		},"json")
    }
    //资金是否可匹配其他出借产品  编辑
    function capitalWhetherEdit(tdid){
 		 $("#capitalWhetherInput"+tdid).removeAttr("style");
  		 $("#capitalWhetherInput"+tdid).attr("style","margin: 0px");
  		 $("#capitalWhetherSpan"+tdid).attr("style","visibility: hidden !important;");
    }
    //资金是否可匹配其他出借产品   保存
    function capitalWhetherSave(tdid){
		 $("#capitalWhetherInput"+tdid).attr("style","margin: 0px ");
 		 $("#capitalWhetherInput"+tdid).attr("style","visibility: hidden !important");
 		 $("#capitalWhetherSpan"+tdid).attr("style","visibility: blok !important;");
 		 var capitalWhether=$("#capitalWhetherInput"+tdid).val();
 		var isCapital=$("input[name='capitalWhether']:checked").val();
 		$.post(ctx+'/match/updateWhetherToOpen',{id:tdid,isCapital:isCapital},function(f){
			if(f){
			  alert("修改成功");
			  window.location.reload();
			}else{
			  alert("修改失败");
			}
		},"json")
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
       			 				<li class="active" > <a href="${ctx}/match/strategySetList">资金策略</a></li>
       			 				<li class="active" > <a href="${ctx}/match/strategySetList">匹配策略</a></li>
       			 		</ul>
       			 </div>
       			 <div class="tabscontbox">
       			  		
       			 		<div id="p1" style="display: block;">
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 	      <tr>
       			 						 	      	  <td colspan="2"><font size="5">产品权重</font></td>
       			 						 	      	  <td align="center"><a href="javascipt:void(0)" onclick="productRecord()">修改记录</a></td>
       			 						 	      	 </tr>
										         <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>产品类型</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
                                                   <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<c:choose>
															  <c:when test="${weightInfo.type=='ASSET'}">
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productType'}">
														              <c:if test="${weightInfo.value=='3'}">
														                                       公积金
														              </c:if>
														              <c:if test="${weightInfo.value=='99'}">
														                                      债转
														              </c:if>
														              <c:if test="${weightInfo.value=='2'}">
														                                      新借款-债券类型-新借款产品2
														              </c:if>
														              <c:if test="${weightInfo.value=='1'}">
														                                   新借款-债券类型-新借款产品1
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:when>
															  <c:otherwise>
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productType'}">
														              <c:if test="${weightInfo.value=='2'}">
														                                     新借款-债券类型-新借款产品2
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:otherwise>
															</c:choose>
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
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				   <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr>
       			 						 	       	  <td colspan="4"><font size="5">金额权重</font></td>
       			 						 	      	  <td align="center"><a href="javascipt:void(0)" onclick="moneyRecord()">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 500px;" align="center"><strong>借款类别</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>借款金额(万)</strong></td>
										                  <td style="border: 1px solid black;width: 200px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>操作时间</strong></td>
										                  <td style="border: 1px solid black;width: 200px;" align="center"><strong>操作</strong></td>
										               </tr>
										            <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 500px;" align="center">
															<c:choose>
															  <c:when test="${weightInfo.type=='ASSET'}">
															    <c:choose>
														            <c:when test="${weightInfo.filed=='borrowType'}">
														              <c:if test="${weightInfo.value=='2'}">
														                                       新借款-债券类型-新借款产品3
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:when>
															  <c:otherwise>
															 
															  </c:otherwise>
															</c:choose>
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															
														</td>
														<td id="moneyTd${weightInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="moneySpan${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='moneyInput${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
														     <fmt:formatDate value="${weightInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="moneyEdit('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="moneySave('${weightInfo.id}')" >保存</a>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				     <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		     <tr>
       			 						 	       	  <td colspan="2"><font size="5">资产等待时间权重</font></td>
       			 						 	      	  <td align="center"><a href="javascipt:void(0)"  onclick="moneyRecord()">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>等待时间(天)</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;"><strong align="center">操作</strong></td>
										               </tr>
										         <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<c:choose>
															  <c:when test="${weightInfo.type=='ASSET'}">
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productTime'}">
														              <c:if test="${weightInfo.value=='3+'}">
														                                       新借款-债券类型-新借款产品3等待3天及以上
														              </c:if>
														              <c:if test="${weightInfo.value=='2'}">
														                                       等待2天
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:when>
															  <c:otherwise>
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productTime'}">
														              <c:if test="${weightInfo.value=='2'}">
														                                       等待2天
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:otherwise>
															</c:choose>
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
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 		</div>
       			 		<div id="p2" class="hide" style="display: none;">
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr>
       			 						 	       	  <td colspan="2"><font size="5">出借产品权重</font></td>
       			 						 	      	  <td align="center"><a href="javascipt:void(0)"  onclick="lendpRoductRecord()">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>产品类型</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
                                                   <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<c:choose>
															  <c:when test="${weightInfo.type=='ASSET'}">
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productType'}">
														              <c:if test="${weightInfo.value=='3'}">
														                                       公积金
														              </c:if>
														              <c:if test="${weightInfo.value=='99'}">
														                                      债转
														              </c:if>
														              <c:if test="${weightInfo.value=='2'}">
														                                      新借款-债券类型-新借款产品2
														              </c:if>
														              <c:if test="${weightInfo.value=='1'}">
														                                   新借款-债券类型-新借款产品1
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:when>
															  <c:otherwise>
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productType'}">
														              <c:if test="${weightInfo.value=='2'}">
														                                     新借款-债券类型-新借款产品2
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:otherwise>
															</c:choose>
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
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				   <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		     <tr>
       			 						 	       	  <td colspan="4"><font size="5">金额权重</font></td>
       			 						 	      	  <td align="center"><a href="javascipt:void(0)"  onclick="moneyRecord2()">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;"  align="center"><strong>出借类型</strong></td>
										                  <td style="border: 1px solid black;width: 550px;"  align="center"><strong>出借金额(万)</strong></td>
										                  <td style="border: 1px solid black; width: 200px;"  align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black; width: 300px;"  align="center"><strong>操作时间</strong></td>
										                  <td style="border: 1px solid black; width: 200px;"  align="center"><strong>操作</strong></td>
										               </tr>
										               <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															个人出借
														</td>
														<td style="border: 1px solid black;width: 550px;" align="center">
														   <c:choose>
															  <c:when test="${weightInfo.type=='ASSET'}">
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productAmount'}">
														              <c:if test="${weightInfo.value=='0-50000'}">
														                                       公积金存量债券-金额-企业-5万以下
														              </c:if>
														              <c:if test="${weightInfo.value=='100000-200000'}">
														                                      存量债券-金额-企业-10-20万
														              </c:if>
														              <c:if test="${weightInfo.value=='200000'}">
														                                      存量债券-金额-企业-20万及以上
														              </c:if>
														              <c:if test="${weightInfo.value=='50000-100000'}">
														                                   存量债券-金额-企业-5-10万
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:when>
															  <c:otherwise>
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productAmount'}">
														              <c:if test="${weightInfo.value=='100000-200000'}">
														                                    新借款-金额-企业-10-20万
														              </c:if>
														              <c:if test="${weightInfo.value=='1000000'}">
														                                     新借款-金额-企业-100万及以上
														              </c:if>
														              <c:if test="${weightInfo.value=='500000-1000000'}">
														                                     新借款-金额-企业-50-100万
														              </c:if>
														              <c:if test="${weightInfo.value=='200000-500000'}">
														                                     新借款-金额-企业-50-100万
														              </c:if>
														              <c:if test="${weightInfo.value=='0-100000'}">
														                                    新借款-金额-企业-10万以下
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:otherwise>
															</c:choose>
														</td>
														<td id="moneyTdTwo${weightInfo.id}" style="border: 1px solid black;width: 80px;" align="center">
															<span id="moneySpanTwo${weightInfo.id}">${weightInfo.weight}</span>
															<input type='text'  id='moneyInputTwo${weightInfo.id}' value='${weightInfo.weight}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<fmt:formatDate value="${weightInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="moneyEditTwo('${weightInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="moneySaveTwo('${weightInfo.id}')" >保存</a>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				     <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr>
       			 						 	       	  <td colspan="2"><font size="5">资金等待时间权重</font></td>
       			 						 	      	  <td align="center"><a href="javascipt:void(0)"  onclick="capitalAwaitRecord()">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>等待时间(天)</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
										           <c:forEach items="${weightInfoList}" var="weightInfo" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<c:choose>
															  <c:when test="${weightInfo.type=='CAPITAL'}">
															    <c:choose>
														            <c:when test="${weightInfo.filed=='productTime'}">
														              <c:if test="${weightInfo.value=='2'}">
														                                   等待2天
														              </c:if>
														            </c:when>       
															    </c:choose>
															  </c:when>
															</c:choose>
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
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
                        </div>
                       <div id="p3" class="hide" style="display: none;">
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr>
       			 						 	       	  <td colspan="3"><font size="5">期限匹配列表</font></td>
       			 						 	      	  <td align="center"><a href="javascipt:void(0)"  onclick="deadlineMatchRecord()">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>出借期限(月)</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>借款期限(月)</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作时间</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
										          <c:forEach items="${matchInstallInfoList}" var="matchInstallInfo" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<c:choose>
															  <c:when test="${matchInstallInfo.type=='2'}">
3
															  </c:when>
															</c:choose>
														</td>
														<td id="deadlineTd${matchInstallInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="deadlineSpan${matchInstallInfo.id}">${matchInstallInfo.capitalNper}</span>
															<input type='text'  id='deadlineInput${matchInstallInfo.id}' value='${matchInstallInfo.capitalNper}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
														   <fmt:formatDate value="${matchInstallInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="deadlineEdit('${matchInstallInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="deadlineSave('${matchInstallInfo.id}')" >保存</a>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				   <div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr>
       			 						 	       	  <td colspan="2"><font size="5">资金是否可匹配其他出借产品</font></td>
       			 						 	      	  <td align="center"><a href="javascipt:void(0)"  onclick="capitalWhetherRecord()">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>匹配出借产品类型</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>状态</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
										          <c:forEach items="${matchInstallInfoList}" var="matchInstallInfo" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<c:choose>
															  <c:when test="${matchInstallInfo.type=='3'}">
															  出借计划--匹配散标集产品
															  </c:when>
															</c:choose>
														</td>
														   <td id="capitalWhetherTd${matchInstallInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="capitalWhetherSpan${matchInstallInfo.id}">
														<c:choose>
														  <c:when test="${matchInstallInfo.whetherToOpen=='1'}">
														            是
														  </c:when>
														  <c:otherwise>
														          否
														  </c:otherwise>
														</c:choose>
															</span>
													        <span id='capitalWhetherInput${matchInstallInfo.id}' style='margin: 0px;visibility: hidden !important;' >
													                <input type="radio"  name="capitalWhether" value="1" checked="checked"/>是
													                <input type="radio" name="capitalWhether" value="0"/>否
													        </span>
															</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="capitalWhetherEdit('${matchInstallInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="capitalWhetherSave('${matchInstallInfo.id}')" >保存</a>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
                        </div>
       			 </div>
       
       </div>
       
       	 <!-- 产品权重      模态框声明 -->
	<div class="modal fade" id="productRecordModal" tabindex="-1" data-backdrop="static" style="width: 700px;left: 430px" >
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="closeProductRecordModal"><span>&times;</span></button>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="text-align: center;font-size: 18px;">
       			 						 <div class="tableBox" >
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 130px;" align="center"><strong>产品类型</strong></td>
										                  <td style="border: 1px solid black;width: 130px;;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>账号</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>时间</strong></td>
										               </tr>
										          <c:forEach items="${tWeightLogList}" var="tWeightLog" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 130px;" align="center">
															${tWeightLog.type}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.weight}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.accountNumber}
														</td>
														<td style="border: 1px solid black;width: 200px;" align="center">
														     <fmt:formatDate value="${tWeightLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
					</div>
				</div>
			</div>
		</div>
	</div> 
	 <!-- 金额权重      模态框声明 -->
	<div class="modal fade" id="moneyRecordModal" tabindex="-1" data-backdrop="static" style="width: 700px;left: 430px" >
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="closeMoneyRecord"><span>&times;</span></button>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="text-align: center;font-size: 18px;">
       			 						 <div class="tableBox" >
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 130px;" align="center"><strong>借款类别</strong></td>
										                  <td style="border: 1px solid black;width: 130px;;" align="center"><strong>借款金额</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>账号</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>时间</strong></td>
										               </tr>
										          <c:forEach items="${tWeightLogList}" var="tWeightLog" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 130px;" align="center">
															${tWeightLog.type}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
															${tWeightLog.amount}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.weight}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.accountNumber}
														</td>
														<td style="border: 1px solid black;width: 200px;" align="center">
														     <fmt:formatDate value="${tWeightLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
					</div>
				</div>
			</div>
		</div>
	</div> 
		 <!--资产等待时间权重      模态框声明 -->
	<div class="modal fade" id="assetRecordModal" tabindex="-1" data-backdrop="static" style="width: 700px;left: 430px" >
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="closeAssetRecord"><span>&times;</span></button>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="text-align: center;font-size: 18px;">
       			 						 <div class="tableBox" >
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 200px;" align="center"><strong>等待时间（天）</strong></td>
										                  <td style="border: 1px solid black;width: 130px;;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>账号</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>时间</strong></td>
										               </tr>
										          <c:forEach items="${tWeightLogList}" var="tWeightLog" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 130px;" align="center">
															 <fmt:formatDate value="${tWeightLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.weight}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.accountNumber}
														</td>
														<td style="border: 1px solid black;width: 200px;" align="center">
														     <fmt:formatDate value="${tWeightLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
					</div>
				</div>
			</div>
		</div>
	</div> 
	<!--出借产品权重      模态框声明 -->
	<div class="modal fade" id="lendpRoductModal" tabindex="-1" data-backdrop="static" style="width: 700px;left: 430px" >
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="closeLendpRoduct"><span>&times;</span></button>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="text-align: center;font-size: 18px;">
       			 						 <div class="tableBox" >
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 130px;" align="center"><strong>产品类型</strong></td>
										                  <td style="border: 1px solid black;width: 130px;;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>账号</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>时间</strong></td>
										               </tr>
										          <c:forEach items="${tWeightLogList}" var="tWeightLog" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 130px;" align="center">
															${tWeightLog.type}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.weight}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.accountNumber}
														</td>
														<td style="border: 1px solid black;width: 200px;" align="center">
														     <fmt:formatDate value="${tWeightLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
					</div>
				</div>
			</div>
		</div>
	</div> 
		<!--金额权重2	      模态框声明 -->
	<div class="modal fade" id="moneyRecordModal2" tabindex="-1" data-backdrop="static" style="width: 700px;left: 430px" >
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="closemoneyRecordtwo"><span>&times;</span></button>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="text-align: center;font-size: 18px;">
       			 						 <div class="tableBox" >
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 130px;" align="center"><strong>出借类型</strong></td>
										                  <td style="border: 1px solid black;width: 300px;;" align="center"><strong>出借金额（万）</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>账号</strong></td>
										                  <td style="border: 1px solid black; width: 300px;" align="center"><strong>时间</strong></td>
										               </tr>
										          <c:forEach items="${tWeightLogList}" var="tWeightLog" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 130px;" align="center">
															${tWeightLog.type}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
															${tWeightLog.type}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.weight}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.accountNumber}
														</td>
														<td style="border: 1px solid black;width: 200px;" align="center">
														     <fmt:formatDate value="${tWeightLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
					</div>
				</div>
			</div>
		</div>
	</div> 
	<!--资金等待时间权重	      模态框声明 -->
	<div class="modal fade" id="capitalAwaitModal" tabindex="-1" data-backdrop="static" style="width: 700px;left: 430px" >
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="closeCapitalAwaitModal"><span>&times;</span></button>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="text-align: center;font-size: 18px;">
       			 						 <div class="tableBox" >
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>等待时间（天）</strong></td>
										                  <td style="border: 1px solid black;width: 300px;;" align="center"><strong>权重</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>账号</strong></td>
										                  <td style="border: 1px solid black; width: 300px;" align="center"><strong>时间</strong></td>
										               </tr>
										          <c:forEach items="${tWeightLogList}" var="tWeightLog" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 130px;" align="center">
															 <fmt:formatDate value="${tWeightLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.weight}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${tWeightLog.accountNumber}
														</td>
														<td style="border: 1px solid black;width: 200px;" align="center">
														     <fmt:formatDate value="${tWeightLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
					</div>
				</div>
			</div>
		</div>
	</div> 
	<!--期限匹配列表	      模态框声明 -->
	<div class="modal fade" id="deadlineMatchModal" tabindex="-1" data-backdrop="static" style="width: 700px;left: 430px" >
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="closeDeadlineMatch"><span>&times;</span></button>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="text-align: center;font-size: 18px;">
       			 						 <div class="tableBox" >
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>出借期限(月)</strong></td>
										                  <td style="border: 1px solid black;width: 300px;;" align="center"><strong>借款期限(月)</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>账号</strong></td>
										                  <td style="border: 1px solid black; width: 300px;" align="center"><strong>时间</strong></td>
										               </tr>
										          <c:forEach items="${matchInstallLogList}" var="matchInstallLog" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${matchInstallLog.assetNper}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${matchInstallLog.capitalNper}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${matchInstallLog.accountNumber}
														</td>
														<td style="border: 1px solid black;width: 200px;" align="center">
														     <fmt:formatDate value="${matchInstallLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
					</div>
				</div>
			</div>
		</div>
	</div> 
	<!--资金是否可匹配其他出借产品	      模态框声明 -->
	<div class="modal fade" id="capitalWhetherModal" tabindex="-1" data-backdrop="static" style="width: 700px;left: 430px" >
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" id="closeCapitalWhether"><span>&times;</span></button>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="text-align: center;font-size: 18px;">
       			 						 <div class="tableBox" >
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 400px;" align="center"><strong>匹配出借产品类型</strong></td>
										                  <td style="border: 1px solid black;width: 300px;;" align="center"><strong>状态</strong></td>
										                  <td style="border: 1px solid black; width: 130px;" align="center"><strong>账号</strong></td>
										                  <td style="border: 1px solid black; width: 300px;" align="center"><strong>时间</strong></td>
										               </tr>
										          <c:forEach items="${matchInstallLogList}" var="matchInstallLog" varStatus="status" >
                                                     <tr>
														<td style="border: 1px solid black;width: 130px;" align="center">
														 ${matchInstallLog.matchType}
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   成功
														</td>
														<td style="border: 1px solid black;width: 130px;" align="center">
														   ${matchInstallLog.accountNumber}
														</td>
														<td style="border: 1px solid black;width: 200px;" align="center">
														     <fmt:formatDate value="${matchInstallLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
														</td>
													</tr>
                                                   </c:forEach>
										          </table>
       			 						 </div>
					</div>
				</div>
			</div>
		</div>
	</div> 
  </body>
</html>