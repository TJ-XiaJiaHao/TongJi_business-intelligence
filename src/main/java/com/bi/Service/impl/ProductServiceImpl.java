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

        String[] transArray = inputStr.split("&");

//        int[] trans = new int[transArray.length];
//        for(int i = 0; i < 2; i++) {
//            trans[i] = Integer.valueOf(transArray[i]);
//        }
//
//        for(int i = 2; i < transArray.length; i++) {
//            String value = transArray[i];
//            int temp[] = productMapper.transferProductByValue(value);
//            if(temp != null) {
//                trans[i] = temp[0];
//            }
//        }
//        System.out.println("--------------" +"trans" + trans.length);
//
//        inputStr = "";
//        for(int i = 0; i < trans.length; i++) {
//            inputStr = inputStr + trans[i];
//            if(i != trans.length - 1)
//                inputStr += "&";
//        }
//
//        System.out.println("--------------AFTER" + inputStr);

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

    public boolean banProduct(String banStr) {
        banStr = banStr.replace("%26","&");
        System.out.println("--------------" + banStr);
        boolean b = kernelImpl.ban(banStr);
        return b;
    }

}
