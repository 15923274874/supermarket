package com.lds.supermarket.service;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.CommodityType;
import com.lds.supermarket.entity.Page;

public interface CommodityTypeService {

    /**
     * 获取全部商品类型
     * @return
     */
    public Page<CommodityType> getAllCommodityType();

    /**
     * 根据分页信息获取相应数据
     * nowPage:查询页数
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    public Page<CommodityType> getAllCommodityType(Integer nowPage, Integer size);


    /**
     * 根据commodity更新数据
     * @param commodityType
     * @return
     */
    public boolean save(CommodityType commodityType);

    /**
     * 根据id获取供应商
     * @param id
     * @return
     */
    public CommodityType getCommodityTypeById(Integer id);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    public boolean del(Integer id);
}
