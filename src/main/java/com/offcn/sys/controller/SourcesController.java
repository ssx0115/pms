package com.offcn.sys.controller;

import com.offcn.common.ResultEntity;
import com.offcn.sys.bean.Sources;
import com.offcn.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/sources")
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;


    //获取权限列表
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> selectSourcesList(){
        //得到的list集合中只有一个Sources元素，是顶级菜单
        List<Sources> sourcesList = sourcesService.selectSourcesList(0);
        //查询顶级菜单下的子菜单
        queryChildren(sourcesList.get(0));
        return sourcesList;
    }
    //完成递归操作
    private void queryChildren(Sources sources) {
        Integer id = sources.getId();
        //得到的是二级菜单
        List<Sources> sourcesList = sourcesService.selectSourcesList(id);
        //使用递归，结束条件是查到三级菜单是获取id为null，就不会再继续递归
        for (Sources sources1 : sourcesList) {
            queryChildren(sources1);
        }
        sources.setChildren(sourcesList);
    }

    //对权限树完成增加操作
    @RequestMapping(value = "/saveInfo",method=RequestMethod.POST)
    public String saveInfo(Sources sources){
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }
    //对权限树完成修改操作
    @RequestMapping(value = "/getSourcesById/{id}",method = RequestMethod.GET)
    public ModelAndView getSourcesById(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("pm-edit");
        Sources sources = sourcesService.getSourcesById(id);
        view.addObject("sources",sources);
        return view;
    }
    //对权限树完成修改操作
    @RequestMapping(value = "/updateInfo",method = RequestMethod.PUT)
    public String updateInfo(Sources sources){
        sourcesService.updateInfo(sources);
        return "redirect:/pm.jsp";
    }
    //对权限树完成删除操作
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity delete(Integer id){
        sourcesService.delete(id);
        return ResultEntity.success();
    }

}
