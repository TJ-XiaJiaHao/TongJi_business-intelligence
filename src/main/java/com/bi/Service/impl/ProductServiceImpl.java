package com.bi.Service.impl;

import com.bi.Domain.BaseResult;
import com.bi.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bi.kernelImpl;
import com.bi.MySQL.DO.ProductDO;
import com.bi.MySQL.Mapper.ProductMapper;

/**
 * Created by Administrator on 2017/6/17.
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public BaseResult<Object> getProduct(String inputStr) {
        inputStr = inputStr.replace("%26","&");
        System.out.println("--------------" + inputStr);
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

        int[] IDs = new int[reArray.length];
        for(int i = 0; i < reArray.length; i++) {
            IDs[i] = Integer.valueOf(reArray[i]);
        }

        ProductDO[] productDOs = new ProductDO[IDs.length];
        for(int i = 0; i < IDs.length; i++) {
            productDOs[i] = productMapper.getProductById(IDs[i]);
        }
        if (productDOs.length == 0){
            return new BaseResult<>(500, "No things found");
        }
        return new BaseResult<>(productDOs);

    }

}
