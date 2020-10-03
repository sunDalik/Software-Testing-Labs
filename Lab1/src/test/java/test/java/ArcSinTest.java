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
        Assert.assertEquals("Wrong result for x = 0", df.format(Math.asin(0)), df.format(ArcSinFunction.getArcSin(0)));
    }

    @Test
    public void testBorders() {
        Assert.assertEquals("Wrong result for x = 1 (right border)", df.format(Math.asin(1)), df.format(ArcSinFunction.getArcSin(1)));
        Assert.assertEquals("Wrong result for x = -1 (left border)", df.format(Math.asin(-1)), df.format(ArcSinFunction.getArcSin(-1)));
    }

    @Test
    public void testLinear() {
        Assert.assertEquals("Wrong result for x = 0.3 (linear growth)", df.format(Math.asin(0.3)), df.format(ArcSinFunction.getArcSin(0.3)));
        Assert.assertEquals("Wrong result for x = -0.3 (linear growth)", df.format(Math.asin(-0.3)), df.format(ArcSinFunction.getArcSin(-0.3)));
        Assert.assertEquals("Wrong result for x = 0.5 (linear growth)", df.format(Math.asin(0.5)), df.format(ArcSinFunction.getArcSin(0.5)));
        Assert.assertEquals("Wrong result for x = -0.5 (linear growth)", df.format(Math.asin(-0.5)), df.format(ArcSinFunction.getArcSin(-0.5)));
    }

    @Test
    public void testExponential() {
        Assert.assertEquals("Wrong result for x = 0.97 (exponential growth)", df.format(Math.asin(0.97)), df.format(ArcSinFunction.getArcSin(0.97)));
        Assert.assertEquals("Wrong result for x = -0.97 (exponential growth)", df.format(Math.asin(-0.97)), df.format(ArcSinFunction.getArcSin(-0.97)));
    }

    @Test
    public void testOutOfBounds() {
        Assert.assertTrue("Result should be NaN for x = -2", Double.isNaN(ArcSinFunction.getArcSin(-2)));
        Assert.assertTrue("Result should be NaN for x = 2", Double.isNaN(ArcSinFunction.getArcSin(2)));
    }
}
