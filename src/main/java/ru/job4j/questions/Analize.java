package ru.job4j.questions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changes = 0;
        int deletes = 0;
        int adds = 0;
        Map<Integer, String> mapPrevious = new HashMap<>();
        Map<Integer, String> mapCurrent = new HashMap<>();
        for (User currents : current) {
            mapCurrent.put(currents.getId(), currents.getName());
        }
        for (User prev : previous) {
            mapPrevious.put(prev.getId(), prev.getName());
        }
        for (Map.Entry<Integer, String> curr : mapCurrent.entrySet()) {
            if (!mapPrevious.containsKey(curr.getKey())) {
                adds++;
            }
        }
        for (Map.Entry<Integer, String> prev : mapPrevious.entrySet()) {
            if (mapCurrent.containsKey(prev.getKey()) && !mapCurrent.containsValue(prev.getValue())) {
                changes++;
                continue;
            }
            if (!mapCurrent.containsKey(prev.getKey())) {
                deletes++;
            }
        }
        return new Info(adds, changes, deletes);
    }
}
