<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>异常放款列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	//异常放款原因
	function findFailelend(failelend){
		 var faile="";
		 $.ajax({
           url : ctx+"/fkuan/userFkuan/finderror",
           type: "POST",
           data: {
               "borrowId": failelend
           },
           dataType: "JSON",
           async: false,
           success: function (data) {
           	faile=data;
           }
		 });
		 return faile;
	}
	function findlend(borrowerId){
		 $("#failelend").empty();
		var failelend=borrowerId;
		var faile=findFailelend(failelend);
		$("#faile").val(faile);
		$("#failelend").append("<strong>"+faile+"</strong>");
		$("#myModal").modal('show');
	}
	//
	function dantiaofk(borrowId,biaoname){
		 $("#content").text("确定要对【"+biaoname+"】进行放款");
		 $("#myModal2").modal('show');
		 $("#singleConfirm").show();
		 $("#allConfirm").hide();
		 $("#noConfirm").hide();
		 
	}
	
	
		$(document).ready(function() {
			$("#myModal2").hide();
			$("#myModal").hide();
			 var borrowId ="";
			 var biaoname="";
			 var idsSubids="";
			<!--导出异常的列表 -->
			$("#errorExceptor").click(function(){
				
				var sub= $("#searchForm").attr("action","${ctx}/fkuan/userFkuan/errorExceptor");
				sub.submit();
				$("#searchForm").attr("action","${ctx}/fkuan/userFkuan/errorFkuanList");
				
			});
			
			//单条数据放款
			$("#errorFk").click(function(){
				
				borrowId=$(this).next().val();
				biaoname=$(this).next().next().val();
				//alert(borrowId+"   "+biaoname);
				 $("#content").text("确定要对【"+biaoname+"】进行放款");
				 $("#myModal2").modal('show');
				 $("#singleConfirm").show();
				 
			});
			//单条数据确认按钮
			$("#singleConfirm").click(function(){
				//alert(borrowId);
				id=$("#errorFk").next().val();
				$.ajax({
					
					url:"${ctx}/fkuan/userFkuan/fkOperation",
					data:{borrowId:id},
					type:"POST",
					dataType:"JSON",
					success:function(data){
						if(data =='1'){
							 alert("放款成功");
							 location.href="${ctx}/fkuan/userFkuan/noFkuanList";
						 }else{
							 alert("放款失败");
						 }
					},
					error:function(data){
						
					}
				}); 
			});
			//批量进行放款判断
			$("#batchDealWith").click(function(){
				
				if($("input[name='check']:checked").length<=0){
					
					 $("#myModal2").modal('show');
					 $("#content").text("请选择要放款的数据");
					 $("#singleConfirm").hide();
					 $("#allConfirm").hide();
					 $("#noConfirm").show();
				}else{
					
					var ids="";
					//ssumm批量放款金额
					var ssumm=0;
					//ssumm批量放款金额的累加
					$("input[name='check']:checked").each(function(){
						
						ids+=$(this).val()+",";
						var money=$(this).next().val();
						//去除金额中的,
						money=money.replace(',','');
						//先将金额*100,去除小数,再转换为数字，进行累加
						ssumm+=Number(money)*100;

					});
					 var newaccountMoney=""+ssumm;
					  var length=newaccountMoney.length;
					  //再对结果进行字符串截取，保留2位小数
					  var result=newaccountMoney.substring(0,length-2)+"."+newaccountMoney.substring(length-2);
					/*  $("#content").text("确定要对【"+idsSubids+"】进行放款"); */
					 $("#content").text("确定批量放款吗?放款金额"+result+"元");
					 $("#singleConfirm").hide();
					 $("#noConfirm").hide();
					 $("#allConfirm").show();
					 $("#myModal2").modal('show');
				}
				
				
			});
			
			//异常的原因
			function findFailelend(){
				 var failelend="";
				 $.ajax({
	                url : ctx+"/fkuan/userFkuan/finderror",
	                type: "POST",
	                data: {
	                    "borrowId": failelend
	                },
	                dataType: "JSON",
	                async: false,
	                success: function (data) {
	                	failelend=data;
	                }
				 });
				 return failelend;
			}
			 $("#errorReason").click(function(){
				 $("#failelend").empty();
				var failelend=$(this).next().val();
				var faile=findFailelend(failelend);
				$("#faile").val(faile);
				$("#failelend").append("<strong>"+faile+"</strong>");
 				$("#myModal").modal('show');
			});
			
			
/*             上面 的都是对方法的 操作 下面是模态框上的确认按钮触发的事件                                        		*/
 
			 //异常的原因确认按钮隐藏
			$("#errorResonConfirm").click(function(){
				$("#myModal").modal('hide');
				
			});
			//批量未选择数据隐藏
			$("#noConfirm").click(function(){
				$("#myModal2").modal('hide');
			});
			//批量进行异常放款
			$("#allConfirm").click(function(){
			 	//alert(idsSubids);
				$.ajax({
					url:"${ctx}/fkuan/userFkuan/fkOperation",
					data:{borrowId:idsSubids},
					type:"POST",
					dataType:"JSON",
					success:function(data){
						if(f=="1"){
							  alert("批量放款成功");
							  location.href="${ctx}/fkuan/userFkuan/noFkuanList";
						  }else{
							  
							  alert("批量放款失败");
						  }
					},
					error:function(data){
						
					}
				}); 
			})
			
			
			
		});//页面加载函数的结尾
		
		
		//分页
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;p
        } 
		 
    
	 //全选
	 function checkAll(obj){
		 
		 if(obj.checked){
			 
			 $("input[name='check']").attr("checked",true);
		 }else{
			 
			 $("input[name='check']").attr("checked",false);
		 }
		 
	 }
	 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/fkuan/userFkuan/">异常放款列表</a></li>
	</ul>
	<form id="searchForm"  action="${ctx}/fkuan/userFkuan/errorFkuanList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>放款时间：</label>
				<input name="time"  value="${map.time}" maxlength="200" type="text" class="input-medium Wdate required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				<input name="time2" value="${map.time2}" maxlength="200" type="text"  class="input-medium Wdate required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>标的编号：</label>
				<input name="borrowerNumber" value="${map.borrowerNumber}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li><label>标的名称：</label>
				<input name="biaoname" value="${map.biaoname}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
		</ul>
		<ul class="ul-form">
		   <li><label>借款人：</label>
				<input name="borrowerName" value="${map.borrowerName}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<input name="borrowerPhone" value="${map.borrowerPhone}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<!-- 导出excel-->
	<div style="float:right;padding-right:20px;">
		<form id=""   method="post" action="" >
        	<input type="button"  value="批量放款" size="30" id="batchDealWith" />
        	<input type="button"  value="导出excel" size="30" id="errorExceptor" />
        	<input type="file" id="f" onchange="this.form.submit()" name="files" style="position:absolute; filter:alpha(opacity=0); opacity:0; width:50px; " size="1" />
		</form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" onclick="checkAll(this)"/>全选</th>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款编号</th>
				<th>借款人</th>
				<th>借款人手机号</th>
				<th>借款金额(元)</th>
				<th>出借年化利率</th>
				<th>出借期限</th>
				<th>还款方式</th>
				<th>放款时间</th>
				<th>操作人</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${errorList}" var="fkuan">
			<tr>
			   <td>
			      <input type="checkbox" name="check" value="${fkuan.borrowerId}"/>
			      <input type="hidden" value="${fkuan.cash}"/>
			   </td>
				<td>
					${fkuan.biaoid}
				</td>
				<td>
				  <a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${fkuan.borrowerId}">${fkuan.biaoname}</a>
				</td>
				<td>
				<a href="${ctx}/borrow/borrowApply/toFKBorrowDetail?id=${fkuan.borrowerId}">${fkuan.borrowerNumber}</a>
				</td>
				<td>
					${fkuan.borrowerName}
				</td>
				<td>${fn:substring(fkuan.borrowerPhone,0,3)}****${fn:substring(fkuan.borrowerPhone,7,11)}
				</td>
				<td>
					${fkuan.cash}
				</td>
				<td>
					${fkuan.reate}%
				</td>
				<td>
					${fkuan.dedaline}
				</td>
				<td>
					${fkuan.type}
				</td>
				<td>${fkuan.lendingTime}
					<%-- <fmt:formatDate value="${fkuan.lendingTime}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
				</td>
				
	<%-- 			<td>
					${message}
				</td> --%>
				
					<td>
					<!--操作人  -->
					${fkuan.operatorUser}
				  </td>
				
				<td colspan="2">
				<!-- <a href="#" id="errorReason">异常原因</a> -->
					<a href="javascript:void(0)" onclick="findlend('${fkuan.borrowerId}')">异常原因</a>
					<input type="hidden" value="${fkuan.borrowerId}"/>
					<%-- <input type="hidden" value="${fkuan.failelend}"/> --%>
					<!-- <a href="#" id="errorFk">放款</a> -->
					<a href="javascript:void(0)" id="errorFk" onclick="dantiaofk('${fkuan.borrowerId}','${fkuan.biaoname}')">放款</a>
					<input type="hidden" value="${fkuan.borrowerId}"/>
					<input type="hidden" value="${fkuan.biaoname}"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<span>借款总额<fmt:formatNumber value="${sumCount!=null?sumCount:0.00}" pattern="#,##0.00#"/>元</span>
	<div class="pagination">${page}</div>
	
	<!-- 模态框声明 -->
<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" keyboard="true">
	<!-- 窗口声明 -->
	<div class="modal-dialog">
		<!-- 内容声明 -->
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title">异常原因</h4>
			</div>
			<div class="modal-body" style="height:80px;">
				<div class="container-fluid">
					<div style="line-height: 80px;text-align: center;font-size: 18px;" id="failelend">
					    <!-- <strong>未知的原因 </strong> -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">返回</button>
				<button class="btn btn-primary" data-dismiss="modal" id="errorResonConfirm">确认</button>
			</div>
		</div>
	</div>
</div>


<!-- 模态框声明 -->
<div class="modal fade" id="myModal2" tabindex="-1" data-backdrop="static" keyboard="true" style="width:450px">
	<!-- 窗口声明 -->
	<div class="modal-dialog">
		<!-- 内容声明 -->
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title">友好提示</h4>
			</div>
			<div class="modal-body"  style="height:80px;">
				<div class="container-fluid">
					<div style="line-height: 80px;text-align: center;font-size: 18px;">
					     <p id="content"></p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">返回</button>
				<button class="btn btn-primary" data-dismiss="modal" id="singleConfirm">确认</button>  <!-- 单条数据进行放款 -->
				<button class="btn btn-primary" data-dismiss="modal" id="noConfirm">确认</button>     <!-- 确认是否有数据进行批量放款 -->
				<button class="btn btn-primary" data-dismiss="modal" id="allConfirm">确认</button>     <!-- 批量放款 -->
			</div>
		</div>
	</div>
</div>

</body>
</html>