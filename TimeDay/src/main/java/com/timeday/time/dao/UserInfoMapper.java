package com.timeday.time.dao;

import com.timeday.time.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserInfoMapper {
    int insertUser(UserInfo record);

    UserInfo selectByUserId(Integer id);

    UserInfo selectByPhone(String phone);
}