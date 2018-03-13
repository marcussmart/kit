package com.ken.work;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by s on 2018/3/12.
 */
public class Mock {

    public int hash(Object key) {
        int h;
        System.out.println(Integer.toHexString(key.hashCode()));
        System.out.println(Integer.toHexString(key.hashCode() >>> 16 ));
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public void remove() {
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);
//        list.remove("b");
        Iterator it = list.iterator();
        if (it.hasNext()) {
            Object a = it.next();
            System.out.println(a);
            it.remove();
        }

        System.out.println(list);
    }

    public static void main(String[] args) {
        Mock mock = new Mock();
        int hash = mock.hash(new Object());
        System.out.println(Integer.toHexString(hash));


        mock.remove();
    }
}
