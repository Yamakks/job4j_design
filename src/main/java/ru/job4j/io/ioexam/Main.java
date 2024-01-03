package ru.job4j.io.ioexam;

import ru.job4j.io.ArgsName;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Some args is null.");
        }
        ArgsName argsOf = ArgsName.of(args);
        Arg.find(argsOf);
    }
}
