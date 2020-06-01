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
		url: url+"/supplier/getAllSupplier", 
		type:"get",
		data:{
			nowPage:page.nowPage,
			size:page.countNum
		},
		dataType: 'json',
		success: function(res){
			// console.log(res);
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
	$("#supplierTable").empty();;
	for(var i = 0; i < data.length; i++){
		var id = $("<td></td>").html(data[i].id);
		var supplierName = $("<td></td>").html(data[i].supplierName);
		var supplierEdit = $("<td></td>").html(data[i].supplierEdit);
		var supplierPeople = $("<td></td>").html(data[i].supplierPeople);
		var supplierPhone = $("<td></td>").html(data[i].supplierPhone);
		var emaile = $("<td></td>").html(data[i].email);
		var address = $("<td></td>").html(data[i].address);
		var btn = $("<td><a href='javascript:void(0)' onclick='showModal("+data[i].id+")' class='btn btn-primary'>详情</a></td>");
		var tr = $("<tr></tr>").append(id,supplierName,supplierEdit,supplierPeople,supplierPhone,emaile,address,btn);
		$("#supplierTable").append(tr);
	}
	loadPageList(page);
}
/**
 * 显示模态框
 */
function showModal(supplierId){
	/**
	 * 根据id加载相应数据
	 */
	$.ajax({
		url: url+"/supplier/getSupplierById", 
		type:"get",
		dataType: 'json',
		data:{
			id:supplierId
		},
		success: function(res){
			loadModal(res.result);
		},
		error:function(res){
			console.log(res);
		}
	 });
	$('#detailModal').modal('show');
}

/**
 * @param {Object} data
 * 根据data加载modal数据
 */
function loadModal(data){
	$("#supplierid").val(data.id);
	$("#supplierName").val(data.supplierName);
	$("#supplierEdit").val(data.supplierEdit);
	$("#supplierPeople").val(data.supplierPeople);
	$("#supplierPhone").val(data.supplierPhone);
	$("#email").val(data.email);
	$("#address").val(data.address);
	$('#detailModal').modal('show');
}

