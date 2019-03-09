package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * @author Sergey Baikov
 * @version $ 1 $
 * @since 09.03.19
 */
public class EvenIteratorTest {

    /**
     * Create a test array.
     */
    private Iterator<Integer> it;

    /**
     * Initialise test array.
     */
    @Before
    public void setUp(){
        it = new EvenIterator(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    /**
     * Test hasNext() and next().
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially () {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Test hasNext() and next().
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder () {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }
    /**
     * Test hasNext().
     */
    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers(){
        it = new EvenIterator(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test hasNext() and next().
     */
    @Test
    public void allNumbersAreEven(){
        it = new EvenIterator(new int[]{2,4,6,8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
    }
}