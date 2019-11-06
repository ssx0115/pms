package com.offcn.sys.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleSourcesMapper {

    void saveInfo(@Param("rid") int rid, @Param("sid") int sid);
}
