package com.lds.supermarket.service;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;

public interface CommodityService {

    /**
     * 获取全部供应商信息
     * @return
     */
    public Page<Commodity> getAllCommodity();

    /**
     * 根据分页信息获取相应数据
     * nowPage:查询页数
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    public Page<Commodity> getAllCommodity(Integer nowPage, Integer size);


    /**
     * 根据commodity更新数据
     * @param commodity
     * @return
     */
    public boolean save(Commodity commodity);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
//    public boolean del(Integer id);
    /**
     * 根据id获取供应商
     * @param id
     * @return
     */
    public Commodity getCommodityById(Integer id);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    public boolean del(Integer id);
}
