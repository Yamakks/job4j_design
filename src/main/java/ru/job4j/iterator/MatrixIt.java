package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row > data.length) {
            if (data[row][column] == 0) {
                column++;
            }
            if (column == data[row].length) {
                row++;
                column = 0;
            }
        }
        return (column < data[row].length && data[row][column] > 0);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length) {
            column = 0;
            row++;
        }
        return data[row][column++];
    }
}
