package com.offcn.usual.service;

import com.offcn.usual.bean.Archives;

import java.util.List;
import java.util.Map;

public interface ArchivesService {
    //查询所有档案信息
    List<Map<String,Object>> selectArchives();
}
