package ru.job4j.map;

import java.util.Calendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, 1991);
        birthday.set(Calendar.MONTH, Calendar.DECEMBER);
        birthday.set(Calendar.DAY_OF_MONTH, 7);

        User man1 = new User("user", 1, birthday);
        User man2 = new User("user", 1, birthday);

    }
}

