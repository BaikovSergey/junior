package ru.job4j.tracker.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di", "ru.job4j.tracker");
        context.refresh();
        StartUIdi storeOne = context.getBean(StartUIdi.class);
        storeOne.add("Petr Arsentev");
        StartUIdi storeTwo = context.getBean(StartUIdi.class);
        storeTwo.add("Ivan ivanov");
        storeTwo.print();
    }
}

