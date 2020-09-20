package test.java;

import lab1.ArcSinFunction;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ArcSinTest {

    private static DecimalFormat df;

    @BeforeClass
    public static void testSetup() {
        df = new DecimalFormat("#.######");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    @Test
    public void testAt0() {
        testWithValue(0);
    }

    @Test
    public void testBorders() {
        testWithValue(-1);
        testWithValue(1);
    }

    @Test
    public void testLinear() {
        testWithValue(-0.3);
        testWithValue(0.3);
        testWithValue(-0.5);
        testWithValue(0.5);
    }

    @Test
    public void testExponential() {
        testWithValue(-0.97);
        testWithValue(0.97);
    }

    @Test
    public void testOutOfBounds() {
        testAssertNaN(-2);
        testAssertNaN(2);
    }

    private void testAssertNaN(double x) {
        Double result = ArcSinFunction.getArcSin(x);
        Assert.assertTrue("Result should be NaN for x = " + x, Double.isNaN(result));
    }

    private void testWithValue(double x) {
        String expected = df.format(Math.asin(x));
        String actual = df.format(ArcSinFunction.getArcSin(x));
        Assert.assertEquals("Wrong result for x = " + x, expected, actual);
    }
}
