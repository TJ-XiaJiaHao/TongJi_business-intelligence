package com.bi.Controller;

import com.bi.Domain.BaseResult;
import com.bi.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xx on 2017/6/17.
 */

@CrossOrigin
@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    /**
     * 获取商品评论信息
     * @return
     */
    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public BaseResult<Object> getReview(@RequestParam("productId")int productId){
        return reviewService.getReview(productId);
    }


}
