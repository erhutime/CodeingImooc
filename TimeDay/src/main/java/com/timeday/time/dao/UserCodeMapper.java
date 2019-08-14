package com.timeday.time.dao;

import com.timeday.time.pojo.UserCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserCodeMapper {
    int insertUserCode(UserCode record);

    UserCode selectCodeByPhone(String phone );

    int updateUserCode(UserCode record);
}