package com.lds.supermarket.service;

import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.User;

import java.util.Map;

public interface UserService {

    /**
     * 获取全部账号
     * @return
     */
    public Page<User> getAllUser();

    /**
     * 根据分页信息获取相应数据
     * nowPage:查询页数
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    public Page<User> getAllUser(Integer nowPage, Integer size);


    /**
     * 根据User更新数据
     * @param User
     * @return
     */
    public Map<String, String> save(User User);

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
    public User getUserById(Integer id);

    /**
     * 判断账号密码是否匹配
     * @param account
     * @return
     */
    public User getUserByAccountAndEmail(String account,String email);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    public boolean del(Integer id);

    /**
     * 根据id修改密码
     * @param oldPassWord
     * @param newPassWord
     * @param id
     * @return
     */
    public  Map<String, String> updatePassWord(String oldPassWord,String newPassWord,Integer id);

    /**
     * 修改密码
     * @param newPassWord
     * @param id
     * @return
     */
    public Map<String, String> registPassWord(String newPassWord,Integer id);

    /**
     * 根据id修改头像
     * @param iconName
     * @param id
     * @return
     */
    public  Map<String, String> updateIcon(String iconName,Integer id);

    /**
     * 根据id绑定邮箱
     * @param email
     * @param id
     * @return
     */
    public  Map<String, String> updateEmail(String email,Integer id);

}
