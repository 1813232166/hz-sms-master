<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>未放款列表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#myModal2").hide();
			$("#myModal").hide();
			var subids="";
		    /* 放款 */
			var name="";
			var id="";
			$(".fk").click(function(){
				 name=$(this).next().val();
				 id=$(this).next().next().val();
				 $(".modal-title").text("友好提示");
			     $("#content").text("确定要对【"+name+"】进行放款");
			     $("#allconfirm").hide();
				 $("#allconfirm2").hide();
				 $("#confirm").show();
			     $("#myModal2").modal('show');
			});
			/* 确认放款*/
			$("#confirm").click(function(){
				$.ajax({
					 type:"POST",
					 url:"${ctx}/fkuan/userFkuan/fkOperation",
					 data:{borrowId:id},
					 dataType:"JSON",
					 success:function(data){
						 if(data =='1'){
							 alert("放款成功");
							 location.href="${ctx}/fkuan/userFkuan/noFkuanList";
						 }else{
							 alert("放款失败");
						 }
					 }
					 
				 });
			});
				
				/* 批量放款 */
				$("#checkAllFKuan").click(function(){
					   if($("input[name='check']:checked").length<=0){
						  $(".modal-title").text("友好提示");
						  $("#content").text("请选择要放款的数据");
						  $("#confirm").hide();
						  $("#allconfirm2").hide();
						  $("#allconfirm").show();
						  $("#myModal2").modal('show');
						  
					  }else{
						  subids = "";
						  //accountMoney批量放款金额
						  var accountMoney=0;
						  //accountMoney批量放款金额的累加
						  $("input[name='check']:checked").each(function(){
							  subids = subids+","+$(this).val();
							  var money = $(this).next().val();
							  //去除金额中的,
							  money=money.replace(',','');
							//先将金额*100,去除小数,再转换为数字，进行累加
							  accountMoney+=Number(money);
						  })
						  subids=subids.substring(1);
						  var result=""+accountMoney.toFixed(2);
						  
						  $(".modal-title").text("友好提示");
						  $("#confirm").hide();
						  $("#allconfirm").hide();
						  $("#allconfirm2").show();
						  $("#myModal2").modal('show');
						  $("#content").text("确定批量放款吗?放款金额"+result+"元");
						
						  
						  
					   };
				});
				
				
				   /*点击批量放款时时候未选择数据时的确定按钮  */
					$("#allconfirm").click(function(){
						$("#myModal2").modal('hide');
					});
				
					/*点击批量放款时钮  */
				   $("#allconfirm2").click(function(){
					   //console.log(subids);
					   $.post("${ctx}/fkuan/userFkuan/fkOperation",
							  {borrowId:subids},
							  function(f){
								  if(f=="1"){
									  alert("批量放款成功");
									  location.href="${ctx}/fkuan/userFkuan/noFkuanList";
								  }else{
									  
									  alert("批量放款失败");
								  }
								  
							  },"json");
					 
			       });
       });  //页面加载函数结尾
			
			
		
		
		/* 分页 */
		 function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        } 
		 
	
	  /* 拒绝放款 */
	  function reasonBy(){
		  $(".modal-title").text("拒绝放款的理由");
		  $("#myModal").modal('show');
		 
	  }
	  
	  /*全选*/
	  function checkAll(obj){
		  
		  if(obj.checked){
			  $("input[name='check']").attr('checked',true);
		  }else{
			  
			  $("input[name='check']").attr('checked',false);
		  }
	  }
	  $(function () {
			//设置全选复选框
			$("#list :checkbox").click(function(){
				allchk();
			});
		}); 
		function allchk(){
			var chknum = $("#list :checkbox").size();//选项总个数
			var chk = 0;
			$("#list :checkbox").each(function () {  
		        if($(this).prop("checked")==true){
					chk++;
				}
		    });
			if(chknum==chk){//全选
				$("#check").prop("checked",true);
			}else{//不全选
				$("#check").prop("checked",false);
			}
		}

	 
	  /*导出未还款列表  */
	 function exportFkuan(){
		 var exportExcel = $("#searchForm").attr("action","${ctx}/fkuan/userFkuan/exportFkuan");
			exportExcel.submit();
			$("#searchForm").attr("action","${ctx}/fkuan/userFkuan/noFkuanList");
		/*  var time=$("#time").val();
		 var time2=$("#time2").val();
		 var borrowerNumber=$("#borrowerNumber").val();
		 var biaoname=$("#biaoname").val();
		 var borrowerName=$("#borrowerName").val();
		 var borrowerPhone=$("#borrowerPhone").val();
		 location.href= ctx +"/fkuan/userFkuan/exportFkuan?time="+time+"&time2="+time2+"&borrowerNumber="+borrowerNumber+"&biaoname="+biaoname+"&borrowerName"+borrowerName+"borrowerPhone"+borrowerPhone; */
	 }
	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/fkuan/userFkuan/noFkuanList">未放款列表</a></li>
	</ul>
	<form id="searchForm" action="${ctx}/fkuan/userFkuan/noFkuanList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>满标时间：</label>
				<input id="time" name="time"  value="${map.time}" maxlength="200" type="text" class="input-medium Wdate required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>至
				<input id="time2" name="time2" value="${map.time2}"  maxlength="200" type="text"  class="input-medium Wdate required" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>标的编号：</label>
				<input id="borrowerNumber" name="borrowerNumber" value="${map.borrowerNumber}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li><label>标的名称：</label>
				<input id="biaoname" name="biaoname" value="${map.biaoname}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
		</ul>
		<ul class="ul-form">
		   <li><label>借款人：</label>
				<input id="borrowerName" name="borrowerName"   value="${map.borrowerName}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<input id="borrowerPhone" name="borrowerPhone"  value="${map.borrowerPhone}" htmlEscape="true" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form>
	<!-- 导出excel-->
	<div style="float:right;padding-right:20px;">
		<form id=""   method="post" action="" >
        	<input type="button"  value="批量放款" size="30"   id="checkAllFKuan" />
        	<input type="button"  value="导出excel" size="30" onclick="exportFkuan()" />
        	<input type="file" id="f" onchange="this.form.submit()" name="files" style="position:absolute; filter:alpha(opacity=0); opacity:0; width:50px; " size="1" />
		</form>
	</div>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="check" onclick="checkAll(this)"/>全选</th>
				<th>标的编号</th>
				<th>标的名称</th>
				<th>借款编号</th>
				<th>借款人</th>
				<th>借款人手机号</th>
				<th>借款金额(元)</th>
				<th>出借年化利率</th>
				<th>出借期限</th>
				<th>还款方式</th>
				<th>满标时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="list">
		  <c:forEach items="${noList}" var="fkuan">
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
				<td>
					${fn:substring(fkuan.borrowerPhone,0,3)}****${fn:substring(fkuan.borrowerPhone,7,11)}
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
					<%-- <c:if test="${fkuan.type =='debx'}">等额本息</c:if>
					<c:if test="${fkuan.type =='xxhb'}">先息后本</c:if>
					<c:if test="${fkuan.type =='ychbx'}">一次性还本付息</c:if> --%>
				</td>
				<td>${fkuan.fullBorrowDate}
					<%-- <fmt:formatDate value="${fkuan.fullBorrowDate}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
				</td>
				<td colspan="2">
				  <c:if test="${not empty fkuan.status}"> 
				     <c:if test="${fkuan.status == '0' }"> 
				                         放款处理中
				    </c:if>
				  </c:if>
				  <c:if test="${empty fkuan.status}"> 
				     <a href="#"  class="fk">放款</a>
				  </c:if>
					
					<input type="hidden" value="${fkuan.biaoname}"/>
					<input type="hidden" value="${fkuan.borrowerId}"/>
					<!-- <a href="#" onclick="reasonBy()">拒绝放款</a> -->
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<span>借款总额<fmt:formatNumber value="${sumCount!=null?sumCount:0.00}" pattern="#,##0.00#"/>元</span>
	<div class="pagination">${page}</div>
	
	<!-- 模态框声明 -->
<div class="modal fade" id="myModal2" tabindex="-1" data-backdrop="static" keyboard="true" style="width:450px">
	<!-- 窗口声明 -->
	<div class="modal-dialog">
		<!-- 内容声明 -->
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body"  style="height:180px;">
				<div class="container-fluid">
					<div style="line-height: 80px;text-align: center;font-size: 18px;">
					     <p id="content"></p>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">返回</button>
				<button class="btn btn-primary" data-dismiss="modal" id="confirm">确认</button>   <!-- //单条数据放款 -->
				<button class="btn btn-primary" data-dismiss="modal" id="allconfirm">确认</button>   <!-- //无数据放款 -->
				<button class="btn btn-primary" data-dismiss="modal" id="allconfirm2">确认</button>   <!-- //批量数据放款 -->
			</div>
		</div>
	</div>
</div>

	<!-- 模态框声明 -->
<div class="modal fade" id="myModal" tabindex="-1" data-backdrop="static" keyboard="true">
	<!-- 窗口声明 -->
	<div class="modal-dialog">
		<!-- 内容声明 -->
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body" style="height:80px;">
				<div class="container-fluid">
				    
					<div>
					 <textarea  style="width:450px; height:60px;">没有理由就是理由</textarea>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">返回</button>
				<button class="btn btn-primary" data-dismiss="modal">确认</button>
			</div>
		</div>
	</div>
</div>

</body>
</html>