public class SinFunction {
    public static Double sin(double x, double eps) {
        if (Double.isInfinite(x) || Double.isNaN(x) || Double.isInfinite(eps) || Double.isNaN(eps)) {
            return Double.NaN;
        }
        double sum = 0.0;
        double term = x;
        int n = 1;
        while (Math.abs(term) > eps / 10) {
            sum += term;
            term *= -Math.pow(x, 2) / ((2 * n) * (2 * n + 1));
            n++;
        }
        return sum;
    }
}
