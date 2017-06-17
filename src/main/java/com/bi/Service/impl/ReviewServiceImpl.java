package com.bi.Service.impl;

import com.bi.Domain.BaseResult;
import com.bi.MySQL.DO.ReviewDO;
import com.bi.MySQL.Mapper.ReviewMapper;
import com.bi.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/17.
 */

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public BaseResult<Object> getReview(int productId) {


        ReviewDO reviewDOs = reviewMapper.getReviewById(productId);

        return new BaseResult<>(reviewDOs);

    }

}
