package com.offcn;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailTest {

    @Test
    public void Test01() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        //获取mailSender邮件发送类
        JavaMailSenderImpl mailSender = context.getBean(JavaMailSenderImpl.class);
        //创建邮件发送实体对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("ssx1776483089@163.com");
        helper.setTo("1776483089@qq.com");
        helper.setSubject("这是发的测试邮件");
        helper.setText("hahahahaahahhahh");
        //发送邮件
        mailSender.send(mimeMessage);
        System.out.println("邮件发送成功！");
    }
}
