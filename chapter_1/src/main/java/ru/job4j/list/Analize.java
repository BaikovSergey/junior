package ru.job4j.list;

import java.util.List;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info(0, 0, 0);
        int deference = previous.size() - current.size();
        if (deference == 0) {
            int counter = 0;
            for (int i = 0; i < previous.size(); i++) {
                if (!previous.get(i).equals(current.get(i))) {
                    counter++;
                }
            }
            result.changed = counter;
        } else if (deference < 0){
            result.added = Math.abs(deference);
        } else {
            result.deleted = deference;
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
