package com.offcn.project.controller;

import com.offcn.project.bean.Model;
import com.offcn.project.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    //添加模块信息
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insert(Model model){
        modelService.insert(model);
        return "redirect:/model/list";
    }
    //查询模块所有信息
    @RequestMapping(value = "/list")
    public ModelAndView getModelList(){
        ModelAndView view = new ModelAndView("project-model");
        List<Model> models = modelService.getModelList();
        view.addObject("models",models);
        return view;
    }
    //查看模块详情信息
    @RequestMapping(value = "/selectModelById/{id}")
    public ModelAndView selectModelById(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("project-model-look");
        Model model = modelService.selectModelById(id);
        view.addObject("model",model);
        return view;
    }
    //查询模块名称
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Model> jsonList(){
        List<Model> modelList = modelService.jsonList();
        return modelList;
    }

}
