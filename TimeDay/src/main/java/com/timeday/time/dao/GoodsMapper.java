package com.timeday.time.dao;

import com.timeday.time.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper {
    int insertGoods(Goods record);

    //通过ID获取单个商品信息
    Goods selectGoodsById(Integer id);

    List<Goods> goodsList();

    int updateGoods(Goods record);

    int increaseSales(@Param("itemId") Integer itemId,@Param("amount") Integer amount);
}