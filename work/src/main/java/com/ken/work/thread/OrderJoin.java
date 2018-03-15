package com.ken.work.thread;

/**
 * Created by s on 2018/3/6.
 */
public class OrderJoin {

    static class OrderThread extends Thread {

        String name;
        Thread before;

        public OrderThread(String name, Thread before) {
            this.name = name;
            this.before = before;
        }

        @Override
        public void run() {
            try {

                if (null != before) {
                    before.join();
                }
                System.out.println(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {

/*
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
*/

        OrderThread orderThread1 = new OrderThread("one", Thread.currentThread());
        OrderThread orderThread2 = new OrderThread("two",orderThread1);
        OrderThread orderThread3 = new OrderThread("three", orderThread2);

        orderThread2.start();
        orderThread3.start();
        orderThread1.start();

        Thread.currentThread().sleep(1000);
        System.out.println("main");

    }




}



