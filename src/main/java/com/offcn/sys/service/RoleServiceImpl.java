package com.offcn.sys.service;

import com.offcn.sys.bean.Role;
import com.offcn.sys.bean.RoleExample;
import com.offcn.sys.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    private RoleMapper roleMapper;

    //添加角色
    @Override
    public int saveInfo(Role role) {
        roleMapper.saveInfo(role);
        Integer roleid = role.getRoleid();
        return roleid;
    }
    //查询角色列表
    @Override
    public List<Role> selectRoleList() {
        List<Role> roleList = roleMapper.selectByExample(new RoleExample());
        return roleList;
    }
}
