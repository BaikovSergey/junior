package ru.job4j.template;

import java.util.HashMap;

public interface ITemplate {

    String generate(String template, HashMap<String, String> data);
}
