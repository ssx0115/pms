package com.offcn.project.service;

import com.offcn.project.bean.Analysis;
import com.offcn.project.bean.AnalysisExample;
import com.offcn.project.bean.Project;
import com.offcn.project.mapper.AnalysisMapper;
import com.offcn.project.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private AnalysisMapper analysisMapper;
    @Resource
    private ProjectMapper projectMapper;
    //添加项目需求分析
    @Override
    public void insertAnalysis(Analysis analysis) {
        analysis.setAddtime(new Date());
        analysis.setUpdatetime(new Date());
        analysisMapper.insert(analysis);
    }
    //查询所有项目需求分析
    @Override
    public List<Analysis> selectAnalysisList() {
        AnalysisExample analysisExample = new AnalysisExample();
        List<Analysis> analysisList = analysisMapper.selectByExample(analysisExample);
        for (Analysis analysis:analysisList ) {
            Integer id = analysis.getId();
            Project project = projectMapper.selectByPrimaryKey(id);
            analysis.setProject(project);
        }
        return analysisList;
    }
    //查看项目详情
    @Override
    public Analysis selectAnalysisById(Integer id) {
        Analysis analysis = analysisMapper.selectByPrimaryKey(id);
        Integer id1 = analysis.getId();
        Project project = projectMapper.selectByPrimaryKey(id1);
        analysis.setProject(project);
        return analysis;
    }
    //编辑项目完成并提交
    @Override
    public void update(Analysis analysis) {
        analysis.setUpdatetime(new Date());
        analysisMapper.updateByPrimaryKeySelective(analysis);
    }
    //批量删除项目需求
    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        AnalysisExample example = new AnalysisExample();
        AnalysisExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int i = analysisMapper.deleteByExample(example);
        if(i > 0){
            return true;
        }
        return false;
    }
    //查询需求的题目
    @Override
    public Analysis selecJsontAnalysisById(Integer empFk) {
        Analysis analysis = analysisMapper.selectByPrimaryKey(empFk);
        return analysis;
    }
}
