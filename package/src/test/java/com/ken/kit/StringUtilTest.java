package com.ken.kit;

import org.junit.Test;

/**
 * Created by s on 2018/6/27.
 */
public class StringUtilTest  {

    private String a = new String("abc");

    private void change(String a) {
        System.out.println(a);
        System.out.println(a == this.a);
        a = "def";
        System.out.println(a);
        System.out.println(this.a);
    }

    @Test
    public void test() {
        change(a);
    }
}
