package ru.job4j.io;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Maxim");
        assertThat(config.value("surname")).isEqualTo("Yakovlev");
    }

    @Test
    void whenFileWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Maxim");
        assertThat(config.value("web")).isEqualTo("www.maxim.ru");
        assertNull(config.value("# Сайт"));
        assertNull(config.value("# Имена"));
    }

    @Test
    void whenFileIsEmpty() throws NoSuchElementException {
        String path = "./data/empty.properties";
        Config config = new Config(path);
        Throwable thrown = assertThrows(NoSuchElementException.class, config::load);
        assertNotNull(thrown.getMessage());
    }

    @Test
    void whenFileIsIncorrect1() throws IllegalArgumentException {
        String path = "./data/incorrect1.properties";
        Config config = new Config(path);
        Throwable thrown = assertThrows(IllegalArgumentException.class, config::load);
        assertNotNull(thrown.getMessage());
    }

    @Test
    void whenFileIsIncorrect2() throws IllegalArgumentException {
        String path = "./data/incorrect2.properties";
        Config config = new Config(path);
        Throwable thrown = assertThrows(IllegalArgumentException.class, config::load);
        assertNotNull(thrown.getMessage());
    }

    @Test
    void whenFileSomeEquals() {
        String path = "./data/pair_with_some.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("maxim=");
        assertThat(config.value("surname")).isEqualTo("maxim=1");
    }
}