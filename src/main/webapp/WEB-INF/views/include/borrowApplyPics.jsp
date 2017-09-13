<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 前台开始 -->
<div class="infor_pic" style="width: 1150px; position:relative;">
	<h2>
		<c:forEach items="${dictionaryMap.picTypeEnumMap }" var="enumItem">
			<c:if test="${borrowPicVo.picType == enumItem.key }">${enumItem.value }</c:if>
		</c:forEach>
	</h2>
	<a href="javascript:;" class="slick_left left" class="left"></a>
	<div class="id_card card" style="width: 890px;">
		<ul class="slick">
			<c:forEach items="${borrowPicVo.frontPicList}" var="borrowPic">
				<li>
					<a href="javascript:;" class="block">
						<img src="${offlineImgShowPath}${borrowPic.picurl}" onclick="fangdaImage(this)" alt="" />
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<a href="javascript:;" class="slick_right right" class="right"></a>
	<c:if test="${showFlag == 'firstTrial' }">
		<input type="button" class="btn btn-primary" onclick="downLoadFrontPics('${borrowDetail.borrowId}', '${borrowPicVo.picType}')" value="下载" style="position: absolute; top:50px; right:-20px;" />
	</c:if>
</div>
<!-- 后台开始 -->
<div class="infor_pic" style="width: 1150px;position:relative;">
	<a href="javascript:;" class="slick_left left" class="left"></a>
	<div class="id_card card" style="width: 890px;">
		<ul class="slick" id="${borrowPicVo.picType}_backImgList">
			<c:forEach items="${borrowPicVo.backPicList}" var="borrowPic">
				<li>
					<a href="javascript:;" class="block">
						<img src="${imgShowPath}${borrowPic.picurl}" onclick="fangdaImage(this)" alt="" />
						<c:if test="${showFlag == 'firstTrial' }">
							<input type="button" onclick="deletePicture(this, 'yes', '${borrowDetail.borrowId}', '${borrowPic.picurl}')" class="del_element" value="×" />
						</c:if>
					</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<a href="javascript:;" class="slick_right right" class="right"></a>
	<c:if test="${showFlag == 'firstTrial' }">
		<input type="file" class="selector_file" onchange="updateImg(this, '${borrowPicVo.picType}', '${imgShowPath}', '${borrowDetail.borrowId}');" name="idcard" multiple="true" style="position: absolute; top:50px; right:-180px;"/> 
	</c:if>
</div>