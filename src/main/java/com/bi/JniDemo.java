package com.bi;

/**
 * Created by Administrator on 2017/6/10.
 */
public class JniDemo {
    static{
        System.loadLibrary("0610");
    }
    public native static void set(int i);
    public native static int get();

    public static void main(String[] args) {
        set(100);
        System.out.println(get());
    }
}
