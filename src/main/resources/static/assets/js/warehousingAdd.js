/**
 * 页面加载完成时进行操作
 */
var commodityList = [];
var supplierId;
$(document).ready(function(){
	supplierId  = GetQueryValue("supplierId");
	if(supplierId != null){
		getCommodityListById(supplierId);
	}else{
		loadTable();
	}
});
/**
 * 显示管理员选项
 */
function showAdmin(){
	$("[data_jurisdiction='admini']").css("display","show");
}
/**
 * 隐藏管理员选项
 */
function hideAdmin(){
	$("[data_jurisdiction='admini']").css("display","none");
}


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
	//计算页数
	if(commodityList.length != 0){
		if(commodityList.length%page.countNum==0){
			page.pageSum = commodityList.length/page.countNum;
		}else{
			page.pageSum = parseInt(commodityList.length/page.countNum)+1;
		}
	}else{
		page.pageSum = 1;
	}
	page.countSum = commodityList.length;
	loadTableData(commodityList);
}

/**
 * @param {Object} data
 * 通过data数据加载表格
 */
function loadTableData(dataList){
	
	var nowCount = (page.nowPage-1)*page.countNum;
	var data = [];
	if(nowCount+5<page.countSum){
		for(var i = 0; i < 5; i++){
			data[i] = dataList[i+nowCount];
		}
	}else{
		for(var i = nowCount; i < page.countSum; i++){
			data[i-nowCount] = dataList[i];
		}
	}
	$("#supplierOrderDetail").empty();
	if(dataList.length > 0){
		for(var i = 0; i < data.length; i++){
			var no = $("<td></td>").html(data[i].no);
			var id = $("<td></td>").html(data[i].id);
			var commodityName = $("<td></td>").html(data[i].commodityName);
			var supplier = $("<td></td>").html(data[i].supplier);
			var num = $("<td></td>").html(data[i].num);
			var price = $("<td></td>").html(data[i].price);
			var sumPrice = $("<td></td>").html(data[i].sumPrice);
			var edit = $("<td></td>").html(data[i].edit);
			var btn = $("<td><a href='javascript:void(0)' onclick='showModal("+data[i].no+")' class='btn btn-primary'>详情</a>&nbsp&nbsp"+
			"<a href='javascript:void(0)' onclick='del("+data[i].no+")' class='btn btn-warning'>删除</a></td>");
			var tr = $("<tr></tr>").append(no,id,commodityName,supplier,num,price,sumPrice,edit,btn);
			$("#supplierOrderDetail").append(tr);
		}
	}
	loadPageList(page);
}
/**
 * 打开添加商品模态框
 */
function showModal(no){
	if(no == null){
		$("#commodityId").val("");
		$("#commodityName").val("");
		$("#supplier").val("");
		$("#num").val("");
		$("#price").val("");
		$("#sumPrice").val("");
		$("#edit").val("");
	}else{
		console.log(commodityList)
		$("#commodityId").val(commodityList[no-1].id);
		$("#commodityName").val(commodityList[no-1].commodityName);
		$("#supplier").val(commodityList[no-1].supplier);
		$("#num").val(commodityList[no-1].num);
		$("#price").val(commodityList[no-1].price);
		$("#sumPrice").val(commodityList[no-1].sumPrice);
		$("#edit").val(commodityList[no-1].edit);
	}
	
	/**
	 * 初始化商品列表
	 */
	$.ajax({
		url: url+"/commodity/getAllCommodity", 
		type:"get",
		dataType: 'json',
		success: function(res){
			if(res.request == "SUCCESS"){
				var data = res.result.list;
				$("#commodityName_menu").empty();
				for(var i = 0; i < data.length; i++){
					var a = $('<a class="dropdown-item" href="javascript:void(0)" onclick="selectCommodityName('+data[i].id+')"></a>');
					a.html(data[i].commodityName);
					$("#commodityName_menu").append(a);	 
				}
			}else{
				window.location.href = "../pages/404.html";
			}			
		},
		error:function(res){
			console.log(res);
		}
	 });
   $("#detailModal").modal("show");
}

/**
 * @param {Object} this
 * 商品选择选择处理
 */
function selectCommodityName(id){
	$.ajax({
		url: url+"/commodity/getCommodityById", 
		type:"get",
		dataType: 'json',
		data:{id:id},
		success: function(res){
			if(res.request == "SUCCESS"){
				loadDetailModal(res.result);	
			}else{
				window.location.href = "../pages/404.html";
			}			
		},
		error:function(res){
			console.log(res);
		}
	 });
}
/**
 * @param {Object} data
 * 根据data改变detaileModal的值
 */
function loadDetailModal(data) {
	$("#commodityId").val(data.id);
	$("#commodityName").val(data.commodityName);
	$("#supplier").val(data.supplier.supplierName);
	$("#price").val(data.commodityPrice);
}

/**
 * 设置总价
 */
function getSumPrice(){
	var num = $("#num").val();
	if(!checkRate(num)){
		showInfoModel("请输入数字");
		return;
	}
	var price = $("#price").val();
	var sumPrice = num * price;
	 $("#sumPrice").val(sumPrice);
}


/**
 * 添加商品
 */
function addCommodity(){
	if($("#commodityId").val() == ""){
		showInfoModel("请选择商品");
		return;
	}
	if($("#num").val() == ""){
		showInfoModel("请输入数量");
		return;
	}
	var flag = false;
	for(var i = 0; i < commodityList.length; i++){
		if(commodityList[i].id == parseInt($("#commodityId").val())){
			var fromData = {
				no:commodityList[i].no,
				id:$("#commodityId").val(),
				commodityName:$("#commodityName").val(),
				supplier:$("#supplier").val(),
				num:$("#num").val(),
				price:$("#price").val(),
				sumPrice:$("#sumPrice").val(),
				edit:$("#edit").val(),
			}
			commodityList[i] = fromData;
			flag = true;
			break;
		}
	}
	if(!flag){
		var fromData = {
			no:commodityList.length+1,
			id:$("#commodityId").val(),
			commodityName:$("#commodityName").val(),
			supplier:$("#supplier").val(),
			num:$("#num").val(),
			price:$("#price").val(),
			sumPrice:$("#sumPrice").val(),
			edit:$("#edit").val(),
		}
		commodityList.push(fromData);
	}
	
	loadTable(page.pageSum);
	$("#detailModal").modal("hide");
}

/**
 * 打开订单信息框
 */
function saveModel(){
	if(commodityList.length == 0){
		showInfoModel("请添加商品后再保存商品");
		return;
	}
	/**
	 * 初始化供应商
	 */
	$.ajax({
		url: url+"/supplier/getAllSupplier", 
		type:"get",
		dataType: 'json',
		success: function(res){
			if(res.request == "SUCCESS"){
				var data = res.result.list;
				$("#supplier_menu").empty();
				for(var i = 0; i < data.length; i++){
					var a = $('<a class="dropdown-item" href="javascript:void(0)" onclick="selectSupplier(this)"></a>');
					a.html(data[i].supplierName);
					a.attr("data_id",data[i].id);
					$("#supplier_menu").append(a);	 
				}
			}else{
				window.location.href = "../pages/404.html";
			}			
		},
		error:function(res){
			console.log(res);
		}
	 });
    if(supplierId == null){
		$("#addTypeNum").val(commodityList.length);
		var num = 0;
		var sumPrice = 0;
		for(var i = 0; i < commodityList.length; i++){
			num += parseInt(commodityList[i].num);
			sumPrice += parseFloat(commodityList[i].sumPrice);
		}
		$("#addCommodityNum").val(num);
		$("#addPrice").val(sumPrice);
	}else{
		$.ajax({
			url: url+"/supplierOrder/getSupplierOrderById", 
			mathod:"get",
			dataType: 'json',
			data:{
				id:supplierId
			},
			success: function(res){
				if(res.request == "SUCCESS"){
					$("#updateid").val(res.result.id);
					$("#addTitle").val(res.result.title);
					$("#addSupplier").attr("data_id",res.result.supplier.id),
					$("#addSupplier").val(res.result.supplier.supplierName);
					$("#addTypeNum").val(res.result.typeNum);
					$("#addCommodityNum").val(res.result.commodityNum);
					$("#addPrice").val(res.result.price);
					$("#addEdit").val(res.result.edit);
				}else{
					window.location.href = "../pages/404.html";
				}			
			},
			error:function(res){
				console.log(res);
			}
		 });
	}
	$('#orderModal').modal('show');
}

/**
 * @param {Object} this
 * 供应商选择选择处理
 */
function selectSupplier(e){
	$("#addSupplier").val("");
	$("#addSupplier").attr("data_id","0");
    $("#addSupplier").val($(e).html());
    $("#addSupplier").attr("data_id",$(e).attr("data_id"));
}


/**
 * 保存订单
 */
function save(){
	var dataList = [];
	console.log()
	for(var i = 0; i < commodityList.length; i++){
		var data = {
			commodityId:commodityList[i].id,
			supplierOrderId:1,//设置订单ID
			price:commodityList[i].price,
			num:commodityList[i].num,
			sumPrice:commodityList[i].sumPrice,
			edit:commodityList[i].edit
		}
		dataList.push(data);
	}
	if($("#addTitle").val() == ""){
		showInfoModel("请输入标题");
		return;
	}
	if($("#addSupplier").attr("data_id") == 0){
		showInfoModel("请选择供应商");
		return;
	}
	var order  = {
		id:$("#updateid").val(),
		title:$("#addTitle").val(),
		supplierId:$("#addSupplier").attr("data_id"),
		typeNum:$("#addTypeNum").val(),
		commodityNum:$("#addCommodityNum").val(),
		price:$("#addPrice").val(),
		edit:$("#edit").val(),
		info:"供货商提交保存",
		state:"已保存"
	}
	var param ={
		details:dataList,
		supplierOrder:order
	}
	console.log(param)
	$.ajax({
		url: url+"/supplierOrderDetail/saveSupplierOrderDetail", 
		method:"post",
		dataType: 'json',
		data:JSON.stringify(param),
		contentType:"application/json",//后台使用RequestBody注解时需要使用
		success: function(res){
			if(res.request == "SUCCESS"){
				showInfoModel("保存成功");
				$('#orderModal').modal('hide');
				commodityList.length = 0;
				loadTableData(commodityList);
				window.location.href = "../pages/supplierOrder.html";
			}else{
				window.location.href = "../pages/404.html";
			}			
		},
		error:function(res){
			console.log(res);
		}
	 });
}

/**
 * @param {Object} no
 * 根据序号删除数据
 */
function del(no){
	 for(var i = no; i < commodityList.length; i++){
		 commodityList[i-1] = commodityList[i];
		 commodityList[i-1].no = i;
	 }
	 commodityList.length = commodityList.length-1;
	loadTable();
}

/**
 * 隐藏模态框
 */
function closeModal(){
	$('#detailModal').modal('hide');
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
 * @param {Object} supplierId
 * 根据supplierid获取订单所属商品列表
 */
function getCommodityListById(supplierId){
	 $.ajax({
	 	url: url+"/supplierOrderDetail/getSupplierOrderDetailBySupplierOrderId", 
	 	method:"post",
	 	dataType: 'json',
	 	data:{
			supplierOrderId:supplierId
		},
	 	success: function(res){
	 		if(res.request == "SUCCESS"){
				commodityList.length = 0;
				 var list = res.result;
				 for(var i = 0; i < list.length; i++){
					var item = {
						no:i+1,
						id:list[i].commodityId,
						commodityName:list[i].commodity.commodityName,
						supplier:list[i].commodity.supplier.supplierName,
						num:list[i].num,
						price:list[i].price,
						sumPrice:list[i].sumPrice,
						edit:list[i].edit,
					} 
					commodityList.push(item);
				 }
	 			loadTable();
	 		}else{
	 			window.location.href = "../pages/404.html";
	 		}			
	 	},
	 	error:function(res){
	 		console.log(res);
	 	}
	  });
 }
