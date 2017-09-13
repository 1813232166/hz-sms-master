<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>平台户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
           
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        /*全选*/
        function checkAll(obj){
            
            if(obj.checked){
                $("input[name='check']").attr('checked',true);
            }else{
                
                $("input[name='check']").attr('checked',false);
            }
        }
        $(function () {
              //设置全选复选框
              $("#list :checkbox").click(function(){
                  allchk();
              });
          }); 
          function allchk(){
              var chknum = $("#list :checkbox").size();//选项总个数
              var chk = 0;
              var borrowIDS="";
              $("#list :checkbox").each(function () {  
                  if($(this).prop("checked")==true){
                      chk++;
                      borrowIDS += ","+$(this).val();
                  }
              });
                  borrowIDS=borrowIDS.substring(1);
                  $("#borrowIds").val(borrowIDS);
              if(chknum==chk){//全选
                  $("#check").prop("checked",true);
              }else{//不全选
                  $("#check").prop("checked",false);
              }
          }

        $(function(){
        	$("#btnSubmit").click(function(){
        		var lowservicecharge = $("#lowservicecharge").val();
                var highservicecharge = $("#highservicecharge").val();
                if(highservicecharge<lowservicecharge){
                	alert("服务费金额-最小金额不得大于最大金额");
                    return false;
                }       
                $("#searchForm").submit();
        	});
        	
        });
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/financialadmis/platformdoor/payin">垫资记录</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="overdue" action="${ctx}/financialadmis/platformdoor/payin" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li style="width: 550px;">已垫付金额：<c:if test="${empty countMount.advancesCount }">0.00</c:if>
            ${countMount.advancesCount }元</li>
            <li style="width: 650px;">已冲抵金额：<c:if test="${empty countMount.offsetCount }">0.00</c:if>
            ${countMount.offsetCount }元</li>
            <li><label>标的编号：</label>
                <input type="text" name="borrowaliasno" id="borrowaliasno" value="${overdue.borrowaliasno }"/>
            </li>
            <li><label>借款编号：</label>
                <input type="text" name="applyId" id="applyId"  value="${overdue.applyId }"/>
            </li>
            <li><label>借款人：</label>
                <input type="text" name="userName" id="userName"  value="${overdue.userName }"/>
            </li>
           <li><label>垫付时间：</label>
                <input name="startAdvancesTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${overdue.startAdvancesTime }"
                   id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
                <input name="endAdvancesTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${overdue.endAdvancesTime }"
                    id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
            </li>
            <li><label>冲抵时间：</label>
                <input name="startOffsetTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${overdue.startOffsetTime }"
                   id="d4323" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4324\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
                <input name="endOffsetTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" value="${overdue.endOffsetTime }"
                    id="d4324" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4323\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
            </li>
            
            <li class="btns">
                <input type="hidden" name="borrowIds" id="borrowIds" />
                <input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
               
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
               <!--  <th><input type="checkbox" id="check" onclick="checkAll(this)"/>全选 </th> -->
                <th>期数</th>  
                <th>标的编号</th>
                <th>借款编号</th>
                <th>借款人</th>
                <th>借款人身份证号</th>
                <th>借款金额（元）</th>
                <th>本期还款总额（元）</th>
                <th>垫付金额（元）</th>
                <th>垫付时间</th>
                <th>冲抵金额（元）</th>
                <th>冲抵时间</th>
            </tr>
        </thead>
        <tbody id="list">
        <c:forEach items="${page.list}" var="overdue" varStatus="tfdAcc">
            <tr>
                 <td>${overdue.period}</td>
                <td>
                   <a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${overdue.borrowId}">${overdue.borrowaliasno}</a>
                </td>
                <td>
                 <a href="${ctx}/borrow/borrowApply/toBorrowDetail?id=${overdue.borrowId}"> ${overdue.applyId}</a>
                </td>
                <td>
                    ${overdue.userName}
                </td>
                <td>
                    ${overdue.idcardno}
                </td>
                <td>
                    ${overdue.borrowamount}
                </td>
                <td>
                    ${overdue.monthlyPaymentOrigin}
                </td>
                <td>
	                <c:choose>
	                    <c:when test="${!empty overdue.advancesSum}">${overdue.advancesSum}</c:when>
	                    <c:otherwise>--</c:otherwise>
	                </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${!empty overdue.advancesTime}">
                            <fmt:formatDate value="${overdue.advancesTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </c:when>
                        <c:otherwise>--</c:otherwise>
                    </c:choose>
                    
                </td>
                 <td>
                     <c:choose>
                        <c:when test="${!empty overdue.offsetSum}"> ${overdue.offsetSum}</c:when>
                        <c:otherwise>--</c:otherwise>
                    </c:choose>
                </td>
                 <td>
                    <c:choose>
                        <c:when test="${!empty overdue.offsetTime}">
                            <fmt:formatDate value="${overdue.offsetTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </c:when>
                        <c:otherwise>--</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>