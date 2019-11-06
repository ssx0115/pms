package com.offcn.project.controller;

import com.offcn.project.bean.Function;
import com.offcn.project.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/function")
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    //添加功能信息
    @RequestMapping(value = "/insert",method= RequestMethod.POST)
    public String insertFunction(Function function){
        functionService.insertFunction(function);
        return "redirect:/function/list";
    }
    //查询所有功能信息
    @RequestMapping(value = "/list",method=RequestMethod.GET)
    public ModelAndView selectFunction(){
        ModelAndView view = new ModelAndView("project-function");
        List<Function> functionList = functionService.selectFunction();
        view.addObject("functionList",functionList);
        return view;
    }

    //查询功能信息
    @RequestMapping(value = "/selecJsonFunctionById/{id}",method = RequestMethod.GET)
    public Function selecJsonFunctionById(@PathVariable("id") Integer id){
        Function function = functionService.selecJsonFunctionById(id);
        return function;
    }

    @RequestMapping(value = "/JsonFunctionList",method = RequestMethod.GET)
    @ResponseBody
    public List<Function> selecJsonFunctionById(){
        List<Function> functions = functionService.selectFunction();
        return functions;
    }

}
