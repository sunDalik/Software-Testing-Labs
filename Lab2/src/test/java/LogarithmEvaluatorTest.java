import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class LogarithmEvaluatorTest {
    public static final double EPS = 1e-6;

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm_test.csv", numLinesToSkip = 1)
    public void testLn(Double x, Double base, Double lnX, Double lnBase, Double logBaseX) {
        Assertions.assertEquals(lnX, LogarithmEvaluator.ln(x, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm_test.csv", numLinesToSkip = 1)
    public void testLog(Double x, Double base, Double lnX, Double lnBase, Double logBaseX) {
        Assertions.assertEquals(logBaseX, LogarithmEvaluator.log(x, base, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/logarithm_test_negative.csv", numLinesToSkip = 1)
    public void testNegative(Double x, Double base, Double lnX, Double lnBase, Double logBaseX, Double eps) {
        Assertions.assertEquals(lnX, LogarithmEvaluator.ln(x, eps), EPS);
        Assertions.assertEquals(logBaseX, LogarithmEvaluator.log(x, base, eps), EPS);
    }
}
