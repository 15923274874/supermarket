/**
 * 页面加载完成时进行操作
 */
$(document).ready(function(){
	loadTable();
});

/**
 * @param {Object} nowPage 需要加载页码
 * @param {Object} size   每页记录条数
 * 网络请求并加载表格
 */
function loadTable(nowPage){
	if(nowPage == null){
		page.nowPage = 1;
	}else{
		page.nowPage = nowPage;
	}
	$.ajax({
		url: url+"/user/getAllUser", 
		type:"get",
		data:{
			nowPage:page.nowPage,
			size:page.countNum
		},
		dataType: 'json',
		success: function(res){
			page.pageSum = res.result.pageSum;//总页数
			page.countSum = res.result.countSum;//总记录数
			loadTableData(res.result.list);
		},
		error:function(res){
			console.log(res);
		}
	 });
}

/**
 * @param {Object} data
 * 通过data数据加载表格
 */
function loadTableData(data){
	$("#commodityTable").empty();
	for(var i = 0; i < data.length; i++){
		var id = $("<td></td>").html(data[i].id);
		var account = $("<td></td>").html(data[i].account);
		var jurisdiction = $("<td></td>").html(data[i].jurisdiction);
		var jurisdictionName = $("<td></td>").html(data[i].jurisdictionName);
		if(data[i].supplier != null){
			var supplier = $("<td></td>").html(data[i].supplier.supplierName);
		}else{
			var supplier = $("<td></td>").html("未绑定供货商");
		}
		var btn = $("<td><a href='javascript:void(0)' onclick='showModal("+data[i].id+")' class='btn btn-primary'>修改</a>&nbsp&nbsp"+
		"<a href='javascript:void(0)' onclick='del("+data[i].id+")' class='btn btn-secondary'>删除</a></td>");
		var tr = $("<tr></tr>").append(id,account,jurisdiction,jurisdictionName,supplier,btn);
		$("#commodityTable").append(tr);
	}
	loadPageList(page);
}
/**
 * 显示模态框
 */
function showModal(userId){
	/**
	 * 加载商品类型列表
	 */
	// $.ajax({
	// 	url: url+"/user/getAllCommodityType", 
	// 	type:"get",
	// 	dataType: 'json',
	// 	success: function(res){
	// 		var data = res.result.list;
	// 		$("#commodityType_menu").empty();
	// 		for(var i = 0; i < data.length; i++){
	// 			var a = $('<a class="dropdown-item" href="javascript:void(0)" onclick="selectCommodityType(this)"></a>');
	// 			a.html(data[i].commodityType);
	// 			a.attr("data_id",data[i].id);	 
	// 			$("#commodityType_menu").append(a);	 
	// 		}
	// 	},
	// 	error:function(res){
	// 		console.log(res);
	// 	}
	//  });
	
	/**
	 * 加载供应商列表
	 */
	$.ajax({
		url: url+"/supplier/getAllSupplier", 
		type:"get",
		dataType: 'json',
		success: function(res){
			var data = res.result.list;
			$("#supplier_menu").empty();
			for(var i = 0; i < data.length; i++){
				var a = $('<a class="dropdown-item" href="javascript:void(0)" onclick="selectSupplier(this)"></a>');
				a.html(data[i].supplierName);
				a.attr("data_id",data[i].id);	 
				$("#supplier_menu").append(a);	 
			}
		},
		error:function(res){
			console.log(res);
		}
	 });
	if(userId != null){
		/**
		 * 根据id加载相应数据
		 */
		$.ajax({
			url: url+"/user/getUserById", 
			type:"get",
			dataType: 'json',
			data:{
				id:userId
			},
			success: function(res){
				loadModal(res.result);
			},
			error:function(res){
				console.log(res);
			}
		 });
	}else{
		loadModal(null)
	}
}
/**
 * @param {Object} this
 * 公司选择处理
 */
function selectCommodityType(e){
	$("#commodityType").val($(e).html());
	$("#commodityType").attr("data_id",$(e).attr("data_id"));
}

function selectJurisdiction(e){
	$("#jurisdictionName").val($(e).html());
	$("#jurisdiction").val($(e).attr("data_id"));
	if($("#jurisdiction").val() == 3){
		$("#supplier").parent().parent().parent().css("display","");
	}else{
		$("#supplier").attr("data_id",0);
		$("#supplier").val('');
		$("#supplier").parent().parent().parent().css("display","none");
	}
}

/**
 * @param {Object} this
 * 公司选择处理
 */
function selectSupplier(e){
	$("#supplier").val($(e).html());
	$("#supplier").attr("data_id",$(e).attr("data_id"));
}


/**
 * @param {Object} data
 * 根据data加载modal数据
 */
function loadModal(data){
	if(data != null){
		$("#userId").val(data.id);
		$("#userId").parent().parent().css("display","");
		$("#account").val(data.account);
		$("#jurisdiction").val(data.jurisdiction);
		
		if(data.supplier != null){
			$("#supplier").attr("data_id",data.supplier.id);
			$("#supplier").val(data.supplier.supplierName);
			$("#supplier").parent().parent().parent().css("display","");
		}else{
			$("#supplier").attr("data_id",0);
			$("#supplier").val("");
			$("#supplier").parent().parent().parent().css("display","none");
		}
		$("#jurisdictionName").val(data.jurisdictionName);
		$('#detailModal').modal('show');
	}else{
		$("#userId").val("");
		$("#userId").parent().parent().css("display","none");
		$("#account").val("");
		$("#account").attr("disabled",false);
		$("#jurisdiction").attr("");
		$("#jurisdictionName").val("");
		$("#supplier").val("");
		$("#supplier").parent().parent().parent().css("display","none");
		$('#detailModal').modal('show');
	}
}

function save(){
	var fromData = {
		id:$("#userId").val(),
		account:$("#account").val(),
		jurisdiction:$("#jurisdiction").val(),
		jurisdictionName:$("#jurisdictionName").val(),
		supplierId:$("#supplier").attr("data_id"),
		passWord:'123456'
	}
	console.log(fromData)
	$.ajax({
		url: url+"/user/save", 
		type:"post",
		dataType: 'json',
		data:fromData,
        success: function(res){
			if(res.request == "SUCCESS"){
				$('#detailModal').modal('hide');
				showInfoModel(res.info);
				$('#infoModal').on('hidden.bs.modal', function (e) {
				  loadTable(page.nowPage);
				})
				
			}else{
				showInfoModel(res.info);
			}
		},
		error:function(res){
			window.location.href = "../pages/404.html";
		}
	 });
}
/**
 * @param {Object} id
 * 删除及确认
 */
function del(id){
	$("#delOrderId").val(id);
	$("#delModal").modal('show');
}
function okDel(){
	var id = $("#delOrderId").val();
	$.ajax({
		url: url+"/user/del", 
		type:"post",
		dataType: 'json',
		data:{
			id:id
		},
	    success: function(res){
			if(res.request == "SUCCESS"){
				$("#delModal").modal('hide');
				$('#detailModal').modal('hide');
				showInfoModel("删除成功");
				$('#infoModal').on('hidden.bs.modal', function (e) {
				  loadTable(page.nowPage);
				})
			}else{
				window.location.href = "../pages/404.html";
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

