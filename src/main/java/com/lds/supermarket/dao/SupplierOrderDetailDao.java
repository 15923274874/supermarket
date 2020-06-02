package com.lds.supermarket.dao;

import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.SupplierOrder;
import com.lds.supermarket.entity.SupplierOrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SupplierOrderDetailDao {

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
     * 根据supplierOrderId获取订单详情
     * @param supplierOrderId
     * @return
     */
    @Select("SELECT * FROM supplierOrderDetail where supplierOrderId=#{supplierOrderId}")
    @Results({
            @Result(column="commodityId",property="commodityId"),
            @Result(
                    property = "commodity",
                    column = "commodityId",
                    one = @One(select = "com.lds.supermarket.dao.CommodityDao.getCommodityById")
            )
    })
    public List<SupplierOrderDetail> getSupplierOrderDetailBySupplierOrderId(Integer supplierOrderId);


    /**
     * 根据supplierOrderDetail对象更新数据
     * @param supplierOrderDetail
     */
    @Update("update supplierOrderDetail set commodityId=#{commodityId},commodityName=#{commodityName},supplierOrderId=#{supplierOrderId}," +
            "price=#{price},num=#{num},sumPrice=#{sumPrice}," +
            "edit=#{edit} where id=#{id}")
    public void update(SupplierOrderDetail supplierOrderDetail);

    /**
     * 根据supplierOrderDetail插入数据
     * @param supplierOrderDetail
     */
    @Insert("insert into supplierOrderDetail(commodityId,commodityName,supplierOrderId,price,num,sumPrice,edit) " +
            "values(#{commodityId},#{commodityName},#{supplierOrderId},#{price},#{num},#{sumPrice},#{edit})")
    public void insert(SupplierOrderDetail supplierOrderDetail);

    /**
     * 根据id删除数据
     * @param supplierOrderId
     */
    @Delete("delete from supplierOrderDetail where supplierOrderId=#{supplierOrderId}")
    public void deleteBySupplierOrderId(Integer supplierOrderId);

    /**
     * 获取总共页数
     * @return
     */
//    @Select("SELECT COUNT(*) FROM supplierOrder")
//    public Integer getSupplierOrderCount();
}
