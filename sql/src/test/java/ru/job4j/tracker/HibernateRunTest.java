package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HibernateRunTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Test
    public void create() {
        Item item = new Item("Item", "Description");
        HibernateRun.create(item, sf);
        List all = HibernateRun.findAll(sf);
        assertEquals(item, all.get(0));
    }

    @Test
    public void update() {
        Item item = new Item("Item", "Description");
        HibernateRun.create(item, sf);
        item.setName("Another Name");
        item.setDescription("Another Description");
        HibernateRun.update(item, sf);
        List all = HibernateRun.findAll(sf);
        assertEquals(item, all.get(0));
    }

    @Test
    public void delete() {
        Item item = new Item("Item", "Description");
        int id = HibernateRun.create(item, sf).getId();
        HibernateRun.delete(id, sf);
        List all = HibernateRun.findAll(sf);
        assertThat(all.size(), is(0));
    }

    @Test
    public void findAll() {
        Item item = new Item("Item", "Description");
        HibernateRun.create(item, sf);
        List all = HibernateRun.findAll(sf);
        assertEquals(item, all.get(0));
    }

    @Test
    public void findById() {
        Item item = new Item("Item", "Description");
        int id = HibernateRun.create(item, sf).getId();
        Item result = HibernateRun.findById(id, sf);
        assertEquals(item, result);
    }
}