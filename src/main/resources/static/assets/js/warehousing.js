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
		url: url+"/supplierOrder/getAllSupplierOrder", 
		type:"get",
		data:{
			nowPage:page.nowPage,
			size:page.countNum
		},
		dataType: 'json',
		success: function(res){
			console.log(res);
			if(res.request == "SUCCESS"){
				page.pageSum = res.result.pageSum;//总页数
				page.countSum = res.result.countSum;//总记录数
				loadTableData(res.result.list);
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
 * 通过data数据加载表格
 */
function loadTableData(data){
	$("#supplierTable").empty();;
	for(var i = 0; i < data.length; i++){
		var id = $("<td></td>").html(data[i].id);
		var time = $("<td></td>").html(data[i].time);
		var title = $("<td></td>").html(data[i].title);
		var supplierId = $("<td></td>").html(data[i].supplierId);
		var supplierName = $("<td></td>").html(data[i].supplier.supplierName);
		var typeNum = $("<td></td>").html(data[i].typeNum);
		var commodityNum = $("<td></td>").html(data[i].commodityNum);
		var price = $("<td></td>").html(data[i].price);
		var state = $("<td></td>").html(data[i].state);
		var edit = $("<td></td>").html(data[i].edit);
		var info = $("<td></td>").html(data[i].info);
		var btn = $("<td><a href='javascript:void(0)' onclick='showModal("+data[i].id+")' class='btn btn-primary'>详情</a>&nbsp&nbsp"+
		"<a href='javascript:void(0)' onclick='del("+data[i].id+")' class='btn btn-warning'>删除</a></td>");
		var tr = $("<tr></tr>").append(id,time,title,supplierId,supplierName,typeNum,commodityNum,price,state,edit,info,btn);
		$("#supplierTable").append(tr);
	}
	loadPageList(page);
}
/**
 * @param {Object} supplierOrderId//订单id
 */
function showModal(supplierOrderId){
    if(supplierOrderId != null){
		/**
		 * 根据id加载相应数据
		 */
		$.ajax({
			url: url+"/supplierOrderDetail/getSupplierOrderDetailBySupplierOrderId", 
			type:"get",
			dataType: 'json',
			data:{
				supplierOrderId:supplierOrderId
			},
			success: function(res){
				if(res.request == "SUCCESS"){
					loadModal(res.result,supplierOrderId);
				}else{
					window.location.href = "../pages/404.html";
				}
			},
			error:function(res){
				console.log(res);
			}
		 });
	}else{
		loadModal(null);
	}
}

/**
 * @param {Object} data
 * 根据data加载modal数据
 */
function loadModal(data,supplierOrderId){
	console.log(data)
	 if(data != null){
       $("#modal_tbody").empty();;
       for(var i = 0; i < data.length; i++){
       	var no = $("<th scope='row'></th>").html(i+1);
       	var commodityId = $("<td></td>").html(data[i].commodityId);
       	var commodityName = $("<td></td>").html(data[i].commodityName);
       	var num = $("<td></td>").html(data[i].num);
       	var commodityPrice = $("<td></td>").html(data[i].price);
       	var sumPrice = $("<td></td>").html(data[i].sumPrice);
       	var edit = $("<td></td>").html(data[i].edit);
         var tr = $("<tr></tr>").append(no,commodityId,commodityName,num,commodityPrice,sumPrice,edit);
       	$("#modal_tbody").append(tr);
       }
	   $.ajax({
	   	url: url+"/supplierOrder/getSupplierOrderById", 
	   	type:"get",
	   	data:{
	   	  id:supplierOrderId
	   	},
	   	dataType: 'json',
	   	success: function(res){
	   		if(res.request == "SUCCESS"){
	   			supplierOrder = res.result;
				$("#modal_footer").empty();
				if(supplierOrder.state != "已提交" && supplierOrder.state != "已确认"){
					var okBtn = $("<a href='javascript:void(0)' onclick='okOrder("+supplierOrderId+")' class='btn btn-secondary'>提交订单</a>");
					var closeBtn = $("<a href='javascript:void(0)' onclick='openErrorModel("+supplierOrderId+")' class='btn btn-danger'>修改订单</a>");
					$("#modal_footer").append(okBtn,closeBtn);
				}else{
				  var closeBtn = $("<a href='#'  class='btn btn-secondary'  data-dismiss='modal'>关闭</a>");
				  $("#modal_footer").append(closeBtn);
				}
				$('#detailModal').modal('show');
	   		}else{
	   			window.location.href = "../pages/404.html";
	   		}			
	   	},
	   	error:function(res){
	   		console.log(res);
	   	}
	    });
	 }
}

/**
 * @param {Object} supplierOrderId
 * 更加订单ID确定订单无误
 */
function okOrder(supplierOrderId){
	$.ajax({
		url: url+"/supplierOrder/save", 
		type:"post",
		dataType: 'json',
		data:{
			id:supplierOrderId,
			state:"已提交",
			info:"供应商提交"
		},
	    success: function(res){
			if(res.request == "SUCCESS"){
				closeModal();
				$('#successModal').modal('show');
				$('#successModal').on('hidden.bs.modal', function (e) {
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
 * 打开跳转修改页面并上传参数
 */
function openErrorModel(id){
	window.location.href = "warehousingAdd.html?supplierId="+id;
}

/**
 * 错误提交
 */
function errorPost(){
	var id = $('#errorOrderId').val();
	var info = $('#orderErrorEdit').val();
	$.ajax({
		url: url+"/supplierOrder/save", 
		type:"post",
		dataType: 'json',
		data:{
			id:id,
			info:info
		},
	    success: function(res){
			closeModal();
			$('#errorModal').modal('hide');
			if(res.request == "SUCCESS"){
				$('#successModal').modal('show');
				$('#successModal').on('hidden.bs.modal', function (e) {
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
 * 表达提交
 */
function postFrom(){
	var fromData = {
		id:$("#supplierid").val(),
		supplierName:$("#supplierName").val(),
		supplierEdit:$("#supplierEdit").val(),
		supplierPeople:$("#supplierPeople").val(),
		supplierPhone:$("#supplierPhone").val(),
		email:$("#email").val(),
		address:$("#address").val(),
	}
	$.ajax({
		url: url+"/supplier/save", 
		type:"post",
		dataType: 'json',
		data:fromData,
        success: function(res){
			if(res.request == "SUCCESS"){
				closeModal();
				$('#successModal').modal('show');
				$('#successModal').on('hidden.bs.modal', function (e) {
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
function del(id){
	$("#delOrderId").val(id);
	$("#delModal").modal('show');
}
function okDel(){
	var id = $("#delOrderId").val();
	$.ajax({
		url: url+"/supplierOrder/del", 
		type:"post",
		dataType: 'json',
		data:{
			id:id
		},
	    success: function(res){
			if(res.request == "SUCCESS"){
				$("#delModal").modal('hide');
				$('#successModal').modal('show');
				$('#successModal').on('hidden.bs.modal', function (e) {
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
 * 隐藏模态框
 */
function closeModal(){
	$('#detailModal').modal('hide');
}
 
