package ru.job4j.map;

import java.util.HashMap;
import java.util.Map;


public class IsArraysEquals <V>{

    public boolean isEquals(char[] first, char[] second) {
        boolean result = false;
        Map<Character, V> firstMap = new HashMap<>();
        Map<Character, V> secondMap = new HashMap<>();
        for (char aFirst : first) {
            firstMap.put(aFirst, null);
        }
        for (char aSecond : second) {
            secondMap.put(aSecond, null);
        }
        if(firstMap.equals(secondMap)) {
            result = true;
        }
        return result;
    }
}
