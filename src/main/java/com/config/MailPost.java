package com.config;


import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Random;

@Component
public class MailPost {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.address}")
    private String From;
    public String MailPost(String email)throws Exception{
        long Now=System.currentTimeMillis();
        long VALID_TIME = 1000 * 60 * 2;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String NowTime=format.format(Now);
        String ValidTIme=format.format(Now+VALID_TIME);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(From);
            message.setTo(email);
            message.setSubject("leyou注册验证码");
            message.setText("您的注册码为"+ RandomUtils.nextInt(new Random(99999),999999)+"两分钟内有效。为了您账户的安全，请勿将验证码转发给他人。如非本人接收，请忽略该邮件。");
            javaMailSender.send(message);
            return ValidTIme;
        }catch (Exception e){
           throw new Exception("检测到异常!");
        }

    }
}
