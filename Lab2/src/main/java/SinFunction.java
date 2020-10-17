public class SinFunction {
    public static Double getSin(double x, double precision) {
        if (Double.isInfinite(x) || Double.isNaN(x) || Double.isInfinite(precision) || Double.isNaN(precision)) {
            return Double.NaN;
        }
        double sum = 0.0;
        double term = x;
        int n = 1;
        while (Math.abs(term) > precision) {
            sum += term;
            term *= -Math.pow(x, 2) / ((2 * n) * (2 * n + 1));
            n++;
        }
        return sum;
    }
}
