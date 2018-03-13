package com.ken.work.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by s on 2018/3/9.
 */
public class CallableAddBarrier implements Callable<Long>{

    private CyclicBarrier barrier;
    private Long from, to;

    public CallableAddBarrier(CyclicBarrier barrier, Long from, Long to) {
        this.barrier = barrier;
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
        System.out.println(from + " " + to + " start");
        Long result = add();
        System.out.println(result);
        barrier.await();
        System.out.println(from + " " + to + " end");
        return result;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {
        CyclicBarrier allDone = new CyclicBarrier(3, () -> System.out.println("finish"));
        
        CallableAddBarrier callable1 = new CallableAddBarrier(allDone, 1l, 10l);
        CallableAddBarrier callable2 = new CallableAddBarrier(allDone, 10l, 20l);
        CallableAddBarrier callable3 = new CallableAddBarrier(allDone, 20l, 30l);


        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Long> a = executor.submit(callable1);
        Future<Long> b = executor.submit(callable2);
        Future<Long> c = executor.submit(callable3);
        executor.shutdown();


        //doSomething()
//        System.out.println("1");
//        allDone.await();

//        System.out.println(c.get());
//        System.out.println(b.get());
//        System.out.println(a.get());




    }

}
