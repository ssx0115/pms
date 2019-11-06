package com.offcn.project.service;

import com.offcn.project.bean.Attachment;

import java.util.List;

public interface AttachmentService {
    //添加附件信息
    void insertAttachment(Attachment attachment);
    //查看所有附件信息
    List<Attachment> selectAttachment();
    //查询附件详情
    Attachment selectAttachmentById(Integer id);
    //修改附件
    void update(Attachment attachment);
    //批量删除附件信息
    boolean batchDelete(Integer[] ids);
}
