package com.offcn.project.service;

import com.offcn.project.bean.Analysis;

import java.util.List;

public interface AnalysisService {
    //添加项目需求分析
    void insertAnalysis(Analysis analysis);
    //查询所有项目需求分析
    List<Analysis> selectAnalysisList();
    //查看项目详情
    Analysis selectAnalysisById(Integer id);
    //编辑项目完成并提交
    void update(Analysis analysis);
    //批量删除项目需求
    boolean batchDelete(Integer[] ids);
    //查询需求的题目
    Analysis selecJsontAnalysisById(Integer empFk);
}
