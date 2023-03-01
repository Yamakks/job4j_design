package ru.job4j.questions;

import java.util.Objects;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changes = 0;
        int deletes = 0;
        int adds = 0;
        for (User prev : previous) {
            if (!current.contains(prev)) {
                deletes++;
            }
        }
        for (User currents : current) {
            if (!previous.contains(currents)) {
                adds++;
            }
        }
        for (User prev : previous) {
            for (User currents : current) {
                if (prev.getId() == currents.getId() && !Objects.equals(prev.getName(), currents.getName())) {
                    changes++;
                    adds--;
                    deletes--;
                }
            }
        }
        return new Info(adds, changes, deletes);
    }
}
