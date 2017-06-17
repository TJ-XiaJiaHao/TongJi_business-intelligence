package com.bi.Service;

import com.bi.Domain.BaseResult;

/**
 * Created by cz on 2017/6/15.
 */

public interface ProductService {
    BaseResult<Object> getProduct(String inputStr);

    boolean banProduct(String banStr);
}



//    @Autowired
//    private ProductRepository productRepository;

//        return productRepository.getRes(Id);


//    BaseResult<Object> getReviews(Integer Id){
//        return productRepository.getRev(Id);
//    }

