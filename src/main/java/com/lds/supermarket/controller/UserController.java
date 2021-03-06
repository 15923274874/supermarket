package com.lds.supermarket.controller;

import com.lds.supermarket.entity.Commodity;
import com.lds.supermarket.entity.MyUtils;
import com.lds.supermarket.entity.Page;
import com.lds.supermarket.entity.User;
import com.lds.supermarket.service.CommodityService;
import com.lds.supermarket.service.LoginService;
import com.lds.supermarket.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user")
@CrossOrigin//解决跨域请求无响应头问题
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private JavaMailSender javaMailSender;//邮箱发送对象

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


    @RequestMapping("/iconUpload")
    public   Map<String,Object> importPicFile1(HttpSession session,HttpServletRequest req, MultipartFile file) {

        Map<String, Object> map = new HashMap<String, Object>();
        User user = (User) session.getAttribute("user");
        if (file.isEmpty()) {
            map.put("result", "error");
            map.put("msg", "上传文件不能为空");
        } else {
            String oriName = file.getOriginalFilename();
            int lastIndexOf = oriName.lastIndexOf(".");
            //获取文件的后缀名 .jpg
            String suffix = oriName.substring(lastIndexOf);
            oriName = user.getId()+suffix;
            try{
                String filePath = "c:\\supermarket\\userIcon\\";//文件保存路径需要创建目录
                File dest = new File(filePath + oriName);
                if(!dest.exists()){
                    dest.mkdirs();//文件不存在创建文件
                }
                file.transferTo(dest);
                userService.updateIcon(oriName,user.getId());
                map.put( "result", "success");
                map.put( "msg","图片上传成功");

            } catch (Exception e) {
                map.put( "result", "error");
                map.put( "msg",e.getMessage());

            }
        }
//        String result=String. valueOf(JSONObject.fromObject (map));
        return map;
    }

    /**
//     * 图片下载，该项目未用
     * @param response
     * @return
     */
//    @RequestMapping("/iconDownload")
    public Map<String,Object>  testDownload(HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String,Object>();
        //待下载文件名
        String fileName = "android.png";
        //设置为png格式的文件
        response.setHeader("content-type", "image/*");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(new File("c:\\supermarket\\userIcon\\" + fileName )));
            int read = bis.read(buff);
            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
            map.put("result","success");
            map.put("msg","文件下载成功");
        } catch (IOException e ) {
            e.printStackTrace();
            map.put("result","error");
            map.put("msg",e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 获取验证码
     * @return
     */

    @RequestMapping("/getVerification")
    public Map<String,Object>  getVerification(HttpSession session,String email) {
        Map<String,Object> map = new HashMap<String,Object>();
        MyUtils utils = new MyUtils(javaMailSender);
        Map<String,Object> resultMap = utils.PostVerification(email);
        if(resultMap.get("result").equals("SUCCESS")){
            session.setAttribute("email",resultMap.get("email"));
            session.setAttribute("verification",resultMap.get("verification"));
            Date dd=new Date();
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sim.format(dd);
            session.setAttribute("verificationTime",time);
            map.put("request","SUCCESS");
            map.put("msg","验证码发送成功");
        }else {
            System.out.println("验证码发送失败");
            map.put("request","ERROR");
            map.put("msg","验证码发送失败");
        }
        return map;
    }

    /**
     * 保存邮箱
     * @param session
     * @param userVerification
     * @return
     * @throws ParseException
     */
    @RequestMapping("/saveEmail")
    public Map<String,Object>  saveEmail(HttpSession session,String userVerification) throws ParseException {
        Map<String,Object> map = new HashMap<String,Object>();
        String email = (String) session.getAttribute("email");//获取session中邮箱
        String verification = (String) session.getAttribute("verification");//获取session中验证码
        String time = (String) session.getAttribute("verificationTime");//获取发送验证码的时间
        Date dd=new Date();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newTime=sim.format(dd);//获取当前时间
        if(verification == null){//如过断开连接
            map.put("request","error");
            map.put("msg","请先发送验证码");
        }else{
            Date d1 = sim.parse(newTime);
            Date d2 = sim.parse(time);
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
            long s = diff / 1000;  //获取时间差
            if(s < 10*30){    //判断是否超过10分钟
                if(userVerification.toLowerCase().equals(verification.toLowerCase())){
                    User user = (User) session.getAttribute("user");
                    userService.updateEmail(email,user.getId());
                    map.put("request","success");
                    map.put("msg","邮箱保存成功");
                }else{
                    map.put("request","success");
                    map.put("msg","邮箱保存成功");
                }
            }else {
                map.put("request","error");
                map.put("msg","验证码已过期，请重新获取");
            }
        }
        return map;
    }
    @RequestMapping("/fogetPassWord")
    public Map<String,Object>  saveEmail(HttpSession session,String account,String userVerification) throws ParseException {
        Map<String,Object> map = new HashMap<String,Object>();
        String email = (String) session.getAttribute("email");//获取session中邮箱
        String verification = (String) session.getAttribute("verification");//获取session中验证码
        String time = (String) session.getAttribute("verificationTime");//获取发送验证码的时间
        Date dd=new Date();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newTime=sim.format(dd);//获取当前时间
        if(verification == null){//如过断开连接
            map.put("request","error");
            map.put("msg","请先发送验证码");
        }else{
            Date d1 = sim.parse(newTime);
            Date d2 = sim.parse(time);
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
            long s = diff / 1000;  //获取时间差
            if(s < 10*30){    //判断是否超过10分钟
                if(userVerification.toLowerCase().equals(verification.toLowerCase())){
                    User user =  userService.getUserByAccountAndEmail(account,email);
                    if(user == null){
                        map.put("request","error");
                        map.put("msg","账号或邮箱错误，请检查是否有误!");
                    }else {
                        userService.registPassWord("123456",user.getId());
                        map.put("request","success");
                        map.put("msg","密码重置成功，默认密码为123456，请尽快修改密码");
                    }
                }else{
                    map.put("request","error");
                    map.put("msg","验证码错误");
                }
            }else {
                map.put("request","error");
                map.put("msg","验证码已过期，请重新获取");
            }
        }
        return map;
    }

}