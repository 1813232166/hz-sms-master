<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>借款终审列表审核</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/css/sanbiaoList.css" rel="stylesheet" type="text/css">
<link href="${ctxStatic}/css/base.css" rel="stylesheet" type="text/css">
<link href="${ctxStatic}/borrow/provident.css" rel="stylesheet" type="text/css">
<!--这里有放大图片的js  -->
<script type="text/javascript" src="${ctxStatic}/borrow/borrowApply.js"></script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/borrow/borrowApply/reviewTrial">借款终审列表</a></li>
		<li class="active"><a href="${ctx}/borrow/borrowApply/reviewTrialEdit/${borrowDetail.borrowId}">借款终审列表审核</a></li>
	</ul>
	<br/>

	<!--这是第一个div -->
	<div class="tabBOX">
		<!--这是第二个div -->
		<div class="tabscontbox">
			<!--这是第三个div -->
			<div style="display: block;">
				<%@ include file="/WEB-INF/views/include/borrowApplyDetail.jsp"%>

				<!-- 审核信息 start -->
				<div class="infor_bottom">
					<h3>
						审核信息：
					</h3>

					<!-- 图片开始 -->
					<c:forEach items="${borrowDetail.borrowPicsVoList}" var="borrowPicVo">
						<%@ include file="/WEB-INF/views/include/borrowApplyPics.jsp"%>
					</c:forEach>
					<!-- 图片结束 -->
					
					<div class="NewListConfig4Button">
						<a href="javascript:;" class="NewListConfig4Button2" id="returnReviewMsg">打回</a> 
						<a href="javascript:;" class="NewListConfig4Button2" id="updateReviewMsg">通过</a> 
						<a href="javascript:;" class="NewListConfig4Button3" id="closeReviewMsg">关闭</a>
					</div>
					
					<form id="editForm" action="" method="post">
						<input type="hidden" id= "token" name="${map.tokenName}" value="${map.token}"/>
						<input type="hidden" id="tokenName" name="hzcf.token.name" value="${map.tokenName}"/>
					
						<input type="hidden" name="borrowId" value="${borrowDetail.borrowId}" />
						<input type="hidden" id="msgText" name="msgText" value="" />
						<div id="imgInputList"></div>
					</form>
				</div>
				<!-- 审核信息 end -->
			
			</div>
			<!--这是第三个div -->
		</div>
		<!--这是第二个div -->
	</div>
	<!--这是第一个div -->

	<!--弹窗-->
	<%@ include file="/WEB-INF/views/include/borrowApplyModal.jsp"%>

	<!-- 图片放大弹框 start -->
	<div class="inforMask"></div>
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
	<!-- 图片放大弹框 end -->

</body>
</html>