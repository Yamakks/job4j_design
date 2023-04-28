package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Date time = new Date();
        List<String> answers = readPhrases();
        boolean stopBit = false;
        List<String> log = new ArrayList<>();
        log.add(time.toString());
        Scanner sc = new Scanner(System.in);
        log.add("Начните общение с ботом, напишите боту");
        System.out.println(log.get(1));
        String question = "";
        while (!OUT.equals(question)) {
            question = sc.nextLine();
            log.add(question);
            if (STOP.equals(question)) {
                stopBit = true;
            }
            if (CONTINUE.equals(question)) {
                stopBit = false;
            }
            if (!stopBit && !OUT.equals(question) && !CONTINUE.equals(question)) {
                String randomAnsw = answers.get(generateRandomInt(answers.size()));
                log.add(String.format("бот: %s", randomAnsw));
                System.out.println(log.get(log.size() - 1));
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(answers::add);
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            for (String string : log) {
                out.println(string);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }
    }
    private static int generateRandomInt(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/chatLog.log", "data/answers.txt");
        cc.run();
    }
}
