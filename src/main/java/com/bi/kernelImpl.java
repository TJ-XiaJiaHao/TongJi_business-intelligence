package com.bi;

/**
 * Created by Administrator on 2017/6/10.
 */
public class kernelImpl {
    static{
        System.loadLibrary("kernelCpp");
    }
    public native static String main(String input);

    public static void main(String[] args) {
        String s = main("1&3&5");
        System.out.println(s);
    }
}
