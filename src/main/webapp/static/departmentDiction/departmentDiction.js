$(document).ready(function() {
			changeCity();
		});

//触发城市下拉框
function changeCity(){
	//获取城市父Id
	var city = $("#ccity").val();
	var pid = $("#province option:selected").attr("id");
	$.ajax({
		type:"post",
		url:ctx+"/departmentdiction/departmentDiction/findCity",
		data:{pid:pid},
		dataType:"json",
		success:function(data){
			$("#city").find("option").remove(); 
			$("#city").text("");
			$("#city").append("<option id='' value=''>请选择</option>");
			for(var i=0;i<data.length;i++){
				$("#city").append("<option id='"+data[i].id+"' value='"+data[i].name+"'>"+data[i].name+"</option>");
			}
		}
	});
}

