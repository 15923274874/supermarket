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
		var supplierName = $("<td></td>").html(data[i].supplierName);
		var supplierEdit = $("<td></td>").html(data[i].supplierEdit);
		var supplierPeople = $("<td></td>").html(data[i].supplierPeople);
		var supplierPhone = $("<td></td>").html(data[i].supplierPhone);
		var emaile = $("<td></td>").html(data[i].email);
		var address = $("<td></td>").html(data[i].address);
		var btn = $("<td><a href='javascript:void(0)' onclick='showModal("+data[i].id+")' class='btn btn-primary'>修改</a>&nbsp&nbsp"+
		"<a href='javascript:void(0)' onclick='del("+data[i].id+")' class='btn btn-warning'>删除</a></td>");
		var tr = $("<tr></tr>").append(id,supplierName,supplierEdit,supplierPeople,supplierPhone,emaile,address,btn);
		$("#supplierTable").append(tr);
	}
	loadPageList(page);
}
/**
 * 显示模态框
 */
function showModal(supplierId){
    if(supplierId != null){
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
				if(res.request == "SUCCESS"){
					loadModal(res.result);
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
function loadModal(data){
	 if(data != null){
		 $("#supplierid").val(data.id);
		 $("#supplierName").val(data.supplierName);
		 $("#supplierEdit").val(data.supplierEdit);
		 $("#supplierPeople").val(data.supplierPeople);
		 $("#supplierPhone").val(data.supplierPhone);
		 $("#email").val(data.email);
		 $("#address").val(data.address);
		 $("#supplierid").parent().parent().css("display","");
		 $('#detailModal').modal('show');
	 }else{
		 $("#supplierid").val("");
		 $("#supplierName").val("");
		 $("#supplierEdit").val("");
		 $("#supplierPeople").val("");
		 $("#supplierPhone").val("");
		 $("#email").val("");
		 $("#address").val("");
		 $("#supplierid").parent().parent().css("display","none");
		 $('#detailModal').modal('show');
	 }
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
	$.ajax({
		url: url+"/supplier/del", 
		type:"post",
		dataType: 'json',
		data:{
			id:id
		},
	    success: function(res){
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
 * 隐藏模态框
 */
function closeModal(){
	$('#detailModal').modal('hide');
}
 
