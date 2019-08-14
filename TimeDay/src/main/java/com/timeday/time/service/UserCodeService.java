package com.timeday.time.service;

public interface UserCodeService {
    //把验证码放入数据库，若用户存在则更新，不存在则新建
    void putCodeByPhone(String phone,String phoneCode);
}
