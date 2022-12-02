package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleIsArtist() {
        RoleStore store = new RoleStore();
        store.add(new Role("5", "Artist", 20));
        Role result = store.findById("5");
        assertThat(result.getRole()).isEqualTo("Artist");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("5", "Artist", 20));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver", 18));
        store.add(new Role("1", "Boxer", 19));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Driver");
    }

    @Test
    void whenReplaceThenRoleIsBoxer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver", 18));
        store.replace("1", new Role("2", "Boxer", 19));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Boxer");
    }

    @Test
    void whenNoReplaceROleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver", 18));
        store.replace("2", new Role("2", "Boxer", 19));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Driver");
        assertThat(store.findById("2")).isNull();
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor", 18));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenRoleIsActor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor", 20));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Actor");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor", 25));
        boolean rsl = store.replace("1", new Role("1", "Boxer", 19));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor", 25));
        boolean rsl = store.replace("10", new Role("10", "Boxer", 18));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor", 18));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Actor", 19));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}