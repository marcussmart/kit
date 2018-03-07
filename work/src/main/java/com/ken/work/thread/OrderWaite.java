package com.ken.work.thread;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 共用一个lock notifyAll时，会有锁竞争
 * Created by s on 2018/3/6.
 */
public class OrderWaite {

    private int i = 1, j = 0, k = 0, tag = 0;
    private int count = 30;
    private final Object lock = new Object();


    public void printNumber() {
        try {
            for (int n = 0; n < count; n++) {
                synchronized (lock) {
                    while (tag != 0) {
//                        System.out.println("\ttag=" + tag + " number wait");
                        lock.wait();
                    }

                    System.out.println(MessageFormat.format("{0}, {1}", i, i + 1));
                    i = i + 2;

                    tag = 1;
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void printLetter() {
        try {
            for (int n = 0; n < count; n++) {
                synchronized (lock) {
                    while (tag != 1) {
//                        System.out.println("\ttag=" + tag + " letter wait");
                        lock.wait();
                    }

//                    System.out.print("char: ");
                    System.out.println((char) ((j % 26) + 65));
                    j = j + 1;

                    tag = 2;
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void printUpperLetter() {
        try {
            for (int n = 0; n < count; n++) {
                synchronized (lock) {
                    while (tag != 2) {
//                        System.out.println("\ttag=" + tag + " upper letter wait");
                        lock.wait();
                    }

//                    System.out.print("upper char: ");
                    System.out.println((char) ((k % 26) + 97));
                    k = k + 1;

                    tag = 0;
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        OrderWaite order = new OrderWaite();
        Runnable printNumber = () -> order.printNumber();
        Runnable printLetter = () -> order.printLetter();
        Runnable printUpperLetter = () -> order.printUpperLetter();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(printNumber);
        executorService.execute(printLetter);
        executorService.execute(printUpperLetter);
        executorService.shutdown();
    }


}

