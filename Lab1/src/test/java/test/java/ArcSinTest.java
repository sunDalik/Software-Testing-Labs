package test.java;


import main.java.ArcSinFunction;
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
    public void testCurve() {
        testWithValue(-0.97);
        testWithValue(0.97);
    }

    @Test
    public void testOutOfBounds() {
        testAssertNull(-2);
        testAssertNull(2);
    }

    private void testAssertNull(double value) {
        Double result = ArcSinFunction.getArcSin(value);
        Assert.assertTrue("Result should be NaN for x = " + value, Double.isNaN(result));
    }

    private void testWithValue(double value) {
        String expected = df.format(Math.asin(value));
        String actual = df.format(ArcSinFunction.getArcSin(value));
        Assert.assertEquals("Wrong result for x = " + value, expected, actual);
    }
}
