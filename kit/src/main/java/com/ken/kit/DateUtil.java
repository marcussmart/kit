package com.ken.kit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by s on 2018/6/27.
 */
public final class DateUtil {

    private static DateFormat format1 = new SimpleDateFormat();
    private static DateFormat format2 = new SimpleDateFormat();
    private static DateFormat format3 = new SimpleDateFormat("yyyyMMdd,HHmmss");
    private static ReentrantLock lock = new ReentrantLock();

    public static String format (Date date) {
        Objects.requireNonNull(date);
        return new SimpleDateFormat().format(date);
    }

    public static synchronized String format1 (Date date) {
        Objects.requireNonNull(date);
        return format1.format(date);
    }

    public static String format2 (Date date) {
        lock.lock();
        try {
            Objects.requireNonNull(date);
            return format2.format(date);
        } finally {
            lock.unlock();
        }
    }

    public static String format3 (Date date) {
        Objects.requireNonNull(date);
        return format3.format(date);
    }


}
