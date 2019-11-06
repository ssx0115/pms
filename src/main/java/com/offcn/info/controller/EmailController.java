package com.offcn.info.controller;

import com.offcn.info.bean.Email;
import com.offcn.info.service.EmailService;
import com.offcn.jobs.EmailJobs;
import com.offcn.project.bean.Model;
import com.offcn.sys.bean.Employee;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    //发送邮件并保存到数据库
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Email email, HttpSession session) throws Exception{
        //创建JobDetail对象，指定对象的任务名称、组名
        JobDetail job = JobBuilder.newJob(EmailJobs.class).withIdentity("job1", "group1").build();
        /*//获取当前时间
        Date startTime = new Date(System.currentTimeMillis());*/

        JobDataMap jobDataMap = job.getJobDataMap();
        jobDataMap.put("email",email);
        jobDataMap.put("JavaMailSenderImpl",javaMailSender);

        //创建SimpleTrigger对象，指定对象名称、组名  设置任务重复执行间隔时间，重复执行次数 启动时间
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().startAt(new Date()).build();
        //创建任务管理器Scheduler对象
        Scheduler sched= StdSchedulerFactory.getDefaultScheduler();
        jobDataMap.put("sched",sched);
        //为Scheduler对象新增JOB以及对应的SimpleTrigger
        sched.scheduleJob(job,trigger);
        //启动定时任务管理器
        sched.start();

        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        email.setEmpFk(eid);
        email.setSendtime(new Date());
        emailService.saveInfo(email);

        return "redirect:/message.jsp";
    }

    //查询当前登录用户的发件箱列表信息
    @RequestMapping(value = "/selectSendMessageByEmpfk",method=RequestMethod.GET)
    public ModelAndView selectSendMessageByEmpfk(HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        List<Map<String,Object>> emails = emailService.selectSendMessageByEmpfk(eid);
        ModelAndView view = new ModelAndView("message");
        view.addObject("emails",emails);
        return view;
    }

}
