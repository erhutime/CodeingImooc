package com.timeday.time.dao;

import com.timeday.time.pojo.SequenceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SequenceInfoMapper {
    SequenceInfo getSequenceByName(String name);

    int updateByPrimaryKeySelective(SequenceInfo record);
}