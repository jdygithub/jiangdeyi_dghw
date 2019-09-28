 $(document).ready(function(){
	//  给input标签绑定change事件 
	 $("#file").on("change" , handleUpload);
	 
});

function handleUpload(evt){
	 if(!evt){
		 evt = window.event;
	 }
	//先获取input标签
	 var $input = $("#file"); 
	 var $img = $("#imgFile"); 
	// console.log(this) 
	 //获取选择图片的个数 
	 var files = evt.currentTarget.files; 
	 var lengthNew = files.length; 
	 var lenExists = $("#showImgList li").length-1;
	
	
	
	  if(lengthNew+lenExists<=12){
			//回显 
		 $.each(files,function(key,value){ 
		 	
			 var fileSize = files[key].size;
			 //转换文件单位
			 var size = fileSize / 1024;
			  //比较文件是否过大
			  if(size>5000){  
			    	layer.msg("图片不能大于5M");
			    	return;
			  }
			 var $newInput = $('<input class="uploadImg test" type="file" name="file"  onchange="handleUpload(event)"  id="file"/>').addClass("uploadImg"); 
			 var newsrc = getObjectURL(files[key]);
			 var $orignInput = $("#uploadImgBtn input[type='file']");
			 $("#uploadImgBtn").append($newInput);
			 $orignInput.removeClass("uploadImg").css("display","none").remove();
			  var newImg = '<div class="weui-grid js_grid " id="showImgList" style="padding: 0;">'+
					    	'<img id="imgFile" accept="image/png,image/jpg" src="'+newsrc+'" onclick="showWholeImage(event)" class="img">'+
					    	'<a onclick="delImg(this)" class="close">'+
					    	'<span >'+
							 '<i  class=am-icon-times></i>'+
							 '</span>'+
					    	'</a>'+
							'</div>'
			  var $newImage = $(newImg);
			  $newImage.append($orignInput);
			  $("#uploadImgBtn").before($newImage); 
			//当前input标签的id属性remove 
			 
		 	 $input.removeAttr("id"); 
			// $("#form01").append($(newInput));
		 }) ;
		 
		 showImgDo();
	 }else{
		 layer.msg("最多上传12张图片");
			 	return;
	}
	 
			 
}
 
function  showImgDo(){
	$(".img-show").mouseenter(function(){
		$(this).children('.view').show();
		$(this).children('.close').show();
	});
	
	$(".img-show").mouseleave(function(){
		$(this).children('.view').hide();
		$(this).children('.close').hide();
	});
}

 function delImg(obj) {
 	$(obj).parent().remove();	
 }
 /*全屏显示图片*/
 function showWholeImage(evt){
	 if(!evt){
		 evt = window.event;
	 }
	var targetEle = evt.srcElement;
	var $img = $("<img src='"+targetEle.src+"' title='单击隐藏' style='display:block;margin:auto;opacity:1.0;'/>");
	var visibleHeight = document.documentElement.clientHeight;
	if(!visibleHeight){
		visibleHeight = document.body.clientHeight;
	}
	if(visibleHeight<=$img[0].naturalHeight){
		var maxwidth = window.screen.width;
		var ratio = maxwidth/$img[0].naturalWidth;
		var height = $img[0].naturalHeight*ratio
		$img.css({"margin-top":(visibleHeight-height)/2+"px","width":"100%"});
		
		if($img[0].naturalHeight/2 >$img[0].naturalWidth){
		$img.css({"height":visibleHeight+"px",
		"width":"100%"});
		}
		
	}else{
		$img.css({"margin-top":(visibleHeight-$img[0].naturalHeight)/2+"px","width":"100%"});
	}
	var $div = $("<div onclick='$(this).remove()' style='z-index:10000;background-color:rgba(0,0,0,0.7);width:100%;height:"+$(document).height+"px;position:fixed;left:0;top:0'></div>");
	$img.appendTo($div);
	
	$div.appendTo($("body"));
	
}
function showImg(id){
	$('#'+ id).click();
    }
  //file的url
function getObjectURL(file) {
	var url = null;
	// 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
	
