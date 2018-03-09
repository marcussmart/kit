package com.ken.work.thread;

import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by s on 2018/3/9.
 */
public class CallableAdd implements Callable<Long>{

    private CountDownLatch latch;
    private Long from, to;

    public CallableAdd(CountDownLatch latch, Long from, Long to) {
        this.latch = latch;
        this.from = from;
        this.to = to;
    }

    public Long add() {
        Long result = 0l;
        if (null != from && null != to && from < to) {
            for (long i = from; i < to; i++ ) {
                result += i;
            }
        }
        return result;
    }


    @Override
    public Long call() throws Exception {
        Long result = add();
        latch.countDown();
        return result;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch allDone = new CountDownLatch(3);
        
        CallableAdd callable1 = new CallableAdd(allDone, 1l, 1000000000l);
        CallableAdd callable2 = new CallableAdd(allDone, 1000000000l, 2000000000l);
        CallableAdd callable3 = new CallableAdd(allDone, 2000000000l, 3000000000l);


        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Long> a = executor.submit(callable1);
        Future<Long> c = executor.submit(callable2);
        Future<Long> b = executor.submit(callable3);
        executor.shutdown();


        //doSomething()
        System.out.println("1");
        allDone.await();

        System.out.println(c.get());
        System.out.println(b.get());
        System.out.println(a.get());

/*
        Boolean aTag, bTag, cTag;
        aTag = bTag = cTag = false;

        while (!aTag || !bTag || !cTag) {
            boolean aTmp = a.isDone();
            boolean bTmp = b.isDone();
            boolean cTmp = c.isDone();

            if (aTmp && !aTag) {
                System.out.println("a done ");
                aTag = aTmp;
            }

            if (bTmp && !bTag) {
                System.out.println("b done ");
                bTag = bTmp;
            }

            if (cTmp && !cTag) {
                System.out.println("c done ");
                cTag = cTmp;
            }
        }
*/



    }

}
