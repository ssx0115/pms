package com.offcn.project.controller;

import com.offcn.project.bean.Analysis;
import com.offcn.project.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "analysis")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    //添加项目需求分析
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertAnalysis(Analysis analysis){
        analysisService.insertAnalysis(analysis);
        return "redirect:/analysis/list";
    }
    //查询所有项目需求分析
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView selectAnalysisList(){
        ModelAndView view = new ModelAndView("project-need");
        List<Analysis> analysisList = analysisService.selectAnalysisList();
        view.addObject("analysisList",analysisList);
        return view;
    }
    //查看项目需求详情
    @RequestMapping(value = "/selectAnalysisById/{id}")
    public ModelAndView selectAnalysisById(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("project-need-look");
        Analysis analysis = analysisService.selectAnalysisById(id);
        view.addObject("analysis",analysis);
        return view;
    }
    //编辑项目需求要先查询到显示在编辑页面
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("project-need-edit");
        Analysis analysis = analysisService.selectAnalysisById(id);
        view.addObject("analysis",analysis);
        return view;
    }
    //编辑项目需求完成并提交
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Analysis analysis){
        analysisService.update(analysis);
        return "redirect:/analysis/list";
    }
    //批量删除项目需求
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer ids[]){
        boolean isSuccess = analysisService.batchDelete(ids);
        Map<String,Object> map = new HashMap<String,Object>();
        if(isSuccess){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else {
            map.put("statusCode",500);
            map.put("message","删除失败");
        }
        return map;
    }
    //查询项目标题
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> jsonList(){
        List<Analysis> analysisList = analysisService.selectAnalysisList();
        return analysisList;
    }
    //查询需求的题目
    @RequestMapping(value = "/selecJsontAnalysisById/{empFk}")
    @ResponseBody
    public Analysis selecJsontAnalysisById(@PathVariable("empFk") Integer empFk){
        Analysis analysis = analysisService.selecJsontAnalysisById(empFk);
        return analysis;
    }

}
