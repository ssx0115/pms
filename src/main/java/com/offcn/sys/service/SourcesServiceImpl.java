package com.offcn.sys.service;

import com.offcn.sys.bean.Sources;
import com.offcn.sys.bean.SourcesExample;
import com.offcn.sys.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {

    @Resource
    private SourcesMapper sourcesMapper;
    //获取权限列表
    @Override
    public List<Sources> selectSourcesList(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sourcesList = sourcesMapper.selectByExample(example);
        return sourcesList;
    }
    //对权限树完成增加操作
    @Override
    public void saveInfo(Sources sources) {
        sourcesMapper.insert(sources);
    }

    @Override
    public Sources getSourcesById(Integer id) {
        Sources sources = sourcesMapper.selectByPrimaryKey(id);
        return sources;
    }
    //对权限树完成修改操作
    @Override
    public void updateInfo(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    @Override
    public void delete(Integer id) {
        sourcesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Sources> getSourcesListById(Integer eid) {
        List<Sources> sourcesList = sourcesMapper.getSourcesListById(eid,1);
        for (Sources sources : sourcesList) {
            Integer id = sources.getId();
            List<Sources> sourcesListById = sourcesMapper.getSourcesListById(eid, id);
            sources.setChildren(sourcesListById);
        }
        return sourcesList;
    }
}
