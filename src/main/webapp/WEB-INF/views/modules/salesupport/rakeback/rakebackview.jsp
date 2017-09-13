<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
 <head>
    <title>用户基本信息</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
	<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
    $(document).ready(function() {
		 $('.tabsList ul li').click(function(){
            $(this).addClass('active').siblings().removeClass('active');
            $('.tabscontbox>div:eq('+$(this).index()+')').show().siblings().hide();
        })
	});
    </script>
    <style type="text/css">
    body {
    	font-size: 20px;
	}
    .Mtable2 div{
    	line-height:45px;
    }
    .Mtable2 table tr td {
    	font-size: 20px;
    	border-bottom: none;
	}
	.tabscontbox {
	    padding: 0px 30px 52px 30px;
	  
	}
	.Mtable2{
		border: none;
	}
    .table td{
    	border-top: none;
    	background: #ffffff;
    }
    </style>
 </head>
  <body>
  <ul class="nav nav-tabs">
		<li class="active" style="font-size: 14px;"><a  href="javascript:void(0)">返佣设置</a></li>
	</ul>
       <div class="tabBOX" style="font-size: 18px;">
       			 <div class="tabscontbox">
       			  		
       			 		<div id="p1" style="display: block;">
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px; margin-bottom: 0px;">
									                 <tr>
									                      
									                      <td>*邀请人类型：
									                     
									                    ${fns:getDictLabel(rakeback.type,'type','')}
									                      </td>
									                      </tr>
									                      <tr>
									                      <td style="border-bottom: none;">*邀请人人数：${rakeback.amount}</td>
									                      <tr>
									                    
            									</table>
       			 						 </div>
       			 				</div>
       			 				
       			 				<h4 style="line-height:50px; padding-left: 15px; font-size: 20px;">邀请人级别设置：</h4>
       			 				<div class="Mtable2">
       			 						 <div class="tableBox">
       			 						 		 <table cellpadding="0" cellspacing="0" >
										               <tr >
										                  <td style="border: 1px solid black;width: 300px;"><strong>邀请人级别名称</strong></td>
										                   <c:if test="${rakeback.commonMoney !=''}">
										                  <td style="border: 1px solid black;width: 178px;"><strong>${rakeback.commonMoney}</strong></td>
										                  </c:if>
										                  <c:if test="${rakeback.silverMoney !=''}">
										                  <td style="border: 1px solid black;"><strong>${rakeback.silverMoney}</strong></td>
										               </c:if>
										                <c:if test="${rakeback.goldMoney!=''}">
										                  <td style="border: 1px solid black;"><strong>${rakeback.goldMoney}</strong></td>
										               </c:if>
										               </tr>
										               <tr>
										                  <td style="border: 1px solid black;">累计邀请出借金额（元）：</td>
										                  <td style="border: 1px solid black;">
										                  ${rakeback.commonamount}
										                  </td>
										                  <c:if test="${rakeback.silverMoney!=''}">
										                  <td style="border: 1px solid black;">
										                   ${rakeback.sliveramount}
										                  </td>
										                  </c:if>
										                  <c:if test="${rakeback.goldMoney!=''}">
										                   <td style="border: 1px solid black;">
										                   ${rakeback.goldamount}
										                  </c:if>
										                  </td>
										               </tr>
										               <tr>
										                   <td style="border: 1px solid black;">一级好友返佣利率（%）：</td>
										                   <td style="border: 1px solid black;">
										                   ${rakeback.commonOneRate}
										                   </td>
										                   <c:if test="${rakeback.silverMoney!=''}">
										                  <td style="border: 1px solid black;">
										                  ${rakeback.silverOneRate}
										                  </td>
										                  </c:if>
										                     <c:if test="${rakeback.goldMoney!=''}">
										                   <td style="border: 1px solid black;">
										                   ${rakeback.goldOneRate}
										                  </c:if>
										               </tr>
										               <tr>
										                  <td style="border: 1px solid black;">二级好友返佣利率（%）：</td>
										                  <td style="border: 1px solid black;">
										                  	   ${rakeback.commonTwoRate}
										                  </td>
										                 <c:if test="${rakeback.silverMoney!=''}">
										                  <td style="border: 1px solid black;">
										                   ${rakeback.silverTwoRate}
										                  </td>
										                  </c:if>
										                  <c:if test="${rakeback.goldMoney!=''}">
										                   <td style="border: 1px solid black;">
										                    ${rakeback.goldTwoRate}
										                   </td>
										                  </c:if>
										               </tr>
										          </table>
       			 						 </div>
       			 						
       			 				</div>
       			 				 <p style="color: red;line-height: 3; padding-left: 30px; font-size: 20px;" >返佣利率为非折标利息</p>
       			 				 <div class="Mtable2">
       			 						<div style="padding-left: 30px;">
       			 							<div><span>
												  *返佣方式：
										             ${fns:getDictLabel(rakeback.refferStatus,'refferStatus','')}
										 	</span>
										  </div>   
										        
										         <div>
										         <span>
										*结算方式：
										 ${fns:getDictLabel(rakeback.jsStatus,'jsStatus','')}
										 </span>
										 </div>
										 
										<div>
										  <span></span>*创建时间：<fmt:formatDate value='${rakeback.createDate}' pattern="yyyy-MM-dd HH:mm" /></span>
										
										</div>
										
										  <div>
										<span></span>*启用时间：<fmt:formatDate value='${rakeback.beginTime}' pattern="yyyy-MM-dd HH:mm" /></span>
										  </div>
										  <div>
										<span>*最近一次修改时间：<fmt:formatDate value='${rakeback.updateDate}' pattern="yyyy-MM-dd HH:mm" /></span>
										</div>
       			 							
       			 						</div>
       			 						
       			 				</div>
       			 				<p>
       			 					<button style="width: 110px;height: 43px;font-size: 20px;position: relative;left: 50%;margin-left: -60px;margin-top: 30px;" onclick="history.go(-1)" class="btn btn-primary">返回</button>
       			 				</p>
       			 		</div><!-- 这是含有 style="display: block;的div -->
       			 		
       			 		
       			 </div>
       
       </div>
  </body>
</html>
