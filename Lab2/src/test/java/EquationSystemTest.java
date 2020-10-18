import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class EquationSystemTest {
    public static Double EPS = 1e-6;

    @Nested
    class EquationSystemIntegrationTest {
        EquationSystem equationSystem;

        @BeforeEach
        public void setUp() {
            TrigonometryEvaluator trigonometryEvaluator = new TrigonometryEvaluator(new SinFunction());
            LogarithmEvaluator logarithmEvaluator = new LogarithmEvaluator(new NaturalLogarithm());
            equationSystem = new EquationSystem(trigonometryEvaluator, logarithmEvaluator);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/equation_system_test.csv", numLinesToSkip = 1)
        public void testEquationSystem(Double x, Double sin, Double sec, Double csc, Double cot, Double cos, Double
                ln, Double log3, Double log10, Double y) {
            Assertions.assertEquals(y, equationSystem.solve(x, EPS), EPS);
        }
    }
}
