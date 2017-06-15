package com.bi.Service;

import java.util.List;
import java.util.ArrayList;
import com.bi.Model.*;
import com.bi.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cz on 2017/6/15.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Products getProduct(Integer Id){
        return productRepository.getRes(Id);
    }

    public Reviews getReviews(Integer Id){
        return productRepository.getRev(Id);
    }
}
