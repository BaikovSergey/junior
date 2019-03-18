package ru.job4j.generics;

public class UserStore extends AbstractStore<User> {

    private SimpleArray <User> userArray;

    public UserStore(int cells) {
        super(new SimpleArray<>(cells));
    }
}
