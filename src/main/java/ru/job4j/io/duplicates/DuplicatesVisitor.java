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

    private final Map<Integer, List<Path>> files = new HashMap<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (Files.isRegularFile(file)) {
                FileProperty el = new FileProperty(Files.size(file), file.toFile().getName());
                int hc = el.hashCode();
                List<Path> someFiles = files.getOrDefault(hc, new ArrayList<>());
                someFiles.add(file);
                files.put(hc, someFiles);
            }

            return CONTINUE;
        }

    public Map<Integer, List<Path>> getFiles() {
        return files;
    }
}
