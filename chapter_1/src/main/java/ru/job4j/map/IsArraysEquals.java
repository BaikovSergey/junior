package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;


public class IsArraysEquals <V>{

    public boolean isEquals(char[] first, char[] second) {
        boolean result = false;
        Map<Character, V> map = new HashMap<>();
        for (int i = 0; i < first.length; i++) {
            map.put(first[i], null);
            map.put(second[i], null);
        }
        if(map.size() == first.length) {
            result = true;
        }
        return result;
    }
}
