package com.ken.work.collection;

/**
 * Created by s on 2018/3/28.
 */
public class LinkedListReverse<T> {

    Node<T> head = new Node<>();

    public void add(T value) {
        add(new Node(value));
    }

    public void add(Node<T> node) {
        node.next = head.next;
        head.next = node;
    }

    public void reversePrint() {
        reverse();
        print();
        reverse();
    }

    public void print() {
        Node<T> current = head.next;
        while(null != current) {
            System.out.println(current.value + "\t" + current);
            current = current.next;
        }
    }


    class Node<T> {
        T value;
        Node<T> next;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        public T value() {
            return value;
        }
    }


    private void reverse() {
        Node first = head.next, next;
        if (null == first) {
            return;
        }
        next = first.next;
        while(null != next) {
            first.next = next.next;
            add(next);
            next = first.next;
        }
    }


}
