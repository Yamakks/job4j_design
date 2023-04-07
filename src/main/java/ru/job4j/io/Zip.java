package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void validateArgs(ArgsName argsName) {
        String dir = argsName.get("d");
        String predicate = argsName.get("e");
        String destination = argsName.get("o");
        Path path = Path.of(dir);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Root folder is incorrect. Usage  ROOT_FOLDER.");
        }
        if (!predicate.startsWith(".") || predicate.length() < 2) {
            throw new IllegalArgumentException("File parameter is incorrect. Usage  .* parameter.");
        }
        if (!destination.endsWith(".zip") || destination.length() < 5) {
            throw new IllegalArgumentException("File name is incorrect. Usage  *.zip parameter.");
        }
    }
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Some args is null.");
        }
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        Path path = Paths.get(argsName.get("d"));
        List<Path> list = Search.search(path, p -> !(p.toString().endsWith(argsName.get("e"))));
        Zip zip = new Zip();
        zip.packFiles(list, new File(argsName.get("o")));

    }
}
