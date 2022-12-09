package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        List<String> list = new SimpleConvert().toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
        assertThat(list).isNotNull().allSatisfy(e -> {
            assertThat(e).containsAnyOf("t", "r", "e");
        });
    }

    @Test
    void checkSet() {
        Set<String> set = new SimpleConvert().toSet(
                "first", "second", "three", "four", "five", "first", "second");
        assertThat(set).hasSize(5)
                .contains("second")
                .containsAnyOf("zero", "second", "six");
        assertThat(set).filteredOnAssertions(e -> assertThat(e.length()).isLessThan(5))
                .hasSize(2)
                .first().isEqualTo("four");
    }

    @Test
    void checkMap() {
        Map<String, Integer> map = new SimpleConvert().toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "three", "second")
                .containsValues(3, 4, 2)
                .doesNotContainValue(-1)
                .containsEntry("four", 3);
    }
}

