package com.offcn.usual.controller;

import com.github.pagehelper.PageInfo;
import com.offcn.common.ResultEntity;
import com.offcn.common.StringUtils;
import com.offcn.sys.bean.Employee;
import com.offcn.usual.bean.BaoXiao;
import com.offcn.usual.bean.Notice;
import com.offcn.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //添加公告信息
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        noticeService.saveInfo(notice);
        return ResultEntity.success();
    }
    //查询所有公告信息
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNoticeJsonList(HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = StringUtils.parsepareterMapToString(parameterMap);

        String requestURI = request.getRequestURI();

        PageInfo<Notice> page = noticeService.getNoticeJsonList(pageNum,parameterMap);
        return ResultEntity.success().put("page",page).put("queryStr",queryStr).put("requestURI",requestURI);
    }

    //返回最新的三条数据
    @RequestMapping(value = "/LaterNoticeList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getLaterNoticeList(){
        List<Notice> noticeList = noticeService.getLaterNoticeList();
        return ResultEntity.success().put("noticeList",noticeList);
    }
    //查询对应的公告数据
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Notice getvaNoticeInfo(@PathVariable("id") Integer nid){
        Notice notice = noticeService.getvaNoticeInfo(nid);
        return notice;
    }
}
