package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    private boolean isOff(String s) {
        return (s.contains("500") || s.contains("400"));
    }
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            boolean flag = false;
            String line;
            List<String> buf = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                if (flag != isOff(line)) {
                    flag = isOff(line);
                        buf.add(line.split(" ", 2)[1]);
                }
            }
            for (int i = 0, j = 1; j < buf.size(); i = i + 2, j = j + 2) {
                out.write(String.format("%s%s%s%n", buf.get(i), ";", buf.get(j)));
            }
            } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
