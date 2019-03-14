package ru.job4j.generics;

public abstract class AbstractStore<T> implements Store{

    public abstract void add(User model);

    public abstract boolean replace(String id, User model);

    public abstract boolean delete(String id);

    public abstract User findById(String id);

    public abstract int indexOf(String id);

}
