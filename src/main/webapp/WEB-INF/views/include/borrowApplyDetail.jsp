<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 借款信息 -->
				<h3>借款信息</h3>
				<div class="tableBox">
					<table class="table" cellpadding="0" cellspacing="0" style="min-width: 800px">
						<tr>
							<td style="width:33%">
								<strong>借款类型：</strong>
								<c:forEach items="${dictionaryMap.borrowTypeEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.type == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
							<td style="width:33%"><strong>借款用途：</strong>${borrowDetail.usageOfLoanType} ${borrowDetail.usageOfLoan}</td>
							<td></td>
						</tr>
						<tr>
							<td><strong>申请金额：</strong>${borrowDetail.borrowAmountLow }元 --- ${borrowDetail.borrowAmountHigh}元</td>
							<td><strong>申请时长：</strong>${borrowDetail.periods }个月</td>
							<td><strong>借款利率：</strong>${borrowDetail.yearRate}%</td>
						</tr>
						<tr>
							<td><strong>最高月还款：</strong>${borrowDetail.monthPrepaymentAmount }元/月</td>
							<td>
								<strong>是否加急：</strong> 
								<c:forEach items="${dictionaryMap.criticalIdEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.criticalId == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
							<td></td>
						</tr>
					</table>
				</div>
				<!-- 借款信息 -->

				<!-- 个人资料 -->
				<h3>个人资料</h3>
				<div class="tableBox">
					<table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
						<tr>
							<td style="width:50%"><strong>姓名：</strong>${borrowDetail.name}</td>
							<td>
								<strong>性别：</strong>
								<c:forEach items="${dictionaryMap.sexEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.sex == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>
								<strong>最高学历：</strong> 
								<c:forEach items="${dictionaryMap.heducationEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.heducation == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
							<td><strong>出生日期：</strong>${borrowDetail.birthday }</td>
						</tr>
						<tr>
							<td><strong>身份证号码：</strong>${borrowDetail.idCardNo }</td>
							<td><strong>证件有效期：</strong>${borrowDetail.validityofDocumnets}</td>
						</tr>
						<tr>
							<td>
								<strong>婚姻状况：</strong> 
								<c:forEach items="${dictionaryMap.maritalStautsEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.maritalStauts == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
							<td>
								<strong>有无子女：</strong>
								<c:forEach items="${dictionaryMap.hasChildrenEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.hasChildren == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>
								<strong>房产状况：</strong> 
								<c:forEach items="${dictionaryMap.realeStateSituationEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.realeStateSituation == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
							<td>
								<strong>共同居住者：</strong> 
								<c:forEach items="${dictionaryMap.coResidentEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.coResident == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td><strong>个人年收入：</strong>${borrowDetail.annualIncome}元</td>
							<td><strong>信用卡最高额度：</strong>${borrowDetail.highCredit}元</td>
						</tr>
						<tr>
							<td>
								<strong>户籍地址：</strong>
								<c:forEach items="${dictionaryMap.provinceList }" var="province">
									<c:if test="${borrowDetail.registryProvince == province.code }">${province.name }</c:if>
								</c:forEach>
								<c:forEach items="${dictionaryMap.cityList }" var="city">
									<c:if test="${borrowDetail.registryCity == city.code }">${city.name }</c:if>
								</c:forEach>
								<c:forEach items="${dictionaryMap.areaList }" var="area">
									<c:if test="${borrowDetail.registryQu == area.code }">${area.name }</c:if>
								</c:forEach>
							</td>
							<td><strong>户籍详细地址：</strong>${borrowDetail.registryAddress}</td>
						</tr>
						<tr>
							<td><strong>户籍邮政编码：</strong>${borrowDetail.registryCode}</td>
							<td></td>
						</tr>
						<tr>
							<td>
								<strong>现居住地址：</strong>
								<c:forEach items="${dictionaryMap.provinceList }" var="province">
									<c:if test="${borrowDetail.familyProvince == province.code }">${province.name }</c:if>
								</c:forEach>
								<c:forEach items="${dictionaryMap.cityList }" var="city">
									<c:if test="${borrowDetail.familyCity == city.code }">${city.name }</c:if>
								</c:forEach>
								<c:forEach items="${dictionaryMap.areaList }" var="area">
									<c:if test="${borrowDetail.familyQu == area.code }">${area.name }</c:if>
								</c:forEach>
							</td>
							<td><strong>现居住地详细地址：</strong>${borrowDetail.familyAddress }</td>
						</tr>
						<tr>
							<td><strong>现居住地邮编：</strong>${borrowDetail.familyCode}</td>
							<td><strong>现居住地电话号码：</strong>${borrowDetail.familyQuhao}--${borrowDetail.familyTel}</td>
						</tr>
					</table>
				</div>
				<!-- 个人资料 -->

				<!-- 单位信息 -->
				<h3>单位信息</h3>
				<div class="tableBox">
					<table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
						<tr>
							<td style="width:50%"><strong>工作单位全称：</strong>${borrowDetail.companyName}</td>
							<td>
								<strong>单位性质：</strong> 
								<c:forEach items="${dictionaryMap.companyStyleEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.companyStyle == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td><strong>进入单位时间：</strong>${borrowDetail.companyInTime}</td>
							<td><strong>所在部门/处室：</strong>${borrowDetail.companyDepartment}</td>
						</tr>
						<tr>
							<td>
								<strong>担任职务：</strong> 
								<c:forEach items="${dictionaryMap.companyAssumeEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.companyAssume == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
							<td><strong>单位邮政编码：</strong>${borrowDetail.companyCode}</td>
						</tr>
						<tr>
							<td>
								<strong>单位地址：</strong>
								<c:forEach items="${dictionaryMap.provinceList }" var="province">
									<c:if test="${borrowDetail.companyPorvince == province.code }">${province.name }</c:if>
								</c:forEach>
								<c:forEach items="${dictionaryMap.cityList }" var="city">
									<c:if test="${borrowDetail.companyCity == city.code }">${city.name }</c:if>
								</c:forEach>
								<c:forEach items="${dictionaryMap.areaList }" var="area">
									<c:if test="${borrowDetail.companyQu == area.code }">${area.name }</c:if>
								</c:forEach>
							</td>
							<td><strong>单位详细地址：</strong>${borrowDetail.companyAddress}</td>
						</tr>
						<tr>
							<td><strong>单位电话号码：</strong>${borrowDetail.companyQuhao}--${borrowDetail.companyTel}</td>
							<td><strong>单位电话分机号：</strong>${borrowDetail.companySuffix}</td>
						</tr>
					</table>
				</div>
				<!-- 单位信息 -->

				<!-- 联系人信息 -->
				<h3>联系人信息</h3>
				<div class="tableBox">
					<table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
						<c:forEach items="${borrowDetail.list }" var="borrowcontact">
							<tr>
								<td>
									<strong>
										<c:forEach items="${dictionaryMap.contactsTypeEnumMap }" var="enumItem">
											<c:if test="${borrowcontact.type == enumItem.key }">${enumItem.value }联系人关系：</c:if>
										</c:forEach>
									</strong>
									${borrowcontact.relation}
								</td>
								<td><strong>姓名：</strong>${borrowcontact.name}</td>
								<td><strong>手机/电话：</strong><c:if test="${borrowcontact.telAreaCode != null }">${borrowcontact.telAreaCode }--</c:if>${borrowcontact.mobile}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- 联系人信息  -->

				<!-- 公积金贷款资料 -->
				<h3>公积金贷款资料</h3>
				<div class="tableBox">
					<table class="table" cellpadding="0" cellspacing="0" style="min-width: 500px">
						<tr>
							<td style="width:50%"><strong>查询网址：</strong>${borrowDetail.queryNet }</td>
							<td>
								<strong>帐户类型：</strong> 
								<c:forEach items="${dictionaryMap.accountTypeEnumMap }" var="enumItem">
									<c:if test="${borrowDetail.accountType == enumItem.key }">${enumItem.value }</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td><strong>账号：</strong>${borrowDetail.accountNo}</td>
							<td><strong>密码：</strong>${borrowDetail.accountPassword}</td>
						</tr>
					</table>
				</div>
				<!-- 公积金贷款资料 -->