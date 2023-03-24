package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        Map<Integer, List<Path>> duplicates = visitor.getFiles();
        for (List<Path> duplicList : duplicates.values()) {
            if (duplicList.size() > 1) {
                System.out.println(duplicList.get(0).getFileName() + ", " + Files.size(duplicList.get(0)) + " bytes");
                for (Path p : duplicList) {
                    System.out.println(p.toAbsolutePath());
                }
            }

        }
    }
}
