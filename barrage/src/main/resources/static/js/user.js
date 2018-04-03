$(function(){
	var name,value; 
	var str=location.href; 
	var num=str.indexOf("?") 
	str=str.substr(num+1); 
	
	var arr=str.split("&"); 
	for(var i=0;i < arr.length;i++){ 
		num=arr[i].indexOf("="); 
		if(num>0){ 
			name=arr[i].substring(0,num);
			value=arr[i].substr(num+1);
			$('#welcome').html("欢迎您，用户："+name);
		} 
	} 
});