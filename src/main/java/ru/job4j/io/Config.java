package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            List<String[]> list;
            list = in.lines()
                    .filter(lines -> !lines.contains("#") && !lines.isEmpty())
                    .map(l -> l.split("=", 2))
                    .toList();
            if (list.isEmpty()) {
                throw new NoSuchElementException("файл конфигурации пуст");
            }
            for (String[] el : list) {
                if (el[0].isEmpty() || el[1].isEmpty()) {
                    throw new IllegalArgumentException("некорректное заполнение файла конфигурации");
                }
                values.put(el[0], el[1]);
            }

        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
    }


    public String value(String key) {
        return values.get(key);
    }
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
        return out.toString();
    }
}

