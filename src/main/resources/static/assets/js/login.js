function login(){
	user = null;
	var account = $("#account").val();
	var password = $("#password").val();
	var remember = document.getElementById('remember');
    $.ajax({
    	url: url+"/login/isLogin", 
    	type:"get",
    	dataType: 'json',
		data:{
			account:account,
			passWord:password
		},
    	success: function(res){
    		if(res.request == "SUCCESS"){
				userinfo = res.user;
				window.location.href = "../index.html";
    		}else{
    			showInfoModel(res.info)
    		}			
    	},
    	error:function(res){
    		// window.location.href = "../pages/404.html";
			console.log(res)
    	}
     });
}


/**
 * @param {Object} info
 * 提示消息
 */
function showInfoModel(info){
	$("#infoModal_info").html(info);
	$('#infoModal').modal('show');
}