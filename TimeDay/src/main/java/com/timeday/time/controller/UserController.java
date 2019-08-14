package com.timeday.time.controller;

import com.alibaba.druid.util.StringUtils;
import com.timeday.time.controller.viewobject.UserVo;
import com.timeday.time.dao.UserCodeMapper;
import com.timeday.time.error.BusinessException;
import com.timeday.time.error.EmBusinessError;
import com.timeday.time.pojo.UserCode;
import com.timeday.time.response.CommonReturnType;
import com.timeday.time.service.UserCodeService;
import com.timeday.time.service.UserService;
import com.timeday.time.service.model.UserModel;
import com.timeday.time.utils.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserCodeService userCodeService;

    @Autowired
    private UserCodeMapper userCodeMapper;

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/getPhoneCode")
    @ResponseBody
    public CommonReturnType getPhoneCode(String phone)  {
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String optCode = String.valueOf(randomInt);
        userCodeService.putCodeByPhone(phone,optCode);
        System.out.println("phone="+phone+"&optCode="+optCode);
        return CommonReturnType.create(optCode, "success");
    }

   @RequestMapping(value = "/userLogin")
    @ResponseBody
    public CommonReturnType userLogin(String phone, String password) throws BusinessException,NoSuchAlgorithmException,UnsupportedEncodingException {
       if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){
          throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
       }
       UserModel  userModal =  userService.validateLogin(phone,password);
       return CommonReturnType.create(userModal,"success");
   }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/userRegister")
    @ResponseBody
    public CommonReturnType userRegister(
            String phone,
            String optCode,
            String name,
            Integer gender,
            String password,
            Integer age) throws BusinessException,NoSuchAlgorithmException,UnsupportedEncodingException {
        UserCode userCode = userCodeMapper.selectCodeByPhone(phone);
        if(userCode == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号输入错误");
        } else if(!(userCode.getCode() ==Integer.parseInt(optCode))) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"验证码输入错误，请重新输入");
        }
        UserModel userModel = new UserModel();
        userModel.setEncryptionPassword(password);
        userModel.setPhone(phone);
        userModel.setAge(age);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setName(name);
        userModel.setRegisterMode("byPhone");
        userModel.setEncryptionPassword(MD5.EncodeByMd5(password));
        userService.userRegister(userModel);
        return  CommonReturnType.create(userModel,"success");
    }


    /**
     * 获取用户信息
     */
    @RequestMapping("/getUserById")
    @ResponseBody
    public CommonReturnType getUserById(Integer id) throws BusinessException {
        UserModel userModal = userService.getUserById(id);
       if (userModal == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        UserVo userVo = convertFromModel(userModal);
        return CommonReturnType.create(userVo, "success");
   }

    private UserVo convertFromModel(UserModel userModal) {
        if (userModal == null) { return null;
       }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModal, userVo);
        return userVo;
    }
}
