package ru.job4j.list;

public class SimpleQueue<T> {

    private Stack<T> input = new Stack<>();
    private Stack<T> output = new Stack<>();

    public T poll() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.poll());
            }
        }
        return this.output.poll();
    }

    public void push(T value) {
        this.input.push(value);
    }
}
