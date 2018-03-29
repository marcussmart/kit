package com.ken.work.collection;

import org.junit.Test;

/**
 * Created by s on 2018/3/28.
 */
public class LinkedListReverseTest {

    @Test
    public void testReversePrint() throws Exception {
        LinkedListReverse list = new LinkedListReverse();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        list.print();
        System.out.println();
        list.reversePrint();
        System.out.println();
        list.print();
    }
}