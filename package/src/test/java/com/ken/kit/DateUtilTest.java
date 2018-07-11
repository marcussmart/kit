package com.ken.kit;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by s on 2018/6/27.
 */
public class DateUtilTest {

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    @Test
    public void testFormat() throws Exception {
        long startTime = System.currentTimeMillis();
        for (int i=0; i < 100000; i++) {
            DateUtil.format(new Date());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("format " + (endTime - startTime) + "ms" );
    }

    @Test
    public void testFormat1() throws Exception {
        long startTime = System.currentTimeMillis();
        for (int i=0; i < 100000; i++) {
            DateUtil.format1(new Date());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("format1 " + (endTime - startTime) + "ms" );
    }

    @Test
    public void testFormat2() throws Exception {
        long startTime = System.currentTimeMillis();
        for (int i=0; i < 100000; i++) {
            DateUtil.format2(new Date());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("format2 " + (endTime - startTime) + "ms" );
    }







}