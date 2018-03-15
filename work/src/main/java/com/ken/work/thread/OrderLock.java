package com.ken.work.thread;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 每个线程有一个一一对应的condition, 线程执行顺序靠condition保证
 * 由于每个线程阻塞在一一对应的condition上，所以直接唤醒等待该condition的唯一线程1
 * Created by s on 2018/3/6.
 */
public class OrderLock {

    ReentrantLock lock = new ReentrantLock();
    Condition numberCondition = lock.newCondition();
    Condition letterCondition = lock.newCondition();
    int i = 1, j = 0, tag = 1;


    public void printNumber() {

        for (int k = 1; k < 10; k++) {
            lock.lock();
            try {
                while (!(tag == 0)) {
//                    System.out.println("\ttag = " + tag + " number wait");
                    numberCondition.await();
                }

                System.out.println(MessageFormat.format("{0} {1}", i, i + 1));
                i = i + 2;

                tag = 1;
                letterCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public void printLetter() {

        for (int k = 1; k < 10; k++) {
            lock.lock();
            try {
                while (!(tag == 1)) {
//                    System.out.println("\ttag = " + tag + " letter wait");
                    letterCondition.await();
                }

                System.out.println((char) ((j % 26) + 65));
                j = j + 1;

                tag = 0;
                numberCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        OrderLock order = new OrderLock();

        Runnable printNumber = () -> order.printNumber();
        Runnable printLetter = () -> order.printLetter();


        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(printLetter);
        executorService.execute(printNumber);
        executorService.shutdown();
    }

}




