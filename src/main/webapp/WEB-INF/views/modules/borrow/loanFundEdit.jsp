<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款管理</title>
	<meta name="decorator" content="default"/>
<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
<c:set var="project" value="${pageContext.request.contextPath}"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".containbox3,.refreshBg").hide();
		});
		
		$(function(){
			
			 //删除节点
		    $('.infor_bottom').find('.del_element').each(function(i,v){
		        $(v).on('click',function(){
		            $(this).parent().remove();
		        })
		    })
		    var slick = function () {
		        var flag = 0;
//		        var imgWidth = $('.card ul li').width();
		        /*console.log(imgWidth);*/
		        $('.left').click('on',function(){
		            $_that = $(this).siblings('.id_card').children('.slick').children('li').length;
		            console.log( $_that);
		            if(flag == -$_that+3){
		                flag = -$_that+4;
		            }
		            flag-=1;
		            $(this).siblings('.id_card').children('.slick').css("left",flag*(218)+'px');
		        })
		        $('.right').click('on',function(){
		            if(flag == 0){
		                flag = -1;
		            }
		            flag+=1;
		            $(this).siblings('.id_card').children('.slick').css("left",flag*(218)+'px');
		        })
		    }
		    slick();
		})

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function updateType(t){
			fileId = t;
		}
		function delPic(id,th,type){
			if(""!=id||"null" != id || null !=id){
				$.ajax({ 
					url : ctx + '/borrow/loanFund/delPic',
					type : 'POST',
					data : {id : id,},
					dataType : 'json',
					error : function(xmlHttpRequest, status, errorThrown) {
						alert("错误代码：" + status + "\n" + "错误原因：" + errorThrown + "\n"
								+ "错误返回：" + xmlHttpRequest.responseText);
					},
					success : function(data) {
						if (data.count!='0'){
							$(th).parent().remove();
						}
					}
					
				});
			}else{
				$(th).parent().remove();
			}
		} 
		function upIdCardFile() {
			
			var imgPath = $('#'+fileId).val();
			if (imgPath == "") {
		        alert("请选择上传的文件！");
		        return false ;
		    }
			
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
						url : '${ctx}/borrow/loanFund/uploadImg',
						type : 'POST',
						data : formdata,
						cache : false,
						contentType : false, //不可缺
						processData : false, //不可缺
						success : function(dataResp) {
							
							var startPic ;
							if(fileId.indexOf("card") >= 0){
								startPic = $("#idcardList1 .newPic").length ;//身份证
							}else if(fileId.indexOf("redit") >= 0){
								startPic = $("#creditList1 .newPic").length ;//征信报告
							}else if(fileId.indexOf("ork") >= 0){
								startPic = $("#workList1 .newPic").length ;//工作证明
							}else if(fileId.indexOf("ncome") >= 0){
								startPic = $("#incomeList1 .newPic").length ;//收入证明
							}else if(fileId == 'address'){
								startPic = $("#addressList1 .newPic").length ;//住址证明
							}else if(fileId.indexOf("usinessAddress") >= 0){
								startPic = $("#businessAddressList1 .newPic").length ;//经营地址证明
							}else if(fileId.indexOf("ouse") >= 0){
								startPic = $("#houseList1 .newPic").length ;//房产证明
							}
							var cont = "";
							for(var i = 0 ,j = startPic; i < dataResp.length ; i++,j++){
								var msg = dataResp[i].code;
								var msgs = dataResp[i].msg;
								if(msg=='0'){
									if(i == 0){
										cont += '<li class="ml_48"><a href="javascript:;"><img class="newPic" src="${project}'+dataResp[i].picurl+'" ></a><input type="button" onclick = "delPic(null,this,\''+fileId+j+'\')" class="del_element" value="×"/></li>';
									}else{
										cont += '<li><a href="javascript:;"><img class="newPic" src="${project}'+dataResp[i].picurl+'" ></a><input type="button" onclick = "delPic(null,this,\''+fileId+j+'\')" class="del_element" value="×"/></li>';
									}
									
									var hidHtm = '<input type="hidden" name="'+fileId+'Img"  id="'+fileId+j+'Img" value = "'+dataResp[i].picurl+'"/>';
									$("#imgList").append(hidHtm);

								}else{
									alert(msgs);
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
		function editBorrow(type){
			$("#type").val(type);
			$("#editForm").submit();
		}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/borrow/loanFund/">借款编辑</a></li>
	</ul>
						<h2 class="H2 mt26">借款信息</h2>
						<div class="NewListConfig">
							<div class="lL left">
								<p>
									<span>借款编号：</span><span class="right">${tBorrow.loannumber}</span>
								</p>
								<p>
									<span>借款利率：</span><span class="right">${tBorrow.anualrate}</span>
								</p>
								 <p>
                                <span>借款形式：</span>
                                	<span class="right">
                                		<c:if test="${tBorrow.borrowtype=='xy'}">
                                			信用借款
                                		</c:if>
                                		<c:if test="${tBorrow.borrowtype=='fd'}">
                                			房贷借款
                                		</c:if>
                                		<c:if test="${tBorrow.borrowtype=='cd'}">
                                			车贷借款
                                		</c:if>
                                	</span>
                            </p>
								<p>
									<span>实收金额：</span><span class="right">${tBorrow.paidinamount}元</span>
								</p>
								<p>
									<span>借款期限：</span><span class="right">${tBorrow.deadline}个月</span>
								</p>
								<p>
									<span>还款日：</span><span class="right">${tBorrow.repaymentdate}日</span>
								</p>
								<p>
									<span>借款用途：</span><span class="right">${tBorrow.usageofloan}</span>
								</p>

								<p>
									<span>保障方式：</span><span class="right">${tBorrow.bztype}</span>
								</p>



							</div>
							<div class="lL right">
								<p>
									<span class="left">借款标题：</span><span>${tBorrow.borrowtitle}</span>
								</p>
								<p>
									<span class="left">批贷时间：</span><span><fmt:formatDate value="${tBorrow.loantime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</span>
								</p>
								<p>
									<span class="left">借款金额：</span><span>${tBorrow.borrowamount}元</span>
								</p>
								<p>
									<span class="left">服务费：</span><span>${tBorrow.servicecharge}元</span>
								</p>
								<p>
									<span class="left">还款方式：</span><span>${tBorrow.borrowway}</span>
								</p>
								<p>
									<span class="left">紧急状态：</span><span>${tBorrow.criticalid}</span>
								</p>
								<!-- <p>
									<span class="left">借款协议：</span><span><a
										href="javascript:;">借款协议01868903</a> </span>
								</p>
								 -->

							</div>


						</div>
						<!--NewListConfig-->
						<!--左右-->
						<h2 class="H2 mt26">个人信息</h2>
						<div class="NewListConfig">
							<div class="lL left">
								<p>
									<span>信用评级：</span><span class="right">${tBorrow.creditrating}</span>
								</p>
								<p>
									<span>姓名：</span><span class="right">${tBorrow.name}</span>
								</p>
								<p>
									<span>年龄：</span><span class="right">${tBorrow.age}</span>
								</p>
								<p>
									<span>年收入：</span><span class="right">${tBorrow.annualincome}万</span>
								</p>
								<p>
									<span>还款来源：</span><span class="right">${tBorrow.repaysource}</span>
								</p>




							</div>
							<div class="lL right">
								<p>
									<span class="left">身份证号：</span><span>${tBorrow.idcardno}</span>
								</p>
								<p>
									<span class="left">性别：</span><span>${tBorrow.sex}</span>
								</p>
								<p>
									<span class="left">所在城市：</span><span>${tBorrow.city}</span>
								</p>
								<p>
									<span class="left">资产负债情况：</span><span>${tBorrow.assetsliabilities}</span>
								</p>


							</div>


						</div>
						<!--NewListConfig-->
						<!--左右-->
						<h2 class="H2 mt26">还款计划</h2>
						<div class="Mtable2">
							<div class="tableBox">
								<table cellpadding="0" cellspacing="0" style="min-width: 1200px;">
									<tr>
										<th>期数</th>
										<th>本金（元）</th>
										<th>利息（元）</th>
										<th>总额（元）</th>
										<th>还款时间</th>
									</tr>
									<c:choose>
										<c:when test="${not empty billplans.list}">
											<c:forEach items="${billplans.list}" var="var">
												<tr>
													<td>${var.period }</td>
													<td>${var.monthCapital }</td>
													<td>${var.monthInterest }</td>
													<td>
														<fmt:formatNumber value="${var.monthCapital + var.monthInterest}" pattern="#0.00" var="x"></fmt:formatNumber> 
													</td>
													<td><fmt:formatDate value="${var.repayTime }"
															pattern="yyyy-MM-dd" /></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr class="main_info">
												<td colspan="100" align="center">没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>

								</table>
							</div>
							<!--统计-->
							<div class="pagination">${page}</div>
						</div>

						<div class="infor_bottom">
                        <h3>审核信息：<span style="color: #da2016">(单张图片不可超过150KB)</span></h3>
                        <div class="infor_operation">
                            <span class="infor_idcard">身份证</span>
                            <input type="file" class="selector_file" onclick="updateType('idcard');" name="idcard" multiple="true" id="idcard"/>
                            <input class="message_sub" onclick="upIdCardFile()" type="submit" value="提交"/>
                        </div>
                        <div class="infor_pic">
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card">
                                <ul id="idcardList1" class="slick">
                                <c:forEach items="${idCardList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48 ">
													<a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													<input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													 <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												 </li>
											</c:if>
									</c:forEach>
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                        </div>
                        <div class="infor_operation">
                            <span class="infor_idcard">征信报告</span>
                            <input type="file" class="selector_file" onclick="updateType('credit');" name="credit"  multiple="true" id="credit"/>
                            <input class="message_sub" type="submit"
                            	 value="提交" onclick="upIdCardFile()"/>
                        </div>
                        <div class="infor_pic">
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card">
                                <ul id="creditList1" class="slick">
                                <c:forEach items="${creditList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													<input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													 <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												 </li>
											</c:if>
									</c:forEach>
                                    
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                        </div>
                        <div class="infor_operation">
                            <span class="infor_idcard">工作证明</span>
                            <input type="file" class="selector_file" onclick="updateType('work');" name="work" multiple="true" id="work"/>
                            <input class="message_sub" type="submit" value="提交" onclick="upIdCardFile()"/>
                        </div>
                        <div class="infor_pic">
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card">
                                <ul id="workList1" class="slick">
                                <c:forEach items="${workList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
													<a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													<input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													 <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												 </li>
											</c:if>
									</c:forEach>
                                   
                                    
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                        </div>
                        <div class="infor_operation">
                            <span class="infor_idcard">收入证明</span>
                            <input type="file" class="selector_file" onclick="updateType('income');" name="income" multiple="true" id="income"/>
                            <input class="message_sub" type="submit" value="提交" onclick="upIdCardFile()" />
                        </div>
                        <div class="infor_pic">
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card">
                                <ul id="incomeList1" class="slick">
                                <c:forEach items="${incomeList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
												<a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
												<input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
													 <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													 <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												 </li>
											</c:if>
									</c:forEach>
                                   
                                   
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                        </div>
                        <div class="infor_operation">
                            <span class="infor_idcard">住址证明</span>
                            <input type="file" class="selector_file" onclick="updateType('address');" name="address" multiple="true" id="address"/>
                            <input class="message_sub" type="submit" value="提交" onclick="upIdCardFile()"/>
                        </div>
                        <div class="infor_pic">
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card">
                                <ul id="addressList1" class="slick">
                                <c:forEach items="${addressList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												<li class="ml_48">
												<a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
												<input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												</li>
											</c:if>
											<c:if test="${v.index != 0 }">
												 <li>
												 <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
												 <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												 </li>
											</c:if>
									</c:forEach>
                                    
                                    
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                        </div>
                        <div class="infor_operation">
                            <span class="infor_idcard">经营地址证明</span>
                            <input type="file" class="selector_file" onclick="updateType('businessAddress');" name="businessAddress" multiple="true" id="businessAddress"/>
                            <input class="message_sub" type="submit" value="提交" onclick="upIdCardFile()"/>
                        </div>
                        <div class="infor_pic">
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card">
                                <ul id="businessAddressList1" class="slick">
                                <c:forEach items="${businessAddressList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												 <li class="ml_48">
												 <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
												 <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												 </li>
											</c:if>
											<c:if test="${v.index != 0 }">
												  <li>
												  <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
												  <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												  </li>
											</c:if>
									</c:forEach>
                                   
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                        </div>
                        <div class="infor_operation">
                            <span class="infor_idcard">房产证明</span>
                            <input type="file" class="selector_file" onclick="updateType('house');" name="house" multiple="true" id="house"/>
                            <input class="message_sub" type="submit" value="提交" onclick="upIdCardFile()"/>
                        </div>
                        <div class="infor_pic">
                            <a href="javascript:;" class="slick_left left" class="left"></a>
                            <div class="id_card card">
                                <ul id="houseList1" class="slick">
                                <c:forEach items="${houseList}" var="p" varStatus="v">
											<c:if test="${v.index == 0 }">
												 <li class="ml_48">
													 <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													 <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												 </li>
											</c:if>
											<c:if test="${v.index != 0 }">
												   <li>
													   <a href="javascript:;" class="block"><img src="${project}${p.picurl}" alt=""/></a>
													   <input type="button" onclick = "delPic('${p.id}',this,null)" class="del_element" value="×"/>
												   </li>
											</c:if>
									</c:forEach>
                                    
                                   
                                </ul>
                            </div>
                            <a href="javascript:;" class="slick_right right" class="right"></a>
                        </div>
                        <div class="NewListConfig4Button">
                            <a href="javascript:;" class="NewListConfig4Button1">保存</a>
                            <a href="javascript:;" class="NewListConfig4Button2">已完成</a>
                            <a href="javascript:;" class="NewListConfig4Button3">关闭</a>
                        </div>
                    </div>
            <form id="editForm" action="${ctx}/borrow/loanFund/editLoanInfo" method="post">
            	<input type="hidden" name="borrowId" value="${tBorrow.borrowId}"/>
            	<input type="hidden" id="type" name="type" value=""/>
            		<div id="imgList">
						
					</div>
            </form>
            
						
            <!--弹窗-->
            <div></div>
            <div  class="containbox3 NewListConfig4Button1Popup1" style="position:fixed; top:25%;left:35%; width: 257px;padding:70px;  display: block;z-index: 11;background: #fff;">
                <div class="con-cent">
                    <h2 class="dalateH2">确定保存的修改？</h2>
                    <div class="con overHide mt46">
                        <input type="button" value="保存"  onclick="editBorrow('1');"  class="Mbtn2Color1 Mbtn2 fl NewListConfig4ButtonClose" >
                        <input type="button" value="取消" class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                    </div>
                </div>
            </div>
            <div class="containbox3 NewListConfig4Button1Popup2" style="position:fixed; top:25%;left:35%; width: 257px;padding:70px;  display: block;z-index: 11;background: #fff;">
                <div class="con-cent">
                    <h2 class="dalateH2">确定提交的修改？</h2>
                    <div class="con overHide mt46">
                        <input type="button" value="提交" onclick="editBorrow('2');"  class="Mbtn2Color1 Mbtn2 fl NewListConfig4ButtonClose" >
                        <input type="button" value="取消"  class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose" >
                    </div>
                </div>
            </div>
            <div class="containbox3 NewListConfig4Button1Popup3" style="position:fixed; top:25%;left:35%; width: 257px;padding:70px;  display: block;z-index: 11;background: #fff;">
                <div class="con-cent">
                    <h2 class="dalateH2">确定关闭？关闭后修改将不做保存。</h2>
                    <div class="con overHide mt46">
                        <input type="button" value="关闭"  class="Mbtn2Color1 Mbtn2 fl NewListConfig4ButtonClose" >
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
</body>
</html>