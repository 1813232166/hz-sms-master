<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<html>
<head>
	<title>标的列表保存成功管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		
		$(document).ready(function() {
			$("#newSubmit").click(function(){
				location.href = ctx+"/borrow/tBorrow/newBorrow";
			});
			 $(".NewListConfig4ButtonClose").click(function(){
                 $(".containbox3,.refreshBg").hide();
             });
			 $("#exportSubmit").click(function(){
				 top.$.jBox.confirm("确认要导出所筛选数据吗？","系统提示",function(v,h,f){
						if(v=="ok"){
							$("#searchForm").attr("action","${ctx}/borrow/tBorrow/exportborrowInfo");
							$("#searchForm").submit();
						}
					},{buttonsFocus:1});
					top.$('.jbox-body .jbox-icon').css('top','55px');
			 })
		});
		function submits(){
			$("#searchForm").attr("action","${ctx}/borrow/tBorrow/list");
			$("#searchForm").submit();
		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function fillBorrow(borrowId,borrowcode,borrowaliasno) {
			$(".refreshBg").show();
            $.ajax({
			    url: "${ctx}/borrow/BbtBorrow/findInvestAmount",    //请求的url地址
			    dataType: "json",   //返回格式为json
			    async: false, //请求是否异步，默认为异步，这也是ajax重要特性
			    data: { "borrowId": borrowId },    //参数值
			    type: "post",   //请求方式
			    beforeSend: function() {
			        //请求前的处理
			    },
			    success: function(req) {
			        //请求成功时处理
			        if(req.success == "1"){
			        	 if(confirm("普享标"+borrowaliasno+"的补标金额为 :"+req.amount+" 元。确定补标？ ")){
			        		 sureFillBorrow(borrowcode,borrowId,req.amount);
			        	 }
			        }
			    },
			    complete: function() {
			        //请求完成的处理
			    },
			    error: function() {
			        //请求出错处理
			        alert("请求服务器失败！");
			    }
			});
		}
		function sureFillBorrow(borrowcode,borrowId,lendAmt){
			
			var anualrate = $("#anualrate"+borrowcode).val();
			var	deadline = $("#deadline"+borrowcode).val();
			var loannumber = $("#loannumber"+borrowcode).val();
				$.post(
						"${ctx}/borrow/BbtBorrow/fillBorrow",
						{ "borrowId": borrowId,"borrowcode":borrowcode,"lendAmt":lendAmt,"anualrate":anualrate,"deadline":deadline,"loannumber":loannumber },
						function(res){
							$(".refreshBg").hide();
							if(res.result==0){
								alert("补标失败");
							}else{
								alert("补标成功");	
								location.reload();
							}
						}
				);
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/tBorrow/">标的列表</a></li>
		<shiro:hasPermission name="borrow:tBorrow:edit"></shiro:hasPermission><li><%-- <a href="${ctx}/borrow/tBorrow/form">标的列表保存成功添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="tBorrow" action="${ctx}/borrow/tBorrow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>还款方式：</label>
				<form:select id="payMethod" path="payMethod" class="input-medium">
					<form:option value="" label="全部"/>
					<form:option value="debx" label="等额本息"/>					
					</form:select>
			</li>
			<li><label>借款人：</label>
				<form:input id="userid" path="userid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>发布时间：</label>
				<input name="beginOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrow.beginOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="endOpenborrowdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tBorrow.endOpenborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li style="float: right;margin-right: 50px;" class="btns"><input id="exportSubmit" class="btn btn-primary" type="button" value="导出"/></li>
			<%-- <li><label>借款形式：</label>
				<form:select path="borrowtype" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('borrowType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			
			<li><label>标的编号：</label>
				<form:input id="borrowaliasno" path="borrowaliasno" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>标的名称：</label>
				<form:input id="borrowalias" path="borrowalias" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li style="float: right;margin-right: 50px;" class="btns"><input id="newSubmit" class="btn btn-primary" type="button" value="新建"/></li>
			<li style="float: left;"><label>状态：</label>
				<form:select id="borrowstatus" path="borrowstatus" class="input-medium">
					<form:option value="" label="全部"/>
					<form:option value="3" label="审核未通过 "/>
					<form:option value="4" label="招标中"/>
					<form:option value="7" label="还款中"/>
					<form:option value="8" label="已结清"/>
					<form:option value="9" label="已流标"/>
					<form:option value="10" label="已逾期"/>
					<form:option value="11" label="已满标"/>
					<form:option value="19" label="待审核"/>
					<form:option value="12" label="已撤销"/> 
					<form:option value="21" label="预发布"/>
		
<%-- 					<form:options items="${fns:getDictListValue('BORROWSTATUS','13,14,15,16,17,18,20,0,6')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
 --%>				</form:select>
			</li>
			<li><label>借款编号：</label>
				<form:input id="loannumber" path="loannumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<%-- <li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li> --%>
			<li style="float: right;margin-right: 50px;" class="btns"><input id="btnSubmit" class="btn btn-primary" onclick="submits();" type="button" value="查询"/></li>
			<li class="clearfix"></li>
			<div style="float:right;padding-right:20px;">
				<li>借款数量：${countMap.count }个</li>&nbsp;&nbsp;&nbsp;<li>借款人：${countMap.USERID }人</li>&nbsp;&nbsp;&nbsp;<li>借款总额：<fmt:formatNumber type="number" value="${countMap.BORROWAMOUNT }" /> 元</li>
			</div>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款编号</th>
				<th>借款人</th>
				<th>借款金额（元）</th>
				<th>出借年化利率</th>
				<th>出借期限</th>				
				<th>还款方式</th>
				<th>创建时间</th>
				<th>发布时间</th>
				<th>状态</th>
				<th>备注</th>
				<shiro:hasPermission name="borrow:tBorrow:edit"></shiro:hasPermission><th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tBorrow">
			<tr>
				<td>
					${tBorrow.borrowaliasno}
				</td>
				<td>
					${tBorrow.borrowalias}
				</td>
				<td>
					${tBorrow.loannumber}
				</td>
				<td>
					${tBorrow.userName}
				</td>
				<td><%-- <a href="${ctx}/borrow/tBorrow/form?id=${tBorrow.id}"> --%>
					
					<fmt:formatNumber type="number" value="${tBorrow.borrowamount}" />
				</td>
				<td>
					${tBorrow.anualrate}
				</td>
				<td>
					${tBorrow.deadline}
				</td>
				
				<td>
					${fns:getDictLabel(tBorrow.payMethod, 'pay_method', '')}
				</td>
				<td>
					<fmt:formatDate value="${tBorrow.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${tBorrow.openborrowdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<%-- <c:if test="${'21'==tBorrow.borrowstatus}">${tBorrow.statusMemo}</c:if> --%>
					<c:if test="${'12'==tBorrow.borrowstatus or '9'==tBorrow.borrowstatus}">
                             ${tBorrow.statusMemo} 					
					</c:if>
					<c:if test="${'12'!=tBorrow.borrowstatus && '9'!=tBorrow.borrowstatus}">
                             ${fns:getDictLabel(tBorrow.borrowstatus, 'BORROWSTATUS', '')}			
					</c:if>
					
				</td>
				<td>
					${tBorrow.bzInfo}
					<%-- ${ tBorrow.borrowstatus}${tBorrow.statusMemo} --%>
					
				</td>
				<shiro:hasPermission name="borrow:tBorrow:edit"></shiro:hasPermission><td>
    				<c:if test="${('19'==tBorrow.borrowstatus)}">
    					<a href="${ctx}/borrow/tBorrow/shenheform?id=${tBorrow.borrowId}">审核</a>
    				</c:if>
    				<c:if test="${('3'==tBorrow.borrowstatus)}">
    					<a href="${ctx}/borrow/convert/toEditNew?borrowIds=${tBorrow.borrowcode}">编辑</a> 
    				</c:if>
 
                   <c:if test="${'21'!=tBorrow.borrowstatus}"> 
   				     <c:if test="${tBorrow.borrowstatus=='0' or tBorrow.borrowstatus=='21' or tBorrow.borrowstatus=='3' or tBorrow.borrowstatus=='4' or tBorrow.borrowstatus=='13' or tBorrow.borrowstatus=='14' or tBorrow.borrowstatus=='15' or tBorrow.borrowstatus=='16' or tBorrow.borrowstatus=='17' or tBorrow.borrowstatus=='18' or tBorrow.borrowstatus=='19' or tBorrow.borrowstatus=='20'}">
    				    <a href="${ctx}/borrow/tBorrow/delete?borrowId=${tBorrow.borrowId}&borrowtype=1" onclick="return confirmx('您确定撤销${tBorrow.borrowalias}的募集？', this.href)">撤销</a>
     				 </c:if> 
     				</c:if>
    				<a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${tBorrow.borrowId}">详情</a> 
    							<input type="hidden" name="anualrate${var.borrowcode }" id="anualrate${var.borrowcode }" value="${var.anualrate} "/> 
								<input type="hidden" name="deadline${var.borrowcode }" id="deadline${var.borrowcode }" value="${var.deadline} "/> 
								<input type="hidden" name="loannumber${var.borrowcode }" id="loannumber${var.borrowcode }" value="${var.loannumber} "/> 
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div class="pagination">${page}</div>
	<!--弹窗-->
            <div></div>
            <div  class="containbox3 NewListConfig4Button1Popup1" style="position:fixed; top:25%;left:35%; width: 257px;padding:70px;  display: none;z-index: 11;background: #fff;">
                <div class="con-cent">
                    <h2 class="dalateH2">确定保存的修改？</h2>
                    <div class="con overHide mt46">
                        <input type="button" value="确定"  onclick="sureBorrow('1');"  class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                        <input type="button" value="取消" class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                    </div>
                </div>
            </div>
	<div class="refreshBg" style="position: fixed;top:0;left:0;opacity:0.3; background: black;width: 100%;height: 100%;display: none;"></div>
</body>
</html>