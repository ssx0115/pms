package com.offcn.usual.service;

import com.offcn.usual.bean.ForumPost;
import com.offcn.usual.bean.ForumPostExample;
import com.offcn.usual.mapper.ForumPostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Resource
    private ForumPostMapper forumPostMapper;

    //发帖
    @Override
    public void saveInfo(ForumPost forumPost) {
        forumPostMapper.insert(forumPost);
    }
    //在主界面显示发帖信息
    @Override
    public List<ForumPost> getLaterNoticeList() {
        ForumPostExample example = new ForumPostExample();
        example.setOrderByClause("createtime desc limit 3");
        List<ForumPost> forumPostList = forumPostMapper.selectByExample(example);
        return forumPostList;
    }

    @Override
    public ForumPost getvaForumPostInfo(Integer forumid) {
        ForumPost forumPost = forumPostMapper.selectByPrimaryKey(forumid);
        return forumPost;
    }


}
