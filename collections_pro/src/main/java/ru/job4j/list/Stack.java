package ru.job4j.list;

public class Stack<T> {

    private SimpleArrayList<T> stack = new SimpleArrayList<>();

    public T poll() {
        return stack.delete();
    }

    public void push(T value) {
        this.stack.add(value);
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
