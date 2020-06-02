var url = "";//网络请求地址
var page = {nowPage:0,countNum:5,pageSum:0,countSum:0};//定义分页对象
var user = null;
$(document).ready(function(){
	setLoginInfo();
})

/**
 * 打开文件选择框
 */
function openFile(){
	var fileElem = document.getElementById("userIcon");
	fileElem.click();
}

/**
 * 头像选择触发
 */
function selectImg() {
	var userIconBtn = $("#userIcon");
	$("#iconImg").attr("src",URL.createObjectURL(userIconBtn[0].files[0]));
}

/**
 * 保存用户信息
 */
function saveUserInfo() {
	var formData = new FormData();
	formData.append('file', $('#userIcon')[0].files[0]);
	$.ajax({
		url: '/user/iconUpload',
		type: 'POST',
		cache: false,
		data: formData,
		processData: false,
		contentType: false,
		success: function(res){
			if(res.result == "success"){
				showInfoModel(res.msg);
			}else{
				showInfoModel(res.msg);
			}
		},
		error:function(res){
		}
	});
}

/**
 * 保存用户信息
 */
function saveUserInfo() {
	var formData = new FormData();
	formData.append('file', $('#userIcon')[0].files[0]);
	$.ajax({
		url: '/user/iconUpload',
		type: 'POST',
		cache: false,
		data: formData,
		processData: false,
		contentType: false,
		success: function(res){
			if(res.result == "success"){
				showInfoModel(res.msg);
			}else{
				showInfoModel(res.msg);
			}
		},
		error:function(res){
		}
	});
}

function setLoginInfo(){
	$.ajax({
		url: url+"/login/getLoginUser",
		type:"post",
		dataType: 'json',
		success: function(res){
			if(res.request == "SUCCESS"){
				user = res.user;
				$("#topAccount").html(res.user.account);
				$("#topJurisdiction").html(res.user.jurisdictionName);
				$("#icon").attr("src",res.user.iconName);
				$("#iconImg").attr("src",res.user.iconName);
			}else{
				console.log(res);
			}
		},
		error:function(res){
			window.location.href = "../pages/404.html";
		}
	 });
}


/**
 * @param {Object} page
 * 根据page对象创建页码列表
 */
function loadPageList(page){
	$("#pageList").empty();
	//当前页码是否为第一页
	if(page.nowPage > 1){
		var li = $('<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="loadTable('+(page.nowPage-1)+')">上一页</a></li>');
	}else{
		var li = $('<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="loadTable(1)">上一页</a></li>');
	}
	$("#pageList").append(li);
	
	//总页码是否大于5页
	if(page.pageSum > 5){
		var m = 2;//基数
		if(page.nowPage < 3){
			m = page.nowPage-1;
		}
		if(page.nowPage + 2 > page.pageSum){
			m = 3;
		}
		if(page.nowPage + 1 > page.pageSum){
			m = 4;
		}
		for(var i = 0; i < 5; i++){
			var a = $("<a class='page-link' href='javascript:void(0)' onclick='loadTable("+(page.nowPage-m+i)+")'></a>").html(page.nowPage-m+i);
			if(page.nowPage-m+i == page.nowPage){
				var li = $("<li class='page-item active'></li>").append(a);
			}else{
				var li = $("<li class='page-item'></li>").append(a);
			}
			$("#pageList").append(li);
		}
	}else{
		for(var i = 1; i <= page.pageSum; i++){
			var a = $("<a class='page-link'href='javascript:void(0)' onclick='loadTable("+i+")'></a>").html(i);
			if(page.nowPage == i){
				var li = $("<li class='page-item active'></li>").append(a);
			}else{
				var li = $("<li class='page-item'></li>").append(a);
			}
			$("#pageList").append(li);
		}
	}
	
	//当前页码是否为最后一页
	if(page.nowPage != page.pageSum){
		var li = $('<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="loadTable('+(page.nowPage+1)+')">下一页</a></li>');
	}else{
		var li = $('<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="loadTable('+page.pageSum+')">下一页</a></li>');
	}
	$("#pageList").append(li);

}
/**
 * @param {Object} input
 * 判断是否为数字
 */
function checkRate(nubmer) {
　　var re = /^[1-9]+[0-9]*]*$/ ; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/ 
　　if (!re.test(nubmer)) {
　　　　return false;
　　}else{
		return true;
	}
}
function checkFloat(nubmer){
	var re = /^(([^0][0-9]+|0)\.([0-9]{1,2})$)|^[1-9]+[0-9]*]*$/ ; //判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
	　　if (!re.test(nubmer)) {
	　　　　return false;
	　　}else{
			return true;
		}
}

/**
 * @param {Object} queryName
 * 根据queryName获取参数值
 */
function GetQueryValue(queryName) {
	  var query = decodeURI(window.location.search.substring(1));
	  var vars = query.split("&");
	 for (var i = 0; i < vars.length; i++) {
	   var pair = vars[i].split("=");
		 if (pair[0] == queryName) { return pair[1]; }
	 }
	 return null;
 }

/**
 * 退出登录
 */
function  quit() {
	$.ajax({
		url: url+"/login/userQuit",
		type:"post",
		dataType: 'json',
		success: function(res){
			if(res.request == "SUCCESS"){
				window.location.href = "../pages/login.html";
			}else{
				console.log(res)
			}
		},
		error:function(res){
			window.location.href = "../pages/404.html";
		}
	});
 }


/**
 * @param {Object} info传入消息
 * 打开消息提示框
 */
function showInfoModel(info){
	$("#infoModal_info").html(info);
	$('#infoModal').modal('show');
}

/**
 * 密码修改
 */
function  updatePassWord() {
	var old = $("#oldPassWord").val();
	var newPassWord=$("#newPassWord").val();
	var newPassWord2=$("#newPassWord2").val();
    if(old == ""){
		showInfoModel("请输入旧密码");
		return;
	}
	if(newPassWord == ""){
		showInfoModel("请输入新密码");
		return;
	}
	if(newPassWord2 == ""){
		showInfoModel("请确认新密码");
		return;
	}
	if(newPassWord != newPassWord2){
		showInfoModel("两次密码不一致");
		return;
	}
	$.ajax({
		url: url+"/user/updatePassword",
		type:"post",
		dataType: 'json',
		data:{
			oldPassWord:old,
			newPassWord:newPassWord
		},
		success: function(res){
			if(res.request == "SUCCESS"){
				showInfoModel(res.info);
				$('#updatePasswordModal').modal('hide');
				setTimeout(function(){ quit() }, 3000);
			}else{
				showInfoModel(res.info);
			}
		},
		error:function(res){
			window.location.href = "../pages/404.html";
		}
	});

}