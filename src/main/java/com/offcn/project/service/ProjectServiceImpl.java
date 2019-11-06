package com.offcn.project.service;

import com.offcn.customer.bean.Customer;
import com.offcn.customer.mapper.CustomerMapper;
import com.offcn.project.bean.Project;
import com.offcn.project.bean.ProjectExample;
import com.offcn.project.mapper.ProjectMapper;
import com.offcn.sys.bean.Employee;
import com.offcn.sys.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private EmployeeMapper employeeMapper;

    //添加新项目
    @Override
    public void insertProject(Project project) {
        projectMapper.insert(project);

    }
    //查询显示所有项目信息
    @Override
    public List<Project> getProjectList() {
        ProjectExample example = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);

        for (Project project:projects) {
            Integer cid = project.getComname();
            Integer eid = project.getEmpFk();
            Customer customer = customerMapper.selectByPrimaryKey(cid);
            Employee employee = employeeMapper.selectByPrimaryKey(eid);
            project.setCustomer(customer);
            project.setEmployee(employee);
        }
        return projects;
    }
    //批量删除项目信息
    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(idList);
        int i = projectMapper.deleteByExample(example);
        return i == ids.length;
    }

    @Override
    public List<Project> search(Integer cid, String keyword, Integer orderby) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();

        if(cid == 0){//按照默认的进行查询
            criteria.andPnameLike("%"+keyword+"%");
            ProjectExample.Criteria criteria1 = example.createCriteria();
            criteria1.andComperLike("%"+keyword+"%");
            example.or(criteria1);
        }else if(cid == 1){
            criteria.andPnameLike("%"+keyword+"%");
        }else {
            criteria.andComperLike("%"+keyword+"%");
        }
        if(orderby == 1){
            example.setOrderByClause("pid desc");
        }
        List<Project> projectList = projectMapper.selectByExample(example);
        for (Project project:projectList) {
            Integer ccid = project.getComname();
            Integer eid = project.getEmpFk();
            Customer customer = customerMapper.selectByPrimaryKey(ccid);
            Employee employee = employeeMapper.selectByPrimaryKey(eid);
            project.setCustomer(customer);
            project.setEmployee(employee);
        }
        return projectList;
    }
    //查看项目的详情
    @Override
    public Project selectProjectById(Integer pid) {
        Project project = projectMapper.selectByPrimaryKey(pid);
        Integer empFk1 = project.getEmpFk();
        Integer comname = project.getComname();
        Customer customer = customerMapper.selectByPrimaryKey(comname);
        Employee employee = employeeMapper.selectByPrimaryKey(empFk1);
        project.setEmployee(employee);
        project.setCustomer(customer);
        return project;
    }

}
