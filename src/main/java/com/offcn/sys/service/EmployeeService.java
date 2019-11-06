package com.offcn.sys.service;

import com.offcn.sys.bean.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    //查询员工所有信息
    public List<Employee> getList();
    //登录验证
    Employee login(Employee employee);
    //查看发件人的信息
    List<Map<String,Object>> getOthers(Integer eid);
    //添加用户
    int saveInfo(Employee employee);
}
