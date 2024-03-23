package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class ImportDB {

        private Properties config;
        private String dump;

        public ImportDB(Properties config, String dump) {
            this.config = config;
            this.dump = dump;
        }

    private boolean isCorrect(String[] el) {
        boolean rsl = el.length == 2;
        if (!rsl || el[0].isEmpty() || el[1].isEmpty()) {
            throw new IllegalArgumentException("Некорректное заполнение файла со спаммерами");
        }
        return true;
    }

        public List<User> load() throws IOException {
            List<User> users = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
                List<String[]> list;
                list = reader.lines()
                        .filter(lines -> !lines.isEmpty())
                        .map(l -> l.split(";", 2))
                        .filter(this::isCorrect)
                        .toList();
                if (list.isEmpty()) {
                    throw new NoSuchElementException("Файл со спамерами пуст");
                }
                for (String[] el : list) {
                    users.add(new User(el[0], el[1]));
                }
            }
            return users;
        }

        public void save(List<User> users) throws ClassNotFoundException, SQLException {
            Class.forName(config.getProperty("driver"));
            try (Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("login"),
                    config.getProperty("password")
            )) {
                for (User user : users) {
                    try (PreparedStatement preparedStatement =
                                 connection.prepareStatement("INSERT INTO spammers(name, email) VALUES (?, ?)")) {
                        preparedStatement.setString(1, user.name);
                        preparedStatement.setString(2, user.email);
                        preparedStatement.execute();
                    }
                }
            }
        }

        private static class User {
            String name;
            String email;

            public User(String name, String email) {
                this.name = name;
                this.email = email;
            }
        }


        public static void main(String[] args) throws Exception {
            Properties config = new Properties();
            try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
                config.load(input);
            }
            ImportDB dataBase = new ImportDB(config, "src/main/resources/dump.txt");
            dataBase.save(dataBase.load());
        }
}
