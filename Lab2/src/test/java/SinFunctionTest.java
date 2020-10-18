import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SinFunctionTest {


    @ParameterizedTest
    @CsvSource({"6.28318530718, 0",
            "3.14159265359, 0",
            "1.57079632679, 1",
            "0.78539816339, 0.70710678118",
            "0.52359877559, 0.5",
            "0, 0",
            "-0.78539816339, -0.70710678118",
            "-0.52359877559, -0.5",
            "-1.57079632679, -1",
            "-3.14159265359, 0",
            "-6.28318530718, 0"})
    public void testSin(Double x, Double sin) {
        double eps = 1e-6;
        Assertions.assertEquals(sin, new SinFunction().sin(x, eps), eps);
    }

    @ParameterizedTest
    @CsvSource({"NaN, 1e-6, NaN",
            "Infinity, 1e-6, NaN",
            "-Infinity, 1e-6, NaN",
            "0.52359877559, NaN, NaN",
            "0.78539816339, Infinity, NaN",
            "1.57079632679, -Infinity, NaN"})
    public void testSinNegative(Double x, Double eps, Double sin) {
        Assertions.assertEquals(sin, new SinFunction().sin(x, eps));
    }
}