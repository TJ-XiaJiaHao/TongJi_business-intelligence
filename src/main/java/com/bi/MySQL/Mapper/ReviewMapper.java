package com.bi.MySQL.Mapper;

import com.bi.MySQL.DO.ReviewDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by xx on 2017/6/17.
 */
@Mapper
public interface ReviewMapper {
    @Select("SELECT * FROM all_reviews WHERE Id=#{ID}")
    ReviewDO getReviewById(@Param("ID") int ID);
}
