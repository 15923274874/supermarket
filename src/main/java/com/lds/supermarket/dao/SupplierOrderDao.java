package com.lds.supermarket.dao;

import com.lds.supermarket.entity.Supplier;
import com.lds.supermarket.entity.SupplierOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SupplierOrderDao {

    /**
     * 获取全部供货商订单
     * @return
     */
    @Select("SELECT * FROM supplierOrder")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            )
    })
    public List<SupplierOrder> getAllSupplierOrder();

    /**
     * 根据分页信息获取相应数据
     * page:开始记录序号
     * size:记录条数
     * @param page
     * @param size
     * @return
     */
    @Select("SELECT * FROM supplierOrder limit #{page},#{size}")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            )
    })
    public List<SupplierOrder> getAllSupplierOrderByPage(Integer page, Integer size);

    /**
     * 根据id获取供货商信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM supplierOrder where id=#{id}")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            )
    })
    public SupplierOrder getSupplierOrderById(Integer id);


    /**
     * 根据supplierOrder对象更新数据
     * @param supplierOrder
     */
    @Update("update supplierOrder set time=#{time},title=#{title}," +
            "supplierId=#{supplierId},typeNum=#{typeNum},commodityNum=#{commodityNum}," +
            "price=#{price},state=#{state},edit=#{edit},info=#{info} where id=#{id}")
    public void update(SupplierOrder supplierOrder);

    /**
     * 根据supplier插入数据
     * @param supplierOrder
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into supplierOrder(time,title,supplierId,typeNum,commodityNum,price,state,edit,info) " +
            "values(#{time},#{title},#{supplierId},#{typeNum},#{commodityNum},#{price},#{state},#{edit},#{info})")
    public void insert(SupplierOrder supplierOrder);

    /**
     * 根据id删除数据
     * @param id
     */
    @Delete("delete from supplierOrder where id=#{id}")
    public void delete(Integer id);

    /**
     * 获取总共页数
     * @return
     */
    @Select("SELECT COUNT(*) FROM supplierOrder")
    public Integer getSupplierOrderCount();
}
