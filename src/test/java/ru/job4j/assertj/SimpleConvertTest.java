package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("name", "surname", "Moscow");
        assertThat(list).hasSize(3)
                .startsWith("name", "surname")
                .endsWith("surname", "Moscow")
                .last()
                .isEqualTo("Moscow");

    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> simpleSet = simpleConvert.toSet("name", "surname", "Moscow", "London", "name");
        assertThat(simpleSet).containsOnly("name", "surname", "Moscow", "London")
                .containsAnyOf("Mexico", "Kaluga", "Moscow")
                .doesNotContain("Mexico");
    }

    @Test
    void toMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> simpleMap = simpleConvert.toMap("Moscow", "London", "Mexico");
        assertThat(simpleMap).hasSize(3)
                .containsKey("London")
                .containsValue(2)
                .doesNotContainKey("Mamba")
                .doesNotContainValue(3)
                .containsEntry("Mexico", 2);
    }
}