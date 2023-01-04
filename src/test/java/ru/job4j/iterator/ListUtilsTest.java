package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenDoubleAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 1, 5);
        assertThat(input).hasSize(4).containsSequence(1, 5, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterLast() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        assertThat(input).hasSize(4).containsSequence(1, 3, 2, 4);
    }
    @Test
    void whenDeleteIfBiggerOrEquals4() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 2, 5);
        ListUtils.addAfter(input, 2, 6);
        ListUtils.removeIf(input, c -> c >= 4);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
    }

    @Test
    void whenDeleteIfLess4() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 2, 5);
        ListUtils.addAfter(input, 2, 6);
        ListUtils.removeIf(input, c -> c < 4);
        assertThat(input).hasSize(3).containsSequence(6, 5, 4);
    }
    @Test
    void whenDeleteIfEquals4() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.addAfter(input, 4, 6);
        ListUtils.removeIf(input, c -> c == 4);
        assertThat(input).hasSize(5).containsSequence(1, 3, 2, 5, 6);
    }

    @Test
    void whenReplaceIfBiggerOrEquals4() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.addAfter(input, 4, 6);
        ListUtils.replaceIf(input, c -> c >= 4, 7);
        assertThat(input).hasSize(6).containsSequence(1, 3, 2, 7, 7, 7);
    }

    @Test
    void whenReplaceIfLess4() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.addAfter(input, 4, 6);
        ListUtils.replaceIf(input, c -> c < 4, 99);
        assertThat(input).hasSize(6).containsSequence(99, 99, 99, 4, 5, 6);
    }
    @Test
    void whenReplaceIfEquals4() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.addAfter(input, 4, 6);
        ListUtils.replaceIf(input, c -> c == 4, 10);
        assertThat(input).hasSize(6).containsSequence(1, 3, 2, 10, 5, 6);
    }

    @Test
    void whenReplaceAnotherList() {
        List<Integer> anotherList = new ArrayList<>(Arrays.asList(1, 4, 5));
        ListUtils.addAfter(input, 1, 4);
        ListUtils.addAfter(input, 2, 5);
        ListUtils.addAfter(input, 3, 6);
        ListUtils.addAfter(input, 4, 7);
        ListUtils.removeAll(input, anotherList);
        assertThat(input).hasSize(3).containsSequence(3, 6, 7);
    }
}