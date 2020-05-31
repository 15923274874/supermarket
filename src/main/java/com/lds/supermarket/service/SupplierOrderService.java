package com.lds.supermarket.service;

import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.SupplierOrder;

public interface SupplierOrderService {

    /**
     * 获取全部供应商订单
     * @return
     */
    public Page<SupplierOrder> getAllSupplierOrder();

    /**
     * 根据分页信息获取相应数据
     * nowPage:查询页数
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    public Page<SupplierOrder> getAllSupplierOrder(Integer nowPage, Integer size);


    /**
     * 根据supplierOrder更新数据
     * @param supplierOrder
     * @return
     */
    public boolean save(SupplierOrder supplierOrder);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    public boolean del(Integer id);
    /**
     * 根据id获取供应商
     * @param id
     * @return
     */
    public SupplierOrder getSupplierOrderById(Integer id);
}
