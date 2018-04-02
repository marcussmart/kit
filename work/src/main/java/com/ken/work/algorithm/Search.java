package com.ken.work.algorithm;

import java.util.Arrays;
import java.util.List;

/**
 * Created by s on 2018/4/2.
 */
public class Search {

    public int search(List<Integer> list, Integer key) {
        int i = 0, j = list.size() - 1;
        if (j < 0) {
            return -1;
        }
        int m;
        while(i <= j) {

            m = i + (j - i) / 2 ;
            Integer current = list.get(m);
            if (current.equals(key)) {
                return m;
            } else if (current.compareTo(key) > 0) {
                j = m - 1;
            } else if (current.compareTo(key) < 0) {
                i = m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1,4,3,2,5,8,9,10,6,7);
        List<Integer> list = Arrays.asList(1, 2);
        list.sort((o1, o2) -> o1.compareTo(o2));

        Search search = new Search();
        int index = search.search(list, 2);
        System.out.println(index);
    }
}
