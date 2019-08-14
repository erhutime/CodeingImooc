package com.timeday.time.dao;

import com.timeday.time.pojo.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderInfoMapper {
    int insertSelective(OrderInfo record);
}