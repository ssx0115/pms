package com.offcn.compare.service;


import com.offcn.compare.bean.BenchMarking;

import java.util.List;

public interface BenchMarkingService {
    //添加对标基本信息
    void saveInfo(BenchMarking benchMarking);
    //对标数据统计
    List<BenchMarking> selectBenchMarkingList(Integer year);
}
