package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;


public class IsArraysEquals {

    public boolean isEquals(char[] first, char[] second) {
        boolean result = false;
        Map<Character, Integer> firstMap = new HashMap<>();
        Map<Character, Integer> secondMap = new HashMap<>();
        for (char c : first) {
            if (!firstMap.containsKey(c)) {
               firstMap.put(c, 1);
            } else {
                firstMap.put(c, firstMap.get(c) + 1);
            }
        }
        for (char c : second) {
            if (!secondMap.containsKey(c)) {
                secondMap.put(c, 1);
            } else {
                secondMap.put(c, secondMap.get(c) + 1);
            }
        }
        if (firstMap.equals(secondMap)) {
            result = true;
        }
        return result;
    }
}
