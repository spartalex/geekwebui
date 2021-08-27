package lesson5.hw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class TriangleAreaTest {

    public static double countArea(int sideA, int sideB, int sideC) throws Exception {
        if (sideA < 0 || sideB < 0 || sideC < 0) throw new Exception();
        double halfP = (sideA + sideB + sideC) / 2;
        double S = (halfP * (halfP - sideA) * (halfP - sideB) * (halfP - sideC));
        return Math.sqrt(S);
    }

    @Test
    @DisplayName("Assert the certain Area")
    void certainArea() throws Exception {
        double result = countArea(2, 2, 2);
        Assertions.assertEquals(1.7320508075688772, result);
    }

    @Test
    @DisplayName("Check positive sides")
    void testPositive() throws Exception {
        assertThat(countArea(2, 2, 2)).isPositive();
    }

    @Test
    @DisplayName("Check negative sides (Fail example)")
    void testNegative() throws Exception {
        assertThat(countArea(-1, -2, -4)).isNegative();
    }

    @Test
    @DisplayName("Check the exception")
    void testIsException() {
        assertThatExceptionOfType(Exception.class).isThrownBy(()
                -> countArea(-1, -1, -1));
    }
}