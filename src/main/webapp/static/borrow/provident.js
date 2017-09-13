	var picCount=0;
	

	//查看
	$(function(){
		$('.slick').each(function(i,v){
	        $(v).on('click',"li a img",function(){
	            $('.inforMask,.inforWindow').css('display','block');
	        })
	    })
	    $('.inforClose a').on('click',function(){
	        $('.inforMask,.inforWindow').css('display','none');
	    })
	
	    //提示信息
	    $('.warmPro').mouseover(function(){
	        $('.warmPuxing').css('display','block');
	    })
	    $('.warmPro').mouseout(function(){
	        $('.warmPuxing').css('display','none');
	    })
	})
		
	function updateType(t) {
		fileId = t;
	}
	function delPic(th) {
		$(th).parent().remove();
		$_that = $(this).parent().children('li').length;
		ulevery = $(this).parent().children('li').outerWidth(true);
		if($_that>4){
			if (flag <= -$_that+4) {
				flag = -$_that+5;
			}
			flag -= 1;
			$(this).siblings('.id_card').children('.slick').css("left", flag * ulevery + 'px');
		}
		
	}
	
	function delImgSrc() {
		$("#ziLioaImages").children().remove();
		
	}
	
	//左右滑动
	/* $(function() {
		 	//删除节点
		    $('.infor_bottom').find('.del_element').each(function(i,v){
		        $(v).on('click',function(){
		            $(this).parent().remove();
		        })
		    })
			var slick = function() {
				var flag = 0;
				$('.left').click('on',function() {
					$_that = $(this).siblings('.id_card').children('.slick').children('li').length;
					//alert($_that);
					ulevery = $(this).siblings('.id_card').children('.slick').children('li').outerWidth(true);
					if($_that>4){
						if (flag <= -$_that+4) {
							flag = -$_that+5;
						}
						flag -= 1;
						$(this).siblings('.id_card').children('.slick').css("left", flag * ulevery + 'px');
					}
				})
				$('.right').click('on',function() {
					if($_that>4){
						if (flag == 0) {
							flag = -1;
						}
						flag += 1;
						$(this).siblings('.id_card').children('.slick').css("left", flag * ulevery + 'px');
					}
				})
			}
			slick(); 
		}) */
    
		//左右滑动 
	/*function addImgSrc(ttt) {
		 //alert(ttt);
		$("#ziLioaImages").children().remove();
		var listImg=document.getElementById(ttt).getElementsByTagName("img");
		var imgSrcs="";
		for(var i=0;i<listImg.length;i++){
			imgSrcs += '<li><img src="'+listImg[i].src+'"></li>';
		}
		$("#ziLioaImages").append(imgSrcs);
		//alert(imgSrcs);
		var ban = $('.contentImg');
	    ban.each(function(i,v){
	        var left = $(v).children('.left'),
	            right = $(v).children('.right'),
	            card = $(v).children('.card'),
	            ul = card.children('.flashImg'),
	            ulevery = ul.children('li').outerWidth(true),
	            cardNum = card.width() / ulevery,
	            flag = cardNum * ulevery,
	            num = 0,
	            allWidth = ulevery * ul.children('li').length;
	        var cardWidth = card.width();
	        ul.css('width',allWidth);
	        if((cardWidth-allWidth) < 0){
	            $('.contentImg .arrow').show();
	        }else{
	            $('.contentImg .arrow').hide();
	        }
	        right.on('click',function(){
	            num -= ulevery;
	            if(num >= -(allWidth - flag)){
	                ul.stop().animate({'left':num});
	            }else{
	                num = -(allWidth - flag)
	            }
	        })
	
	        left.on('click',function(){
	            num += ulevery;
	            if(num <= 0){
	                ul.stop().animate({'left':num});
	            }else{
	                num = 0;
	            }
	        })
	
	    }) 
	}
	 */
		
	 // 点击放大 不左右滑动
	 function fangdaImage(ttt) {
		//alert(ttt.src);
		$("#ziLioaImages").children().remove();
		var imgSrcs="";
			imgSrcs += '<li><img src="'+ttt.src+'"></li>';
		$("#ziLioaImages").append(imgSrcs);
	    
	}
	 
	 