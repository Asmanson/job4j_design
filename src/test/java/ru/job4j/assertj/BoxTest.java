package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 4);
        assertThat(box.whatsThis()).isEqualTo("Cube")
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("Cu");
    }

    @Test
    void isTetrahedronArea() {
        Box box = new Box(4, 4);
        assertThat(box.getArea()).isNotZero()
                .isPositive()
                .isEqualTo(27.0d, withPrecision(1.0d));
    }

    @Test
    void isThisWho() {
        Box box = new Box(2, 4096);
        assertThat(box.whatsThis()).isEqualTo("Unknown object")
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("object")
                .startsWith("Unknown");
    }

    @Test
    void isWhoArea() {
        Box box = new Box(2, 4096);
        assertThat(box.getArea()).isGreaterThan(-1)
                .isLessThan(1)
                .isEqualTo(0);
    }

    @Test
    void isNotExist() {
        Box box = new Box(2, -12);
        assertThat(box.isExist()).isFalse();
    }
}