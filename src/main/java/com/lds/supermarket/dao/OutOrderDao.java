package com.lds.supermarket.dao;

import com.lds.supermarket.entity.OutOrder;
import com.lds.supermarket.entity.SupplierOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OutOrderDao {

    /**
     * 获取全部供货商订单
     * @return
     */
    @Select("SELECT * FROM outOrder")
//    @Results({
//            @Result(column="supplierId",property="supplierId"),
//            @Result(
//                    property = "supplier",
//                    column = "supplierId",
//                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
//            )
//    })
    public List<OutOrder> getAllOutOrder();

    /**
     * 根据分页信息获取相应数据
     * page:开始记录序号
     * size:记录条数
     * @param page
     * @param size
     * @return
     */
    @Select("SELECT * FROM outOrder limit #{page},#{size}")
//    @Results({
//            @Result(column="supplierId",property="supplierId"),
//            @Result(
//                    property = "supplier",
//                    column = "supplierId",
//                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
//            )
//    })
    public List<OutOrder> getAllOutOrderByPage(Integer page, Integer size);

    /**
     * 根据id获取出库订单
     * @param id
     * @return
     */
    @Select("SELECT * FROM outOrder where id=#{id}")
//    @Results({
//            @Result(column="supplierId",property="supplierId"),
//            @Result(
//                    property = "supplier",
//                    column = "supplierId",
//                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
//            )
//    })
    public OutOrder getOutOrderById(Integer id);


    /**
     * 根据outOrder对象更新数据
     * @param outOrder
     */
    @Update("update outOrder set time=#{time},title=#{title}," +
            "userId=#{userId},typeNum=#{typeNum},commodityNum=#{commodityNum}," +
            "price=#{price},state=#{state},info=#{info} where id=#{id}")
    public void update(OutOrder outOrder);

    /**
     * 根据outOrder插入数据
     * @param outOrder
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into outOrder(time,title,userId,typeNum,commodityNum,price,state,info) " +
            "values(#{time},#{title},#{userId},#{typeNum},#{commodityNum},#{price},#{state},#{info})")
    public void insert(OutOrder outOrder);

    /**
     * 根据id删除数据
     * @param id
     */
    @Delete("delete from outOrder where id=#{id}")
    public void delete(Integer id);

    /**
     * 获取总共页数
     * @return
     */
    @Select("SELECT COUNT(*) FROM outOrder")
    public Integer getoutOrderCount();
}
