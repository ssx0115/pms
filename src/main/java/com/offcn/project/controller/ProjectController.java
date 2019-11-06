package com.offcn.project.controller;

import com.offcn.customer.bean.Customer;
import com.offcn.project.bean.Project;
import com.offcn.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    //添加新项目
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertProject(Project project){
        projectService.insertProject(project);
        return "redirect:/project/list";
    }
    //查询显示所有项目信息
    @RequestMapping(value = "/list")
    public ModelAndView getProjectList(){
        ModelAndView view = new ModelAndView("project-base");
        List<Project> projects = projectService.getProjectList();
        view.addObject("projects",projects);
        return view;
    }
    //批量删除项目信息
    @RequestMapping(value = "/batchDelete",method = RequestMethod.DELETE)
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer [] ids){
        boolean isSuccess = projectService.batchDelete(ids);
        Map<String,Object> map = new HashMap<String,Object>();
        if(isSuccess) {
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else{
            map.put("statusCode",500);
            map.put("message","删除失败");
        }
        return map;
    }
    //按条件查询
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid,String keyword,Integer orderby){
        List<Project> projects = projectService.search(cid,keyword,orderby);
        ModelAndView view = new ModelAndView("project-base");
        view.addObject("projects",projects);
        return view;
    }
    //查询对应的项目名称
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> infoCustomer(){
        List<Project> projects = projectService.getProjectList();
        return projects;
    }
    //查看项目的详情
    @RequestMapping(value = "/selectProjectById/{pid}",method = RequestMethod.GET)
    public ModelAndView selectProjectById(@PathVariable("pid") Integer pid){
        Project project = projectService.selectProjectById(pid);
        ModelAndView view = new ModelAndView("project-base-look");
        view.addObject("project",project);
        return view;
    }

}
