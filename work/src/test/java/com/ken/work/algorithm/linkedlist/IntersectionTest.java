package com.ken.work.algorithm.linkedlist;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntersectionTest {

    @Test
    public void testIntersection() throws Exception {

        Node<Integer> first = new Node<>(), second = new Node<>();
        Node<Integer> a1 = new Node<>(1);
        Node<Integer> a2 = new Node<>(3);
        Node<Integer> b1 = new Node<>(2);
        Node<Integer> c = new Node<>(4);
        Node<Integer> d = new Node<>(5);

        add(first, d);
        add(first, c);
        add(first, a2);
        add(first, a1);
        
        add(second, d);
        add(second, c);
        add(second, b1);

        Intersection<Integer> intersection = new Intersection<>();
        Node<Integer> intersectionNode = intersection.intersection(first, second);
        
        System.out.println(intersectionNode);
        if (null != intersectionNode) {
            System.out.println(intersectionNode.value);
        }
    }

    public <T> void add(Node<T> head, Node<T> node) {
        node.next = head.next;
        head.next = node;
    }
}