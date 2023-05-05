package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<List<String>> data = new ArrayList<>();
        List<List<String>> filteredList = new ArrayList<>();
        String source = argsName.get("path");
        String target = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");
        List<Integer> indexes;
        int index = 0;
        int size = 0;
        try (Scanner scanner = new Scanner(Paths.get(source).toFile());
             PrintWriter out = "stdout".equals(target)
                     ? new PrintWriter(System.out)
                     : new PrintWriter(new FileWriter(target))) {
            String[] head = scanner.nextLine().split(delimiter);
            size = head.length;
            for (String s : head) {
                List<String> list = new ArrayList<>();
                list.add(s);
                data.add(list);
            }
            scanner.useDelimiter("(;)|(\n)|(\r\n)");
            while (scanner.hasNext()) {
                String value = scanner.next();
                data.get(index).add(value);
                index++;
                if (index == size) {
                    index = 0;
                }
            }
            for (String f : filters) {
                for (List<String> list : data) {
                    if (f.equals(list.get(0))) {
                        filteredList.add(list);
                        break;
                    }
                }
            }
            for (int i = 0; i < data.get(0).size(); i++) {
                StringJoiner rsl = new StringJoiner(delimiter);
                for (int j = 0; j < filters.length - 1; j++) {
                    rsl.add(filteredList.get(j).get(i));
                }
                out.println(rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Неправильное количество аргументов");
        }
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        handle(argsName);
    }
}
