package lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class AssumptionExamples {

    @Test
    void trueAssumption() {
        assumeTrue(1 == 2);
        assertEquals(1, 1);
    }

    @Test
    void assumeThat() {
        assumingThat("test".equals("test"),
                () -> assertTrue(true));
        assertAll(() -> assertTrue(true),
                () -> assertFalse(false),
                () -> assertEquals(1 ,2),
                () -> assertEquals(1 ,3));
    }
}
