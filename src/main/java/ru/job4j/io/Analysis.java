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
            boolean flag = true;
            String line;
            while ((line = in.readLine()) != null) {
                if (isOff(line) == flag) {
                    out.write(line, 4, line.length() - 4);
                    out.append(";").append(flag ? "" : System.lineSeparator());
                    flag = !flag;
                }
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
