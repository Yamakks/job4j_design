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

    private static boolean validateArgs(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException("Some args is null.");
        }
        Path path = Path.of(args[0].substring(3));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Root folder is incorrect. Usage  ROOT_FOLDER.");
        }
        if (!args[1].startsWith(".") && !(args[1].length() > 1)) {
            throw new IllegalArgumentException("File parameter is incorrect. Usage  .* parameter.");
        }
        if ("^[^\\s]+\\.[^\\s.]+$".matches(args[2])) {
            throw new IllegalArgumentException("File name is incorrect. Usage  *.* parameter.");
        }
        return true;
    }
    public void packFiles(List<File> sources, File target) {
        for (File source : sources) {
            packSingleFile(source, target);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
           Zip zip = new Zip();
           zip.packSingleFile(
                   new File("./pom.xml"),
                   new File("./pom.zip")
           );
        if (validateArgs(args)) {
           ArgsName argName = ArgsName.of(args);
           Path path = Paths.get(argName.get("d"));
           Path condition = Paths.get(argName.get("e"));
           List<Path> list = Search.search(path, p -> !p.toFile().getName().equals(condition));
           List<File> fileList = new ArrayList<>();
           for (Path paths : list) {
               fileList.add(paths.toFile());
           }
           Path path1 = Path.of(argName.get("o"));
           //Path destination = Files.createFile(path1);
           Zip zipproj = new Zip();
           zipproj.packFiles(fileList, new File(path1.toString()));
       }
    }
}
