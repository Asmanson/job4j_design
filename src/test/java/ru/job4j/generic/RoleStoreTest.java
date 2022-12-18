package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsFaust() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Faust"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Faust");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Faust"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsFaust() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Faust"));
        store.add(new Role("1", "Hamlet"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Faust");
    }

    @Test
    void whenReplaceThenRolenameIsHamlet() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Faust"));
        store.replace("2", new Role("2", "Hamlet"));
        Role result = store.findById("2");
        assertThat(result.getRolename()).isEqualTo("Hamlet");
    }

    @Test
    void whenDeleteThenRolenameIsHamlet() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Hamlet"));
        assertThat(store.delete("2")).isTrue();
    }
}