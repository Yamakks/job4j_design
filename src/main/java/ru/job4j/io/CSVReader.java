package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        var file = File.createTempFile("temp", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(argsName.get("path").getBytes());
    }
    public static void main(String[] args) {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
