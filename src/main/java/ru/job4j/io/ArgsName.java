package ru.job4j.io;

import java.util.*;
import java.util.stream.Collectors;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    private boolean argContainsValue(String el) {
        if (el.split("=", 2)[1].isEmpty()) {
            throw new IllegalArgumentException("Error: This argument "
                    + "'"
                    + el
                    + "'"
                    + " does not contain a value");
        }
        return true;
    }
    private boolean argContainsKey(String el) {
        if ((el.split("=", 2)[0].substring(1).isEmpty())) {
            throw new IllegalArgumentException("Error: This argument "
                    + "'"
                    + el
                    + "'"
                    + " does not contain a key");
        }
        return true;
    }
    private boolean containEqualsSign(String el) {
        if (!(el.contains("="))) {
            throw new IllegalArgumentException("Error: This argument "
                    + "'"
                    + el
                    + "'"
                    + " does not contain an equal sign");
        }
        return true;
    }
    private boolean startSign(String el) {
        if (!el.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument "
                    + "'"
                    + el
                    + "'"
                    + " does not start with a '-' character");
        }
        return true;
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: " + "'" + key + "'" + " is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(args));
        List<String[]> buf =
        list.stream()
                .map(s -> s.substring(1).split("=", 2))
                .toList();
        for (String[] b : buf) {
            values.put(b[0], b[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        int truth = 0;
        for (String arg : args) {
            if (names.containEqualsSign(arg)
                    && names.startSign(arg)
                    && names.argContainsValue(arg)
                    && names.argContainsKey(arg)) {
                truth++;
            }
            if (truth == args.length) {
                names.parse(args);
            }
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
