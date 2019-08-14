package com.timeday.time.dao;

import com.timeday.time.pojo.PromoGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PromoGoodsMapper {
    PromoGoods selectByItemId(Integer itemId);
}