package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sergey Baikov
 * @version $ 1 $
 * @since 07.03.19
 */
public class ArrayIterator implements Iterator<Integer> {

    /**
     * Хранит массив
     */
    private final int[][] values;
    /**
     * Хранит индекс строик массива
     */
    private int rowCell = 0;
    /**
     * Хранит индекс столбца массива
     */
    private int columnCell = 0;

    /**
     * Конструктор
     *
     * @param ints входящий массив
     */
    public ArrayIterator(int[][] ints) {
        this.values = ints;
    }

    /**
     * Метод проверяет наличие следующего элемента в мсассиве
     *
     * @return есть элемент / нет элемента
     */
    @Override
    public boolean hasNext() {
        return rowCell < values.length;
    }

    /**
     * Метод возвращает текущий элемент массива и сдвигает каретку (указатель) на следующий элемент
     *
     * @return элемент массива
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("End of List");
        }
        Integer result = values[rowCell][columnCell];
        columnCell++;
        if (columnCell == values[rowCell].length) {
            columnCell = 0;
            rowCell++;
            }
        return result;
    }
}
