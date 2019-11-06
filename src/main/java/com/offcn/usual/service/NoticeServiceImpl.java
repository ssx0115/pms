package com.offcn.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.offcn.common.StringUtils;
import com.offcn.usual.bean.BaoXiao;
import com.offcn.usual.bean.BaoXiaoExample;
import com.offcn.usual.bean.Notice;
import com.offcn.usual.bean.NoticeExample;
import com.offcn.usual.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;
    //添加公告信息
    @Override
    public void saveInfo(Notice notice) {
        notice.setNdate(new Date());
        noticeMapper.insert(notice);
    }
    //查询所有公告信息
    @Override
    public PageInfo<Notice> getNoticeJsonList(Integer pageNum, Map<String, Object> parameterMap) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria = example.createCriteria();
        Map<String,String> mybatilsMap = StringUtils.parseParamterMapToMyBatilsMap(parameterMap);
        PageHelper.startPage(pageNum,3);
        List<Notice> notices = noticeMapper.selectByExample(example);
        PageInfo<Notice> page = new PageInfo<Notice>(notices,5);//显示五页
        return page;
    }
    //返回最新的三条数据
    @Override
    public List<Notice> getLaterNoticeList() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 3");
        List<Notice> noticeList = noticeMapper.selectByExample(example);
        return noticeList;
    }
    //查询对应的公告数据
    @Override
    public Notice getvaNoticeInfo(Integer nid) {
        Notice notice = noticeMapper.selectByPrimaryKey(nid);
        return notice;
    }
}
