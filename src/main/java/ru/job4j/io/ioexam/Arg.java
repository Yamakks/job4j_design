package ru.job4j.io.ioexam;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Arg {

    private static void validate(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("Root doesn't exist");
        }
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("File isn't directory");
        }
        Pattern pattern = Pattern.compile("^.+\\..+$");
        if (!pattern.matcher(argsName.get("n")).find()) {
            throw new IllegalArgumentException("Incorrect -n parameter: Searching file must have extension");
        }
        if (!pattern.matcher(argsName.get("o")).find()) {
            throw new IllegalArgumentException("Incorrect -o parameter: Result file must have extension");
        }
    }

    public static void find(ArgsName argsName) {
        validate(argsName);
        String source = argsName.get("d");
        String fileName = argsName.get("n");
        String findParameter = argsName.get("t");
        String result = argsName.get("o");
        Predicate<Path> condition = null;
        if ("name".equals(findParameter)) {
            condition = p -> p.toString().contains(fileName);
        } else if ("mask".equals(findParameter)) {
            Pattern pattern = Pattern.compile(maskToRegex(fileName));
            condition = p -> pattern.matcher(p.getFileName().toString()).find();
        } else if ("regex".equals(findParameter)) {
            Pattern pattern = Pattern.compile(fileName);
            condition = p -> pattern.matcher(p.getFileName().toString()).find();
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(result))) {
            Search.search(Path.of(source), condition).forEach(out :: println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String maskToRegex(String mask) {
        mask = replace(mask, '.', "\\.");
        mask = replace(mask, '*', ".+");
        mask = replace(mask, '?', ".{1}");
        StringBuilder sb = new StringBuilder();
        sb.append("^");
        sb.append(mask);
        sb.append("$");
        return sb.toString();
    }

    private static String replace(String s, char ch, String reg) {
        int foundIndex = s.indexOf(ch);
        StringBuilder str = new StringBuilder();
        StringBuilder buf = new StringBuilder(s);
        while (foundIndex != -1) {
            for (int i = 0; i < foundIndex; i++) {
                str.append(buf.charAt(i));
            }
            str.append(reg);
            StringBuilder sb = new StringBuilder();
            for (int i = foundIndex + 1; i < buf.length(); i++) {
                sb.append(buf.charAt(i));
            }
            buf = sb;
            foundIndex = buf.toString().indexOf(ch);
        }
        str.append(buf);
        return str.toString();
    }

}
