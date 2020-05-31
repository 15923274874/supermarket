package com.lds.supermarket.service;

import com.lds.supermarket.entity.OutOrder;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.SupplierOrder;

import java.util.Map;

public interface OutOrderService {

    /**
     * 获取全部出库订单
     * @return
     */
    public Page<OutOrder> getAllOutOrder();

    /**
     * 根据分页信息获取相应数据
     * nowPage:查询页数
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    public Page<OutOrder> getAllOutOrder(Integer nowPage, Integer size);


    /**
     * 根据outOrder更新数据
     * @param outOrder
     * @return
     */
    public Map<String,String> save(OutOrder outOrder);

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
    public OutOrder getOutOrderById(Integer id);
}
