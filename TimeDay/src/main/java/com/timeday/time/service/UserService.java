package com.timeday.time.service;

import com.timeday.time.error.BusinessException;
import com.timeday.time.service.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {
    //通过UserID获取用户
    UserModel getUserById(Integer id);
    //用户注册
    void userRegister(UserModel userModel) throws BusinessException;
    //用户登录
    UserModel validateLogin(String phone,String encryptionPassword) throws BusinessException,NoSuchAlgorithmException,UnsupportedEncodingException;
}
