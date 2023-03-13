package ru.job4j.exam;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BinaryArrTest {

    @Test
    void whenNo1() {
        int[] mass = new int[] {0, 0, 0, 0};
        assertThat(BinaryArr.sumOfOnes(mass)).isEqualTo(0);
    }

    @Test
    void when1() {
        int[] mass = new int[] {0, 0, 1, 0};
        assertThat(BinaryArr.sumOfOnes(mass)).isEqualTo(1);
    }

    @Test
    void when3() {
        int[] mass = new int[] {0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0};
        assertThat(BinaryArr.sumOfOnes(mass)).isEqualTo(3);
    }

    @Test
    void whenNoBinary() {
        int[] mass = new int[] {0, 1, 1, 0, 0, 1, 0, 2, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0};
        assertThat(BinaryArr.sumOfOnes(mass)).isEqualTo(-1);
    }

}