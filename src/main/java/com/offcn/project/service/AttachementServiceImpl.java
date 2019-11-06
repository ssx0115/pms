package com.offcn.project.service;

import com.offcn.project.bean.Attachment;
import com.offcn.project.bean.AttachmentExample;
import com.offcn.project.bean.Project;
import com.offcn.project.mapper.AttachmentMapper;
import com.offcn.project.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class AttachementServiceImpl implements AttachmentService {
    @Resource
    private AttachmentMapper attachmentMapper;
    @Resource
    private ProjectMapper projectMapper;

    //添加附件信息
    @Override
    public void insertAttachment(Attachment attachment) {
        attachmentMapper.insert(attachment);
    }
    //查看所有附件信息
    @Override
    public List<Attachment> selectAttachment() {
        AttachmentExample example = new AttachmentExample();
        List<Attachment> attachments = attachmentMapper.selectByExample(example);
        for (Attachment attachment: attachments) {
            Integer proFk = attachment.getProFk();
            Project project = projectMapper.selectByPrimaryKey(proFk);
            attachment.setProject(project);
        }
        return attachments;
    }
    //查询附件详情
    @Override
    public Attachment selectAttachmentById(Integer id) {
        Attachment attachment = attachmentMapper.selectByPrimaryKey(id);
        Integer proFk = attachment.getProFk();
        Project project = projectMapper.selectByPrimaryKey(proFk);
        attachment.setProject(project);
        return attachment;
    }
    //修改附件信息
    @Override
    public void update(Attachment attachment) {
        attachmentMapper.updateByPrimaryKeySelective(attachment);
    }
    //批量删除附件信息
    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        AttachmentExample example = new AttachmentExample();
        AttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int i = attachmentMapper.deleteByExample(example);
        return i == ids.length;
    }
}
