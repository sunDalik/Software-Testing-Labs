import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

public class TrigonometryEvaluatorTest {
    public static final double EPS = 1e-6;

    @Nested
    static class TrigonometryEvaluatorUnitTest {
        
    }

    @Nested
    static class TrigonometryEvaluatorIntegrationTest {
        public static TrigonometryEvaluator trigonometryEvaluator;

        @BeforeAll
        public static void setUpEvaluator() {
            trigonometryEvaluator = new TrigonometryEvaluator(new SinFunction());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        public void testSin(Double x, Double sin, Double sec, Double csc, Double cot) {
            Assertions.assertEquals(sin, trigonometryEvaluator.sin(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        public void testSec(Double x, Double sin, Double sec, Double csc, Double cot) {
            Assertions.assertEquals(sec, trigonometryEvaluator.sec(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        public void testCsc(Double x, Double sin, Double sec, Double csc, Double cot) {
            Assertions.assertEquals(csc, trigonometryEvaluator.csc(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        public void testCot(Double x, Double sin, Double sec, Double csc, Double cot) {
            Assertions.assertEquals(cot, trigonometryEvaluator.cot(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test_negative.csv", numLinesToSkip = 1)
        public void testNegative(Double x, Double eps, Double sin, Double sec, Double csc, Double cot) {
            Assertions.assertEquals(sin, trigonometryEvaluator.sin(x, eps));
            Assertions.assertEquals(sec, trigonometryEvaluator.sec(x, eps));
            Assertions.assertEquals(csc, trigonometryEvaluator.csc(x, eps));
            Assertions.assertEquals(cot, trigonometryEvaluator.cot(x, eps));
        }
    }
}
