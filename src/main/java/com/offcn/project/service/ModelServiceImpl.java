package com.offcn.project.service;

import com.offcn.project.bean.Analysis;
import com.offcn.project.bean.Model;
import com.offcn.project.bean.ModelExample;
import com.offcn.project.bean.Project;
import com.offcn.project.mapper.AnalysisMapper;
import com.offcn.project.mapper.ModelMapper;
import com.offcn.project.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private AnalysisMapper analysisMapper;

    //添加模块信息
    @Override
    public void insert(Model model) {
        modelMapper.insert(model);
    }
    //查询模块所有信息
    @Override
    public List<Model> getModelList() {
        ModelExample modelExample = new ModelExample();

        List<Model> models = modelMapper.selectByExample(modelExample);
        for (Model model : models) {
            String proname = model.getProname();
            Integer analysisFk = model.getAnalysisFk();
            int pid = Integer.valueOf(proname).intValue();
            Project project = projectMapper.selectByPrimaryKey(pid);
            Analysis analysis = analysisMapper.selectByPrimaryKey(analysisFk);
            model.setAnalysis(analysis);
            model.setProject(project);
        }
        return models;
    }
    //查询模块名称
    @Override
    public List<Model> jsonList() {
        List<Model> modelList = modelMapper.selectByExample(new ModelExample());
        return modelList;
    }
    //查看模块详情
    @Override
    public Model selectModelById(Integer id) {
        Model model = modelMapper.selectByPrimaryKey(id);
        String proname = model.getProname();
        Integer analysisFk = model.getAnalysisFk();
        int pid = Integer.valueOf(proname).intValue();
        Project project = projectMapper.selectByPrimaryKey(pid);
        Analysis analysis = analysisMapper.selectByPrimaryKey(analysisFk);
        model.setAnalysis(analysis);
        model.setProject(project);
        return model;
    }
}
