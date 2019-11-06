package com.offcn.usual.service;

import com.github.pagehelper.PageInfo;
import com.offcn.usual.bean.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    //添加公告信息
    void saveInfo(Notice notice);
    //查询所有公告信息
    PageInfo<Notice> getNoticeJsonList(Integer pageNum, Map<String, Object> parameterMap);
    //返回最新的三条数据
    List<Notice> getLaterNoticeList();
    //查询对应的公告数据
    Notice getvaNoticeInfo(Integer nid);
}
