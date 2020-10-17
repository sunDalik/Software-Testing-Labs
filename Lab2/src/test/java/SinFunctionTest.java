import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SinFunctionTest {
    @ParameterizedTest
    @CsvSource({"3.14159265359, 0",
            "1.57079632679, 1",
            "0.78539816339, 0.70710678118",
            "0.52359877559, 0.5",
            "0, 0",
            "-0.78539816339, -0.70710678118",
            "-0.52359877559, -0.5",
            "-1.57079632679, -1",
            "-3.14159265359, 0"})
    public void testSin(double x, double sin) {
        double precision = 1e-6;
        Assertions.assertEquals(sin, SinFunction.getSin(x, precision), precision);
    }

    @ParameterizedTest
    @CsvSource({"NaN, 1e-6, NaN",
            "Infinity, 1e-6, NaN",
            "-Infinity, 1e-6, NaN",
            "0.52359877559, NaN, NaN",
            "0.78539816339, Infinity, NaN",
            "1.57079632679, -Infinity, NaN"})
    public void testSinNegative(double x, double precision, double sin) {
        Assertions.assertEquals(sin, SinFunction.getSin(x, precision));
    }
}
