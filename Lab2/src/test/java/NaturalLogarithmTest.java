import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NaturalLogarithmTest {
    @ParameterizedTest
    @CsvSource({"1, 0",
            "2, 0.69314718056",
            "2.71828182846, 1",
            "5, 1.60943791243",
            "0.7, -0.35667494393",
            "0.2, -1.60943791243",
            "0.05, -2.99573227355",
            "Infinity, Infinity"})
    public void testLn(double x, double ln) {
        double precision = 1e-6;
        Assertions.assertEquals(ln, NaturalLogarithm.ln(x, precision), precision);
    }

    @ParameterizedTest
    @CsvSource({"0, 1e-6,NaN",
            "-5, 1e-6,NaN",
            "-Infinity, 1e-6,NaN",
            "2, NaN, NaN",
            "3, Infinity, NaN",
            "5, -Infinity, NaN"})
    public void testLnNegative(double x, double precision, double ln) {
        Assertions.assertEquals(ln, NaturalLogarithm.ln(x, precision));
    }
}
