package ru.job4j.exam;

public class BinaryArr {
    static int sumOfOnes(int[] mas) {
        int sum = 0;
        int count = 0;
        for (int el : mas) {
            if (el < 0 || el > 1) {
                sum = -1;
                break;
            }
            count++;
            if (el == 0) {
                count = 0;
            }
            if (count >= sum) {
                sum = count;
            }
        }

        return sum;
    }
}
