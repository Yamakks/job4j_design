package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        Scanner scanner = new Scanner(new File(argsName.get("path")));
        String line = scanner.nextLine();
        List<Integer> integers = checkColumnForDelete(line, argsName.get("filter"), delimiter);
        String rsl = null;
        boolean firstLine = true;
        while (scanner.hasNextLine()) {
            if (firstLine) {
                firstLine = false;
                rsl = generatingString(line, integers, delimiter)
                        + System.lineSeparator();
                continue;
            }
            rsl = rsl.concat(generatingString(scanner.nextLine(), integers, delimiter)
                    + System.lineSeparator());
        }
        printResult(rsl, argsName);
    }

    private static String generatingString(String line, List<Integer> cellForDelete, String delimiter) {
        List<String> tmp = List.of(line.split(delimiter));
        return tmp.stream()
                .filter(s -> cellForDelete.contains(tmp.indexOf(s)))
                .reduce((s1, s2) -> s1 + delimiter + s2).get();
    }

    private static List<Integer> checkColumnForDelete(String firstLine, String columnForDelete, String delimiter) {
        List<String> arr = List.of(firstLine.split(delimiter));
        return Stream.of(columnForDelete.split(","))
                .filter(arr::contains)
                .map(arr::indexOf)
                .toList();
    }

    private static void validateArgs(ArgsName argsName) {
        File f = new File(argsName.get("path"));
        if (!f.exists() || !f.isFile()) {
            throw new IllegalArgumentException("Указанный файл не существует или не является файлом");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Указан не csv-файл");
        }
        if ("\";\"".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Указан разделитель не для CSV-файла");
        }
    }
    private static void printResult(String rsl, ArgsName argsName) {
        if ("stdout".equals(argsName.get("out"))) {
            System.out.println(rsl);
        } else {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(argsName.get("out")))) {
                out.write(rsl.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Неправильное количество аргументов");
        }
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        handle(argsName);
    }
}
