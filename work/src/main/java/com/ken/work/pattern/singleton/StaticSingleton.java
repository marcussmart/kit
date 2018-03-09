package com.ken.work.pattern.singleton;

/**
 * Created by s on 2018/3/9.
 */
public class StaticSingleton {

    private static StaticSingleton instance = new StaticSingleton();

    private StaticSingleton() {
    }

    public static StaticSingleton getInstance() {
        return instance;
    }

}
