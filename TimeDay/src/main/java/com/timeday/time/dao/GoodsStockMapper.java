package com.timeday.time.dao;

import com.timeday.time.error.BusinessException;
import com.timeday.time.pojo.GoodsStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface GoodsStockMapper {

    int insertGoodsStock(GoodsStock record);

    GoodsStock selectByItemId(Integer itemId);

    //库存减少
    int decreaseStock(@Param("itemId") Integer itemId, @Param("amount")Integer amount) throws BusinessException;
}