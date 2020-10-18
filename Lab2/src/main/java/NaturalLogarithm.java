public class NaturalLogarithm implements INaturalLogarithm {
    public Double ln(double x, double eps) {
        if (x <= 0 || Double.isNaN(x) || Double.isInfinite(eps) || Double.isNaN(eps)) {
            return Double.NaN;
        }
        if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        double sum = 0;
        double term = 1;
        double n = 1;
        while (Math.abs(term) > eps / 10) {
            sum += term;
            term = 1 / (2 * n + 1) * Math.pow(Math.pow(x - 1, 2) / Math.pow(x + 1, 2), n);
            n++;
        }
        return sum * 2 * (x - 1) / (x + 1);
    }
}
