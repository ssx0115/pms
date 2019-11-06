package com.offcn.sys.service;

import com.offcn.sys.mapper.EmpRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmpRoleServiceImpl implements EmpRoleService  {

    @Resource
    private EmpRoleMapper empRoleMapper;

    @Override
    public void insert(int empid, String[] roleids) {
        for (String roleid : roleids) {
            empRoleMapper.insert(empid,Integer.parseInt(roleid));
        }

    }
}
