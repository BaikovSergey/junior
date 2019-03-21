package ru.job4j.map;

import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void test() {
        User first = new User("Bob", 1, new GregorianCalendar(1989, 0, 1));
        User second = new User("Bob", 1, new GregorianCalendar(1989, 0, 1));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);
        System.out.println(map.get(first).hashCode());
        System.out.println(map.get(second).hashCode());
    }
}