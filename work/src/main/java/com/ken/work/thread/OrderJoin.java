package com.ken.work.thread;

/**
 * Created by s on 2018/3/6.
 */
public class OrderJoin {




    public static void main(String[] args) throws InterruptedException {

        Runnable threadOne = () ->  System.out.println("Thread one");
        Runnable threadTwo = () ->  System.out.println("Thread two");
        Runnable threadThree = () ->  System.out.println("Thread three");

        Thread one = new Thread(threadOne);
        one.start();
        one.join();

        Thread two = new Thread(threadTwo);
        two.start();
        two.join();

        Thread three = new Thread(threadThree);
        three.start();
        three.join();
    }

}



