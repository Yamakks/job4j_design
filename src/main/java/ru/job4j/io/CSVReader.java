package ru.job4j.io;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
    }
    public static void main(String[] args) {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
