

//点击“筛选”，出现复选框
/*function showCheck(){
	//获取筛选条件
	var bztype = $("#bztype option:selected").val();
	var borrowtype = $("#borrowtype option:selected").val();
	var deadline = $("#deadline option:selected").val();
	var loannumber = $("#loannumber").val();
	var repaymentdate = $("#repaymentdate option:selected").val();
	var payMethod = $("#payMethod option:selected").val();
	var criticalid = $("#bztype option:selected").val();
	var beginBorrowamount = $("#beginBorrowamount").val();
	var endBorrowamount = $("#endBorrowamount").val();
	var beginAnualrate = $("#beginAnualrate").val();
	var endAnualrate = $("#endAnualrate").val();
	var beginLoantime = $("#beginLoantime").val();
	var endLoantime = $("#endLoantime").val();
	//异步请求  局部刷新列表
	$.ajax({
		type:"post",
		url:ctx+"/borrow/tBorrow/showCheck",
		data:{bztype:bztype,borrowtype:borrowtype,deadline:deadline,loannumber:loannumber,repaymentdate:repaymentdate,
			payMethod:payMethod,criticalid:criticalid,beginBorrowamount:beginBorrowamount,endBorrowamount:endBorrowamount,
			beginAnualrate:beginAnualrate,endAnualrate:endAnualrate,beginLoantime:beginLoantime,endLoantime:endLoantime},
		dataType:"json",
		success:function(data){
			var list = data.list;
			$("#contentTable").empty();
			$("#contentTable").append("<thead><tr>" +
					"<th><input type='checkbox' id='qx' onclick='quanxuan()'/>全选</th>" +
					"<th>序号</th><th>借款编号</th><th>借款期限</th><th>借款利率</th><th>借款金额</th>" +
					"<th>还款日</th><th>保障方式</th><th>还款方式</th><th>借款形式</th><th>债权来源</th>" +
					"<th>紧急状态</th><th>批货时间</th>" +
					"</tr></thead><tbody id='tborrowList'></tbody>");
			for(var i in list){
				$("#tborrowList").append("<tr>" +
						"<td><input type='checkbox' value='"+list[i].borrowId+"' name='chk_list'/></td>" +
						"<td>"+i+"</td>" +
						"<td>"+list[i].loannumber+"</td>" +
						"<td>"+list[i].deadline+"</td>" +
						"<td>"+list[i].anualrate+"</td>" +
						"<td>"+list[i].borrowamount+"</td>" +
						"<td>"+list[i].repaymentdate+"</td>" +
						"<td>"+list[i].bztype+"</td>" +
						"<td>"+list[i].payMethod+"</td>" +
						"<td>"+list[i].borrowtype+"</td>" +
						"<td>借款</td>" +
						"<td>"+getDictLabel(list[i].criticalid,'criticalid','')+"</td>" +
						"<td>"+list[i].loantime+"</td>" +
						"</tr>");
			}
			$(".pagination").empty();
			$(".pagination").append(data+
					"<input type='button' class='btn btn-primary' value='下一步' onclick='next()' style='margin-left: 500px;'/>" +
					"<input style='float: right; margin-right: 50px;' id='btnCancel' class='btn' type='button' value='返 回' onclick='history.go(-1)'/>");
			
		}
	});
}

//获取字典标签
function getDictLabel(data, value, defaultValue){
	for (var i=0; i<data.length; i++){
		var row = data[i];
		if (row.value == value){
			return row.label;
		}
	}
	return defaultValue;
}
*/

//全选、反选
function quanxuan(){
	if($("#qx").attr("checked")){
		$(":checkbox").attr("checked",true);
	}else{
		$(":checkbox").attr("checked",false);
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
		$("#qx").prop("checked",true);
	}else{//不全选
		$("#qx").prop("checked",false);
	}
}














//获取选中属性值，跳转下一步
function next(){
	 var arrChk=$("input[name='chk_list']:checked");
	 var borrowIds = "";
	 $(arrChk).each(function(){
		 borrowIds = borrowIds+","+$(this).val();
	 });
	 borrowIds = borrowIds.substring(1);
	 if(null == borrowIds || ""==borrowIds){
		 alert('您尚未选择匹配债权，请选择！')
		 return false;
	 }else{
		window.location.href="../convert/toEdit?borrowIds="+borrowIds;
	 }
}
function submit(){
	$("#ishidden").val("1");
	$("#searchForm").submit();
}
function showCheck(){
	var parents = $(":checkbox").parent();
	var innervalue = parents.attr("style","");
	var b = xuhao();
}
function xuhao(){
	var xuhao = document.getElementById("xuhao");
	var xuhaos = document.getElementsByClassName("xuhaos")
	xuhao.style="display:none";
	for(var i = 0 ; i < xuhaos.length;i++){
		xuhaos[i].style="display:none";
	}
	
	return false;
}
$(document).ready(function() {
	/*var ishidden = "${ishidden}"*/
	if(ishidden == "0"){
		showCheck();
	}
});