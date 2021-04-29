package lesson4;

import lesson4.utils.TimingExtention;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(TimingExtention.class)
public class FunctionsTest {
    Functions functions = new Functions();
    private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);

    @BeforeAll
    static void beforeAll() {
        logger.info("Выполнится перед всем тестовым набором");
    }

    @BeforeEach
    void setUp() {
        logger.info("Выполнится перед каждым тестом");
    }

    @Test
    void testGivenEvenNumberWhenCheckIsEvenThenTrue() {
        boolean result = functions.isNumberEven(2);
        Assertions.assertTrue(result);
    }

    @Test
    @RepeatedTest(10)
    void testGivenOddNumberWhenCheckIsEvenThenFalse() {
        boolean result = functions.isNumberEven(3);
        Assertions.assertFalse(result, "Try to check number is odd");
    }

    @Test
    @DisplayName("Проверить функцию isNumberPositive положительным числом")
    void testIsNumberPositive() {
        Assertions.assertTrue(functions.isNumberPositive(1));
    }

    @Test
    void testIsPrimeFunctionWithNegativeNumber() {
        Assertions.assertFalse(functions.isPrime(-1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123321", "abccba"})
    void testIsPalindromePositive(String word) {
        Assertions.assertTrue(functions.isPalindrome(word));
    }

    @ParameterizedTest
    @CsvSource({"123321, true", "123, false", "12321, true"})
    void testIsPalindrome(String word, boolean result) {
        Assertions.assertEquals(functions.isPalindrome(word), result);
    }

    @AfterEach
    void tearDown() {
        logger.debug("Выполнится после каждого теста");
    }

    @AfterAll
    static void afterAll() {
        logger.debug("Выполнится после всех тестовых наборов");
    }
}
