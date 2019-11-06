package com.offcn.project.service;

import com.offcn.project.bean.Function;

import java.util.List;

public interface FunctionService {
    //添加功能信息
    void insertFunction(Function function);
    //查询所有功能信息
    List<Function> selectFunction();
    //查询功能信息
    Function selecJsonFunctionById(Integer id);
}
