package com.lds.supermarket.dao;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.CommodityType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommodityTypeDao {

    /**
     * 获取全部商品信息
     * @return
     */
    @Select("SELECT * FROM commodityType")
    public List<CommodityType> getAllCommodityType();

    /**
     * 根据分页信息获取相应数据
     * page:开始记录序号
     * size:记录条数
     * @param page
     * @param size
     * @return
     */
    @Select("SELECT * FROM commodityType limit #{page},#{size}")
    public List<CommodityType> getAllCommodityTypeByPage(Integer page, Integer size);

    /**
     * 根据id获取商品类型
     * @param id
     * @return
     */
    @Select("SELECT * FROM commodityType where id=#{id}")
    public CommodityType getCommodityTypeById(Integer id);

    /**
     * 根据CommodityType对象更新数据
     * @param commodityType
     */
    @Update("update commodityType set commodityType=#{commodityType},personNo=#{personNo}," +
            "personName=#{personName},personPhone=#{personPhone} where id=#{id}")
    public void update(CommodityType commodityType);

    /**
     * 根据commodity插入数据
     * @param commodityType
     */
    @Insert("insert into commodityType(commodityType,personNo,personName,personPhone) " +
            "values(#{commodityType},#{personNo},#{personName},#{personPhone})")
    public void insert(CommodityType commodityType);

    /**
     * 获取总共页数
     * @return
     */
    @Select("SELECT COUNT(*) FROM commodityType")
    public Integer getCommodityTypeCount();

    /**
     * 根据id删除数据
     * @param id
     */
    @Delete("delete from commodityType where id=#{id}")
    public void delete(Integer id);
}
