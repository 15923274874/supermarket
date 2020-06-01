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
		url: url+"/commodity/getAllCommodity", 
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
		var commodityName = $("<td></td>").html(data[i].commodityName);
		var commodityEdit = $("<td></td>").html(data[i].commodityEdit);
		var commodityType = $("<td></td>").html(data[i].commodityType.commodityType);
		var commodityPrice = $("<td></td>").html(data[i].commodityPrice);
		var commodityCales = $("<td></td>").html(data[i].commodityCales);
		var commodityStock = $("<td></td>").html(data[i].commodityStock);
		var supplierName = $("<td></td>").html(data[i].supplier.supplierName);
		var btn = $("<td><a href='javascript:void(0)' onclick='showModal("+data[i].id+")' class='btn btn-primary'>修改</a>&nbsp&nbsp"+
		"<a href='javascript:void(0)' onclick='del("+data[i].id+")' class='btn btn-secondary'>删除</a></td>");
		var tr = $("<tr></tr>").append(id,commodityName,commodityEdit,commodityType,commodityPrice,commodityCales,commodityStock,supplierName,btn);
		$("#commodityTable").append(tr);
	}
	loadPageList(page);
}
/**
 * 显示模态框
 */
function showModal(commodityId){
	/**
	 * 加载商品类型列表
	 */
	$.ajax({
		url: url+"/commodityType/getAllCommodityType", 
		type:"get",
		dataType: 'json',
		success: function(res){
			var data = res.result.list;
			$("#commodityType_menu").empty();
			for(var i = 0; i < data.length; i++){
				var a = $('<a class="dropdown-item" href="javascript:void(0)" onclick="selectCommodityType(this)"></a>');
				a.html(data[i].commodityType);
				a.attr("data_id",data[i].id);	 
				$("#commodityType_menu").append(a);	 
			}
		},
		error:function(res){
			console.log(res);
		}
	 });
	
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
	if(commodityId != null){
		/**
		 * 根据id加载相应数据
		 */
		$.ajax({
			url: url+"/commodity/getCommodityById", 
			type:"get",
			dataType: 'json',
			data:{
				id:commodityId
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
		$("#commodityId").val(data.id);
		$("#commodityId").parent().parent().css("display","");
		$("#commodityName").val(data.commodityName);
		$("#commodityEdit").val(data.commodityEdit);
		
		$("#commodityType").attr("data_id",data.commodityType.id);
		$("#commodityType").val(data.commodityType.commodityType);
		$("#commodityCales").val(data.commodityCales);
		$("#commodityPrice").val(data.commodityPrice);
		$("#commodityStock").val(data.commodityStock);
		$("#supplier").val(data.supplier.supplierName);
		$("#supplier").attr("data_id",data.supplier.id);
		$('#detailModal').modal('show');
	}else{
		$("#commodityId").val("");
		$("#commodityId").parent().parent().css("display","none");
		$("#commodityName").val("");
		$("#commodityName").attr("");
		$("#commodityEdit").val("");
		$("#commodityType").val("");
		$("#commodityType").attr("data_id",0);
		$("#commodityPrice").val("");
		$("#commodityCales").val("0");
		$("#commodityStock").val("0");
		$("#supplier").val("");
		$("#supplier").attr("data_id",0);
		$('#detailModal').modal('show');
	}
}

function postFrom(){
	var fromData = {
		id:$("#commodityId").val(),
		commodityName:$("#commodityName").val(),
		commodityEdit:$("#commodityEdit").val(),
		typeId:$("#commodityType").attr("data_id"),
		commodityPrice:$("#commodityPrice").val(),
		commodityCales:$("#commodityCales").val(),
		commodityStock:$("#commodityStock").val(),
		supplierId:$("#supplier").attr("data_id")
	}
	if(fromData.commodityName == ""){
		showInfoModel("请输入商品名");
		return;
	}
	if(fromData.commodityPrice == 0){
		showInfoModel("请输入价格");
		return;
	}
	if(fromData.supplierId == 0){
		showInfoModel("请选择供应商");
		return;
	}
	$.ajax({
		url: url+"/commodity/save", 
		type:"post",
		dataType: 'json',
		data:fromData,
        success: function(res){
			if(res.request == "SUCCESS"){
				$('#detailModal').modal('hide');
				showInfoModel("保存成功");
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
		url: url+"/commodity/del", 
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


