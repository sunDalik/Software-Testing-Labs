public class LogarithmEvaluator {
    public static Double log(double x, double base, double precision) {
        if (x <= 0 || base <= 0 || base == 1 || Double.isNaN(x) || Double.isNaN(base) || Double.isNaN(precision) || Double.isInfinite(base) || Double.isInfinite(precision) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        return NaturalLogarithm.ln(x, precision) / NaturalLogarithm.ln(base, precision);
    }
}
