package com.lds.supermarket.controller;


import com.lds.supermarket.entity.User;
import com.lds.supermarket.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin//解决跨域请求无响应头问题
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/isLogin")
    private Map<String,Object> login(User user, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        user = loginService.getUserBypas(user);
        if(user != null){
            map.put("code","1");
            map.put("request","SUCCESS");
            map.put("user",user);
            session.setAttribute("user",user);
        }else {
            map.put("code","-1");
            map.put("request","ERROR");
            map.put("info","用户名或密码错误");
        }
        return  map;
    }

    @RequestMapping("/getLoginUser")
    private Map<String,Object> login(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if(user != null){
            map.put("code","1");
            map.put("request","SUCCESS");
            map.put("user",user);
        }else {
            map.put("code","-1");
            map.put("request","ERROR");
            map.put("info","未登录");
        }
        return  map;
    }

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping("/userQuit")
    private Map<String,Object> quit(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        session.setAttribute("user",null);
        User user = (User) session.getAttribute("user");
        if(user == null){
            map.put("code","1");
            map.put("request","SUCCESS");
            map.put("user",user);
        }else {
            map.put("code","-1");
            map.put("request","ERROR");
            map.put("info","未登录");
        }
        return  map;
    }
}
