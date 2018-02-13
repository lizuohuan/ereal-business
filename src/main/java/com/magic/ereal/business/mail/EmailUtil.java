package com.magic.ereal.business.mail;

import com.magic.ereal.business.entity.Email;

import java.util.List;

/**
 * 邮件发送工具类
 * Created by Eric Xie on 2017/6/14 0014.
 */
public class EmailUtil {




    public static void sendEmail(Email email){
        new Thread(new SendEmailThread(email.getEmailAddress(),email.getSubject(),email.getContent())).start();
    }



    public static void sendEmail(List<Email> emails){
        for (Email email : emails) {
            new Thread(new SendEmailThread(email.getEmailAddress(),email.getSubject(),email.getContent())).start();
        }
    }



    public static void sendEmail(List<String> emails,String subject, String content){
        new Thread(new SendEmailThread(emails,subject,content)).start();
    }

    public static void main(String[] args) {

        sendEmail(new Email("616650704@qq.com","测试","测试"));
    }





}
