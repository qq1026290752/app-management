package com.yulece.app.management.comments.provider;

/**
 * Copyright © 2019 eSunny Info. Tech Ltd. All rights reserved.
 *
 * @author wangyichao@28ph.cn
 * @Title: Lazy
 * @Package com.yulece.app.management.comments.provider
 * @Description:
 * @Date 2019-07-18 15:33
 **/
public class Lazy {

    private static boolean initialized = false;

    static {
        System.out.println("静态匿名" + initialized);
        Thread t = new Thread(() ->{
            initialized = true;
        } );
        t.start();
        try {
            System.out.println("join之前执行" + initialized);
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("main方法中的" + initialized);
    }

    static{

    }
}
