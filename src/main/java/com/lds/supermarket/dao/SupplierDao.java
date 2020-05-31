package com.lds.supermarket.dao;

import com.lds.supermarket.entity.Supplier;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SupplierDao {

    /**
     * 获取全部供货商信息
     * @return
     */
    @Select("SELECT * FROM supplier")
//    @Results({
//            @Result(column="id",property="id",id=true),
//            @Result(
//                    property = "commodityList",
//                    column = "id",
//                    many = @Many(select = "com.lds.supermarket.dao.CommodityDao.getCommodityById")
//            )
//    })
    public List<Supplier> getAllSupplier();

    /**
     * 根据分页信息获取相应数据
     * page:开始记录序号
     * size:记录条数
     * @param page
     * @param size
     * @return
     */
    @Select("SELECT * FROM supplier limit #{page},#{size}")
//    @Results({
//            @Result(column="id",property="id",id=true),
//            @Result(
//                    property = "commodityList",
//                    column = "id",
//                    many = @Many(select = "com.lds.supermarket.dao.CommodityDao.getCommodityById")
//            )
//    })
    public List<Supplier> getAllSupplierByPage(Integer page,Integer size);

    /**
     * 根据id获取供货商信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM supplier where id=#{id}")
//    @Results({
//            @Result(column="id",property="id",id=true),
//            @Result(
//                    property = "commodityList",
//                    column = "id",
//                    many = @Many(select = "com.lds.supermarket.dao.CommodityDao.getCommodityById")
//            )
//    })
    public Supplier getSupplierById(Integer id);


    /**
     * 根据supplier对象更新数据
     * @param supplier
     */
    @Update("update supplier set supplierName=#{supplierName},supplierEdit=#{supplierEdit}," +
            "supplierPeople=#{supplierPeople},supplierPhone=#{supplierPhone},email=#{email}," +
            "address=#{address} where id=#{id}")
    public void update(Supplier supplier);

    /**
     * 根据supplier插入数据
     * @param supplier
     */
    @Insert("insert into supplier(supplierName,supplierEdit,supplierPeople,supplierPhone,email,address) " +
            "values(#{supplierName},#{supplierEdit},#{supplierPeople},#{supplierPhone},#{email},#{address})")
    public void insert(Supplier supplier);

    /**
     * 根据id删除数据
     * @param id
     */
    @Delete("delete from supplier where id=#{id}")
    public void delete(Integer id);

    /**
     * 获取总共页数
     * @return
     */
    @Select("SELECT COUNT(*) FROM supplier")
    public Integer getSupplierCount();
}
