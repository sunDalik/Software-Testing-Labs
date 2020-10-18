import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;

public class LogarithmEvaluatorTest {
    public static final double EPS = 1e-6;

    @Nested
    class LogarithmEvaluatorUnitTest {
        INaturalLogarithm mock;
        LogarithmEvaluator logarithmEvaluator;

        @BeforeEach
        public void setupLogarithmEvaluator() {
            mock = Mockito.mock(NaturalLogarithm.class);
            logarithmEvaluator = new LogarithmEvaluator(mock);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/logarithm_test.csv", numLinesToSkip = 1)
        public void testLn(Double x, Double base, Double lnX, Double lnBase, Double logBaseX) {
            Mockito.when(mock.ln(Mockito.eq(x), Mockito.anyDouble())).thenReturn(lnX);

            Assertions.assertEquals(lnX, logarithmEvaluator.ln(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/logarithm_test.csv", numLinesToSkip = 1)
        public void testLog(Double x, Double base, Double lnX, Double lnBase, Double logBaseX) {
            Mockito.when(mock.ln(Mockito.eq(x), Mockito.anyDouble())).thenReturn(lnX);
            Mockito.when(mock.ln(Mockito.eq(base), Mockito.anyDouble())).thenReturn(lnBase);

            Assertions.assertEquals(logBaseX, logarithmEvaluator.log(x, base, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/logarithm_test_negative.csv", numLinesToSkip = 1)
        public void testNegative(Double x, Double base, Double lnX, Double lnBase, Double logBaseX, Double eps) {
            Mockito.when(mock.ln(Mockito.eq(x), Mockito.doubleThat(e -> e.isInfinite() || e.isNaN()))).thenReturn(Double.NaN);
            Mockito.when(mock.ln(Mockito.eq(x), Mockito.doubleThat(e -> !e.isInfinite() && !e.isNaN()))).thenReturn(lnX);
            Mockito.when(mock.ln(Mockito.eq(base), Mockito.doubleThat(e -> e.isInfinite() || e.isNaN()))).thenReturn(Double.NaN);
            Mockito.when(mock.ln(Mockito.eq(base), Mockito.doubleThat(e -> !e.isInfinite() && !e.isNaN()))).thenReturn(lnBase);

            Assertions.assertEquals(lnX, logarithmEvaluator.ln(x, eps), EPS);
            Assertions.assertEquals(logBaseX, logarithmEvaluator.log(x, base, eps), EPS);
        }
    }

    @Nested
    class LogarithmEvaluatorIntegrationTest {
        LogarithmEvaluator logarithmEvaluator;

        @BeforeEach
        public void setupLogarithmEvaluator() {
            logarithmEvaluator = new LogarithmEvaluator(new NaturalLogarithm());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/logarithm_test.csv", numLinesToSkip = 1)
        public void testLn(Double x, Double base, Double lnX, Double lnBase, Double logBaseX) {
            Assertions.assertEquals(lnX, logarithmEvaluator.ln(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/logarithm_test.csv", numLinesToSkip = 1)
        public void testLog(Double x, Double base, Double lnX, Double lnBase, Double logBaseX) {
            Assertions.assertEquals(logBaseX, logarithmEvaluator.log(x, base, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/logarithm_test_negative.csv", numLinesToSkip = 1)
        public void testNegative(Double x, Double base, Double lnX, Double lnBase, Double logBaseX, Double eps) {
            Assertions.assertEquals(lnX, logarithmEvaluator.ln(x, eps), EPS);
            Assertions.assertEquals(logBaseX, logarithmEvaluator.log(x, base, eps), EPS);
        }
    }
}
