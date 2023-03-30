package ru.job4j.io;

import java.util.*;
import java.util.stream.Collectors;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    private boolean inControl(String el) {
        return (!el.isEmpty() && el.charAt(0) == '-');
    }
    private boolean isCorrect(String[] el) {
        return el.length == 2 && !(el[0].isEmpty()) && !(el[1].isEmpty());
    }

    public String get(String key) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Arguments not passed to program");
    }
        return values.get(key);
    }

    private void parse(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(args));
        List<String[]> buf =
        list.stream()
                .filter(this::inControl)
                .map(s -> s.substring(1))
                .map(s -> s.split("=", 2))
                .filter(this::isCorrect)
                .toList();
        for (String[] b : buf) {
            values.put(b[0], b[1]);
        }
        /* TODO parse args to values. */
    }

    public static ArgsName of(String[] args) {
        /* TODO add the necessary checks. */
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
