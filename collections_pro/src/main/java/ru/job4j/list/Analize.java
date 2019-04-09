package ru.job4j.list;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info(0, 0, 0);
        List<User> list = new ArrayList<>(previous);
        Map<Integer, User> map = new HashMap<>();
        for (User user : current) {
            map.put(user.id, user);
        }
        for (User user : list) {
            User temp = findById(user.id, map);
            if (temp == null) {
                result.deleted++;

            } else {
                if (!temp.equals(user)) {
                    result.changed++;
                    map.remove(temp.id, temp);
                }
                map.remove(temp.id, temp);
            }
        }
        result.added = map.size();
        return result;
    }

    private User findById(int id, Map<Integer, User> map) {
        User result = null;
        if (map.containsKey(id)) {
            result = map.get(id);
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
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
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
            return "Info{"
                    + "added="
                    + added
                    + ", changed="
                    + changed
                    + ", deleted="
                    + deleted
                    + '}';
        }
    }
}
