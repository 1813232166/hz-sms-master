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
  //期限匹配列表
    function deadlineMatchRecord(){
    	$("#deadlineMatchModal").modal('show');
    }
  //资金是否可匹配其他出借产品
    function capitalWhetherRecord(){
    	$("#capitalWhetherModal").modal('show');
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
       			 				<li class="active" > <a href="${ctx}/match/capitalStrategy">资金策略</a></li>
       			 				<li class="active" > <a href="${ctx}/match/matchStrategy">匹配策略</a></li>
       			 		</ul>
       			 </div>
       			 <div class="tabscontbox">
                       <div id="p3" style="display: block;">
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr style="height: 40px;">
       			 						 	       	  <td colspan="3"><font size="5">期限匹配列表</font></td>
       			 						 	      	  <td align="center"><a href="${ctx}/match/matchInstallLog/1">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>出借期限(月)</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>借款期限(月)</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作时间</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
										          <c:forEach items="${matchInstallInfoList}" var="matchInstallInfo" varStatus="status" >
                                                   		<c:choose>
															  <c:when test="${matchInstallInfo.type=='1'}">
                                                     <tr>
														<td style="border: 1px solid black;width: 300px;" align="center">
															${matchInstallInfo.capitalNper}
														</td>
														<td id="deadlineTd${matchInstallInfo.id}" style="border: 1px solid black;width: 300px;" align="center">
															<span id="deadlineSpan${matchInstallInfo.id}">${matchInstallInfo.assetNper}</span>
															<input type='text'  id='deadlineInput${matchInstallInfo.id}' value='${matchInstallInfo.assetNper}' style='margin: 0px;visibility: hidden !important;'  />
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<fmt:formatDate value="${matchInstallInfo.updateTime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /> 
														</td>
														<td style="border: 1px solid black;width: 300px;" align="center">
															<a href="javascript:void(0)"  onclick="deadlineEdit('${matchInstallInfo.id}')">编辑</a>
															<a href="javascript:void(0)"  onclick="deadlineSave('${matchInstallInfo.id}')" >保存</a>
														</td>
													</tr>
													  </c:when>
													</c:choose>
                                                   </c:forEach>
										          </table>
       			 						 </div>
       			 				</div>
       			 				
       			 				   <div class="Mtable2" style="margin-top: 20px;">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
       			 						 		    <tr style="height: 40px;">
       			 						 	       	  <td colspan="2"><font size="5">资金是否可匹配其他出借产品</font></td>
       			 						 	      	  <td align="center"><a href="${ctx}/match/matchInstallLog/3">修改记录</a></td>
       			 						 	      	 </tr>
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>匹配出借产品类型</strong></td>
										                  <td style="border: 1px solid black;width: 300px;" align="center"><strong>状态</strong></td>
										                  <td style="border: 1px solid black;" align="center"><strong>操作</strong></td>
										               </tr>
										          <c:forEach items="${matchInstallInfoList}" var="matchInstallInfo" varStatus="status" >
										          		<c:choose>
															  <c:when test="${matchInstallInfo.type=='3'}">
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