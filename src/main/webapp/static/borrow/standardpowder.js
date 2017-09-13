//精确计算乘法
var mul = function(a, b) {
    var c = 0,
        d = a.toString(),
        e = b.toString();
    try {
        c += d.split(".")[1].length;
    } catch (f) {}
    try {
        c += e.split(".")[1].length;
    } catch (f) {}
    return Number(d.replace(".", "")) * Number(e.replace(".", "")) / Math.pow(10, c);
};

//精确计算加法
var floatAdd = function(a, b){
    var c, d, e;
    try {
        c = a.toString().split(".")[1].length;
    } catch (f) {
        c = 0;
    }
    try {
        d = b.toString().split(".")[1].length;
    } catch (f) {
        d = 0;
    }
    return e = Math.pow(10, Math.max(c, d)), (mul(a, e) + mul(b, e)) / e;
};

//动态计算借款总额
var sumTotalAccount = function(selectBorrowsObj){
	var totalAccount = 0;
	for(var key in selectBorrowsObj){
		totalAccount = floatAdd(totalAccount, selectBorrowsObj[key]);
	}
	$('#totalAccount').html(totalAccount);
};

//显示下一步按钮
var isDisabledNextStep = function(selectBorrowsObj){
	if($.isEmptyObject(selectBorrowsObj)){
		$("#nextStep").css("background", "#eeeeee");
		$("#nextStep").prop("disabled", true);
	}else{
		$("#nextStep").css("background", "#f39800");
		$("#nextStep").prop("disabled", false);
	}
};

//转selectBorrowsValue值为json对象
var getObj = function(){
	var selectBorrowsJson = $('#selectBorrowsValue').val();
	var selectBorrowsObj = jQuery.parseJSON(selectBorrowsJson);
	if(null == selectBorrowsObj){
		selectBorrowsObj = {};
	}
	return selectBorrowsObj;
};

//转json对象为selectBorrowsValue的值
var setObj = function(selectBorrowsObj){
	//动态计算借款总额
	sumTotalAccount(selectBorrowsObj);
	isDisabledNextStep(selectBorrowsObj);
	
	var selectBorrowsJson = $.toJSON(selectBorrowsObj);
	$('#selectBorrowsValue').val(selectBorrowsJson);
};

//搜索清空selectBorrowsValue的值
var searchToPage = function(page){
	setObj({});
	topage(1);
};

//复选框复现以及计算借款总额
var showCheckboxAndTotalAccount = function(){
	var selectAllChecked = true;
	var selectBorrowsObj = getObj();
	//复选框复现
	$.each($("input[name='selectBorrow']"), function(index,element){
		for(var key in selectBorrowsObj){
			if(key == $(element).val()){
				$(element).prop('checked', true);
			}
		}
		
		if(!$(element).prop('checked')){
			selectAllChecked = false;
		}
	});
	//全选框复现
	if(selectAllChecked){
		$('#selectAll').prop('checked', true);
	}else{
		$('#selectAll').prop('checked', false);
	}
	//动态计算借款总额
	sumTotalAccount(selectBorrowsObj);
	isDisabledNextStep(selectBorrowsObj);
};

//======================发布页===============================
//动态计算借款总额
var sumTotalAccountRelease = function(){
	var totalAccount = 0;
	$.each($("input[name='borrowAmountHidden']"), function(index,element){
		totalAccount = floatAdd(totalAccount, $(element).val());
	});
	$('#totalAccountRelease').html(totalAccount);
};

Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
	if (this[i] == val) return i;
	}
	return -1;
};
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
	this.splice(index, 1);
	}
	};
//删除已选债权
var deleteBorrow = function(borrow,borrowId,loannumber){
	var b = confirm("确定删除编号为“"+loannumber+"”的债权/借款");
	if(!b){
		return false;
	}
	if($(borrow).parents('tr').siblings().length > 1){
		$(borrow).parents('tr').remove();
		sumTotalAccountRelease();
		$("#"+borrowId).text("");
		$("#d"+borrowId).remove();
	}else{
		alert('当前为最后一条已选债权');
	}
};

//验证募集期
var checkRaiseterm = function(){
	var raiseterm = $('#raiseterm').val();
	raiseterm = raiseterm.replace(/[^\d]/g,'');
	if('0' == raiseterm){
		raiseterm = 1;
	}
	$('#raiseterm').val(raiseterm);
	var raisetermunit = $('#raisetermunit').val();
	if('1' == raisetermunit){//天
		if(raiseterm > 7){
			$('#raiseterm').val('7');
		}
	}else{//小时
		if(raiseterm > 24){
			$('#raiseterm').val('24');
		}
	}
};

//发布标的预览
var releaseView = function(){
	var warnings = $('#warnings').val().trim();//风险提示
	var raiseterm = $('#raiseterm').val();//募集期
	var openBorrowDate = $('#openBorrowDate').val();//发布时间
	var minTender = $('#minTenderSum').val();//起始金额
	var usageofloan_ = $('#usageofloan').val().trim();
	
	var checkflag = true;
	if(!warnings){
//		MsgBox('请输入风险提示');
//        return false;
		checkflag = false;
	}
	if(!usageofloan_){
//		MsgBox('请输入风险提示');
//        return false;
		checkflag = false;
	}

	if(!raiseterm){
//		MsgBox('请输入募集期');
//        return false;
		checkflag = false;
	}
	if(!minTender){
//		MsgBox('请输入募集期');
//        return false;
		checkflag = false;
	}
	if(isNaN(minTender)||minTender*1<0){
//		MsgBox('请输入起始金额');
//        return false;
		checkflag = false;
	}
	if($('#preparation').prop('checked')){
		if(!openBorrowDate){
//			MsgBox('请输入发布时间');
//			return false;
			checkflag = false;
		}
	}
	
	if(!checkflag){
		$("#containboxId,.refreshBg").show();
		return false;
	}
	
	var warningsVal = $('#warnings').val().trim();
	var usageofloanVal = $('#usageofloan').val().trim();
	var fillmarkVal = $('#fillmark').prop('checked')? '是': '否';
	var raisetermVal = $('#raiseterm').val();
	var raisetermunitVal = $('#raisetermunit').val() == '1'? '天': '小时';
	var minTenderSumVal = $('#minTenderSum').val() + '元';
	
	var openBorrowDateVal;
	if($('#immediate').prop('checked')){
		openBorrowDateVal = '立即发布';
	}else{
		openBorrowDateVal = $('#openBorrowDate').val();
	}
	
	$('#warningsView').html(warningsVal);
	$('#usageofloanView').html(usageofloanVal);
	$('#fillmarkView').html(fillmarkVal);
	$('#raisetermView').html(raisetermVal + raisetermunitVal);
	$('#minTenderSumView').html(minTenderSumVal);
	$('#openBorrowDateView').html(openBorrowDateVal);
	
	$("#containbox2Id,.refreshBg").show();
};

//保存编辑
var releaseSave = function(){
	var warnings = $('#warnings').val().trim();//风险提示
	var raiseterm = $('#raiseterm').val();//募集期
	var openBorrowDate = $('#openBorrowDate').val();//发布时间
	var minTender = $('#minTenderSum').val();//起始金额
	var usageofloan_ = $('#usageofloan').val().trim();
	
	var checkflag = true;
	if(!warnings){
		checkflag = false;
	}
	if(!usageofloan_){
		checkflag = false;
	}
	if(!raiseterm){
		checkflag = false;
	}
	if(!minTender){
		checkflag = false;
	}
	if(isNaN(minTender)||minTender*1<0){
		checkflag = false;
	}
	if($('#preparation').prop('checked')){
		if(!openBorrowDate){
			checkflag = false;
		}
	}
	
	if(!checkflag){
		$("#containboxId,.refreshBg").show();
		return false;
	}
	
	$('#releaseBorrowForm').attr('action', ctx + '/borrow/borrowList/saveBorrowListEdit');
	$('#releaseBorrowForm').trigger('submit');
};




//关闭模态框
var cancelBorrow = function(){
	$(".containbox3,.refreshBg").hide();
};

//发布标的
var releaseBorrow = function(){
	$('#releaseBorrowForm').attr('action', ctx + '/borrow/convert/releaseBorrow');
	$('#releaseBorrowForm').trigger('submit');
};
//发布集合标的
var releaseBorrowList = function(){
	$('#releaseBorrowForm').attr('action', ctx + '/borrow/convertList/releaseBorrow');
	$('#releaseBorrowForm').trigger('submit');
};

var CurentTime = function() {
    var now = new Date();
    var year = now.getFullYear();      
    var month = now.getMonth() + 1;    
    var day = now.getDate();          
    var hh = now.getHours();           
    var mm = now.getMinutes();         
    var clock = year + "-";
    if (month < 10)
        clock += "0";
    clock += month + "-";
    if (day < 10)
        clock += "0";
    clock += day + " ";
    if (hh < 10)
        clock += "0";
    clock += hh + 1 + ":00:00";
    return clock;
};

$(function(){
	$('#selectAll').click(function(){
		var selectBorrowsObj = getObj();
		$.each($("input[name='selectBorrow']"), function(index,element){
			var borrowId = $(element).val();
			var amount = $(element).attr("amount");
			
			if($('#selectAll').prop('checked')){
				$(element).prop('checked', true);
				selectBorrowsObj[borrowId] = amount;
			}else{
				$(element).prop('checked', false);
				delete selectBorrowsObj[borrowId];
			}
		});
		setObj(selectBorrowsObj);
	});
	
	$("input[name='selectBorrow']").click(function(){
		var selectAllChecked = true;
		var selectBorrowsObj = getObj();
		$.each($("input[name='selectBorrow']"), function(index,element){
			var borrowId = $(element).val();
			var amount = $(element).attr("amount");
			
			if($(element).prop("checked")){
				selectBorrowsObj[borrowId] = amount;
			}else{
				selectAllChecked = false;
				delete selectBorrowsObj[borrowId];
			}
		});
		setObj(selectBorrowsObj);
		
		//全选框
		if(selectAllChecked){
			$('#selectAll').prop('checked', true);
		}else{
			$('#selectAll').prop('checked', false);
		}
	});
	
	//复选框复现以及计算借款总额
	showCheckboxAndTotalAccount();
	
	//是否立即发布
	$("input[name='borrowStatus']").click(function(){
		if($('#preparation').prop('checked')){
			$('#preparationDateDiv').show();
			$('#preparationDate').val(null);
		}else{
			$('#preparationDateDiv').hide();
		}
	});
	
});