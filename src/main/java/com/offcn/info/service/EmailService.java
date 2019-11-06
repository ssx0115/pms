package com.offcn.info.service;

import com.offcn.info.bean.Email;

import java.util.List;
import java.util.Map;

public interface EmailService {
    //发送邮件并保存到数据库
    void saveInfo(Email email);
    //查询当前登录用户的发件箱列表信息
    List<Map<String,Object>> selectSendMessageByEmpfk(Integer eid);
}
