package com.lds.supermarket.dao;

import com.lds.supermarket.entity.OutOrderDetail;
import com.lds.supermarket.entity.SupplierOrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OutOrderDetailDao {

    /**
     * 获取全部供订单详情
     * @return
     */
//    @Select("SELECT * FROM supplierOrder")
//    @Results({
//            @Result(column="supplierId",property="supplierId"),
//            @Result(
//                    property = "supplier",
//                    column = "supplierId",
//                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
//            )
//    })
//    public List<SupplierOrder> getAllSupplierOrder();

    /**
     * 根据分页信息获取相应数据
     * page:开始记录序号
     * size:记录条数
     * @param page
     * @param size
     * @return
     */
//    @Select("SELECT * FROM supplierOrder limit #{page},#{size}")
//    @Results({
//            @Result(column="supplierId",property="supplierId"),
//            @Result(
//                    property = "supplier",
//                    column = "supplierId",
//                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
//            )
//    })
//    public List<SupplierOrder> getAllSupplierOrderByPage(Integer page, Integer size);

    /**
     * 根据outOrderId获取订单详情
     * @param outOrderId
     * @return
     */
    @Select("SELECT * FROM outOrderDetail where outOrderId=#{outOrderId}")
    @Results({
            @Result(column="commodityId",property="commodityId"),
            @Result(
                    property = "commodity",
                    column = "commodityId",
                    one = @One(select = "com.lds.supermarket.dao.CommodityDao.getCommodityById")
            )
    })
    public List<OutOrderDetail> getOutOrderDetailByOutOrderId(Integer outOrderId);


    /**
     * 根据outOrderDetail对象更新数据
     * @param outOrderDetail
     */
    @Update("update outOrderDetail set commodityId=#{commodityId},outOrderId=#{outOrderId}," +
            "price=#{price},num=#{num},sumPrice=#{sumPrice}," +
            "where id=#{id}")
    public void update(OutOrderDetail outOrderDetail);

    /**
     * 根据outOrderDetail插入数据
     * @param outOrderDetail
     */
    @Insert("insert into outOrderDetail(commodityId,outOrderId,price,num,sumPrice) " +
            "values(#{commodityId},#{outOrderId},#{price},#{num},#{sumPrice})")
    public void insert(OutOrderDetail outOrderDetail);

    /**
     * 根据id删除数据
     * @param outOrderId
     */
    @Delete("delete from outOrderDetail where outOrderId=#{outOrderId}")
    public void deleteByOutOrderId(Integer outOrderId);

    /**
     * 获取总共页数
     * @return
     */
//    @Select("SELECT COUNT(*) FROM supplierOrder")
//    public Integer getSupplierOrderCount();
}
