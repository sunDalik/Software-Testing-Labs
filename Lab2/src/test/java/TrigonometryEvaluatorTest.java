import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class TrigonometryEvaluatorTest {
    public static final double EPS = 1e-6;

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
    public void testSin(Double x, Double sin, Double sec, Double csc, Double cot) {
        Assertions.assertEquals(sin, TrigonometryEvaluator.sin(x, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
    public void testSec(Double x, Double sin, Double sec, Double csc, Double cot) {
        Assertions.assertEquals(sec, TrigonometryEvaluator.sec(x, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
    public void testCsc(Double x, Double sin, Double sec, Double csc, Double cot) {
        Assertions.assertEquals(csc, TrigonometryEvaluator.csc(x, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
    public void testCot(Double x, Double sin, Double sec, Double csc, Double cot) {
        Assertions.assertEquals(cot, TrigonometryEvaluator.cot(x, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/trigonometry_test_negative.csv", numLinesToSkip = 1)
    public void testNegative(Double x, Double eps, Double sin, Double sec, Double csc, Double cot) {
        Assertions.assertEquals(sin, TrigonometryEvaluator.sin(x, eps));
        Assertions.assertEquals(sec, TrigonometryEvaluator.sec(x, eps));
        Assertions.assertEquals(csc, TrigonometryEvaluator.csc(x, eps));
        Assertions.assertEquals(cot, TrigonometryEvaluator.cot(x, eps));
    }
}
