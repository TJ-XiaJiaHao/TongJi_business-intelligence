package com.bi.MySQL.Mapper;

import com.bi.MySQL.DO.ProductDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xx on 2017/6/17.
 */
@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM amazon_meta WHERE Id=#{ID}")
    ProductDO getProductById(@Param("ID") int ID);

    @Select("SELECT Id FROM amazon_meta WHERE title=#{value}")
    int[] transferProductByValue(@Param("value") String value);


}
