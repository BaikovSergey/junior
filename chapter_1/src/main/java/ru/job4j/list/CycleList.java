package ru.job4j.list;

public class CycleList {

    static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    public boolean hasCycle2(Node first) {
        boolean result = false;
        if (first != null) {
            Node back = first;
            Node front = first;
            while (true) {
                back = back.next;
                front = front.next.next;
                if (back == front) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}





