package com.ken.work.algorithm.linkedlist;

/**
 * 交点
 */
public class Intersection<T> {

    
        
    public Node<T> intersection(Node<T> first, Node<T> second) {
        
        if (null == first || null == second) {
            return null;
        }
        Node firstTail = first, secondTail = second;
        int firstSize = 0, sendSize = 0;
        while (firstTail.next != null) {
            firstTail = firstTail.next;
            firstSize++;
        }
        while (secondTail.next != null) {
            secondTail = secondTail.next;
            sendSize++;
        }
        
        if (firstTail != secondTail) {
            return null;
        }

        Node firstStep = first, secondStep = second;
        if (firstSize > sendSize) {
            int step = firstSize - sendSize;
            while (step-- > 0) {
                firstStep = firstStep.next;
            }
        }  else if (firstSize < sendSize) {
            int step = sendSize - firstSize;
            while(step-- > 0) {
                secondStep = secondStep.next;
            }
        }
        
        while (null != firstStep && null != secondStep) {
            if (firstStep == secondStep) {
                return firstStep;
            }
            firstStep = firstStep.next;
            secondStep = secondStep.next;
        }
        return null;
    }    

}
