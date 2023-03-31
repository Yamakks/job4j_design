package ru.job4j.io;

import java.util.*;
import java.util.stream.Collectors;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    private boolean argContainsValue(String el) {
        return (!(el.split("=", 2)[1].isEmpty()));
    }
    private boolean argContainsKey(String el) {
        return (!(el.split("=", 2)[0].substring(1).isEmpty()));
    }
    //private boolean containEqualsSign(String el) {
        //return el.contains("=");
    //}
    private boolean startSign(String el) {
        return el.startsWith("-");
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: " + "\'" + key + "\'" + " is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(args));
        List<String[]> buf =
        list.stream()
                .filter(this::argContainsKey)
                .filter(this::argContainsValue)
                //.filter(this::containEqualsSign)
                .filter(this::startSign)
                .map(s -> s.substring(1))
                .map(s -> s.split("=", 2))
                .toList();
        for (String[] b : buf) {
            values.put(b[0], b[1]);
        }
        /* TODO parse args to values. */
    }

    public static ArgsName of(String[] args) {
        /* TODO add the necessary checks. */
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        for (String arg : args) {
            if (!"=".contains(arg)) {
                throw new IllegalArgumentException("Error: This argument "
                        + "\'"
                        + arg
                        + "\'"
                        + " does not contain an equal sign");
            }
            if (!names.argContainsKey(arg)) {
                throw new IllegalArgumentException("Error: This argument "
                        + "\'"
                        + arg
                        + "\'"
                        + " does not contain a key");
            }
            if (!names.argContainsValue(arg)) {
                throw new IllegalArgumentException("Error: This argument "
                        + "\'"
                        + arg
                        + "\'"
                        + " does not contain a value");
            }
            if (!names.startSign(arg)) {
                throw new IllegalArgumentException("Error: This argument "
                        + "\'"
                        + arg
                        + "\'"
                        + " does not start with a '-' character");
            }
                names.parse(args);
            }
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
