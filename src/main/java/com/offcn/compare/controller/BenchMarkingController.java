package com.offcn.compare.controller;

import com.offcn.compare.bean.BenchMarking;
import com.offcn.compare.service.BenchMarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/benchmarking")
public class BenchMarkingController {
    @Autowired
    private BenchMarkingService benchMarkingService;

    //添加对标基本信息
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(BenchMarking benchMarking){
        benchMarkingService.saveInfo(benchMarking);
        return "redirect:/indexvalue-base.jsp";
    }

    //对标数据统计
    @RequestMapping(value = "/list/{year}",method = RequestMethod.GET)
    @ResponseBody
    public List<BenchMarking> selectBenchMarkingList(@PathVariable("year") Integer year){
        List<BenchMarking> benchMarkingList = benchMarkingService.selectBenchMarkingList(year);
        return benchMarkingList;
    }


}
