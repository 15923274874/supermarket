$(document).ready(function(){
    var time = setInterval(function () {
        console.log(user)
        if(user != null){
            //判断是否是供货商
            if(user.jurisdiction != 3){
                showAdmin();
            }else {
                hideAdmin()
            }
            clearInterval(time);
        }
    },500);

});

/**
 * 显示管理员选项
 */
function showAdmin(){
    $("[data_jurisdiction='admini']").show();

   // console.log(juris)
}
/**
 * 隐藏管理员选项
 */
function hideAdmin(){
    $("[data_jurisdiction='admini']").hide();
}

document.writeln(" <!-- ============================================================== -->");
document.writeln("        <!-- left sidebar -->");
document.writeln("        <!-- ============================================================== -->");
document.writeln("        <div class=\'nav-left-sidebar sidebar-dark\'>");
document.writeln("            <div class=\'menu-list\'>");
document.writeln("                <nav class=\'navbar navbar-expand-lg navbar-light\'>");
document.writeln("                    <a class=\'d-xl-none d-lg-none\' href=\'#\'>Dashboard</a>");
document.writeln("                    <button class=\'navbar-toggler\' type=\'button\' data-toggle=\'collapse\' data-target=\'#navbarNav\' aria-controls=\'navbarNav\' aria-expanded=\'false\' aria-label=\'Toggle navigation\'>");
document.writeln("                        <span class=\'navbar-toggler-icon\'></span>");
document.writeln("                    </button>");
document.writeln("                    <div class=\'collapse navbar-collapse\' id=\'navbarNav\'>");
document.writeln("                        <ul class=\'navbar-nav flex-column\'>");
document.writeln("                            <li class=\'nav-divider\'>");
document.writeln("                                菜单");
document.writeln("                            </li>");
document.writeln("                            <li class=\'nav-item \'>");
document.writeln("                                <a class=\'nav-link active\' href=\'../index.html\'><i class=\' fas fa-clone\'></i>首页</a>");
document.writeln("                            </li>");

document.writeln("                            <li class=\'nav-item \'>");
document.writeln("                                <a class=\'nav-link active\' href=\'#\' data-toggle=\'collapse\' aria-expanded=\'false\' data-target=\'#submenu-1\' aria-controls=\'submenu-1\'><i class=\'fas fa-archive\'></i>商品管理 <span class=\'badge badge-success\'>6</span></a>");
document.writeln("                                <div id=\'submenu-1\' class=\'collapse submenu\' style=\'\'>");
document.writeln("                                    <ul class=\'nav flex-column\'>");
document.writeln("                                        <li class=\'nav-item\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'../pages/commodity.html\'>商品信息</a>");
document.writeln("                                        </li>");
document.writeln("                                        <li class=\'nav-item\' data_jurisdiction=\'admini\' style=\'display: none\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'../pages/commodityType.html\'>商品分类</a>");
document.writeln("                                        </li>");
document.writeln("                                    </ul>");
document.writeln("                                </div>");
document.writeln("                            </li>");

document.writeln("                            <li class=\'nav-item \'>");
document.writeln("                                <a class=\'nav-link active\' href=\'#\' data-toggle=\'collapse\' aria-expanded=\'false\' data-target=\'#submenu-2\' aria-controls=\'submenu-1\'><i class=\'fas fa-database\'></i>库存管理 <span class=\'badge badge-success\'>6</span></a>");
document.writeln("                                <div id=\'submenu-2\' class=\'collapse submenu\' style=\'\'>");
document.writeln("                                    <ul class=\'nav flex-column\'>");
document.writeln("                                        <li class=\'nav-item\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'../pages/warehousing.html\'>入库订单</a>");
document.writeln("                                        </li>");
document.writeln("                                        <li class=\'nav-item\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'../pages/warehousingAdd.html\'>入库订单添加</a>");
document.writeln("                                        </li>");
document.writeln("                                        <li class=\'nav-item\' data_jurisdiction=\'admini\' style=\'display: none\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'../pages/OutOrder.html\'>出库订单</a>");
document.writeln("                                        </li>");
document.writeln("										<li class=\'nav-item\' data_jurisdiction=\'admini\' style=\'display: none\'>");
document.writeln("										    <a class=\'nav-link\' href=\'../pages/warehousingOut.html\'>出库订单添加</a>");
document.writeln("										</li>");
document.writeln("                                    </ul>");
document.writeln("                                </div>");
document.writeln("                            </li>");

document.writeln("                            <li class=\'nav-item \' data_jurisdiction=\'admini\' style=\'display: none\'>");
document.writeln("                                <a class=\'nav-link active\' href=\'#\' data-toggle=\'collapse\' aria-expanded=\'false\' data-target=\'#submenu-3\' aria-controls=\'submenu-1\'><i class=\'fas fa-server\'></i>供货商管理 <span class=\'badge badge-success\'>6</span></a>");
document.writeln("                                <div id=\'submenu-3\' class=\'collapse submenu\' style=\'\'>");
document.writeln("                                    <ul class=\'nav flex-column\'>");
document.writeln("                                        <li class=\'nav-item\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'../pages/supplier.html\'>供货商信息</a>");
document.writeln("                                        </li>");
document.writeln("                                        <li class=\'nav-item\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'../pages/supplierOrder.html\'>供货商订单</a>");
document.writeln("                                        </li>");
document.writeln("										<li class=\'nav-item\'>");
document.writeln("										    <a class=\'nav-link\' href=\'../pages/supplierManagement.html\'>供货商管理</a>");
document.writeln("										</li>");
document.writeln("                                    </ul>");
document.writeln("                                </div>");
document.writeln("                            </li>");

document.writeln("                            <li class=\'nav-item \' data_jurisdiction=\'admini\' style=\'display: none\'>");
document.writeln("                                <a class=\'nav-link active\' href=\'#\' data-toggle=\'collapse\' aria-expanded=\'false\' data-target=\'#submenu-4\' aria-controls=\'submenu-1\'><i class=\'fas fa-donate\'></i>资金管理 <span class=\'badge badge-success\'>6</span></a>");
document.writeln("                                <div id=\'submenu-4\' class=\'collapse submenu\' style=\'\'>");
document.writeln("                                    <ul class=\'nav flex-column\'>");
document.writeln("                                        <li class=\'nav-item\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'pages/commodity.html\'></a>");
document.writeln("                                        </li>");
document.writeln("                                        <li class=\'nav-item\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'commodityType.html\'>资金信息</a>");
document.writeln("                                        </li>");
document.writeln("                                    </ul>");
document.writeln("                                </div>");
document.writeln("                            </li>");

document.writeln("                            <li class=\'nav-item \' data_jurisdiction=\'admini\' style=\'display: none\'>");
document.writeln("                                <a class=\'nav-link active\' href=\'#\' data-toggle=\'collapse\' aria-expanded=\'false\' data-target=\'#submenu-5\' aria-controls=\'submenu-1\'><i class=\'fas fa-user\'></i>用户管理 <span class=\'badge badge-success\'>6</span></a>");
document.writeln("                                <div id=\'submenu-5\' class=\'collapse submenu\' style=\'\'>");
document.writeln("                                    <ul class=\'nav flex-column\'>");
document.writeln("                                        <li class=\'nav-item\'>");
document.writeln("                                            <a class=\'nav-link\' href=\'../pages/user.html\'>用户信息</a>");
document.writeln("                                        </li>");
document.writeln("                                    </ul>");
document.writeln("                                </div>");
document.writeln("                            </li>");

document.writeln("                        </ul>");
document.writeln("                    </div>");
document.writeln("                </nav>");
document.writeln("            </div>");
document.writeln("        </div>");
document.writeln("        <!-- ============================================================== -->");
document.writeln("        <!-- end left sidebar -->");
document.writeln("        <!-- ============================================================== -->");