package com.ken.work.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by s on 2018/3/22.
 */
public class ThreadLocalVal {

    final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public ThreadLocalVal() {
    }

    public void isInit() {
        System.out.println("source=" + threadLocal.get());
        threadLocal.set(1);
        System.out.println(Thread.currentThread());
        Integer a = threadLocal.get();
        System.out.println(a);

//        while (true) {
//            Integer a = threadLocal.get();
//            if (a != null && 1 != a) {
//                System.out.println("not equal 1");
//            }
//        }
    }

    public void add() {
        System.out.println("source=" + threadLocal.get());
        threadLocal.set(2);
        System.out.println(Thread.currentThread());
        Integer a = threadLocal.get();
        System.out.println(a);

//        while (true) {
//            Integer a = threadLocal.get();
//            if (a != null && a != 2) {
//                System.out.println("not equal 2");
//            }
//        }
    }


    public static void main(String[] args) {
        ThreadLocalVal threadLocalVal = new ThreadLocalVal();

        Runnable r1 = () -> threadLocalVal.isInit();
        Runnable r2 = () -> threadLocalVal.add();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(r2);
        executor.execute(r1);
        executor.shutdown();
    }
}
