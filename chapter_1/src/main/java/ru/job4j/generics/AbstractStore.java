package ru.job4j.generics;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray <T> array;

    AbstractStore(SimpleArray<T> array) {
        this.array = array;
    }

    public void add(T model) {
        this.array.add(model);
    }

    public boolean replace(String id, T model) {
        boolean result = false;
        T temp = findById(id);
        if (temp != null) {
            this.array.set(indexOf(id), model);
            result = true;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            this.array.remove(indexOf(id));
            result = true;
        }
        return result;
    }

    public T findById(String id) {
        T result = null;
        for (T user: array
        ) {
            if (id.equals(user.getId())) {
                result = user;
                break;
            }
        }
        return result;
    }

    public int indexOf(String id) {
        int result = -1;
        for (int i = 0; i < this.array.getIndex(); i++) {
            if (this.array.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
