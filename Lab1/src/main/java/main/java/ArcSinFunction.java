package main.java;

public class ArcSinFunction {
    public static double precision = 1E-10;

    // power series implementation
    public static Double getArcSin(double x) {
        if (Math.abs(x) > 1) return Double.NaN;
        if (x == 1.0) return Math.PI / 2;
        if (x == -1.0) return -Math.PI / 2;

        double currentTerm = x;
        double result = 0.0;
        int n = 1;

        while (Math.abs(currentTerm) >= precision) {
            result += currentTerm;
            currentTerm = currentTerm * x * x * (2 * n - 1) * (2 * n - 1) / ((2 * n) * (2 * n + 1));
            n++;
        }

        return result;
    }
}
