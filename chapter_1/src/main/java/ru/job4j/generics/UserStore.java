package ru.job4j.generics;

public class UserStore implements Store<User> {

    private SimpleArray <User> userArray;

    public UserStore(int cells) {
        this.userArray = new SimpleArray<User>(cells);
    }

    @Override
    public void add(User model) {
        this.userArray.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        boolean result = false;
        for (User user: userArray
             ) {
            if (id.equals(user.getId())) {
                 this.userArray.set();
                 result = true;
                 break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (User user: userArray
        ) {
            if (id.equals(user.getId())) {
                this.userArray.remove();
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public User findById(String id) {
        User result = null;
        for (User user: userArray
             ) {
            if (id.equals(user.getId())) {
                result = user;
                break;
            }
        }
        return result;
    }
}
