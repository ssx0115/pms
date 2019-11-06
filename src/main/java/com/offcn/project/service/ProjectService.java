package com.offcn.project.service;

import com.offcn.project.bean.Project;

import java.util.List;

public interface ProjectService {
    //添加新项目
    public void insertProject(Project project);
    //查询显示所有项目信息
    public List<Project> getProjectList();
    //批量删除项目信息
    public boolean batchDelete(Integer[] ids);
    //按条件查询
    List<Project> search(Integer cid, String keyword, Integer orderby);
    //查看项目的详情
    Project selectProjectById(Integer pid);
}
