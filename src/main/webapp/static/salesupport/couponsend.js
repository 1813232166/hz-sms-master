function CouponSend(userMobile,couponGroupId,activityId,url) {
	this.init = function(){ 
		    var pathName = window.location.pathname.substring(1);  
		    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));  
		    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';  
	}; 
	this.showUsedCoupon = function() 
	{ 
		var htmlStr="";
         $.ajax({
                 url: url,
                 type: "POST",
                 data: {
                     "userMobile": userMobile,
                     "couponGroupId":couponGroupId,
                     "activityId":activityId
               },
             dataType: "JSON",
             success: function (result) {
                    $("#couponUseDet").html("");
                        $.each(result, function (i, a) {

                        	var timeStr="无";
                            var effectiveDayStr="无限制";
                            var loanAmountMinStr="无限制";
                            
                            if ('interest'==a.couponTypeId) {
                                couponType="增益券";
                            }else if('fullDown'==a.couponTypeId)
                            	{
                            		couponType="满减券";
                            	}else if('cash'==a.couponTypeId)
                            	{
                            		couponType="现金券";
                            	}else if('cashBack'==a.couponTypeId){
                            		couponType="返现券";
                            	}

                        	if(a.effectiveDays && parseInt(a.effectiveDays)!=0 ){
                        		effectiveDayStr = parseInt(a.effectiveDays)+ "天";
                        	}     
                            if (undefined!=a.beginTime&&(undefined!=a.endTime)&&(''!=a.beginTime) && (''!=a.endTime) ) {
                                timeStr=a.beginTime+"至"+a.endTime;
                            }
                            if(parseFloat(a.loanAmountMin)!=0.00){
                            	loanAmountMinStr=parseFloat(a.loanAmountMin);
							 }
                            htmlStr += "<tr><td>" +(i+1)+ "</td><td>" +couponType+ "</td><td>" +parseFloat(a.faceValue)
                                        + "</td><td>" +effectiveDayStr+ "</td><td>" +timeStr+ "</td><td>" +loanAmountMinStr+ "</td><td>" ;
	                               
                             	if(a.loanTermList=="全部" ){
                                    htmlStr+="无限制";
                                 }else{
                                     htmlStr+=a.loanTermList;
                                 }
                                 if(a.loanChannelList=="全部" ){
                                        htmlStr+= "</td><td>" +"无限制";
                                 }else{
                                     htmlStr+= "</td><td>" +a.loanChannelList;
                                 }
                                 if(!a.useTime){
                                	 htmlStr+= "</td><td> 无"
                                 }else{
                                	 htmlStr+= "</td><td>" +a.useTime;
                                 }
                                 htmlStr+=  "</td></tr>";
                                 
                        });
                    $("#couponUseDet").append(htmlStr);
             }
         });
        $(".refreshBg").show();
        $("#containbox2Id").show();
	}; 
	
	
} //function CouponSend() 