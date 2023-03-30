package ru.job4j.io;

import java.util.*;
import java.util.stream.Collectors;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    private boolean isCorrect(String[] el) {
        boolean rsl = el.length == 2;
        if (!rsl || el[0].isEmpty() || el[1].isEmpty()) {
            throw new IllegalArgumentException("некорректное заполнение файла конфигурации");
        }
        return rsl;
    }

    public String get(String key) {
        /* TODO add the necessary checks. */
        return values.get(key);
    }

    private void parse(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(args));
        List<String[]> buf =
        list.stream()
                .map(s -> s.split("=", 2))
                .filter(this::isCorrect)
                .toList();
        for (String[] b : buf) {
            values.put(b[0].substring(1), b[1]);
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
