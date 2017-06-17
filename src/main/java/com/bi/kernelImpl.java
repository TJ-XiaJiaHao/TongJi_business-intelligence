package com.bi;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/10.
 */
public class kernelImpl {
    static{
        System.loadLibrary("kernelCpp");
    }

    public native static String main(String input);
    public native static boolean ban(String inputban);
    public native static void init();

    public void main(String[] args) {
//        String s = main("3&5&10&2&82");
        init();
        String s = main("2&8&5&300");
        System.out.println("Java -> C++");
        System.out.println(s);

        boolean flag = ban("2&8&5&300&44");
        System.out.println("Java -> C++: ban");
        System.out.println(flag);

//        ArrayList<Integer> inputArrayList = new ArrayList<Integer>();
//        inputArrayList.add(1);
//        inputArrayList.add(3);
//        inputArrayList.add(5);
//
//        String str = "";
//
//        for(int i = 0; i < inputArrayList.size(); i ++) {
//            str += inputArrayList.get(i);
//            if(i != inputArrayList.size() - 1) {
//                str += "&";
//            }
//        }
//        System.out.println(str);
    }

}
