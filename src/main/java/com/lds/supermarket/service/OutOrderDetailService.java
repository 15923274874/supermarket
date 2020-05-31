package com.lds.supermarket.service;

import com.lds.supermarket.entity.OutOrder;
import com.lds.supermarket.entity.OutOrderDetail;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.entity.SupplierOrderDetail;

import java.util.List;

public interface OutOrderDetailService {
    /**
     * 根据outOrderId获取订单详情
     * @param outOrderId
     * @return
     */
    public List<OutOrderDetail> getOutOrderDetailByOutOrderId(Integer outOrderId);

    /**
     * 删除outOrderId相关数据
     * @param outOrderId
     * @return
     */
    public boolean delByOutOrderId(Integer outOrderId);

    /**
     * 根据details更新数据
     * @param details
     * @param outOrder   所属订单信息
     * @return
     */
    public boolean save(List<OutOrderDetail> details, OutOrder outOrder);
}
