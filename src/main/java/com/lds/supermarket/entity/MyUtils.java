package com.lds.supermarket.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
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
    private String sender = "2812510392@qq.com";//邮件发送弟子

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
        sendVerification(email,"验证码",str);

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
//        try {
//            SimpleMailMessage mainMessage = new SimpleMailMessage();
//            System.out.println("开始邮件发送");
//            System.out.println("发送方地址为"+sender);
//            mainMessage.setTo(email);
//            mainMessage.setFrom(sender);
//            //发送的标题
//            mainMessage.setSubject(title);
//            //发送的内容
//            mainMessage.setText(text);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return true;
    }

}
