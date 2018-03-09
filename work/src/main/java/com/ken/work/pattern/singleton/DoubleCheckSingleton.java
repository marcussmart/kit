package com.ken.work.pattern.singleton;

/**
 * Created by s on 2018/3/9.
 */
public class DoubleCheckSingleton {

    private volatile Helper instance = null;

    public Helper getHelper() {
        Helper local = instance;
        if (null == local) {
            synchronized (this) {
                local = instance;
                if (null == local) {
                    instance = local = new Helper();
                }
            }
        }
        return local;
    }

}
