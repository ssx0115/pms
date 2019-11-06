package com.offcn.sys.service;

import com.offcn.sys.bean.Role;

import java.util.List;

public interface RoleService {
    //添加角色
    int saveInfo(Role role);
    //查询角色列表
    List<Role> selectRoleList();
}
