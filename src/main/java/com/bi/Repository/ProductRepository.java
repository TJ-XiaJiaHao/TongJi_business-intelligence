package com.bi.Repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.bi.Model.*;

/**
 * Created by cz on 2017/6/15.
 */
@Mapper
public interface ProductRepository {
    /**
     * 获取
     * @param Id
     * @return
     */
    @Select("select * from amazon_meta  where Id = #{Id}")
    Products getRes(@Param("Id") Integer Id);

    @Select("select * from all_reviews where Id =#{Id}")
    Reviews getRev(@Param("Id") Integer Id);

}
