package com.ken.work.algorithm.linkedlist;

public class Node<T> {

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
