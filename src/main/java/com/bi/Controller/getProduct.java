package com.bi.Controller;

import com.bi.Model.Products;
import com.bi.Model.Reviews;
import com.bi.Service.ProductService;
import com.bi.kernelImpl;
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
    public ArrayList<HashMap<String, Object>> getProduct(@RequestBody String inputStr) {
//        return productService.getProduct(1);


        String s = kernelImpl.main(inputStr);
        System.out.println(s);
        s = s.substring(1, s.length());
        System.out.println("Java -> C++");
        System.out.println(s);

        String[] reArray = s.split("&");
        System.out.println(reArray.length);
        for(int i = 0; i < reArray.length; i++) {
            System.out.println(reArray[i]);
        }

        List<String> Ids = Arrays.asList(reArray);
        ArrayList<HashMap<String, Object>> results = new ArrayList<>();
        for(String Id:Ids){
            Products products = productService.getProduct(Integer.valueOf(Id));
            Reviews reviews = productService.getReviews(Integer.valueOf(Id));
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
