package com.timeday.time.service.impl;

import com.timeday.time.dao.UserInfoMapper;
import com.timeday.time.dao.UserPassWordMapper;
import com.timeday.time.error.BusinessException;
import com.timeday.time.error.EmBusinessError;
import com.timeday.time.pojo.UserInfo;
import com.timeday.time.pojo.UserPassWord;
import com.timeday.time.service.UserService;
import com.timeday.time.service.model.UserModel;
import com.timeday.time.utils.MD5;
import com.timeday.time.validator.ValidationResult;
import com.timeday.time.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserPassWordMapper userPassWordMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
     UserInfo userInfo = userInfoMapper.selectByUserId(id);
     if(userInfo == null){
        return  null;
     }
     UserPassWord userPassWord =  userPassWordMapper.selectByUserId(userInfo.getId());
     return convertFromDataObject(userInfo,userPassWord);
    }

    @Override
    public UserModel validateLogin(String phone, String encryptionPassword) throws BusinessException,NoSuchAlgorithmException,UnsupportedEncodingException {
        //通过手机获取验证码
        UserInfo userInfo = userInfoMapper.selectByPhone(phone);
        if(userInfo == null ){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPassWord userPassWord = userPassWordMapper.selectByUserId(userInfo.getId());
        UserModel userModel = convertFromDataObject(userInfo,userPassWord);
        //验证密码是否匹配
        if(!StringUtils.equals( MD5.EncodeByMd5(encryptionPassword),userModel.getEncryptionPassword())){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    @Override
    @Transactional
    public void userRegister(UserModel userModel) throws BusinessException {
         if(userModel == null){
             throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
         }
        ValidationResult result = validator.validate(userModel);
        if(result.isHasErrors()){
             throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
         }
        UserInfo userInfo = convertFromMode(userModel);
         //唯一值
         try {
             userInfoMapper.insertUser(userInfo);
         }catch (DuplicateKeyException ex){
             throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已经注册");
        }
        userModel.setId(userInfo.getId());
        UserPassWord userPassWord = convertPassWordFromMode(userModel);
        userPassWordMapper.insertUserPassWord(userPassWord);
    }

    private UserModel convertFromDataObject(UserInfo userInfo, UserPassWord userPassWord){
        if(userInfo == null){
            return  null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfo,userModel);
        if(userPassWord !=null){
            userModel.setEncryptionPassword(userPassWord.getEncryptionPassword());
        }
        return userModel;
    }

    private UserInfo convertFromMode(UserModel userModel){
        if(userModel== null){
            return  null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userModel,userInfo);
        return userInfo;
    }

    private UserPassWord convertPassWordFromMode(UserModel userModel){
        if(userModel== null){
            return  null;
        }
        UserPassWord userPassWord = new UserPassWord();
        userPassWord.setEncryptionPassword(userModel.getEncryptionPassword());
        userPassWord.setUserId(userModel.getId());
        return userPassWord;
    }
}
