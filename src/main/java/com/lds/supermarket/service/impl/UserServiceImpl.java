package com.lds.supermarket.service.impl;

import com.lds.supermarket.dao.UserDao;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.User;
import com.lds.supermarket.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Page<User> getAllUser() {

        Page<User> page = new Page<User>();
        page.setCountSum(userDao.getUserCount());//设置总记录条数
        page.setNowPage(1);//设置当前页码
        page.setCountNum(page.getCountSum());//设置显示记录条数
        page.setPageSum();//设置总页码

        page.setList(userDao.getAllUser());
        return page;
    }

    @Override
    public Page<User> getAllUser(Integer nowPage, Integer size) {

        Page<User> page = new Page<User>();
        page.setCountSum(userDao.getUserCount());//设置总记录条数
        page.setNowPage(nowPage);//设置当前页码
        page.setCountNum(size);//设置显示记录条数
        page.setPageSum();//设置总页码
        Integer countStart = (page.getNowPage()-1) * size;//设置开始查询记录数
        page.setList(userDao.getAllUserByPage(countStart,size));
        return page;
    }


    @Override
    public Map<String, String> save(User user) {
        Map<String,String> map = new  HashMap<>();
        try {
            if(user.getId() != null){
                userDao.update(user);
                map.put("request","SUCCESS");
                map.put("info","修改数据成功");
            }else{
                if(userDao.getUserByAccount(user.getAccount()) == 0){
                    userDao.insert(user);
                    map.put("request","SUCCESS");
                    map.put("info","添加用户成功，默认密码为"+user.getPassWord());
                }else {
                    map.put("request","ERROR");
                    map.put("info","添加用户失败，系统已存在账号名为"+user.getAccount()+"的账号,请更换账号");
                }
            }
            return map;
        }catch(Exception e){
            e.printStackTrace();
            map.put("request","ERROR");
            map.put("info",e.getMessage());
            return map;
        }
    }

    @Override
    public boolean del(Integer id) {
        try {
             if(id != null){
                 userDao.delete(id);
                 return true;
             }else{
                 return false;
             }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
