package ru.job4j.io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("path/paths");
        Files.createDirectories(dir);
        Path target = Paths.get("path");
        Path path1 = Path.of("path/paths/path1.txt");
        Files.createFile(path1);
        Path path2 = Path.of("path/path2.txt");
        Files.createFile(path2);
        Files.delete(path2);
        DirectoryStream<Path> paths = Files.newDirectoryStream(target);
        paths.forEach(System.out::println);
    }
}
