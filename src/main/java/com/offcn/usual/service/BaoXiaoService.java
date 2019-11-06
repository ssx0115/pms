package com.offcn.usual.service;

import com.github.pagehelper.PageInfo;
import com.offcn.usual.bean.BaoXiao;

import java.util.Map;

public interface BaoXiaoService {
    //添加报销
    void saveInfo(BaoXiao baoXiao);
    //查询我的报销信息
    PageInfo<BaoXiao> selectBaoxiaoList(Integer eid, Integer pageNum, Map<String, Object> parameterMap);
}
