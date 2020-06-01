package com.lds.supermarket.controller;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.User;
import com.lds.supermarket.service.CommodityService;
import com.lds.supermarket.service.LoginService;
import com.lds.supermarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin//解决跨域请求无响应头问题
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;
    /**
     * 获取供货商信息
     * nowPage:查询页码
     * size:记录条数
     * @param nowPage
     * @param size
     * @return
     */
    @RequestMapping("/getAllUser")
    public Map<String,Object> getAllUser(Integer nowPage,Integer size){
        Map<String,Object> map = new HashMap<String,Object>();
        Page<User> page = null;
        map.put("code",1);
        map.put("request","SUCCESS");
        if(nowPage == null || size == null){
            page = userService.getAllUser();
        }else {
            page = userService.getAllUser(nowPage,size);
        }
        map.put("result",page);
        return map;
    }

    /**
     * 根据id获取单个供应商信息
     * @param id
     * @return
     */
    @RequestMapping("/getUserById")
    public Map<String,Object> getUserById(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",1);
        map.put("request","SUCCESS");
        User user = userService.getUserById(id);
        map.put("result",user);
        return  map;
    }

    @RequestMapping("/save")
    public Map<String,String> update(User user){
        Map<String,String> map = new HashMap<String,String>();
        map = userService.save(user);
        return  map;
    }

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @RequestMapping("/del")
    public Map<String,Object> delete(Integer id){
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = userService.del(id);
        if(flag){
            map.put("code",1);
            map.put("request","SUCCESS");
        }else {
            map.put("code",-1);
            map.put("request","ERROR");
        }
        return  map;
    }

    /**
     * 修改密码
     * @param oldPassWord
     * @param newPassWord
     * @return
     */
    @RequestMapping("/updatePassword")
    public Map<String,Object> updatePassword(HttpSession session, String oldPassWord, String newPassWord){
        Map<String,Object> map = new HashMap<String,Object>();
        User user = (User) session.getAttribute("user");
        if(user == null){
            map.put("code",-1);
            map.put("request","ERROR");
            map.put("info","请先登录！");
        }else {
            Map<String,String > infoMap = userService.updatePassWord(oldPassWord,newPassWord,user.getId());
            map.put("info",infoMap.get("info"));
            map.put("code",infoMap.get("code"));
            map.put("request",infoMap.get("request"));
        }
        return  map;
    }
}