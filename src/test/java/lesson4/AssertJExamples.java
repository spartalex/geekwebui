package lesson4;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExamples {
    Functions functions = new Functions();
    @Test
    void testEvenNumber() {
        assertThat(functions.isNumberEven(3)).isFalse();
    }

    @Test
    void testCollection() {
        List<String> stringList = new ArrayList<>(Arrays.asList("twst", "tttt", "wwwww"));
        assertThat(1).isGreaterThan(0).isLessThan(2);
        assertThat(stringList).containsAnyOf("twst1", "rere");
    }
}
