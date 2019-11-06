package com.offcn.sys.controller;

import com.offcn.common.ResultEntity;
import com.offcn.project.bean.Model;
import com.offcn.sys.bean.Role;
import com.offcn.sys.service.RoleService;
import com.offcn.sys.service.RoleSourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleSourcesService roleSourcesService;

    //添加角色
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    private ResultEntity saveInfo(Role role,String ids){
        //1、向角色表中添加数据，而且必须返回该表中的id
        int roleid = roleService.saveInfo(role);
        //2.添加数据到角色表和来源表两者的中间表中
        roleSourcesService.saveInfo(roleid,ids);
        return ResultEntity.success();
    }
    //查询角色列表
    @RequestMapping(value="/list",method=RequestMethod.GET)
    public ModelAndView selectRoleList(){
        ModelAndView view  = new ModelAndView("role");
        List<Role> roleList = roleService.selectRoleList();
        view.addObject("roleList",roleList);
        return view;
    }
    //查询角色信息
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> selectjsonList(){
        List<Role> roleList = roleService.selectRoleList();
        return roleList;
    }
}
