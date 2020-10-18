import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TrigonometryEvaluatorTest {
    public static final double EPS = 1e-6;

    @Nested
    class TrigonometryEvaluatorUnitTest {
        ISinFunction mock;
        TrigonometryEvaluator trigonometryEvaluator;

        @BeforeEach
        void setUpMock() {
            mock = Mockito.mock(ISinFunction.class);
            trigonometryEvaluator = new TrigonometryEvaluator(mock);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testSin(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            when(mock.sin(eq(x), Mockito.anyDouble())).thenReturn(sin);

            Assertions.assertEquals(sin, trigonometryEvaluator.sin(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testSec(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            when(mock.sin(eq(x + Math.PI / 2), Mockito.anyDouble())).thenReturn(cos);

            Assertions.assertEquals(sec, trigonometryEvaluator.sec(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testCsc(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            when(mock.sin(eq(x), Mockito.anyDouble())).thenReturn(sin);

            Assertions.assertEquals(csc, trigonometryEvaluator.csc(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testCot(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            when(mock.sin(eq(x), Mockito.anyDouble())).thenReturn(sin);
            when(mock.sin(eq(x + Math.PI / 2), Mockito.anyDouble())).thenReturn(cos);

            Assertions.assertEquals(cot, trigonometryEvaluator.cot(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testCos(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            when(mock.sin(eq(x + Math.PI / 2), Mockito.anyDouble())).thenReturn(cos);

            Assertions.assertEquals(cos, trigonometryEvaluator.cos(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test_negative.csv", numLinesToSkip = 1)
        void testNegative(Double x, Double eps, Double sin, Double sec, Double csc, Double cot, Double cos) {
            when(mock.sin(eq(x), Mockito.doubleThat(e -> e.isInfinite() || e.isNaN()))).thenReturn(Double.NaN);
            when(mock.sin(eq(x), Mockito.doubleThat(e -> !e.isInfinite() && !e.isNaN()))).thenReturn(sin);
            when(mock.sin(eq(x + Math.PI / 2), Mockito.doubleThat(e -> e.isInfinite() || e.isNaN()))).thenReturn(Double.NaN);
            when(mock.sin(eq(x + Math.PI / 2), Mockito.doubleThat(e -> !e.isInfinite() && !e.isNaN()))).thenReturn(cos);

            Assertions.assertEquals(sin, trigonometryEvaluator.sin(x, eps));
            Assertions.assertEquals(sec, trigonometryEvaluator.sec(x, eps));
            Assertions.assertEquals(csc, trigonometryEvaluator.csc(x, eps));
            Assertions.assertEquals(cot, trigonometryEvaluator.cot(x, eps));
            Assertions.assertEquals(cos, trigonometryEvaluator.cos(x, eps));
        }
    }

    @Nested
    class TrigonometryEvaluatorIntegrationTest {
        TrigonometryEvaluator trigonometryEvaluator;

        @BeforeEach
        void setUpEvaluator() {
            trigonometryEvaluator = new TrigonometryEvaluator(new SinFunction());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testSin(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            Assertions.assertEquals(sin, trigonometryEvaluator.sin(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testSec(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            Assertions.assertEquals(sec, trigonometryEvaluator.sec(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testCsc(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            Assertions.assertEquals(csc, trigonometryEvaluator.csc(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testCot(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            Assertions.assertEquals(cot, trigonometryEvaluator.cot(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test.csv", numLinesToSkip = 1)
        void testCos(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
            Assertions.assertEquals(cos, trigonometryEvaluator.cos(x, EPS), EPS);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/trigonometry_test_negative.csv", numLinesToSkip = 1)
        void testNegative(Double x, Double eps, Double sin, Double sec, Double csc, Double cot, Double cos) {
            Assertions.assertEquals(sin, trigonometryEvaluator.sin(x, eps));
            Assertions.assertEquals(sec, trigonometryEvaluator.sec(x, eps));
            Assertions.assertEquals(csc, trigonometryEvaluator.csc(x, eps));
            Assertions.assertEquals(cot, trigonometryEvaluator.cot(x, eps));
            Assertions.assertEquals(cos, trigonometryEvaluator.cos(x, eps));
        }
    }
}
