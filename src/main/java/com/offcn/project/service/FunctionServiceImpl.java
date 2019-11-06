package com.offcn.project.service;

import com.offcn.project.bean.*;
import com.offcn.project.mapper.AnalysisMapper;
import com.offcn.project.mapper.FunctionMapper;
import com.offcn.project.mapper.ModelMapper;
import com.offcn.project.mapper.ProjectMapper;
import com.offcn.sys.bean.Employee;
import com.offcn.sys.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService{
    @Resource
    private FunctionMapper functionMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private AnalysisMapper analysisMapper;

    //添加功能信息
    @Override
    public void insertFunction(Function function) {
        functionMapper.insert(function);
    }
    //查询所有功能信息
    @Override
    public List<Function> selectFunction() {
        FunctionExample example = new FunctionExample();
        List<Function> functions = functionMapper.selectByExample(example);
        for (Function function : functions) {
            Integer modeleFk = function.getModeleFk();
            Model model = modelMapper.selectByPrimaryKey(modeleFk);
            Integer analysisFk = model.getAnalysisFk();
            Analysis analysis = analysisMapper.selectByPrimaryKey(analysisFk);
            Integer id = analysis.getId();
            Project project = projectMapper.selectByPrimaryKey(id);
            function.setProject(project);
            function.setAnalysis(analysis);
            function.setModel(model);
        }
        return functions;
    }
    //查询功能信息
    @Override
    public Function selecJsonFunctionById(Integer id) {
        Function function = functionMapper.selectByPrimaryKey(id);
        return function;
    }
}
