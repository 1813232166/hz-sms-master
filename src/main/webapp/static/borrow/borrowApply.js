//============左右滑动============
$(function() {
	var slick = function() {
		var flag = 0;
		$('.left').click('on',function() {
			$_that = $(this).siblings('.id_card').children('.slick').children('li').length;
			//alert("左点");
			ulevery = $(this).siblings('.id_card').children('.slick').children('li').outerWidth(true);
			if($_that>4){
				if (flag <= -$_that+4) {
					flag = -$_that+5;
				}
				flag -= 1;
				$(this).siblings('.id_card').children('.slick').css("left", flag * ulevery + 'px');
			}
		})
		$('.right').click('on',function() {
			//alert("右点");
			if($_that>4){
				if (flag == 0) {
					flag = -1;
				}
				flag += 1;
				$(this).siblings('.id_card').children('.slick').css("left", flag * ulevery + 'px');
			}
		})
	}
	slick(); 
}) 

//============图片放大============
//查看
$(function() {
	$('.slick').each(function(i, v) {
		$(v).on('click', "li a img", function() {
			$('.inforMask,.inforWindow').css('display', 'block');
		})
	})
	$('.inforClose a').on('click', function() {
		$('.inforMask,.inforWindow').css('display', 'none');
	})
	//提示信息
	$('.warmPro').mouseover(function() {
		$('.warmPuxing').css('display', 'block');
	})
	$('.warmPro').mouseout(function() {
		$('.warmPuxing').css('display', 'none');
	})
})

function delImgSrc() {
	$("#ziLioaImages").children().remove();
}

// 点击放大 不左右滑动
function fangdaImage(ttt) {
	//alert(ttt.src);
	$("#ziLioaImages").children().remove();
	var imgSrcs = "";
	imgSrcs += '<li><img src="' + ttt.src + '"></li>';
	$("#ziLioaImages").append(imgSrcs);
}

//============图片操作============
//删除
function deletePicture(ele, isExists, bid, picName){
	if(confirm("你确定要删除这一张图片？")){
		if(isExists == 'yes'){
			$.ajax({
				url: ctx+"/borrow/borrowApply/deleteImageByOwner",
				type:"POST",
				dataType:"JSON",
				data:{"picUrl":picName,"borrowId":bid},
				success:function(s){
					if(s=='1'){
//						location.reload();
						$(ele).parent().parent().remove();
					}else{
						alert("删除失败！");
					}
				}
			});
		}else{
			//删除未提交图片值
			$('#' + picName + 'Img').remove();
			$(ele).parent().parent().remove();
		}
	}
};

//上传
function updateImg(ele, picType, imgShowPath, bid){
	//校验
	var imgPath = $(ele).val();
	if (imgPath == "") {
        alert("请选择上传的文件！");
        return false ;
    }
    var isfix = true,fixArr = [ "jpg", "gif", "png", "jpeg"];
    strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
    for ( var fix in fixArr ) {
        if (fixArr[fix].toLowerCase() == strExtension.toLowerCase() ) {
            isfix = false;
            break;
        }
    }
    if (isfix) {
        alert("上传的文件类型不正确！");
        return false ;
    }
	//上传
	var formdata = new FormData();
	$.each($(ele)[0].files, function(i, file){
		formdata.append('file', file);
	});
	$(ele).val(null);
	$.ajax({
		url : ctx + '/borrow/borrowApply/uploadImg',
		type : 'POST',
		data : formdata,
		cache : false,
		contentType : false, //不可缺
		processData : false, //不可缺
		success : function(dataResp) {
			var imgHtml = "";
			var imgInputHtml = "";
			for(var i = 0 ; i < dataResp.length ; i++){
				var code = dataResp[i].code;  //0 为上传成功
				var msgs = dataResp[i].msg;   //上传成功
				if(code=='0'){
					var picId = dataResp[i].picurl.split('.')[0];
					imgHtml += '<li><a href="javascript:;"><img class="newPic" src="' + imgShowPath + dataResp[i].picurl + '" onclick="fangdaImage(this)">' + 
							'<input type="button" onclick = "deletePicture(this, \'no\', \'' + bid + '\', \'' + picId + '\')" class="del_element" value="×"/></a></li>';
					imgInputHtml +='<input type="hidden" name="' + picType + 'Img" id="' + picId + 'Img" value = "' + dataResp[i].picurl + '"/>';
				}else{
					alert(msgs);    //异常原因
				}
			}
			$("#" + picType + "_backImgList").append(imgHtml); 
			$("#imgInputList").append(imgInputHtml);
		},
		error: function(data) {
			alert('发生错误');
//			$('#hzwUploading').hide();
//			$("#buttonId").attr("disabled", false); 
		}
	});
};

//下载图片
function downLoadFrontPics(borrowId, picType){
//	location.href=ctx+"/borrow/borrowApply/downloadZipImage?id=" + borrowId;
	location.href = ctx + "/borrow/borrowApply/downLoadFrontPics/" + borrowId + "/" + picType;
}

//============按钮弹框============
$(function(){
	var colseMsg = function(){
		$(".containbox3,.refreshBg").hide();
	};
	
	var saveMsgWithoutText = function(){
		colseMsg();
		$("#msgText").val(modalText);
		$("#editForm").submit(); 
	};
	
	var saveMsg = function(){
		var modalText = $.trim($("#modalText").val());
		if(modalText.length == 0){
			alert('备注不能为空');
			return false;
		}
		colseMsg();
		$("#msgText").val(modalText);
		$("#editForm").submit(); 
	};
	var saveMsgZ = function(){
		var modalText = $.trim($("#modalText").val());
		if(modalText.length == 0){
			alert('备注不能为空');
			return false;
		}
		 $.post(ctx+'/borrow/borrowApply/reviewTrialUpdate',$("#editForm").serialize(),function(data){
			 flag=true;
				if ("undefined" == typeof(data.tokenmsg)) {
					if(data.success){
               		   window.location.href = ctx+"/borrow/borrowApply/reviewTrial";
	               	}else{
	               		alert(data.message);
	               	}
         		} else {
         			alert(data.tokenmsg);
         		}
		  },'json');

	};
	var closeToFirstTrial = function(){
		colseMsg();
		location.href = ctx + "/borrow/borrowApply/firstTrial";
	};
	
	// 初审保存
	var saveFirstMsg = function(){
		$("#editForm").attr("action", ctx + "/borrow/borrowApply/firstTrialSave");
		$("#saveWithoutText").click(saveMsgWithoutText);
		$("#cancelWithoutText").click(colseMsg);
		$("#titleWithoutText").text("确定保存修改？");
		$("#modalWithoutText,.refreshBg").show();
	};
	
	// 初审提交
	var updateFirstMsg = function(){
		// 提交校验提示
		var picTypeListJson = $('#picTypeList').val().replace(/'/g, '"');
		var picTypeList = JSON.parse(picTypeListJson);
		var msg = "";
		$.each(picTypeList, function(i, picType){
			var code = picType.code;
			var value = picType.value;
			if($("#" + code + "_backImgList").children().length == 0){
				msg = "请上传" + value + "！";
				return false;
			}
		});
		if(msg.length > 0){
			alert(msg);
			return false;
		};
		
		$("#editForm").attr("action", ctx + "/borrow/borrowApply/firstTrialUpdate");
		$("#saveWithText").click(saveMsg);
		$("#cancelWithText").click(colseMsg);
		$("#modalWithText,.refreshBg").show();
	};
	
	// 初审打回
	var returnFirstMsg = function(){
		$("#editForm").attr("action", ctx + "/borrow/borrowApply/firstTrialReturn");
		$("#saveWithText").click(saveMsg);
		$("#cancelWithText").click(colseMsg);
		$("#modalWithText,.refreshBg").show();
	};
	
	// 初审关闭
	var closeFirstMsg = function(){
		$("#saveWithoutText").click(closeToFirstTrial);
		$("#cancelWithoutText").click(colseMsg);
		$("#titleWithoutText").text("确定关闭？关闭后修改将不做保存。");
		$("#modalWithoutText,.refreshBg").show();
	};
	
	$('#saveFirstMsg').click(saveFirstMsg);
	$('#updateFirstMsg').click(updateFirstMsg);
	$('#returnFirstMsg').click(returnFirstMsg);
	$('#closeFirstMsg').click(closeFirstMsg);
	
	var closeToReviewTrial = function(){
		location.href = ctx + "/borrow/borrowApply/reviewTrial";
	}
	
	// 终审提交
	var updateReviewMsg = function(){
		$("#editForm").attr("action", ctx + "/borrow/borrowApply/reviewTrialUpdate");
		$("#saveWithText").click(saveMsgZ);
		$("#cancelWithText").click(colseMsg);
		$("#modalWithText,.refreshBg").show();
	};
	
	// 终审打回
	var returnReviewMsg = function(){
		$("#editForm").attr("action", ctx + "/borrow/borrowApply/reviewTrialReturn");
		$("#saveWithText").click(saveMsg);
		$("#cancelWithText").click(colseMsg);
		$("#modalWithText,.refreshBg").show();
	};
	
	// 终审关闭
	var closeReviewMsg = function(){
		$("#saveWithoutText").click(closeToReviewTrial);
		$("#cancelWithoutText").click(colseMsg);
		$("#titleWithoutText").text("确定关闭？关闭后修改将不做保存。");
		$("#modalWithoutText,.refreshBg").show();
	};
	
	$('#updateReviewMsg').click(updateReviewMsg);
	$('#returnReviewMsg').click(returnReviewMsg);
	$('#closeReviewMsg').click(closeReviewMsg);
});

