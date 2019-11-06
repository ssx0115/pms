package com.offcn.sys.controller;

import com.offcn.sys.bean.Employee;
import com.offcn.sys.bean.Sources;
import com.offcn.sys.service.EmpRoleService;
import com.offcn.sys.service.EmployeeService;
import com.offcn.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmpRoleService empRoleService;
    @Autowired
    private SourcesService sourcesService;
    //查询员工详情
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getList(){
        return employeeService.getList();
    }
    //登录验证
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes redirectAttributes){
        String validateCode = (String)session.getAttribute("validateCode");
        if(code.equalsIgnoreCase(validateCode)){
            employee = employeeService.login(employee);
            if(employee != null){

                Integer eid = employee.getEid();
                List<Sources> sourcesList = sourcesService.getSourcesListById(eid);
                session.setAttribute("sourcesList",sourcesList);

                session.setAttribute("loginUser",employee);
                return "redirect:/index.jsp";
            }else{
                redirectAttributes.addFlashAttribute("errorMsg","用户名或者密码不正确");
                return "redirect:/login";//这里必须经过springmvc的映射，不能直接重定向到页面，
            }
        }
        redirectAttributes.addFlashAttribute("errorMsg","验证码不正确");
        return "redirect:/login";
    }
    //注销退出
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        //销毁session
        session.invalidate();
        return "redirect:/login.jsp";
    }

    //查看发件人的信息
    @RequestMapping(value = "/others",method=RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getOthers(HttpSession session){
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        Integer eid = loginUser.getEid();
        List<Map<String,Object>> others = employeeService.getOthers(eid);
        return others;
    }

    //添加用户
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Employee employee,String [] roleids){
        //1.在员工表中添加数据
        int empid = employeeService.saveInfo(employee);
        //2.往员工和角色的连表中添加数据
        empRoleService.insert(empid,roleids);
        return "redirect:/user.jsp";
    }
}
