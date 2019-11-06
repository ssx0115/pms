package com.offcn.sys.service;

import com.offcn.sys.bean.Position;

import java.util.List;

public interface PositionService {
    //查询用户的职位信息
    List<Position> selectPositionList();
}
