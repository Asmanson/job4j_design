package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNameLength() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is");
    }

    @Test
    void checkNameNotContainsEquel() {
        NameLoad nameLoad = new NameLoad();
        String name = "Атом - какие-то маленькие ребята";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name)
                .hasMessageContaining("=");
    }

    @Test
    void checkNameStartsWithEquel() {
        NameLoad nameLoad = new NameLoad();
        String name = "= Атом какие-то маленькие ребята";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name)
                .hasMessageContaining("key");
    }

    @Test
    void checkNameEndWithEquel() {
        NameLoad nameLoad = new NameLoad();
        String name = "Атом какие-то маленькие ребята =";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(name)
                .hasMessageContaining("contain a value");
    }
}