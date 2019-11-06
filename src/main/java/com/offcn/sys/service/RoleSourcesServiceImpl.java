package com.offcn.sys.service;

import com.offcn.sys.mapper.RoleSourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleSourcesServiceImpl implements RoleSourcesService {

    @Resource
    private RoleSourcesMapper roleSourcesMapper;


    @Override
    public void saveInfo(int roleid, String ids) {
        String[] idsArr = ids.split(",");
        for (String s : idsArr) {
            roleSourcesMapper.saveInfo(roleid,Integer.parseInt(s));
        }
    }
}
