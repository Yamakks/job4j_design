package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final HashMap<Integer, FileProperty> files = new HashMap<Integer, FileProperty>();
    int i = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            i++;
            files.put(i, new FileProperty((long) file.toFile().length(), file.getFileName().toString()));
            return CONTINUE;
        }
}
