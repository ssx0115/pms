package com.offcn.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offcn.common.StringUtils;
import com.offcn.usual.bean.BaoXiao;
import com.offcn.usual.bean.BaoXiaoExample;
import com.offcn.usual.mapper.BaoXiaoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BaoXiaoServiceImpl implements BaoXiaoService {
    @Resource
    private BaoXiaoMapper baoXiaoMapper;
    //添加报销
    @Override
    public void saveInfo(BaoXiao baoXiao) {
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        baoXiao.setBxid(prefix);
        baoXiao.setBxstatus(0);
        baoXiaoMapper.insert(baoXiao);
    }
    //查询我的报销信息
    @Override
    public PageInfo<BaoXiao> selectBaoxiaoList(Integer eid, Integer pageNum, Map<String, Object> parameterMap) {

        BaoXiaoExample example = new BaoXiaoExample();
        BaoXiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);

        Map<String,String> mybatilsMap = StringUtils.parseParamterMapToMyBatilsMap(parameterMap);
        String status = mybatilsMap.get("status");
        System.out.println(status);
        String keyword = mybatilsMap.get("keyword");
        if(status != null && status != ""){
            criteria.andBxstatusEqualTo(Integer.parseInt(status));
        }
        if(keyword != null && keyword != ""){
            criteria.andBxremarkLike(keyword);
        }
        PageHelper.startPage(pageNum,3);
        List<BaoXiao> baoXiaos = baoXiaoMapper.selectByExample(example);
        PageInfo<BaoXiao> page = new PageInfo<BaoXiao>(baoXiaos,5);//显示五页


        return page;
    }

}
