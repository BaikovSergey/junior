package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * Test replace.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findAll().get(0).getName(), is("test2"));
    }

    /**
     * Test replace null.
     */
    @Test
    public void whenReplaceNullThenReturnPreviousName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        tracker.add(previous);
        Item test = new Item("add", "testAdd");
        tracker.replace("123", test);
        assertThat(tracker.findAll().get(0).getName(), is("test1"));
    }
    /**
     * Test delete.
     */
    @Test
    public void whenDeleteOneObjectThenOneLeftInArray() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription");
        tracker.add(first);
        Item second = new Item("test2",  "testDescription2");
        tracker.add(second);
        tracker.delete(first.getId());
        assertThat(tracker.findAll().get(0), is(second));
    }

    /**
     * Test findAll.
     */
    @Test
    public void whenTwoObjectsInArrayWhenArrayLengthIsTwo() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription");
        tracker.add(first);
        Item second = new Item("test2", "testDescription2");
        tracker.add(second);
        assertThat(tracker.findAll().size(), is(2));
    }

    /**
     * Test findByName.
     */
    @Test
    public void whenFindByNameReturnItemWithThatName() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription");
        tracker.add(first);
        Item second = new Item("test2", "testDescription2");
        tracker.add(second);
        assertThat(tracker.findByName("test1").get(0), is(first));
    }

    /**
     * Test findById.
     */
    @Test
    public void whenFindByIdWhenFindObjectWithThisId() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription");
        tracker.add(first);
        assertThat(tracker.findById(first.getId()).getId(), is(first.getId()));
    }
}