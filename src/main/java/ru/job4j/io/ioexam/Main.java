package ru.job4j.io.ioexam;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Arg keys = new Arg(args);
        FileFinder finder = new FileFinder(keys);
        finder.search();
    }
}
