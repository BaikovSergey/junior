package ru.job4j.iterator;

import java.util.Iterator;

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
    private int indexI = 0;
    /**
     * Хранит индекс столбца массива
     */
    private int indexJ = 0;

    /**
     * Конструктор
     * @param ints входящий массив
     */
    public ArrayIterator(int[][] ints) {
        this.values = ints;
    }

    /**
     * Метод проверяет наличие следующего элемента в мсассиве
     * @return есть элемент / нет элемента
     */
    @Override
    public boolean hasNext() {
        return indexI < values.length;
    }

    /**
     * Метод возвращает текущий элемент массива и сдвигает каретку (указатель) на следующий элемент
     * @return элемент массива
     */
    @Override
    public Integer next() {
        Integer result = values[indexI][indexJ];
        if (hasNext()) {
            indexJ++;
            if (indexJ == values[indexI].length) {
                indexJ = 0;
                indexI++;
            }
        }
        return result;
    }
}
