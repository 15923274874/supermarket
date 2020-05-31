package com.lds.supermarket.service;

import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;

import java.util.List;

public interface SupplierService {

    /**
     * 获取全部供应商信息
     * @return
     */
    public Page<Supplier> getAllSupplier();

    /**
     * 根据分页信息获取相应数据
     * nowPage:查询页数
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    public Page<Supplier> getAllSupplier(Integer nowPage,Integer size);


    /**
     * 根据supplier更新数据
     * @param supplier
     * @return
     */
    public boolean save(Supplier supplier);

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
    public Supplier getSupplierById(Integer id);
}
