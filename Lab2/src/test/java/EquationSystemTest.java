import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

public class EquationSystemTest {
    public static Double EPS = 1e-6;

    public ILogarithmEvaluator getLogarithmEvaluatorStub(Double x, Double ln, Double log3, Double log10) {
        ILogarithmEvaluator logarithmStub = Mockito.mock(ILogarithmEvaluator.class);
        Mockito.when(logarithmStub.ln(Mockito.eq(x), Mockito.anyDouble())).thenReturn(ln);
        Mockito.when(logarithmStub.log(Mockito.eq(x), Mockito.eq(3.0), Mockito.anyDouble())).thenReturn(log3);
        Mockito.when(logarithmStub.log(Mockito.eq(x), Mockito.eq(10.0), Mockito.anyDouble())).thenReturn(log10);
        return logarithmStub;
    }

    public ITrigonometryEvaluator getTrigonometryEvaluatorStub(Double x, Double sin, Double sec, Double csc, Double cot, Double cos) {
        ITrigonometryEvaluator trigonometryStub = Mockito.mock(ITrigonometryEvaluator.class);
        Mockito.when(trigonometryStub.sin(Mockito.eq(x), Mockito.anyDouble())).thenReturn(sin);
        Mockito.when(trigonometryStub.sec(Mockito.eq(x), Mockito.anyDouble())).thenReturn(sec);
        Mockito.when(trigonometryStub.csc(Mockito.eq(x), Mockito.anyDouble())).thenReturn(csc);
        Mockito.when(trigonometryStub.cot(Mockito.eq(x), Mockito.anyDouble())).thenReturn(cot);
        Mockito.when(trigonometryStub.cos(Mockito.eq(x), Mockito.anyDouble())).thenReturn(cos);
        return trigonometryStub;
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/equation_system_test.csv", numLinesToSkip = 1)
    public void testEquationSystemStub(Double x, Double sin, Double sec, Double csc, Double cot, Double cos, Double
            ln, Double log3, Double log10, Double y) {
        EquationSystem equationSystem = new EquationSystem(getTrigonometryEvaluatorStub(x, sin, sec, csc, cot, cos), getLogarithmEvaluatorStub(x, ln, log3, log10));
        Assertions.assertEquals(y, equationSystem.solve(x, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/equation_system_test.csv", numLinesToSkip = 1)
    public void testEquationSystemTrigStub(Double x, Double sin, Double sec, Double csc, Double cot, Double cos, Double
            ln, Double log3, Double log10, Double y) {
        EquationSystem equationSystem = new EquationSystem(getTrigonometryEvaluatorStub(x, sin, sec, csc, cot, cos), new LogarithmEvaluator(new NaturalLogarithm()));
        Assertions.assertEquals(y, equationSystem.solve(x, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/equation_system_test.csv", numLinesToSkip = 1)
    public void testEquationSystemLogStub(Double x, Double sin, Double sec, Double csc, Double cot, Double cos, Double
            ln, Double log3, Double log10, Double y) {
        EquationSystem equationSystem = new EquationSystem(new TrigonometryEvaluator(new SinFunction()), getLogarithmEvaluatorStub(x, ln, log3, log10));
        Assertions.assertEquals(y, equationSystem.solve(x, EPS), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/equation_system_test.csv", numLinesToSkip = 1)
    public void testEquationSystem(Double x, Double sin, Double sec, Double csc, Double cot, Double cos, Double
            ln, Double log3, Double log10, Double y) {
        EquationSystem equationSystem = new EquationSystem(new TrigonometryEvaluator(new SinFunction()), new LogarithmEvaluator(new NaturalLogarithm()));
        Assertions.assertEquals(y, equationSystem.solve(x, EPS), EPS);
    }
}
