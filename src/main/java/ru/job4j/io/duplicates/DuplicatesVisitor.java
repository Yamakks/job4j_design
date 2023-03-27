package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLOutput;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.util.Objects.hash;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty el = new FileProperty(Files.size(file), file.toFile().getName());
        List<Path> someFiles = files.computeIfAbsent(el, k -> new ArrayList<>());
        someFiles.add(file);
        files.putIfAbsent(el, someFiles);
        return CONTINUE;
    }

    public void printDuplicates() throws IOException {
        for (List<Path> duplicList : files.values()) {
            if (duplicList.size() > 1) {
                System.out.println(duplicList.get(0).getFileName() + ", " + Files.size(duplicList.get(0)) + " bytes");
                for (Path p : duplicList) {
                    System.out.println(p.toAbsolutePath());
                }
            }
        }
    }
}
