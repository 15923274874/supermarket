package com.lds.supermarket.dao;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.SupplierOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommodityDao {

    /**
     * 获取全部商品信息
     * @return
     */
    @Select("SELECT * FROM commodity")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            ),
            @Result(column="typeId",property="typeId"),
            @Result(
                    property = "commodityType",
                    column = "typeId",
                    one = @One(select = "com.lds.supermarket.dao.CommodityTypeDao.getCommodityTypeById")
            )
    })
    public List<Commodity> getAllCommodity();

    /**
     * 根据分页信息获取相应数据
     * page:开始记录序号
     * size:记录条数
     * @param page
     * @param size
     * @return
     */
    @Select("SELECT * FROM commodity limit #{page},#{size}")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            ),
            @Result(column="typeId",property="typeId"),
            @Result(
                    property = "commodityType",
                    column = "typeId",
                    one = @One(select = "com.lds.supermarket.dao.CommodityTypeDao.getCommodityTypeById")
            )
    })
    public List<Commodity> getAllCommodityByPage(Integer page,Integer size);

    /**
     * 根据id获取商品信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM commodity where id=#{id}")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            ),
            @Result(column="typeId",property="typeId"),
            @Result(
                    property = "commodityType",
                    column = "typeId",
                    one = @One(select = "com.lds.supermarket.dao.CommodityTypeDao.getCommodityTypeById")
            )
    })
    public Commodity getCommodityById(Integer id);

    /**
     * 根据commodity对象更新数据
     * @param commodity
     */
    @Update("update commodity set commodityName=#{commodityName},commodityEdit=#{commodityEdit}," +
            "commodityPrice=#{commodityPrice},commodityCales=#{commodityCales},commodityStock=#{commodityStock}," +
            "supplierId=#{supplierId},typeId=#{typeId} where id=#{id}")
    public void update(Commodity commodity);

    /**
     * 根据id和stock添加库存
     * @param id
     * @param stock
     */
    @Update("update commodity set commodityStock=commodityStock+#{stock} where id=#{id}")
    public void updateCommodityStock(Integer id,Integer stock);

    /**
     * 根据id和stock出库
     * @param id
     * @param stock
     */
    @Update("update commodity set commodityStock=commodityStock-#{stock},commodityCales=commodityCales+#{stock} where id=#{id}")
    public void updateCommodityStockOnOut(Integer id,Integer stock);
    /**
     * 根据commodity插入数据
     * @param commodity
     */
    @Insert("insert into commodity(commodityName,commodityEdit,commodityPrice,commodityCales,commodityStock,supplierId,typeId) " +
            "values(#{commodityName},#{commodityEdit},#{commodityPrice},#{commodityCales},#{commodityStock},#{supplierId},#{typeId})")
    public void insert(Commodity commodity);

    /**
     * 获取总共页数
     * @return
     */
    @Select("SELECT COUNT(*) FROM commodity")
    public Integer getCommodityCount();

    /**
     * 根据id删除数据
     * @param id
     */
    @Delete("delete from commodity where id=#{id}")
    public void delete(Integer id);
}
