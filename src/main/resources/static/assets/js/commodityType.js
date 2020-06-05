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
		url: url+"/commodityType/getAllCommodityType", 
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
	$("#commodityTypeTable").empty();
	for(var i = 0; i < data.length; i++){
		var id = $("<td></td>").html(data[i].id);
		var commodityType = $("<td></td>").html(data[i].commodityType);
		var personNo = $("<td></td>").html(data[i].personNo);
		var personName = $("<td></td>").html(data[i].personName);
		var personPhone = $("<td></td>").html(data[i].personPhone);
		var btn = $("<td><a href='javascript:void(0)' onclick='showModal("+data[i].id+")' class='btn btn-primary'>修改</a>&nbsp&nbsp"+
		"<a href='javascript:void(0)' onclick='del("+data[i].id+")' class='btn btn-secondary'>删除</a></td>");
		var tr = $("<tr></tr>").append(id,commodityType,personNo,personName,personPhone,btn);
		$("#commodityTypeTable").append(tr);
	}
	loadPageList(page);
}
/**
 * 显示模态框
 */
function showModal(commodityTypeId){
	if(commodityTypeId != null){
		/**
		 * 根据id加载相应数据
		 */
		$.ajax({
			url: url+"/commodityType/getCommodityTypeById", 
			type:"get",
			dataType: 'json',
			data:{
				id:commodityTypeId
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
		$("#commodityTypeId").val(data.id);
		$("#commodityTypeId").parent().parent().css("display","");
		$("#commodityType").val(data.commodityType);
		$("#personNo").val(data.personNo);
		$("#personName").val(data.personName);
		$("#personPhone").val(data.personPhone);
		$('#detailModal').modal('show');
	}else{
		$("#commodityTypeId").val("");
		$("#commodityTypeId").parent().parent().css("display","none");
		$("#commodityType").val("");
		$("#personNo").val("");
		$("#personName").val("");
		$("#personPhone").val("");
		$('#detailModal').modal('show');
	}
}

function postFrom(){
	var fromData = {
		id:$("#commodityTypeId").val(),
		commodityType:$("#commodityType").val(),
		personNo:$("#personNo").val(),
		personName:$("#personName").val(),
		personPhone:$("#personPhone").val(),
	}
	if(fromData.commodityType == ""){
		showInfoModel("请输入商品类型");
		return;
	}
	if(fromData.personNo == "" || !checkRate(fromData.personNo)){
		showInfoModel("请输入正确的负责人工号");
		return;
	}
	if(fromData.personName == ""){
		showInfoModel("请输入负责人姓名");
		return;
	}
	if(fromData.personPhone == "" || !checkRate(fromData.personPhone)){
		showInfoModel("请输入正确的负责人电话");
		return;
	}
	$.ajax({
		url: url+"/commodityType/save", 
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
		url: url+"/commodityType/del", 
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

