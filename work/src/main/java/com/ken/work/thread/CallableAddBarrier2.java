package com.ken.work.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by s on 2018/3/9.
 */
public class CallableAddBarrier2 implements Callable<Long>{

    private CyclicBarrier barrier;
    private ConcurrentHashMap<String, Long> map;
    private Long from, to;

    public CallableAddBarrier2(CyclicBarrier barrier, ConcurrentHashMap<String, Long> map, Long from, Long to) {
        this.barrier = barrier;
        this.map = map;
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
        System.out.println(from + " " + to + " result: " + result);
        map.put(from + "-" + to, result);

        barrier.await();
        return result;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        CyclicBarrier allDone = new CyclicBarrier(3, () -> {
            Long total = 0l;
            for (Long part: map.values()) {
                total += part;
            }
            System.out.println(total);
        });

        CallableAddBarrier2 callable1 = new CallableAddBarrier2(allDone, map, 1l, 1000000000l);
        CallableAddBarrier2 callable2 = new CallableAddBarrier2(allDone, map, 1000000000l, 2000000000l);
        CallableAddBarrier2 callable3 = new CallableAddBarrier2(allDone, map, 2000000000l, 3000000000l);


        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(callable1);
        executor.submit(callable2);
        executor.submit(callable3);
        executor.shutdown();


        //doSomething()
        System.out.println("do something ");

    }

}
