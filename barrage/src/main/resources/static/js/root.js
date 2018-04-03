$(function(){
	$("#listen").bind("click", function(e){
		console.log("--click");
		var id = $("#rootId").val();
		console.log("--rootId:" + id);
		
		$.get("/root/listen","port=" + id, function(){
			console.log("--监听成功");
			pull();
		});
	});
});

function pull(){
	$.get("/root/pull",function(e){
		console.log("--拉取数据");
		$("#content").append("<p>"+ e +"</p>");
		pull();
	});
}