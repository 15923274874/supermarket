package com.lds.supermarket.service;

import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.entity.SupplierOrderDetail;

import java.util.List;

public interface SupplierOrderDetailService {
    /**
     * 根据supplierOrderId获取订单详情
     * @param supplierOrderId
     * @return
     */
    public List<SupplierOrderDetail> getSupplierOrderDetailBySupplierOrderId(Integer supplierOrderId);

    /**
     * 删除supplierOrderId相关数据
     * @param supplierOrderId
     * @return
     */
    public boolean delBySupplierOrderId(Integer supplierOrderId);

    /**
     * 根据details更新数据
     * @param details
     * @param supplierOrder   所属订单信息
     * @return
     */
    public boolean save(List<SupplierOrderDetail> details,SupplierOrder supplierOrder);
}
