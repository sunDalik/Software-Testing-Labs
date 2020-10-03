package test.java;

import lab1.ArcSinFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ArcSinTest {

    private static DecimalFormat df;

    @BeforeAll
    public static void testSetup() {
        df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    @Test
    public void testAt0() {
        Assertions.assertEquals(df.format(Math.asin(0)), df.format(ArcSinFunction.getArcSin(0)), "Wrong result for x = 0");
    }

    @Test
    public void testBorders() {
        Assertions.assertEquals(df.format(Math.asin(1)), df.format(ArcSinFunction.getArcSin(1)), "Wrong result for x = 1 (right border)");
        Assertions.assertEquals(df.format(Math.asin(-1)), df.format(ArcSinFunction.getArcSin(-1)), "Wrong result for x = -1 (left border)");
    }

    @Test
    public void testLinear() {
        Assertions.assertEquals(df.format(Math.asin(0.3)), df.format(ArcSinFunction.getArcSin(0.3)), "Wrong result for x = 0.3 (linear growth)");
        Assertions.assertEquals(df.format(Math.asin(-0.3)), df.format(ArcSinFunction.getArcSin(-0.3)), "Wrong result for x = -0.3 (linear growth)");
        Assertions.assertEquals(df.format(Math.asin(0.5)), df.format(ArcSinFunction.getArcSin(0.5)), "Wrong result for x = 0.5 (linear growth)");
        Assertions.assertEquals(df.format(Math.asin(-0.5)), df.format(ArcSinFunction.getArcSin(-0.5)), "Wrong result for x = -0.5 (linear growth)");
    }

    @Test
    public void testExponential() {
        Assertions.assertEquals(df.format(Math.asin(0.97)), df.format(ArcSinFunction.getArcSin(0.97)), "Wrong result for x = 0.97 (exponential growth)");
        Assertions.assertEquals(df.format(Math.asin(-0.97)), df.format(ArcSinFunction.getArcSin(-0.97)), "Wrong result for x = -0.97 (exponential growth)");
    }

    @Test
    public void testOutOfBounds() {
        Assertions.assertTrue(Double.isNaN(ArcSinFunction.getArcSin(-2)), "Result should be NaN for x = -2");
        Assertions.assertTrue(Double.isNaN(ArcSinFunction.getArcSin(2)), "Result should be NaN for x = 2");
    }
}
