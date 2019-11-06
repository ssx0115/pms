package com.offcn.usual.service;

import com.offcn.usual.bean.ForumPost;

import java.util.List;

public interface ForumPostService {
    //发帖
    void saveInfo(ForumPost forumPost);
    //在主界面显示发帖信息
    List<ForumPost> getLaterNoticeList();
    //查询对应的帖子数据
    ForumPost getvaForumPostInfo(Integer forumid);
}
