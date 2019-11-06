package com.offcn.jobs;

import com.offcn.info.bean.Email;
import org.quartz.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailJobs implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Email email = (Email) dataMap.get("email");
        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl)dataMap.get("JavaMailSenderImpl");
        Scheduler sched = (Scheduler)dataMap.get("sched");
        try {
        //创建邮件发送实体对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setFrom("ssx1776483089@163.com");
            helper.setTo(email.getEname());
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent(),true);
            //发送邮件
            javaMailSender.send(mimeMessage);
            System.out.println("邮件发送成功！");
            //关闭定时任务管理器
            sched.shutdown(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
