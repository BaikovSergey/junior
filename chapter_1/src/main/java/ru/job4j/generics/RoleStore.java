package ru.job4j.generics;

public class RoleStore implements Store<Role> {

    private SimpleArray <Role> roleArray;

    public RoleStore(int cells) {
        this.roleArray = new SimpleArray<Role>(cells);
    }

    @Override
    public void add(Role model) {

    }

    @Override
    public boolean replace(String id, Role model) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Role findById(String id) {
        return null;
    }
}
