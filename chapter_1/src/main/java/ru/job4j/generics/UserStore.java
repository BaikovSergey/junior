package ru.job4j.generics;

public class UserStore extends AbstractStore {

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
        User temp = findById(id);
        if (temp != null) {
            this.userArray.set(indexOf(id), model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            this.userArray.remove(indexOf(id));
            result = true;
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

    public int indexOf(String id) {
        int result = -1;
        for (int i = 0; i < this.userArray.getIndex(); i++) {
            if (this.userArray.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
