<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
	<div></div>
	<div id="modalWithText" class="containbox3 NewListConfig4Button1Popup1" style="display:none; position: fixed; top: 25%; left: 35%; width: 350px; padding: 20px; z-index: 11; background: #fff;">
		<div class="con-cent">
			<h2 id="titleWithText" class="dalateH2">备注</h2>
			<textarea id="modalText" style="width:100%; height:200px;font-size:18px;" onkeyup="this.value = this.value.substring(0, 200)"></textarea>
			<div class="con overHide mt46">
				<input id="saveWithText" type="button" value="确定" class="Mbtn2Color1 Mbtn2 fl NewListConfig4ButtonClose"> 
				<input id="cancelWithText" type="button" value="取消" class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose">
			</div>
		</div>
	</div>
	<div id="modalWithoutText" class="containbox3 NewListConfig4Button1Popup2" style="display:none; position: fixed; top: 25%; left: 35%; width: 257px; padding: 70px; z-index: 11; background: #fff;">
		<div class="con-cent">
			<h2 class="dalateH2"><span id="titleWithoutText">备注</span></h2>
			<div class="con overHide mt46">
				<input id="saveWithoutText" type="button" value="确定" class="Mbtn2Color1 Mbtn2 fl NewListConfig4ButtonClose"> 
				<input id="cancelWithoutText" type="button" value="取消" class="Mbtn2Color2 Mbtn2 fr NewListConfig4ButtonClose">
			</div>
		</div>
	</div>
	<div class="refreshBg" style="display:none; position: fixed; top: 0; left: 0; opacity: 0.3; background: black; width: 100%; height: 100%;"></div>
