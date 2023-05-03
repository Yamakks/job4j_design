package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        var file = File.createTempFile("temp", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(argsName.get("path").getBytes());
        }
    }
    private static boolean validateArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Some args is null.");
        }
        if (!".csv".contains(args[0])) {
            throw new IllegalArgumentException("File is incorrect. Usage  ROOT_FOLDER.");
        }
        if ((args[1].split("=")[1].length() == 0) && !(args[1].length() > 3)) {
            throw new IllegalArgumentException("Delimiter parameter is incorrect.");
        }
        if ((args[2].split("=")[1].length() == 0) && !(args[2].length() > 3)) {
            throw new IllegalArgumentException("Output parameter is incorrect.");
        }
        if ((args[3].split("=")[1].length() == 0) && !(args[2].length() > 3)) {
            throw new IllegalArgumentException("Filter parameter is incorrect.");
        }
        return true;
    }
    public static void main(String[] args) {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
