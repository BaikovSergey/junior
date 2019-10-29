package ru.job4j.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template implements ITemplate {

    /**
     * Contains regEx to find in string e.g. ${name}.
     */
    private final Pattern KEYS = Pattern.compile("\\$\\{(.*?)}");

    /**
     * Replaces each substring of this string that matches the given regEx with the given replacement.
     * @param template String with keys e.g. ${name}.
     * @param data Map with pairs key -> value e.g. name -> Sergei.
     * @return String with replacements.
     */
    @Override
    public String generate(String template, HashMap<String, String> data) {
        String result = template;
        Matcher matcher = this.KEYS.matcher(template);
        ArrayList<String> list = findAllMatches(matcher);
        if (listContainsKeys(list, data)) {
            matcher.reset();
            while (matcher.find()) {
                result = result.replaceAll(Pattern.quote(matcher.group()), data.get(matcher.group(1)));
            }
        } else {
            throw new NoSuchKeyException("No such key in table");
        }
        return result;
    }

    /**
     * Find all matches in given string and put them to list.
     * @param matcher matcher.
     * @return list.
     */
    private ArrayList<String> findAllMatches(Matcher matcher) {
        ArrayList<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }

    /**
     * Checks if all matches contains in given map.
     * @param listOfKeys list of keys in string.
     * @param data Map with pairs key -> value e.g. name -> Sergei.
     * @return true if all matches contains in given map.
     */
    private boolean listContainsKeys(ArrayList<String> listOfKeys, HashMap<String, String> data) {
        boolean result = true;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if(!listOfKeys.contains(entry.getKey())) {
                result = false;
                break;
            }
        }
        return result;
    }
}
