package com.offcn.usual.service;

import com.offcn.usual.bean.Archives;
import com.offcn.usual.bean.ArchivesExample;
import com.offcn.usual.mapper.ArchivesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArchivesServiceImpl implements ArchivesService {
    @Resource
    private ArchivesMapper archivesMapper;
    //查询所有档案信息
    @Override
    public List<Map<String,Object>> selectArchives() {
        return archivesMapper.selectArchives();
    }
}
