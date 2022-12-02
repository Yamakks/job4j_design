package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
            storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.put(id, model);
        }
        return storage.containsKey(id);
    }

    @Override
    public boolean delete(String id) {
        T object = findById(id);
        boolean rsl = object != null;
        if (rsl) {
            storage.remove(id);
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        return storage.getOrDefault(id, null);
    }

}
