public class LogarithmEvaluator {
    public static Double log(double x, double base, double eps) {
        if (x <= 0 || base <= 0 || base == 1 || Double.isNaN(x) || Double.isNaN(base) || Double.isNaN(eps) || Double.isInfinite(base) || Double.isInfinite(eps) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        return NaturalLogarithm.ln(x, eps) / NaturalLogarithm.ln(base, eps);
    }
}
