package com.lds.supermarket.dao;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 获取全部商品信息
     * @return
     */
    @Select("SELECT * FROM user")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            )
    })
    public List<User> getAllUser();

    /**
     * 根据分页信息获取相应数据
     * page:开始记录序号
     * size:记录条数
     * @param page
     * @param size
     * @return
     */
    @Select("SELECT * FROM user limit #{page},#{size}")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            )
    })
    public List<User> getAllUserByPage(Integer page, Integer size);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM user where id=#{id}")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            )
    })
    public User getUserById(Integer id);

    /**
     * 根据user获取用户信息
     * @param user
     * @return
     */
    @Select("SELECT * FROM user where account=#{account} and passWord=#{passWord}")
    @Results({
            @Result(column="supplierId",property="supplierId"),
            @Result(
                    property = "supplier",
                    column = "supplierId",
                    one = @One(select = "com.lds.supermarket.dao.SupplierDao.getSupplierById")
            ),
    })
    public User getUserByPas(User user);


    /**
     * 根据user对象更新数据
     * @param user
     */
    @Update("update user set jurisdiction=#{jurisdiction},jurisdictionName=#{jurisdictionName}," +
            "supplierId=#{supplierId},,email=#{email},,iconName=#{iconName} where id=#{id}")
    public void update(User user);

    /**
     * 根据id修改密码
     * @param passWord
     *  @param id
     */
    @Update("update user set passWord=#{passWord} where id=#{id}")
    public void updatePassWord(String passWord,Integer id);

    /**
     * 根据id修改头像
     * @param iconName
     *  @param id
     */
    @Update("update user set iconName=#{iconName} where id=#{id}")
    public void updateIconName(String iconName,Integer id);

    /**
     * 查找账号是否已经存在
     * @param account
     */
    @Select("SELECT COUNT(*) FROM user where account=#{account}")
    public Integer getUserByAccount(String account);

    /**
     * 查找旧密码是否真诚
     * @param oldPassWord
     */
    @Select("SELECT COUNT(*) FROM user where passWord=#{oldPassWord} and id=#{id}")
    public Integer getUserByOldPassWord(String oldPassWord,Integer id);
    /**
     * 根据user插入数据
     * @param user
     */
    @Insert("insert into user(account,passWord,jurisdiction,jurisdictionName,supplierId,email,iconName) " +
            "values(#{account},#{passWord},#{jurisdiction},#{jurisdictionName},#{supplierId},#{email},#{iconName})")
    public void insert(User user);

    /**
     * 获取总共页数
     * @return
     */
    @Select("SELECT COUNT(*) FROM user")
    public Integer getUserCount();

    /**
     * 根据id删除数据
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    public void delete(Integer id);
}
