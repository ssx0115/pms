package com.offcn.usual.mapper;


import java.util.List;

import com.offcn.usual.bean.ForumPost;
import com.offcn.usual.bean.ForumPostExample;
import org.apache.ibatis.annotations.Param;

public interface ForumPostMapper {
    int countByExample(ForumPostExample example);

    int deleteByExample(ForumPostExample example);

    int deleteByPrimaryKey(Integer forumid);

    int insert(ForumPost record);

    int insertSelective(ForumPost record);

    List<ForumPost> selectByExample(ForumPostExample example);

    ForumPost selectByPrimaryKey(Integer forumid);

    int updateByExampleSelective(@Param("record") ForumPost record, @Param("example") ForumPostExample example);

    int updateByExample(@Param("record") ForumPost record, @Param("example") ForumPostExample example);

    int updateByPrimaryKeySelective(ForumPost record);

    int updateByPrimaryKey(ForumPost record);

    int saveInfo(ForumPost record);
}