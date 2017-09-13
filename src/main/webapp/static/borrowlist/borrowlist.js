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
var sumTotalAccountRelease = function(){
	var totalAccount = 0;
	$.each($("input[name='borrowAmountHidden']"), function(index,element){
		totalAccount = floatAdd(totalAccount, $(element).val());
	});
	$('#totalAccountRelease').html(totalAccount);
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
	var usageofloan_ = $('#usageofloan').val().trim();//项目介绍
	var raiseterm = $('#raiseterm').val();//募集期
	var minTender = $('#minTenderSum').val();//起始金额
	
	var checkflag = true;
	if(!warnings){// 风险提示
		checkflag = false;
	}
	if(!usageofloan_){// 项目介绍
		checkflag = false;
	}
	if(!raiseterm){// 募集期
		checkflag = false;
	}
	if(isNaN(minTender)||minTender*1<0){
		checkflag = false;
	}
	
	if(!checkflag){
		$("#containboxId,.refreshBg").show();
		return false;
	}
	
	/*var warningsVal = $('#warnings').val().trim();
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
	
	$("#containbox2Id,.refreshBg").show();*/
	$('#releaseBorrowForm').trigger('submit');
};

//关闭模态框
var cancelBorrow = function(){
	$(".containbox3,.refreshBg").hide();
};

var confirmBorrowList = function(){
	$("#containboxId,.refreshBg").show();
};

//发布散标集
var releaseBorrowList = function(){
	$(".containbox3,.refreshBg").hide();
	$('#releaseBorrowForm').trigger('submit');
};

