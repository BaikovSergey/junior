package ru.job4j.generics;

public class RoleStore extends AbstractStore<Role> {

    private SimpleArray<Role> roleArray;

    public RoleStore(int cells) {
        super(new SimpleArray<>(cells));
    }
}
