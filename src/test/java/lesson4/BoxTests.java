package lesson4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

public class BoxTests {
    Box box;

    @BeforeAll
    static void beforeAll() {

    }

    @Nested
    @DisplayName("when new")
    class WhenEmpty {

        @BeforeEach
        void createBox() {
            box = new Box();
        }

        @Test
        void checkExceptionWhenShuffleBox() {
            assertThatNullPointerException().isThrownBy(() -> box.shuffleBalls());
        }

        @Test
        void removeBallTest() {
            assertThatExceptionOfType(BoxIsEmptyException.class).isThrownBy(() -> box.deleteBall());
        }

        @Nested
        class WhenWithBalls {
            @BeforeEach
            void addBall() {
                box.addBall();
            }

            @Test
            void testBallAddition() {
                int ballsCount = box.getBallsCounter();
                box.addBall();
                assertThat(box.getBallsCounter()).isEqualTo(ballsCount + 1);
            }
        }
    }
}
