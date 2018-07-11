package com.ken.kit;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by s on 2018/6/27.
 */
public class DateTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i=0; i< 10000; i++) {
/*
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    DateUtil.format3(new Date(new Random().nextLong()));
                }
            });
*/
            executorService.execute(new Task());
        }
        executorService.shutdown();

    }

    static class Task implements Runnable {
        @Override
        public void run() {
            DateUtil.format3(new Date(new Random().nextLong()));
        }
    }
}
