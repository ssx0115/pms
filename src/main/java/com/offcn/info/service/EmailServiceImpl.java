package com.offcn.info.service;

import com.offcn.info.bean.Email;
import com.offcn.info.bean.EmailExample;
import com.offcn.info.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Resource
    private EmailMapper emailMapper;

    //发送邮件并保存到数据库
    @Override
    public void saveInfo(Email email) {
        emailMapper.insert(email);
    }
    //查询当前登录用户的发件箱列表信息
    @Override
    public List<Map<String,Object>> selectSendMessageByEmpfk(Integer eid) {
        List<Map<String,Object>> emails = emailMapper.selectSendMessageByEmpfk(eid);
        return emails;
    }
}

