package ru.job4j.io.ioexam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFinder {
    private Arg args;
    private List<Path> list = new ArrayList<>();

    public FileFinder(Arg args) {
        this.args = args;
    }

    private void writeLog(String target) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)))) {
            for (Path line : list) {
                writer.write(line.toString() + System.lineSeparator());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void search() throws IOException {
        if (args.mask()) {
            this.list = findByMask(args.name());
        } else if (args.fullName()) {
            this.list = findByFullName(args.name());
        } else if (args.regEx() != null) {
            this.list = findByRegExp(args.name());
        }
        writeLog(args.output());
    }

    private List<Path> findByMask(String mask) throws IOException {
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.args.directory()))
        ) {
            return filesStream
                    .map(Path::toFile)
                    .filter(
                            file -> file.getName().contains(mask.substring(1))
                    )
                    .map(File::toPath)
                    .collect(Collectors.toList());
        }
    }


    private List<Path> findByFullName(String fullName) throws IOException {
        try (Stream<Path> filesStream = Files.walk(Paths.get(this.args.directory()))
        ) {
            return filesStream
                    .filter(file -> file.getFileName() != null)
                    .filter(file -> file.getFileName().toString().contains(fullName))
                    .collect(Collectors.toList());
        }
    }

    public List<Path> findByRegExp(String regEx) {
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        List<Path> result = new ArrayList<>();
        try (Stream<Path> filesStream = Files.walk(Paths.get(args.directory()))) {
            filesStream
                    .filter(path -> pattern.matcher(path.getFileName().toString()).find())
                    .forEach(result::add);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
