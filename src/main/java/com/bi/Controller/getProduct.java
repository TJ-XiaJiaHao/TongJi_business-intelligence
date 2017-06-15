package com.bi.Controller;

import com.bi.Model.Products;
import com.bi.Model.Reviews;
import com.bi.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import java.util.ArrayList;


/**
 * Created by cz on 2017/6/15.
 */

@CrossOrigin
@RestController
@RequestMapping("/products")
public class getProduct {
    @Autowired
    private ProductService productService;

    /**
     * 获取商品信息
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<HashMap<String, Object>> getProduct(@RequestBody String Info) {
//        return productService.getProduct(1);
        List<Integer> Ids = Arrays.asList(5,12,123,51,7,8,3,7,11,111,232);
        ArrayList<HashMap<String, Object>> results = new ArrayList<>();
        for(Integer Id:Ids){
            Products products = productService.getProduct(Id);
            Reviews reviews = productService.getReviews(Id);
            HashMap<String, Object> result = new HashMap<>();
            result.put("Id", products.getId());
            result.put("ASIN", products.getASIN());
            result.put("Categories", products.getCategories());
            result.put("Reviews", products.getReviews());
            result.put("Salesrank", products.getSalesrank());
            result.put("Title", products.getTitle());
            result.put("Group", products.getGroups());
            result.put("Similar", products.getSimilar());
            if (reviews == null)
                result.put("AllReview", null);
            else
                result.put("AllReview", reviews.getAllReviews());
            results.add(result);
        }
        return results;
    }
}
