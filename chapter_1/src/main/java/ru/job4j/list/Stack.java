package ru.job4j.list;

public class Stack<T> {

    private SimpleArrayList<T> stack = new SimpleArrayList<>();

    public T poll() {
        T result = this.stack.get(0);
        this.stack.delete();
        return result;
    }

    public void push(T value) {
        this.stack.add(value);
    }
}
