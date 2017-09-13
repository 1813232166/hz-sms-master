<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款列表详情管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
 	<link href="${ctxStatic}/borrow/provident.css" rel="stylesheet" type="text/css">
 	<!--这里有放大图片的js  -->
	<script type="text/javascript" src="${ctxStatic}/borrow/provident.js"></script>
	<c:set var="project" value="${pageContext.request.contextPath}"/>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".containbox3,.refreshBg").hide();   //弹出框 隐藏
		});
		
		//左右滑动
		 $(function() {
		 	//删除节点
		    $('.infor_bottom').find('.del_element').each(function(i,v){
		        $(v).on('click',function(){
		            $(this).parent().remove();
		        })
		    })
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
		//下载图片
		function downLoad(t){
		   var id=$("#downImageBorrowId").val();
		   location.href=ctx+"/borrow/borrowApply/downloadZipImage?id="+id;
		}
		
		//给图片动态赋值
		function updateType(t){
		    //alert(t);
			fileId = t;
		}
	    
		//删除图片
		 function delPic(id,th,type){
			//alert($("#"+type+"Img"));
			$("#"+type+"Img").remove();
			$(th).parent().remove();
			
		}
		//判断上传图片类型
		function upIdCardFile(ty) {
	      if($("#"+ty).val() ==""){
	    	 alert("请选择上传文件!");
	    	 return false;
	      }
		     
			var imgPath = $('#'+fileId).val();
			//alert(imgPath);
			if (imgPath == "") {
		        alert("请选择上传的文件！");
		        return false ;
		    }
			
			fileId=ty;  //当前的文件类型
			//判断上传文件的后缀名  png、jpg、jpeg、gif
		    var isfix = true,fixArr = [ "jpg", "gif", "png", "jpeg"];
		    strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
		    for ( var fix in fixArr ) {
		        if ( fixArr[fix].toLowerCase() == strExtension.toLowerCase() ) {
		            isfix = false;
		            break;
		        }
		    }
		    if (isfix ) {
		    	
		        alert("上传的文件类型不正确！");
		        return false ;
		    }
		   
			var formdata = new FormData();
			//为FormData对象添加数据
			$.each($('#'+fileId)[0].files, function(i, file) {
				//alert("开始为FormData对象添加数据");
				formdata.append('file', file);
				//alert(i+";FormData:"+formdata);
			});
			
			$.ajax({
						url : '${ctx}/borrow/borrowApply/uploadImg',
						type : 'POST',
						data : formdata,
						cache : false,
						contentType : false, //不可缺
						processData : false, //不可缺
						success : function(dataResp) {
							//alert(dataResp.length);
							var startPic ;
							if(fileId.indexOf("idcard") >= 0){
								startPic = $("#idcardList1 .newPic").length ;//身份证
							}else if(fileId.indexOf("credit") >= 0){
								startPic = $("#creditList1 .newPic").length ;//个人征信
							}else if(fileId.indexOf("workAndIncom") >= 0){
								startPic = $("#workAndIncomList1 .newPic").length ;//工作及收入证明
							}else if(fileId.indexOf("other") >= 0){
								startPic = $("#otherList1 .newPic").length ;//其他证明
							}else if(fileId.indexOf("mobile") >= 0){
								startPic = $("#mobileList1 .newPic").length ;//手机通话记录详单
							}
							//alert(startPic);
							var cont = "";
							for(var i = 0 ,j = startPic; i < dataResp.length ; i++,j++){
								var msg = dataResp[i].code;  //0 为上传成功
								var msgs = dataResp[i].msg;   //上传成功
								if(msg=='0'){
									if(i == 0){
										cont += '<li class="ml_48"><a href="javascript:;"><img class="newPic" src="${IP}'+dataResp[i].picurl+'" onclick="fangdaImage(this)"></a><input type="button" onclick = "delPic(null,this,\''+fileId+j+'\')" class="del_element" value="×"/></li>';
									}else{
										cont += '<li><a href="javascript:;"><img class="newPic" src="${IP}'+dataResp[i].picurl+'" onclick="fangdaImage(this)"></a><input type="button" onclick = "delPic(null,this,\''+fileId+j+'\')" class="del_element" value="×"/></li>';
									}
									
									var hidHtm = '<input type="hidden" name="'+fileId+'Img"  id="'+fileId+j+'Img" value = "'+dataResp[i].picurl+'"/>';
									//alert(hidHtm);
									$("#imgList").append(hidHtm);

								}else{
									alert(msgs);    //异常原因
								}
							}
							$("#"+fileId+"List1").append(cont); 
						},
						error : function(data) {
							alert('发生错误');
							$('#hzwUploading').hide();
							$("#buttonId").attr("disabled", false); 
						}
					});
		}
		//点击 保存和已完成
		function editBorrow(type,borrowId){
		   // alert(borrowId);
		   var idnum = $("#idcardAfter").children().length;
		   var crenum = $("#creditAfter").children().length;
		   var worknum =  $("#workAndIncomAfter").children().length;
		   var othernum = $("#otherAfter").children().length;
		   var mobilenum = $("#mobileAfter").children().length;
		   //第一次进入编辑页面
		   if(idnum==0 && crenum==0 && worknum==0 && othernum==0 && mobilenum==0){
			   
			    if($("input[name='idcardImg']").length<=0){
					  alert("请上传身份证证明！");
					  return false;
				  }
				  
				  if($("input[name='creditImg']").length<=0){
					  alert("请上传个人征信报告！");
					  return false;
				  }
				  if($("input[name='workAndIncomImg']").length<=0){
					  alert("请上传工作以及收入证明！");
					  return false;
				  }
				  if($("input[name='otherImg']").length<=0){
					  alert("请上传其他资料！");
					  return false;
				  }
				  if($("input[name='mobileImg']").length<=0){
					  alert("请上传手机通话记录详单！");
					  return false;
				  }
		   }
		   //第二次进入编辑页面
		   if(idnum!=0 && crenum!=0 && worknum!=0 && othernum!=0 && mobilenum!=0){
			   //后台的存片存在的情况  不进行判断
			    $("#type").val(type);
				$("#borId").val(borrowId);
				$("#editForm").submit(); 
		   }else{
			   //后台的图片 删除了    要进行每项判断
			   if(idnum==0){
				   if($("input[name='idcardImg']").length<=0){
						  alert("请上传身份证证明！");
						  return false;
				   }
			   }
			   if(crenum==0){
				     if($("input[name='creditImg']").length<=0){
						  alert("请上传个人征信报告！");
						  return false;
					  }
			   }
			   if(worknum==0){
				   if($("input[name='workAndIncomImg']").length<=0){
						  alert("请上传工作以及收入证明！");
						  return false;
					}
			   }
			   if(othernum==0){
				   if($("input[name='otherImg']").length<=0){
						  alert("请上传其他资料！");
						  return false;
				    }
			   }
			   if(mobilenum==0){
				   if($("input[name='mobileImg']").length<=0){
						  alert("请上传手机通话记录详单！");
						  return false;
				    }
			   }
			   
			    $("#type").val(type);
				$("#borId").val(borrowId);
				$("#editForm").submit(); 
		   }
		  
		   
		 
			
		}
		
		//关闭按钮
		function closePage(){
			location.href="${ctx}/borrow/borrowApply/borrowList";
		}
		//删除图片
		function deletePictures(url){
			var userId = $("#downImageBorrowId").val();
			if(confirm("你确定要删除这一张图片？")){
				$.ajax({
					url: ctx+"/borrow/borrowApply/deleteImageByOwner",
					type:"POST",
					dataType:"JSON",
					data:{"picUrl":url,"borrowId":userId},
					success:function(s){
						if(s=='1'){
							location.reload();
						}else{
							alert("删除失败！");
						}
					}
				});
			}else{
				location.reload();
			}
			
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/borrow/borrowApply/borrowList">借款列表编辑</a></li>
		<li class="active"><a href="${ctx}/borrow/borrowApply/toBorrowDetailPage?id=${detail.borrowId}">借款列表详情</a></li>
	</ul><br/>
	
	<div class="tabBOX"><!--这是第一个div -->
       			 <div class="tabscontbox"><!--这是第二个div -->
       			 		<div style="display: block;"><!--这是第三个div -->
       			 			    <h3>借款信息</h3>
       			 						 <div class="tableBox"><!-- 这是借款信息 -->
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 800px">
									                 <tr>
									                      <td><strong>借款类型：</strong>
															  <c:if test="${detail.type=='1' }">信用标</c:if>
										                      <c:if test="${detail.type=='2' }">抵押标</c:if>
										                      <c:if test="${detail.type=='3' }">公积金贷款</c:if>
														  </td>
									                      <td><strong>借款用途：</strong>
									                      		${detail.usageOfLoanType}
									                      		${detail.usageOfLoan}
									                      </td>
									                      <td></td>
									                 </tr>
									                 <tr>
									                      <td><strong>申请金额：</strong>
										                     ${detail.MINTENDERSUM }元 ---
										                      	${detail.MAXTENDERSUM}元 
									                      </td>
									                      <td><strong>申请时长：</strong>
									                      		${detail.DEADLINE }个月 
									                      </td>
									                      <td>
									                             <strong>借款利率：</strong>
									                      			${detail.ANUALRATE}%
									                      </td>
									                 </tr>
									                 <tr>
									                      <td><strong>最高月还款：</strong>
									                      	${detail.monthPrepaymentAmount }元/月
									                      
									                      </td>
									                      <td><strong>是否加急：</strong>
									                      		<c:if test="${detail.criticalId =='1'}">是</c:if>
							                  					<c:if test="${detail.criticalId =='2'}">否</c:if>
									                      </td>
									                      <td></td>
									                 </tr>
            									</table>
       			 						 </div><!-- 这是借款信息 -->
       			 				
       			 				<h3>个人资料</h3>
       			 						 <div class="tableBox"><!--这是个人资料  -->
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
									                 <tr>
									                      <td><strong>姓名：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.name}</td>
									                      <td><strong>性别：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                      		${detail.sex=='1'?'男':'女' }
									                      </td>
									                 </tr>
									                 <tr>
									                      <td><strong>最高学历：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                      		<c:if test="${detail.heducation =='10'}">
									                      		        硕士及以上
									                      		</c:if>
									                      		<c:if test="${detail.heducation =='20'}">
									                      		        本科
									                      		</c:if>
									                      		<c:if test="${detail.heducation =='30'}">
									                      		        专科
									                      		</c:if>
									                      		<c:if test="${detail.heducation =='60'}">
									                      		        高中
									                      		</c:if>
									                      		<c:if test="${detail.heducation =='70'}">
									                      		      初中及以下
									                      		</c:if>
									                      </td>
									                      <td><strong>出生日期：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                      		${detail.birthday }
									                      		
									                      </td>
									                 </tr>
									                 <tr>
									                      <td><strong>身份证号码：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                      	${detail.idCardNo }
									                      </td>
									                      <td><strong>证件有效期：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                      		${detail.validityofDocumnets}
									                      		
									                      </td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>婚姻状况：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                 			<c:if test="${detail.maritalStauts =='10'}">
									                 			     已婚
									                 			</c:if>
									                 			<c:if test="${detail.maritalStauts =='20'}">
									                 			     未婚
									                 			</c:if>
									                 			<c:if test="${detail.maritalStauts =='40'}">
									                 			    离异
									                 			</c:if>
									                 			<c:if test="${detail.maritalStauts =='30'}">
									                 			   丧偶
									                 			</c:if>
									                 		</td>
									                 		<td>
									                 			<strong>有无子女：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                 			${detail.hasChildren=='1'?'有':'没有'}
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>房产状况：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                 			<c:if test="${detail.realeStateSituation =='1'}">
									                 			    有房无贷款
									                 			</c:if>
									                 			<c:if test="${detail.realeStateSituation =='2'}">
									                 			  有房有贷款
									                 			</c:if>
									                 			<c:if test="${detail.realeStateSituation =='3'}">
									                 			 其他
									                 			</c:if>
									                 		</td>
									                 		<td>
									                 			<strong>共同居住者：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                 			
									                 			<c:if test="${detail.coResident =='1'}">
									                 			    父母
									                 			</c:if>
									                 			<c:if test="${detail.coResident =='2'}">
									                 			  配偶及子女
									                 			</c:if>
									                 			<c:if test="${detail.coResident =='3'}">
									                 			    朋友
									                 			</c:if>
									                 			<c:if test="${detail.coResident =='4'}">
									                 			    独居
									                 			</c:if>
									                 			<c:if test="${detail.coResident =='5'}">
									                 			  其他
									                 			</c:if>
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>个人年收入：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.annualIncome}
									                 			元
									                 		</td>
									                 		<td>
									                 			<strong>信用卡最高额度：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.highCredit}
									                 			元
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>户籍地址：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                 			${detail.registryProvince}
									                 			${detail.registryCity}
									                 			${detail.registryQu}
									                 		</td>
									                 		<td>
									                 			<strong>户籍详细地址：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.registryAddress}
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>户籍邮政编码：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.registryCode}
									                 		</td>
									                 		<td>
									                 			<strong></strong>
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>现居住地址：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									                 			${detail.familyProvince}
									                 			${detail.familyCity}
									                 			${detail.familyQu}
									                 		</td>
									                 		<td>
									                 			<strong>现居住地详细地址：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.familyAddress }
									                 		</td>
									                 </tr>
									                 <tr>
									                 		<td>
									                 			<strong>现居住地邮编：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.familyCode}
									                 		</td>
									                 		<td>
									                 			<strong>现居住地电话号码：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.familyTel}
									                 		</td>
									                 </tr>
            									</table>
       			 						 </div><!--这是个人资料  -->
       			 				
       			 				<h3>单位信息</h3>
       			 						 <div class="tableBox"><!--这是单位信息  -->
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>工作单位全称：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.companyName}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>单位性质：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
       			 						 		 			<c:if test="${detail.companyStyle =='1'}">
       			 						 		 			     机关及事业单位
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.companyStyle =='2'}">
       			 						 		 			   国营
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.companyStyle =='3'}">
       			 						 		 			     民营
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.companyStyle =='4'}">
       			 						 		 			  三资企业
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.companyStyle =='5'}">
       			 						 		 			    其他
       			 						 		 			</c:if>
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>进入单位时间：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
       			 						 		 				${detail.companyInTime}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>所在部门/处室：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.companyDepartment}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>担任职务：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
       			 						 		 			<c:if test="${detail.companyAssume =='1'}">创始人/负责人</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='2'}">高层管理人员</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='3'}">中层管理人员</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='4'}">一般管理人员</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='5'}">普通员工</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='6'}">临时员工</c:if>
       			 						 		 			<c:if test="${detail.companyAssume =='7'}">其他</c:if>
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>单位邮政编码：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.companyCode}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>单位地址：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
       			 						 		 				${detail.companyPorvince}
       			 						 		 				${detail.companyCity}
       			 						 		 				${detail.companyQu}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>单位详细地址：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.companyAddress}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>单位电话号码：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
       			 						 		 			${detail.companyTel}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>单位电话分机号：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.companySuffix}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	
       			 						 		 </table>
       			 						 </div><!--这是单位信息  -->
       			 				
       			 				<h3>联系人信息</h3>
       			 						 <div class="tableBox"><!--这是联系人信息  -->
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
       			 						 		 	<c:forEach items="${detail.list}" var="borrowcontacts">
       			 						 		 	<tr>
       			 						 		 		<strong>
       			 						 		 				<c:if test="${borrowcontacts.type=='3'}">
       			 						 		 				   <td><strong>家庭联系人关系：</strong>${borrowcontacts.relation}</td>
       			 						 		 				   <td><strong>姓名：</strong>${borrowcontacts.name}</td>
       			 						 		 				   <td><strong>手机/电话：</strong>${borrowcontacts.mobile}</td>
       			 						 		 				</c:if>
       			 						 		 				<c:if test="${borrowcontacts.type=='5'}">
       			 						 		 				   <td><strong>工作联系人关系：</strong>${borrowcontacts.relation}</td>
       			 						 		 				   <td><strong>姓名：</strong>${borrowcontacts.name}</td>
       			 						 		 				   <td><strong>手机/电话：</strong>${borrowcontacts.mobile}</td>
       			 						 		 				</c:if>
       			 						 		 				<c:if test="${borrowcontacts.type=='4'}">
       			 						 		 				   <td><strong>紧急联系人关系：</strong>${borrowcontacts.relation}</td>
       			 						 		 				   <td><strong>姓名：</strong>${borrowcontacts.name}</td>
       			 						 		 				   <td><strong>手机/电话：</strong>${borrowcontacts.mobile}</td>
       			 						 		 				</c:if>
       			 						 		 			</strong>
       			 						 		 	</tr>
       			 						 		 	</c:forEach>
       			 						 		 	
       			 						 		 	
       			 						 		 	
       			 						 		 </table>
       			 						 </div><!--这是联系人信息  -->
       			 				
       			 				<h3>公积金贷款资料</h3>
       			 				
       			 						 <div class="tableBox"><!--这是公积金贷款资料  -->
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
       			 						 		 	
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>查询网址：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.queryNet }
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>帐户类型：&nbsp;&nbsp;&nbsp;&nbsp;</strong>
       			 						 		 			<c:if test="${detail.accountType =='1'}">
       			 						 		 			     身份证
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.accountType =='2'}">
       			 						 		 			    联名卡
       			 						 		 			</c:if>
       			 						 		 			<c:if test="${detail.accountType =='3'}">
       			 						 		 			    用户名
       			 						 		 			</c:if>
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 	<tr>
       			 						 		 		<td>
       			 						 		 			<strong>账号：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.accountNo}
       			 						 		 		</td>
       			 						 		 		<td>
       			 						 		 			<strong>密码：&nbsp;&nbsp;&nbsp;&nbsp;</strong>${detail.accountPassword}
       			 						 		 		</td>
       			 						 		 	</tr>
       			 						 		 </table>
       			 						 </div><!--这是公积金贷款资料  -->
       			 				
       <div class="infor_bottom">
   <h3>审核信息：<span style="color: #da2016">(单张图片不可超过150KB)</span>
        <input type="button" class="btn btn-primary" onclick="downLoad()" value="一键下载" style="margin-left:300px;"/>
   </h3>
                   
                     <div class="infor_pic" style="width:1150px;"><!--身份证明开始  -->
                      <h2>身份证证明</h2>
                       <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul  class="slick"  id="idcardList2">
                                <c:forEach items="${idCardList1}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48 ">
													<a href="javascript:;" class="block"><img src="${IP_FTP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP_FTP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                      <a href="javascript:;" class="slick_right right" class="right"></a>
                      <input type="hidden" value="${detail.borrowId}" id="downImageBorrowId"/>
                      <input type="hidden" value="idcard"  id="idcards"/>
                 </div><!--身份证明结尾  -->
                 <div class="infor_pic" style="width:1150px;"><!--身份证明 后台  -->
                       <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul  class="slick" id="idcardAfter">
                                <c:forEach items="${IdCardList2}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48 ">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                     <a href="javascript:;" class="slick_right right" class="right"></a>
                 </div><!--身份证明 后台结束 -->
                   
                   <div class="infor_operation" style="padding-left: 100px;">
                      	上传：
                       <input type="file" class="selector_file" onclick="updateType('idcard');" name="idcard" multiple="true" id="idcard"/>
                       <input class="message_sub" onclick="upIdCardFile('idcard')" type="button" value="提交"/>
                      <!--  <input type="button" class="message_sub" onclick="downLoad('idcards')" type="button" value="下载" style="float:right"/> -->
                   </div>
                   
                   
                   
			     <div class="infor_pic" style="width:1150px;"><!--身份证明开始  -->
                       <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="idcardList1" class="slick" onclick="addImgSrcNoSlip('idcardList1');">
                           
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                 </div><!--身份证明结尾  -->
                 
                 
                 
                  <div class="infor_pic" style="width:1150px;"><!-- 个人征信报告 -->
                  			 <h2>个人征信报告</h2>
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul  class="slick" id="creditList2">
                                <c:forEach items="${creditList1}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP_FTP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP_FTP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                            <input type="hidden" value="credit" id="creditt"/>
             </div><!-- 个人征信报告 -->	
                 
                   <div class="infor_pic" style="width:1150px;"><!-- 个人征信报告 后台 -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul  class="slick" id="creditAfter">
                                <c:forEach items="${CreditList2}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
             </div><!-- 个人征信报告  后台-->	
				 <div class="infor_operation" style="padding-left: 100px;">
	                        	 上传：  
                          <input type="file" class="selector_file" onclick="updateType('credit');" name="credit"  multiple="true" id="credit"/>
                          <input class="message_sub" type="button" value="提交" onclick="upIdCardFile('credit')"/>
                         <!--  <input type="button" class="message_sub" onclick="downLoad('creditt')" type="button" value="下载" style="float:right"/> -->
	              </div>
                      <div class="infor_pic" style="width:1150px;"><!-- 个人征信报告 -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="creditList1" class="slick" onclick="addImgSrc('creditList1');" >
                               
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
             </div><!-- 个人征信报告 -->	
             
              
             <div class="infor_pic" style="width:1150px;"><!-- 工作及收入证明 -->
             				 <h2>工作及收入证明</h2>
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul class="slick" id="workAndIncomList2">
                                <c:forEach items="${workAndincomeList1}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP_FTP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP_FTP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                             <input type="hidden" value="workAndIncom" id="workAndIncome"/>
                   </div><!-- 工作及收入证明 -->				
								
             			 <div class="infor_pic" style="width:1150px;"><!-- 工作及收入证明 后台-->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul class="slick"  id="workAndIncomAfter">
                                <c:forEach items="${WorkAndincomeList2}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                   </div><!-- 工作及收入证明 后台 -->			
				<div class="infor_operation" style="padding-left: 100px;">
                           	上传：
                            <input type="file" class="selector_file" onclick="updateType('workAndIncom');" name="workAndIncom" multiple="true" id="workAndIncom"/>
                            <input class="message_sub" type="submit" value="提交" onclick="upIdCardFile('workAndIncom')"/>
                           <!--  <input type="button" class="message_sub" onclick="downLoad('workAndIncome')" type="button" value="下载" style="float:right;"/> -->
                </div>
                        <div class="infor_pic" style="width:1150px;"><!-- 工作及收入证明 -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="workAndIncomList1" class="slick" onclick="addImgSrc('workAndIncomList1');">
                             
                                    
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                   </div><!-- 工作及收入证明-->				
								
<%-- 				 
              <div class="infor_pic" style="width:1150px;"><!--其他证明  -->
              			 <h2>其他证明</h2>
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul  class="slick"  id="otherList2">
                                <c:forEach items="${otherList1}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
												<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                             <input type="hidden" value="other" id="others"/>
                      </div><!--其他证明  -->	        
                      
                       <div class="infor_pic" style="width:1150px;"><!--其他证明 后台 -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul  class="slick"  id="otherAfter">
                                <c:forEach items="${OtherList2}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
												<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                      </div><!--其他证明  后台-->	        
                      
                      
			 <div class="infor_operation" style="padding-left: 100px;">
                       	上传：
                        <input type="file" class="selector_file" onclick="updateType('other');" name="other" multiple="true" id="other"/>
                        <input class="message_sub" type="submit" value="提交" onclick="upIdCardFile('other')" />
                        <!-- <input type="button" class="message_sub" onclick="downLoad('others')" type="button" value="下载" style="float:right;"/> -->
             </div>
                       <div class="infor_pic" style="width:1150px;"><!--其他证明  -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="otherList1" class="slick" onclick="addImgSrc('otherList1');">
                               
                                   
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                      </div><!--其他证明  -->	
                      
              
              <div class="infor_pic" style="width:1150px;"><!-- 手机通话记录详单 -->
             				 <h2>手机通话记录详单</h2>
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul class="slick" id="mobileList2">
                                <c:forEach items="${mobileList1}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                   </div><!-- 手机通话记录详单 -->				
								
             			 <div class="infor_pic" style="width:1150px;"><!-- 手机通话记录详单 后台-->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul class="slick"  id="mobileAfter">
                                <c:forEach items="${MobileList2}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${IP}${p.picurl}" onclick="fangdaImage(this)" alt=""/><input type="button" onclick = "deletePictures('${p.picurl}')" class="del_element" value="×"/></a>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                   </div><!-- 手机通话记录详单 后台 -->			
				<div class="infor_operation" style="padding-left: 100px;">
                           	上传：
                            <input type="file" class="selector_file" onclick="updateType('mobile');" name="mobile" multiple="true" id="mobile"/>
                            <input class="message_sub" type="button" value="提交" onclick="upIdCardFile('mobile')"/>
                           <!--  <input type="button" class="message_sub" onclick="downLoad('workAndIncome')" type="button" value="下载" style="float:right;"/> -->
                </div>
                        <div class="infor_pic" style="width:1150px;"><!-- 工作及收入证明 -->
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card" style="width:890px;">
                                <ul id="mobileList1" class="slick" onclick="addImgSrc('mobileList1');">
                             
                                    
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                   </div><!-- 手机通话记录详单-->
 --%>              		
								
					    <div class="NewListConfig4Button">
                            <a href="javascript:;" class="NewListConfig4Button1">保存</a>
                            <a href="javascript:;" class="NewListConfig4Button2">已完成</a>
                            <a href="javascript:;" class="NewListConfig4Button3">关闭</a>
                        </div>	
								
	<form id="editForm" action="${ctx}/borrow/borrowApply/updateBorrowStatus" method="post">
          	<input type="hidden" name="borrowId" value="${tBorrow.borrowId}"/>
          	<input type="hidden" id="type" name="type" value=""/>
          	<input type="hidden" id="borId" name="borId" value=""/>
          		<div id="imgList">
				
			    </div>
    </form>	
			
							
							
								
						      </div><!--这是审核资料    左右滑动的效果-->
       			 		</div><!--这是第三个div -->
       			  </div><!--这是第二个div -->
       		</div><!--这是第一个div -->
       		
       		
			
			
		<!--弹窗-->
            <div></div>
            <div  class="containbox3 NewListConfig4Button1Popup1" style="position:fixed; top:25%;left:35%; width: 257px;padding:70px;  display: block;z-index: 11;background: #fff;">
                <div class="con-cent">
                    <h2 class="dalateH2">确定保存的修改？</h2>
                    <div class="con overHide mt46">
                        <input type="button" value="保存"  onclick="editBorrow('1','${detail.borrowId}');"  class="Mbtn2Color1 Mbtn2 fl NewListConfig4ButtonClose" >
                        <input type="button" value="取消" class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                    </div>
                </div>
            </div>
            <div class="containbox3 NewListConfig4Button1Popup2" style="position:fixed; top:25%;left:35%; width: 257px;padding:70px;  display: block;z-index: 11;background: #fff;">
                <div class="con-cent">
                    <h2 class="dalateH2">确定提交的修改？</h2>
                    <div class="con overHide mt46">
                        <input type="button" value="提交" onclick="editBorrow('2','${detail.borrowId}');"  class="Mbtn2Color1 Mbtn2 fl NewListConfig4ButtonClose" >
                        <input type="button" value="取消"  class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                    </div>
                </div>
            </div>
            <div class="containbox3 NewListConfig4Button1Popup3" style="position:fixed; top:25%;left:35%; width: 257px;padding:70px;  display: block;z-index: 11;background: #fff;">
                <div class="con-cent">
                    <h2 class="dalateH2">确定关闭？关闭后修改将不做保存。</h2>
                    <div class="con overHide mt46">
                        <input type="button" value="关闭"  class="Mbtn2Color1 Mbtn2 fl NewListConfig4ButtonClose" onclick="closePage()">
                        <input type="button" value="取消" class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                    </div>
                </div>
            </div>
<div class="refreshBg" style="position: fixed;top:0;left:0;opacity:0.3; background: black;width: 100%;height: 100%;"></div>
	<script type="text/javascript">
 		$(function(){
                $(".NewListConfig4Button1").click(function(){
                    $(".NewListConfig4Button1Popup1,.refreshBg").show();
                })
                $(".NewListConfig4Button2").click(function(){
                    $(".NewListConfig4Button1Popup2,.refreshBg").show();
                })
                $(".NewListConfig4Button3").click(function(){
                    $(".NewListConfig4Button1Popup3,.refreshBg").show();
                })
                $(".NewListConfig4ButtonClose").click(function(){
                    $(".containbox3,.refreshBg").hide();
                });
 		});
   </script>
						 				
	<div class="inforMask"></div>
	<!--图片弹窗部分 start-->
	<div class="inforWindow">
		<div class="contentImg">
			<!-- <a class="arrow left" href="javascript:void(0)"> <span></span></a>
			<a class="arrow right" href="javascript:void(0)"> <span></span></a> -->
			<div class="card">
				<ul class="flashImg" id="ziLioaImages"></ul>
			</div>
		</div>
		<div class="inforClose">
			<a href="javascript:;" onclick="delImgSrc();">×</a>
		</div>
	</div>
	<!--图片弹窗部分 end-->		
	
</body>
</html>