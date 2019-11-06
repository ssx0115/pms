package com.offcn.compare.service;

import com.offcn.compare.bean.BenchMarking;
import com.offcn.compare.mapper.BenchMarkingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BenchMarkingServiceImpl implements BenchMarkingService {
    @Resource
    private BenchMarkingMapper benchMarkingMapper;

    //添加对标基本信息
    @Override
    public void saveInfo(BenchMarking benchMarking) {
        benchMarkingMapper.saveInfo(benchMarking);
    }
    //对标数据统计
    @Override
    public List<BenchMarking> selectBenchMarkingList(Integer year) {
        List<BenchMarking> benchMarkingList = benchMarkingMapper.selectBenchMarkingList(year);
        return benchMarkingList;
    }
}
