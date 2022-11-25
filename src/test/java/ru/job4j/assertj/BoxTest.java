package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isEqualTo("Sphere");
    }
    @Test
    void isThisUNKNOWN() {
        Box box = new Box(-1, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isEqualTo("Unknown object");
    }
    @Test
    void isThisRealFigure() {
        Box box = new Box(4, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isGreaterThan(0)
                .isEqualTo(4);
    }
    @Test
    void isThisNoRealFigure() {
        Box box = new Box(-4, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isLessThan(0)
                .isEqualTo(-1);
    }
    @Test
    void isThisFigureExist() {
        Box box = new Box(0, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }
    @Test
    void isThisFigureNoExist() {
        Box box = new Box(-1, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }
    @Test
    void isAreaOfSphereTrue() {
        Box box = new Box(0, 1);
        double area = box.getArea();
        assertThat(area).isGreaterThan(12d)
                .isLessThan(13d)
                .isCloseTo(12.5d, withPrecision(0.5d))
                .isCloseTo(12.55d, withPrecision(0.02d));
    }
    @Test
    void isAreaOfCubeTrue() {
        Box box = new Box(8, 1);
        double area = box.getArea();
        assertThat(area).isGreaterThan(5d)
                .isLessThan(7d)
                .isCloseTo(6d, withPrecision(0.001d))
                .isCloseTo(6d, withPrecision(0.0001d));
    }

}