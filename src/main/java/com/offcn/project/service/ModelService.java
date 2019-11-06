package com.offcn.project.service;

import com.offcn.project.bean.Model;

import java.util.List;

public interface ModelService {
    //添加模块信息
    void insert(Model model);
    //查询模块所有信息
    List<Model> getModelList();
    //查询模块名称
    List<Model> jsonList();
    //查看模块详情
    Model selectModelById(Integer id);
}
