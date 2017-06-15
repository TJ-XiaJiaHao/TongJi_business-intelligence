package com.bi.Controller;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bi.kernelImpl;

import java.util.ArrayList;

@Configuration
public class DefaultViewController {
    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {

        ArrayList<Integer> inputArrayList = new ArrayList<Integer>();
        inputArrayList.add(3);
        inputArrayList.add(5);
        inputArrayList.add(10);
        inputArrayList.add(2);
        inputArrayList.add(82);

        String str = "";

        for(int i = 0; i < inputArrayList.size(); i ++) {
            str += inputArrayList.get(i);
            if(i != inputArrayList.size() - 1) {
                str += "&";
            }
        }

        String inputStr = str;
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

        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName(
                        "forward:/mainpage.html");
            }
        };
    }

}