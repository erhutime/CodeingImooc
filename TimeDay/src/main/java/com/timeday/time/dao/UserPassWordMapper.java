package com.timeday.time.dao;

import com.timeday.time.pojo.UserPassWord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserPassWordMapper {
    int insertUserPassWord(UserPassWord record);

    UserPassWord selectByUserId(Integer userId);
}