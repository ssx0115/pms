package com.offcn.sys.service;

import org.apache.ibatis.annotations.Param;

public interface RoleSourcesService {

    void saveInfo(int roleid, String ids);
}
