package com.lds.supermarket.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 各种操作工具类
 */
@Component
public class MyUtils {
    private String sender = "2812510392@qq.com";//邮件发送地址
    private JavaMailSender javaMailSender;
    public MyUtils(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    /**
     * 验证码发送
     * @return
     */
    public Map<String,Object> PostVerification(String email){
        Map<String,Object> map = new HashMap<>();
        if(email == null || !isEmail(email)){
            map.put("result","ERROR");
            map.put("msg","请传入合法的邮箱");
            return  map;
        }
        String str = "";
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        for (int i = 0; i <6; i++){
            char num = ch[random.nextInt(ch.length)];
            str += num;
        }
        String title = "超市后台管理系统邮箱绑定";
        String text = "你的验证码为："+str+",该验证码有效期为:10分钟,输入验证码时不区分大小写，如果不是本人操作，请忽略本次邮件!";
        sendVerification(email,title,text);

        map.put("result","SUCCESS");
        map.put("email",email);
        map.put("verification",str);
        map.put("msg","发送邮箱为:"+email+"随机验证码为:"+str);
        return  map;
    }

    /**
     * 判断email是否是正确的邮箱地址
     * @param email
     * @return
     */
    public boolean isEmail(String  email){
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 开始发送验证码
     */
    public boolean sendVerification(String email,String title,String text){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email);
        mailMessage.setSubject(title);
        mailMessage.setText(text);
        try {
            javaMailSender.send(mailMessage);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送邮件失败");
            return false;
        }
        return true;
    }

}
