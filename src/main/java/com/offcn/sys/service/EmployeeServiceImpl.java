package com.offcn.sys.service;

import com.offcn.sys.bean.Employee;
import com.offcn.sys.bean.EmployeeExample;
import com.offcn.sys.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Resource
    private EmployeeMapper employeeMapper;

    //查询员工所有信息
    @Override
    public List<Employee> getList() {
        return employeeMapper.selectByExample(new EmployeeExample());
    }
    //登录验证
    @Override
    public Employee login(Employee employee) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        if(employees != null && employees.size() > 0){
            employee = employees.get(0);
            return employee;
        }
        return null;
    }
    //查看发件人的信息
    @Override
    public List<Map<String,Object>> getOthers(Integer eid) {
        List<Map<String, Object>> others = employeeMapper.getOthers(eid);
        return others;
    }
    //添加用户
    @Override
    public int saveInfo(Employee employee) {
        employeeMapper.insertEmp(employee);
        return employee.getEid();
    }
}
