<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
 <head>
    <title>返佣新建</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
	 <style>

.form-actions {
    position: relative;
    left: 50%;
    margin-left: -40px;
    padding: 19px 20px 20px;
    margin-top: 20px;
   /*  margin-bottom: 20px; */
    background-color: #f5f5f5;
    border-top: none; 
}
	</style> 
    <script type="text/javascript">
    $(document).ready(function() {
		 $('.tabsList ul li').click(function(){
            $(this).addClass('active').siblings().removeClass('active');
            $('.tabscontbox>div:eq('+$(this).index()+')').show().siblings().hide();
        })
    })
  function docheck(){
        	var silverMoney= $("#silverMoney").val();
        	var sliveramount= $("#sliveramount").val();
        	var silverOneRate= $("#silverOneRate").val();
        	var silverTwoRate= $("#silverTwoRate").val();
			var goldMoney= $("#goldMoney").val();
			var goldamount= $("#goldamount").val();
        	var goldOneRate= $("#goldOneRate").val();
        	var goldTwoRate= $("#goldTwoRate").val();
        	var commonMoney= $("#commonMoney").val();
			var commonamount= $("#commonamount").val();
			var commonOneRate= $("#commonOneRate").val();
        	var commonTwoRate= $("#commonTwoRate").val();
        	
        	var leixing=$("#leixing").val();
    		var type=$("#type").val();
    		var all=leixing.split(",");
    		for (var i = 0; i < all.length; i++) {
    			if (all[i] == type) {
    				alert("邀请人类型重复");
    				return false;
    			}
    		}
			 
        	if(commonOneRate!=null && commonOneRate!=""){
				   if(isNaN(commonOneRate)){
					   alert("一级好友返佣利率必须为数字")
					   return false;
				   }
			
			   }
        	if(commonTwoRate!=null && commonTwoRate!=""){
				   if(isNaN(commonTwoRate)){
					   alert("二级好友返佣利率必须为数字")
					   return false;
				   }
			
			   }
        	  if(commonamount!=""||commonOneRate!=""||commonTwoRate!=""){
				 if(commonMoney==null || commonMoney==""){
				 alert("请填写邀请人级别名称");
				 return false;
				  }
			 } 
			if(silverMoney!=null && silverMoney!="")
				{
				   if(sliveramount==null ||sliveramount==""){
					   alert("银牌推荐人累计邀请出借金额（元）不能为空");
					   return false;
				   }else{
					   if(isNaN(sliveramount)){
						   alert("银牌推荐人出借金额必须为数字")
						   return false;
					   }else{
						   if(sliveramount<=commonamount){
							   alert("银牌推荐人累计邀请出借金额（元）大于普通推荐人累计邀请出借金额")
							   return false;
						   }
					   }
				   }
				   if(silverOneRate!=null && silverOneRate!=""){
					   if(isNaN(silverOneRate)){
						   alert("银牌一级好友返佣利率必须为数字")
						   return false;
					   }
				
				   }
				   if(silverTwoRate!=null && silverTwoRate!=""){
					   if(isNaN(silverTwoRate)){
						   alert("银牌二级好友返佣利率必须为数字")
						   return false;
					   }
				
				   }
				   
			 }else{
				 if(sliveramount!=""||silverOneRate!=""||silverTwoRate!=""){
					 alert("请填写银牌邀请人级别名称");
					 return false;
				 }
			 }
			if(goldMoney!=null && goldMoney!="")
			{
			   if(goldamount==null ||goldamount==""){
				   alert("金牌推荐人累计邀请出借金额（元）不能为空");
				   return false;
			   }else{
				   if(isNaN(goldamount)){
					   alert("金牌推荐人出借金额必须为数字")
					   return false;
				   }else{
					   if(goldamount<=sliveramount){
						   alert("金牌推荐人累计邀请出借金额（元）大于银牌推荐人累计邀请出借金额")
						   return false;
					   }
				   }
			   }
			   if(goldOneRate!=null && goldOneRate!=""){
				   if(isNaN(goldOneRate)){
					   alert("金牌一级好友返佣利率必须为数字")
					   return false;
				   }
			
			   }
			   if(goldTwoRate!=null && goldTwoRate!=""){
				   if(isNaN(goldTwoRate)){
					   alert("金牌二级好友返佣利率必须为数字")
					   return false;
				   }
			
			   }
			   
		 }else{
			 if(goldamount!=""||goldOneRate!=""||goldTwoRate!=""){
				 alert("请填写金牌邀请人级别名称");
				 return false;
			 }
		 } 
			
		 }
    </script>
 </head>
  <body>
  <ul class="nav nav-tabs">
		<li class="active"><a  href="javascript:void(0)">返佣设置</a></li>
	</ul>
  <form id="searchForm"  action="${ctx}/rakeback/rakebackMessage/save" method="post" onsubmit="return docheck()" class="breadcrumb form-search"  >
       <div class="tabBOX">
       			 <div class="tabscontbox">
       			  	<input type="hidden" id="leixing" value="${leixing}" >	
       			 		<div id="p1" style="display: block;">
       			 				<div class="Mtable2" style="margin-top: 0px;">
       			 						 <div class="tableBox">
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
									                 <tr>
									                      
									                      <td>*邀请人类型：
									                       
									                     <c:choose>
							       <c:when test="${rakeback.type =='1' or rakeback.type =='2'}">
							           ${fns:getDictLabel(rakeback.type,'type','')}
							       </c:when>
							       <c:otherwise>
							       	  <select id="type" name="type" >
																<option   value="1" >理财师</option>
																<option    value="2" >推荐人</option>
														  </select>
							       </c:otherwise>
        				    </c:choose>
									                      </td>
									                 </tr>
            									</table>
       			 						 </div>
       			 				</div>
       			 				
       			 				<h4>邀请人级别设置：</h4>
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;"><strong>邀请人级别名称</strong></td>
										                   <%-- <c:if test="${rakeback.commonMoney !=''}"> --%>
										                   
										                  <td style="border: 1px solid black;width: 300px;">
										                  <strong><input type="text"   name="commonMoney" class="text" id="commonMoney"  value='${rakeback.commonMoney}'> 
										                  </strong>
										                  </td>
										                  <td style="border: 1px solid black;">
										                  <strong><input type="text"  name="silverMoney" class="text" id="silverMoney" value='${rakeback.silverMoney}'  > 
										                  </strong>
										                  </td>
										                  <td style="border: 1px solid black;">
										                  <strong><input type="text"  name="goldMoney" class="text" id="goldMoney" value='${rakeback.goldMoney}'  > 
										                  </strong>
										                  </td>
										                  <%-- </c:if> --%>
										                 <%--  <c:if test="${rakeback.silverMoney !=''}">
										                  <td style="border: 1px solid black;"><strong>${rakeback.silverMoney}</strong></td>
										               </c:if>
										                <c:if test="${rakeback.goldMoney!=''}">
										                  <td style="border: 1px solid black;"><strong>${rakeback.goldMoney}</strong></td>
										               </c:if> --%>
										               </tr>
										               <tr>
										                  <td style="border: 1px solid black;">累计邀请出借金额（元）：</td>
										                  <td style="border: 1px solid black;">
										                   <input type="text" readonly name="commonamount" class="text" id="commonamount" value='0'  > 
										                  </td>
										                  <td style="border: 1px solid black;">
										                  <input type="text"  name="sliveramount" class="text" id="sliveramount" value='${rakeback.sliveramount}'  > 
										                  </td>
										                  
										                 
										                   <td style="border: 1px solid black;">
										                    <input type="text"  name="goldamount" class="text" id="goldamount" value='${rakeback.goldamount}'  > 
										                
										                  </td>
										               </tr>
										               <tr>
										                   <td style="border: 1px solid black;">一级好友返佣利率（%）：</td>
										                   <td style="border: 1px solid black;">
										                   <input type="text"  name="commonOneRate" class="text" id="commonOneRate" value='${rakeback.commonOneRate}'  > 
										                   </td>
										                   
										                  <td style="border: 1px solid black;">
										                  <input type="text"  name="silverOneRate" class="text" id="silverOneRate" value='${rakeback.silverOneRate}'  > 
										                  </td>
										        
										                   <td style="border: 1px solid black;">
										                   <input type="text"  name="goldOneRate" class="text" id="goldOneRate" value='${rakeback.goldOneRate}'  > 
										                   </td>
								
										               </tr>
										               <tr>
										                  <td style="border: 1px solid black;">二级好友返佣利率（%）：</td>
										                  <td style="border: 1px solid black;">
										                  <input type="text"  name="commonTwoRate" class="text" id="commonTwoRate" value='${rakeback.commonTwoRate}'  > 
										                  </td>
										                 
										                  <td style="border: 1px solid black;">
										                 <input type="text"  name="silverTwoRate" class="text" id="silverTwoRate" value='${rakeback.silverTwoRate}'  > 
										                  </td>
										                
										                   <td style="border: 1px solid black;">
										                     <input type="text"  name="goldTwoRate" class="text" id="goldTwoRate" value='${rakeback.goldTwoRate}'  > 
										                   </td>
										               </tr>
										          </table>
       			 						 </div>
       			 				</div>
       			 				 <td><strong  style="line-height: 10;">*返佣方式：</strong>
									                     <select    id="refferStatus" name="refferStatus" >
																 <option value="0" <c:if  test="${rakeback.refferStatus=='0'}"> selected="selected" </c:if>>一次返佣</option>
														  </select>
									                      </td></br>
									                      <td><strong>*结算方式：</strong>
									                      
									                     <select name="jsStatus" id="jsStatus" name="jsStatus" >
														   <option value="1" <c:if  test="${rakeback.jsStatus=='1'}"> selected="selected" </c:if>>线上返佣</option>
														   <option value="0" <c:if  test="${rakeback.jsStatus=='0'}"> selected="selected" </c:if>>线下返佣</option>
														  </select>
									                      </td>
       			 		</div><!-- 这是含有 style="display: block;的div -->
       			 		
       			 		
       			 </div>
       
       </div>
     <c:if test="${rakeback!=''}">
       <input type="hidden" name="id" id="id" value="${rakeback.id}">
       </c:if>
       <div class="form-actions" >
			<input id="btnSubmit"   class="btn btn-primary" type="submit" value="确定"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="取消" onclick="history.go(-1)"/>
		</div>
       </form>
  </body>
</html>
