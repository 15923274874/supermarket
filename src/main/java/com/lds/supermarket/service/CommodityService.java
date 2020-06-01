package com.lds.supermarket.service;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.User;

public interface CommodityService {

    /**
     * 获取全部供应商信息
     * @param user  用户
     * @return
     */
    public Page<Commodity> getAllCommodity(User user);

    /**
     * 根据分页信息获取相应数据
     * @param user  用户
     * @param nowPage 查询页数
     * @param size 记录条数
     * @return
     */
    public Page<Commodity> getAllCommodity(Integer nowPage, Integer size,User user);


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
