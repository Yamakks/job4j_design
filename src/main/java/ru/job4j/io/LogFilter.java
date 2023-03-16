package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.join;


public class LogFilter {
    public List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            result = in.lines()
                    .map(lines -> lines.split(" "))
                    .filter(s -> s[s.length - 2].equals("404"))
                    .map(s -> join(" ", s))
                    .toList();
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out :: println);

    }
}
