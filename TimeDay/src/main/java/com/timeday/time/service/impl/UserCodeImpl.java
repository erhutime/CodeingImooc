package com.timeday.time.service.impl;

import com.timeday.time.dao.UserCodeMapper;
import com.timeday.time.pojo.UserCode;
import com.timeday.time.service.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserCodeImpl implements UserCodeService {
   @Autowired
   private UserCodeMapper userCodeMapper;

    @Override
    public void putCodeByPhone(String phone,String phoneCode) {
        UserCode userCode = userCodeMapper.selectCodeByPhone(phone);
        if(userCode == null){
            userCode = new UserCode();
            userCode.setPhone(phone);
            userCode.setCode(Integer.valueOf(phoneCode));
            //注册
            userCode.setCodeType(0);
            userCode.setStartDate(new Date());
            userCodeMapper.insertUserCode(userCode);
        }else {
            userCode.setCode(Integer.parseInt(phoneCode));
            userCodeMapper.updateUserCode(userCode);
        }
    }
}
