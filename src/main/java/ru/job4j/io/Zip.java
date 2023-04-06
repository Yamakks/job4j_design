package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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
        if (predicate.startsWith(".") && !(predicate.length() > 1)) {
            throw new IllegalArgumentException("File parameter is incorrect. Usage  .* parameter.");
        }
        if ("^[^\\s]+\\.[^\\s.]+$".matches(destination)) {
            throw new IllegalArgumentException("File name is incorrect. Usage  *.* parameter.");
        }
    }
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source :sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
    }

    /**public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) throws IOException {
        System.out.println(args.length);
        if (!(args.length == 3)) {
            throw new IllegalArgumentException("Some args is null.");
        }
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        /**Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
           );*/
           Path path = Paths.get(argsName.get("d"));
           Path condition = Paths.get(argsName.get("e"));
           List<Path> list = Search.search(path, p -> !p.toString().endsWith(String.valueOf(condition)));
           /**List<File> fileList = new ArrayList<>();
           for (Path paths : list) {
               fileList.add(paths.toFile());
           }*/
           Path path1 = Path.of(argsName.get("o"));
           Zip zipproj = new Zip();
           zipproj.packFiles(list, new File(path1.toString()));

    }
}
