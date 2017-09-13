<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>平台账户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
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
            allchk();
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
        	$("#exportExcel").click(function(){
        		var exportExcel = $("#searchForm").attr("action","${ctx}/financialadmis/platformdoor/exportPlatformDoorList");
        		$("#searchForm").submit();
                $("#searchForm").attr("action","${ctx}/financialadmis/platformdoor/");
        	})
        });
    </script>
</head>
<body>
    <ul class="nav nav-tabs">
        <li class="active"><a href="${ctx}/financialadmis/platformdoor/">服务费列表</a></li>
    </ul>
    <form:form id="searchForm" modelAttribute="platformdoor" action="${ctx}/financialadmis/platformdoor/" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <ul class="ul-form">
            <li style="width: 550px;">服务费金额：<c:if test="${countMount.servicechargecount ==null}">0.00</c:if>${countMount.servicechargecount}元</li>
            <li style="width: 650px;">进账风险保证金：<c:if test="${countMount.riskReservecount ==null}">0.00</c:if>${countMount.riskReservecount}元</li>
            <li></li>
            <%-- <li style="width: 350px;">出账风险保证金：${countMount.outriskReservecount }元</li> --%>
            <li><label>标的编号：</label>
                <input type="text" name="borrowaliasno" id=""  value="${platformdoor.borrowaliasno }" />
            </li>
            <li><label>标的名称：</label>
                <input type="text" name="borrowalias" id=""  value="${platformdoor.borrowalias }" />
            </li>
            <li><label>借款编号：</label>
                <input type="text" name="loannumber" id="loannumber"  value="${platformdoor.loannumber }" />
            </li>
            <li><label>借款人：</label>
                <input type="text" name="userName" id=""  value="${platformdoor.userName }" />
            </li>
            <li><label>服务费金额：</label>
                <input type="text" name="lowservicecharge" value="${platformdoor.lowservicecharge }"  id="lowservicecharge" class="servicecharge1"/>至<input type="text" name="highservicecharge"  value="${platformdoor.highservicecharge }"  class="servicecharge1" id="highservicecharge" />元
            </li>
            <li><label>放款时间：</label>
                <input name="startlendingtime" value='<fmt:formatDate value="${platformdoor.startlendingtime }"  pattern="yyyy-MM-dd HH:mm:ss"/>' type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   id="d4321" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4322\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>至
                <input name="endlendingtime" value='<fmt:formatDate value="${platformdoor.endlendingtime }" pattern="yyyy-MM-dd HH:mm:ss"/>'  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                    id="d4322" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4321\');}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
            </li>
            <li class="btns">
                <input type="hidden" name="borrowIds" id="borrowIds" />
                <input id="btnSubmit" class="btn btn-primary" type="button" value="查询"/>
               <input  class="btn btn-primary" type="button" value="导出" id="exportExcel"/>
            </li>
            <li class="clearfix"></li>
        </ul>
    </form:form>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
            <tr>
                <th><input type="checkbox" id="check" onclick="checkAll(this)"/>全选 </th>
                <th>标的编号</th>
                <th>标的名称</th>
                <th>借款编号</th>
                <th>借款人</th>
                <th>借款人身份证号</th>
                <th>借款金额(元)</th>
                <th>借款利率</th>
                <th>借款期限(月)</th>
                <th>还款方式</th>
                <th>放款时间</th>
                <th>服务费金额(元)</th>
                <th>进账风险保证金</th>
               <!--  <th>出账风险保证金</th> -->
            </tr>
        </thead>
        <tbody id="list">
        <c:forEach items="${page.list}" var="platformdoor" varStatus="tfdAcc">
            <tr>
                <td><input type="checkbox" name="check" class="checkboxAll" value="${platformdoor.borrowId}"/></td>
                 <td>${platformdoor.borrowaliasno}</td>
                <td>
                   <a href="${ctx}/borrow/tBorrow/borrowdetail?borrowId=${platformdoor.borrowId}"> ${platformdoor.borrowalias}</a>
                </td>
                <td>
                 <a href="${ctx}/borrow/borrowApply/toBorrowDetail?id=${platformdoor.borrowId}"> ${platformdoor.loannumber}</a>
                </td>
                <td>
                    ${platformdoor.userName}
                </td>
                <td>
                    ${platformdoor.idcardno}
                </td>
                <td>
                    ${platformdoor.borrowamount}
                </td>
                <td>
                    ${platformdoor.anualrate}
                </td>
                <td>
                    ${platformdoor.ratio}
                </td>
                <td>${platformdoor.payMethod}</td>
                <td>
                    <fmt:formatDate value="${platformdoor.lendingtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>
                    ${platformdoor.servicecharge}
                </td>
                <td>
                    ${platformdoor.riskReserve}
                </td>
                <%-- <td>
                    ${platformdoor.riskReserve}
                </td> --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>