package ru.job4j.questions;

import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changes = 0;
        int deletes = 0;
        int addes = 0;
        if (previous.size() < current.size() ) {
            addes = current.size() - previous.size();
        } else if(previous.size() > current.size()) {
            deletes = previous.size() - current.size();
        }
        return new Info(addes, changes, deletes);
    }
}
