package com.bi.Service;

import com.bi.Domain.BaseResult;

/**
 * Created by xx on 2017/6/15.
 */

public interface ReviewService {
    BaseResult<Object> getReview(int productId);
}
