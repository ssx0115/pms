package com.offcn.usual.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.offcn.common.StringUtils;
import com.offcn.sys.bean.Employee;
import com.offcn.usual.bean.BaoXiao;
import com.offcn.usual.service.BaoXiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/baoxiao")
public class BaoXiaoController {
    @Autowired
    private BaoXiaoService baoXiaoService;

    //添加报销
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(BaoXiao baoXiao, HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        baoXiao.setEmpFk(eid);
        baoXiaoService.saveInfo(baoXiao);
        return "redirect:/baoxiao/list";
    }
    //查询我的报销信息
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView selectBaoxiaoList(HttpServletRequest request, HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        /*
        获取请求参数，map的key浏览器传过来的参数的键去掉指定的前缀search_剩下的部分
        map的value值就是浏览器传过来的参数的值
         */
        Map<String, Object> parameterMap = WebUtils.getParametersStartingWith(request, "search_");
        String queryStr = StringUtils.parsepareterMapToString(parameterMap);
        Employee loginUser = (Employee) session.getAttribute("loginUser");

        String requestURI = request.getRequestURI();


        Integer eid = loginUser.getEid();
        PageInfo<BaoXiao> page = baoXiaoService.selectBaoxiaoList(eid,pageNum,parameterMap);
        ModelAndView view = new ModelAndView("mybaoxiao-base");
        view.addObject("page",page);
        view.addObject("queryStr",queryStr);
        view.addObject("requestURI",requestURI);
        return view;
    }

}
