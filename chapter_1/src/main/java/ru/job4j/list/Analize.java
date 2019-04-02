package ru.job4j.list;

import java.util.List;
import java.util.Objects;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info(0, 0, 0);
        List<User> first = previous;
        List<User> second = current;
        result.changed = changed(first, second);
        result.added = added(first, second) - changed(first, second);
        result.deleted = deleted(first, second) - changed(first, second);

        return result;
    }

    private int added(List<User> previous, List<User> current) {
        int result = 0;
        for (User user : current) {
            if (!previous.contains(user)) {
                result ++;
            }
        }
        return result;
    }

    private int deleted(List<User> previous, List<User> current) {
        int result = 0;
        for (User user : previous) {
            if (!current.contains(user)) {
                result ++;
            }
        }
        return result;
    }

    private int changed(List<User> previous, List<User> current) {
        int result = 0;
        User temp = null;
        for (User user : previous) {
            temp = findById(user.getId(), current);
            if (temp != null ) {
                if (!user.getName().equals(temp.getName())) {
                    result++;
                }
            }
        }
        return result;
    }

    private User findById(int id, List<User> list) {
        User result = null;
        for (User user : list) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        return result;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {

        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }
    }
}
