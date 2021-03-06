package ru.job4j.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenTreeHas1ThenHasNextIsTrue() {
        Tree<Integer> tree = new Tree<>(1);
        Iterator<Integer> it = tree.iterator();
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenIterateThroughTreeThen123456() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> it = tree.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void whenNodesHaveOnly2BranchesThenTreeIsBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        assertThat(tree.isBinary(tree), is(true));
        }

        @Test
    public void whenNodeHave3BranchesThenTreeIsNotBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        assertThat(tree.isBinary(tree), is(false));
    }

    @Test
    public void iteratorTest() {
        Tree<Integer> tree = new Tree<>(5);
        tree.add(5, 1);
        Iterator<Integer> it1 = tree.iterator();
        int[] first = new int[2];
        first[0] = it1.next();
        first[1] = it1.next();
        Iterator<Integer> it2 = tree.iterator();
        int[] second = new int[2];
        second[0] = it2.next();
        second[1] = it2.next();
        assertThat(first, is(second));
    }

}