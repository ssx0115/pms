package com.offcn.sys.service;

import com.offcn.sys.bean.Sources;

import java.util.List;

public interface SourcesService {
    //获取权限列表
    List<Sources> selectSourcesList(int i);
    //对权限树完成增加操作
    void saveInfo(Sources sources);
    //对权限树完成修改操作
    Sources getSourcesById(Integer id);
    //对权限树完成修改操作
    void updateInfo(Sources sources);
    //对权限树完成删除操作
    void delete(Integer id);

    List<Sources> getSourcesListById(Integer eid);
}
