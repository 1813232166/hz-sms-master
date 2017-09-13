<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产队列管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(function() {
		
		$("#myModal").hide();
		$("#confirmreturn").hide();
		$("#confirmAddWeight").hide();
		$("#batchResetWeight").hide();
		
		$(".weight").click(function(){
			 var capitalCode=$(this).next().val();
			 var id=$(this).next().next().val();
			 var stickTop=$(this).next().next().next().val();
			 if(stickTop=='0'){
				$("#isStickTop0").attr("checked","checked");
			 }else{
				 $("#isStickTop1").attr("checked","checked");
			 }
			$("#ida").val(id);
			$("#dialog1").text("出借编号:"+capitalCode);
			$("#confirmreturn").hide();
			$("#confirmAddWeight").show();
			$("#batchResetWeight").hide();
			$("#myModal").modal('show');
		});
		
		$(".batchResetWeight").click(function(){
			$("#dialog3").text("确实重新计算权重？");
			$("#confirmreturn").hide();
			$("#dialog1").hide();
			$("#dialog2").hide();
			$("#batchResetWeight").show();
			$("#myModal").modal('show');
		});
		
		
		$("#batchResetWeight").click(function(){
			
			$.ajax( {  
	    	    type : "POST",  
	    	    url : ctx+'/match/asset/batchResetWeight',
	    	    data : {
	    	     }, 
	    	    dataType: "json",  
	    	    success : function(data) {
	    	    	if(data.rtnFlag){
						$("#batchResetWeight").hide();
						$("#dialog1").hide();
						$("#dialog2").hide();
						$("#confirmreturn").show();
						$("#dialog3").text("重新计算权重成功");
					}else{
						$("#batchResetWeight").hide();
						$("#dialog1").hide();
						$("#dialog2").hide();
						$("#confirmreturn").show();
						$("#dialog3").text("重新计算权重失败"+data.msg);
					}
	    	    	
	    	    }, 
	    	    
	    	    error :function(){  
	    	        alert("网络连接出错！");  
	    	    }  
		});
			
			
		});
			
			
		$("#confirmAddWeight").click(function(){
			var id=$("#ida").val();
			var customWeight=$("#customWeight").val();
			
			var reg = /^[1-9]\d*$/;
			if(!reg.test(customWeight)){
				alert("请输入大于0的整数");
				return false;
			}
			
			var isStickTop =$('input:radio:checked').val();
			$.post(ctx+'/match/asset/singleResetWeight',{id:id,customWeight:customWeight,stickTop:isStickTop},function(f){
				if(f){
					$("#confirmAddWeight").hide();
					$("#dialog1").hide();
					$("#dialog2").hide();
					$("#confirmreturn").show();
					$("#dialog3").text("修改成功");
				}else{
					$("#confirmAddWeight").hide();
					$("#dialog1").hide();
					$("#dialog2").hide();
					$("#confirmreturn").show();
					$("#dialog3").text("修改失败");
				}
			},"json")
		});
		
		$("#cancel").click(function(){
			$("#myModal").modal('hide');
		});
		
		$("#close1").click(function(){
			$("#myModal").modal('hide');
		});
		
		$("#confirmreturn").click(function(){
			$("#myModal").hide();
			$("#searchForm").submit();
		});
			/*提交查询*/
			$("#btnSubmit").click(function(){
				   var  beginTimes=  $("#beginTimes").val();
				   var endTimes = $("#endTimes").val();
				   var  beginWeight=  $("#beginWeight").val();
				   var  endWeight = $("#endWeight").val();

				   if(beginTimes!=null&&beginTimes!="" && endTimes!=null&&endTimes!="") {
					   
					   if(beginTimes>endTimes){
						    alert("开始时间不能大于结束时间");
// 						    $("#beginTimes").val("");
// 						    $("#endTimes").val("");
						   return false;
					    }
				     }
				   if(beginWeight!=null&&beginWeight!="" && endWeight!=null&&endWeight!="") {
					   var num=/^[0-9]*$/;
					   if(!num.test(beginWeight)){
						   alert("权重请输入数字");
						   return false;
					   }
					   if(!num.test(endWeight)){
						   alert("权重请输入数字");
						   return false;
					   }
					   if(beginWeight>endWeight){
						    alert("权重开始值要小于结束值");
						   return false;
					    }
				     }
				$("#searchForm").attr("action","${ctx}/match/asset/list");
				$("#searchForm").submit();
			});
			
			
			
		});	
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        } 

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/match/asset/">资产队列列表</a></li>
		<shiro:hasPermission name="match:asset:edit"><li><a href="${ctx}/match/asset/form">资产队列添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="asset" action="${ctx}/match/asset/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="ida" type="hidden" value=""/>
		<ul class="ul-form">
			<li>
				标的编号:	<form:input id="borrowcode" path="borrowcode" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
				借款编号:	<form:input id="assetCode" path="assetCode" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[\W]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\W]/g,''))"/>
				借款人姓名:<form:input id="realName" path="realName" htmlEscape="false" maxlength="32" class="input-medium" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"
    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"/>
				身份证号:	<form:input id="idCard" path="idCard" htmlEscape="false" maxlength="18" class="input-medium" onkeyup="value=value.replace(/^[a-zA-Z]+\D*|^\d{0,16}[a-zA-Z]+|[^0-9Xx]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>

			</li>
			<li>
			          产品类型:	<form:select path="productId" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('PRODUCT_ID')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
			          债权类型：<form:select path="originalAssetId" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('ORIGINAL_ASSET_ID')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
			          撮合状态：	<form:select path="status" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('CAPITAL_STATUS')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
				资产来源：	<form:select path="sourceAssetId" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('CAPITAL_SOURCE')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
			</li>

		</ul>
		<ui class="ul-form">
		   <li >
		         资产类别：	<form:select path="assetCategory" class="input-medium">
							<form:option value="" label="请选择.."/>
							<form:options items="${fns:getDictList('ASSET_CATEGORY')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				       </form:select>
		   加入资产队列时间:<input name="beginTimes" readonly="readonly" id="beginTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${asset.beginTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至 
				   <input name="endTimes" readonly="readonly" id="endTimes" type="text"  maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${asset.endTimes}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					
					
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   权重:<input type="text" name="beginWeight" class="input-medium" value="${asset.beginWeight }" id="beginWeight" style="width: 60px" >
			至<input type="text" name="endWeight" class="input-medium" value="${asset.endWeight }" id="endWeight" style="width: 60px">
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			</li>
			<ui class="ul-form">
			  <li>
			   	是否置顶：	<form:select path="stickTop" class="input-medium">
					<form:option value="" label="请选择.."/>
					<form:options items="${fns:getDictList('IS_STICK_TOP')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			 <input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索" style="margin-right: 20px;"/>
			  </li>
			</ui>
		</ui>
        <table style="width: 80%" >
        <tr>
	        <td  style="width: 80px;"  align="right" >原始资产(元):</td><td style="width: 70px;" >${oriAssetMap["oriasset"]} </td>
	        <td  align="right" valign="top">原始待匹配资产(元):</td><td style="width: 70px;" >${origWaitAssetMap["origwaitasset"]}</td>
	        <td  align="right" valign="top">原始部分匹配资产(元):</td><td style="width: 70px;" >${origPortMatcAssetMap["origportmatcasset"]}</td>
	        <td  align="right" valign="top">原始剩余资产(元):</td><td style="width: 70px;" >${origResiAssetMap["origresiasset"]}</td>
	        
        </tr>
        <tr>
	        <td  align="right">转让资产(元):</td><td style="width: 70px;" >${tranAssetMap["tranasset"]}</td>
	        <td  align="right">转让待匹配资产(元):</td><td style="width: 70px;" >${tranWaitMatcAssetMap["tranwaitmatcasset"]}</td>
	        <td  align="right">转让部分匹配资产(元):</td><td style="width: 70px;" >${tranPortMatcAssetMap["tranportmatcasset"]}</td>
	        <td align="right">转让剩余资产(元):</td><td style="width: 70px;" >${tranResiAssetMap["tranresiasset"]}</td>
	        <td align="right">	<a href="javascript:void(0)" class="batchResetWeight">重新计算权重</a><td>
        </tr>
        </table>
	</form:form>
	<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width: 80px;">序号</th>
				<th style="width: 90px;">标的编号</th>
				<th style="width: 90px;">借款编号</th>
				<th style="width: 90px;">资产类别</th>
				<th style="width: 40px;">借款人姓名</th>
				<th style="width: 120px;">身份证号</th>
				<th style="width: 70px;">借款金额(元)</th>
				<th style="width: 120px;">剩余金额（元）</th>
				<th style="width: 50px;">借款期限（月）</th>
				<th style="width: 50px;">借款年化收益（%）</th>
				<th style="width: 50px;">加入资产队列时间</th>
				<th style="width: 50px;">产品类型</th>
				<th style="width: 50px;">债权类型</th>
				<th style="width: 50px;">撮合状态</th>
				<th style="width: 50px;">权重</th>
				<th style="width: 50px;">是否置顶</th>
				<th style="width: 50px;">资产来源</th>
				<th style="width: 50px;">撮合笔数</th>
				<th style="width: 130px;">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="asset" varStatus="status" >
			<tr>
				<td><!-- 序号 -->
				${status.count+page.pageSize*(page.pageNo-1)}
				</td>
				<!-- 标的编号 -->
				<td>
					${asset.borrowcode}
				</td>
				<!-- 借款编号 -->
				<td>
					${asset.assetCode}
				</td>
				<!-- 资产类别-->
				<td>
					${fns:getDictLabel(asset.assetCategory, 'ASSET_CATEGORY', '')}
				</td>
				<!-- 借款人姓名 -->
				<td>
					${asset.realName}
				</td>
				<!-- 身份证号 -->
				<td>
				   ${asset.idCard}
				</td>
				<!-- 借款金额(元) -->
				<td>
					 ${asset.amount}
				</td>
				<!-- 剩余金额（元） -->
				<td>
					 ${asset.residueAmount}
				</td>
				<!-- 借款期限（月） -->
				<td>
					 ${asset.nper}
				</td>
				<!-- 借款年化收益（%） -->
				<td>
				     <c:if test="${!empty asset.rate}">
				      ${asset.rate}%	
				     </c:if>
				</td>
				<!-- 加入资产队列时间 -->
		        <td>
		        <fmt:formatDate value="${asset.entryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 产品类型 -->
				<td>
				     ${fns:getDictLabel(asset.productId, 'PRODUCT_ID', '')}
				</td>
				<!-- 债权类型 -->
				<td>
                      ${fns:getDictLabel(asset.originalAssetId, 'ORIGINAL_ASSET_ID', '')}					
				</td>
				<!-- 撮合状态 -->
				<td>
				      ${fns:getDictLabel(asset.status, 'CAPITAL_STATUS', '')}
				</td>
				<!-- 权重 -->
				<td>
				     ${asset.weight}
				</td>
				<!-- 是否置顶 -->
				<td>
				${fns:getDictLabel(asset.stickTop, 'IS_STICK_TOP', '')}
					 	
				</td>
				<!-- 资产来源 -->
				<td>
				     ${fns:getDictLabel(asset.sourceAssetId, 'CAPITAL_SOURCE', '')}
				</td>
				<!-- 撮合笔数 -->
				<td>
					 ${asset.matchingPenNumber}			
				</td>
				<td>
				<a href="javascript:void(0)" class="weight">权重</a>
				<input type="hidden" value="${asset.assetCode}">
				<input type="hidden" value="${asset.id}">
				<input type="hidden" value="${asset.stickTop}">
				</td>
			</tr>
		 </c:forEach> 
			
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
		<!-- 模态框声明 -->
	<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" style="width: 450px;">
		<!-- 窗口声明 -->
		<div class="modal-dialog" style="margin: 0 auto;">
			<!-- 内容声明 -->
			<div class="modal-content">
				<div class="modal-header">
					
					<button class="close" id="close1"><span>&times;</span></button>
					<h4 class="modal-title">修改权重</h4>
				</div>
				<div class="modal-body"  style="height:80px;">
					<div  style="line-height: 30px;text-align: center;font-size: 18px;">
							<label id="dialog1"></label>
							<div id="dialog2">
							<label>加权重：</label>
							<input id="customWeight" htmlEscape="false" value="1" maxlength="10" class="input-medium" onkeyup="value=value.replace(/[^\d]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" /><br>
							<label>是否置顶：</label><input name="isStickTop" id="isStickTop1" type="radio" value="1"/>是 <input name="isStickTop" id="isStickTop0"  type="radio" value="0"/>否
							</div>
							<p id="dialog3"></p>
					</div>
				</div>
				<div class="modal-footer">
					<p class="text-center">
						<button class="btn btn-primary" id="cancel" style="margin-right: 50px;">取消</button>
						<button class="btn btn-primary" id="confirmreturn" style="margin-left: 50px;">确认</button>
						<button class="btn btn-primary" id="confirmAddWeight" style="margin-left: 50px;">确认</button>
						<button class="btn btn-primary" id="batchResetWeight" style="margin-left: 50px;">确认</button>
					</p>
				</div>
			</div>
		</div>
	</div> 
</body>
</html>