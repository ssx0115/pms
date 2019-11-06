package com.offcn.usual.controller;

import com.offcn.common.ResultEntity;
import com.offcn.sys.bean.Employee;
import com.offcn.usual.bean.ForumPost;
import com.offcn.usual.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/fatie")
public class ForumPostController {

    @Autowired
    private ForumPostService forumPostService;
    //发帖
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(ForumPost forumPost, HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        forumPost.setCreatetime(new Date());
        forumPost.setEmpFk3(eid);
        forumPost.setStatus(0);
        forumPostService.saveInfo(forumPost);
        return "redirect:/main.jsp";
    }

    //返回最新的三条数据
    @RequestMapping(value = "/fatieList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getLaterNoticeList(){
        List<ForumPost> forumPostList = forumPostService.getLaterNoticeList();
        return ResultEntity.success().put("forumPostList",forumPostList);
    }
    //查询对应的帖子数据
    @RequestMapping(value = "/info/{forumid}",method = RequestMethod.GET)
    @ResponseBody
    public ForumPost getvaNoticeInfo(@PathVariable("forumid") Integer forumid){
        ForumPost forumPost = forumPostService.getvaForumPostInfo(forumid);
        String forumcontent = forumPost.getForumcontent();
        String newforumcontent = forumcontent.replaceAll("[><p/]", "");
        forumPost.setForumcontent(newforumcontent);
        return forumPost;
    }



}
