package com.ken.work.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by s on 2018/3/27.
 */
public class ListRemove {

    private List<Integer> list = new LinkedList<>();

    void add() {
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(1);
        System.out.println(list);
    }

    void removeWithException() {
        for (Integer i: list) {
            if (i.equals(2)) {
//                list.remove(2);
                list.add(5);
            }
        }
        System.out.println(list);
    }

    void remove() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(2)) {
                list.remove(i);
                System.out.print(i + "\t");
                i--;
            }
            System.out.println(list);
        }
    }

    void removeByIterator() {
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()) {
            Integer current = it.next();
            if (current.equals(2)) {
                it.remove();
            }
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        ListRemove lr = new ListRemove();
        lr.add();
//        lr.removeWithException();
//        lr.remove();
        lr.removeByIterator();
    }
}
